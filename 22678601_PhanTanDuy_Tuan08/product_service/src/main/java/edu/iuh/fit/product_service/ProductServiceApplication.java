package edu.iuh.fit.product_service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    // --- 1. MODEL ---
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Product implements Serializable {
        private int id;
        private String name;
        private double price;
        private String description;
        private String imageUrl;
    }

    // --- 2. SERVICE (Phải có @Service và phải là STATIC) ---
    @Service
    public static class ProductService {
        @Autowired
        private StringRedisTemplate redisTemplate;

        @Autowired
        private ObjectMapper objectMapper;

        private static final String KEY_PREFIX = "product:";

        public List<Product> getAllProducts() {
            Set<String> keys = redisTemplate.keys(KEY_PREFIX + "*");
            if (keys == null) return List.of();
            return keys.stream()
                    .map(key -> {
                        try {
                            return objectMapper.readValue(redisTemplate.opsForValue().get(key), Product.class);
                        } catch (Exception e) { return null; }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }

        public Product getProductById(int id) {
            try {
                String json = redisTemplate.opsForValue().get(KEY_PREFIX + id);
                return json != null ? objectMapper.readValue(json, Product.class) : null;
            } catch (Exception e) { return null; }
        }

        public void saveProduct(Product p) throws JsonProcessingException {
            redisTemplate.opsForValue().set(KEY_PREFIX + p.getId(), objectMapper.writeValueAsString(p));
        }
    }

    // --- 3. CONTROLLER (Phải có @RestController và phải là STATIC) ---
    @RestController
    @RequestMapping("/api/products")
    @CrossOrigin(origins = "*")
    public static class ProductController {

        @Autowired
        private ProductService productService; // Spring sẽ tìm bean ProductService ở trên

        @GetMapping
        public List<Product> getAll() {
            return productService.getAllProducts();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Product> getById(@PathVariable int id) {
            Product p = productService.getProductById(id);
            return p != null ? ResponseEntity.ok(p) : ResponseEntity.notFound().build();
        }
    }

    // --- 4. DATA SEEDER ---
    @Bean
    CommandLineRunner initData(ProductService service, StringRedisTemplate redis) {
        return args -> {
            System.out.println(">>> Bơm dữ liệu Flash Sale vào Redis...");
            service.saveProduct(new Product(1, "Laptop Acer Nitro", 20000000, "Siêu rẻ", "acer.jpg"));
            service.saveProduct(new Product(2, "Chuột Logi", 500000, "Siêu nhạy", "logi.jpg"));

            // Seed luôn tồn kho cho máy Host (Inventory)
            redis.opsForValue().set("stock:1", "50");
            redis.opsForValue().set("stock:2", "100");
            System.out.println(">>> Done!");
        };
    }
}