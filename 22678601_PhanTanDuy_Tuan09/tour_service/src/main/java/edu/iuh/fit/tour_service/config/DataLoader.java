package edu.iuh.fit.tour_service.config;

import edu.iuh.fit.tour_service.entity.Tour;
import edu.iuh.fit.tour_service.repository.TourRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(TourRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Tour("Tour Đà Lạt 3N2Đ", "Khám phá thành phố ngàn hoa", new BigDecimal("3500000"), 20));
                repository.save(new Tour("Tour Phú Quốc 4N3Đ", "Nghỉ dưỡng biển xanh cát trắng", new BigDecimal("5500000"), 15));
                repository.save(new Tour("Tour Sapa 2N1Đ", "Săn mây Fansipan", new BigDecimal("2500000"), 30));
            }
        };
    }
}
