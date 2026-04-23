package edu.iuh.fit.product_service.services;

import edu.iuh.fit.product_service.models.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final RedisTemplate<String, Object> redisTemplate;

    public List<Product> getAllProducts() {
        Set<String> keys = redisTemplate.keys("product:*");
        if (keys == null || keys.isEmpty()) {
            return new ArrayList<>();
        }
        
        List<Object> objects = redisTemplate.opsForValue().multiGet(keys);
        List<Product> products = new ArrayList<>();
        if (objects != null) {
            for (Object obj : objects) {
                if (obj instanceof Product) {
                    products.add((Product) obj);
                }
            }
        }
        return products;
    }

    public Product getProductById(long id) {
        return (Product) redisTemplate.opsForValue().get("product:" + id);
    }
}
