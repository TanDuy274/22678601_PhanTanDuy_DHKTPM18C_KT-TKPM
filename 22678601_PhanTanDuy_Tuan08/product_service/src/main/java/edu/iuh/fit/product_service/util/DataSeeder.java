package edu.iuh.fit.product_service.util;

import edu.iuh.fit.product_service.models.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner {

    private final RedisTemplate<String, Object> redisTemplate;
    private final org.springframework.data.redis.core.StringRedisTemplate stringRedisTemplate;

    @Override
    public void run(String... args) throws Exception {
        log.info("Starting data seeding for Redis...");

        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "MacBook Pro M3", 40000000, "Flash sale 12h đêm", "macbook.png"));
        products.add(new Product(2, "iPhone 15 Pro", 30000000, "Siêu phẩm Apple 2023", "iphone15.png"));
        products.add(new Product(3, "iPad Pro M2", 20000000, "Màn hình Liquid Retina XDR", "ipad.png"));
        products.add(new Product(4, "AirPods Pro 2", 5000000, "Chống ồn chủ động vượt trội", "airpods.png"));
        products.add(new Product(5, "Apple Watch Ultra 2", 22000000, "Dành cho người yêu thể thao", "watch.png"));

        for (Product product : products) {
            String productKey = "product:" + product.getId();
            String stockKey = "stock:" + product.getId();

            // Seed Product data (JSON)
            redisTemplate.opsForValue().set(productKey, product);
            log.info("Seeded product: {}", productKey);

            // Seed Stock data (Integer as String for DECR operation)
            stringRedisTemplate.opsForValue().set(stockKey, "100");
            log.info("Seeded stock: {} with value 100", stockKey);
        }

        log.info("Data seeding completed.");
    }
}
