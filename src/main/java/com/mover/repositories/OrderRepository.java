package com.mover.repositories;

import com.mover.entities.orderrelated.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.user.userId = :userId")
    List<Order> findByUserId(@Param("userId") Long userId);

    //sorts order from db by city and status
    @Query("SELECT o FROM Order o WHERE " +
            "LOWER(o.pickupLocation.address) LIKE LOWER(CONCAT('%', :city, '%')) " +
            "AND o.status = :status " +
            "ORDER BY o.createdAt DESC")
    List<Order> findOrdersByPickupCityAndStatus(@Param("city") String city, @Param("status") String status);

}
