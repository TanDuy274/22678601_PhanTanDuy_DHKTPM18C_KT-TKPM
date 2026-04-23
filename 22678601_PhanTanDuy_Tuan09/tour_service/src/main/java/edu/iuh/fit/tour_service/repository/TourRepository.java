package edu.iuh.fit.tour_service.repository;

import edu.iuh.fit.tour_service.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
}
