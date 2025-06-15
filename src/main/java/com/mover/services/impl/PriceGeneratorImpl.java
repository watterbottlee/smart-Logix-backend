package com.mover.services.impl;

import com.mover.payloads.apirequests.OrderRequest;
import com.mover.services.PriceGenerator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PriceGeneratorImpl implements PriceGenerator {

    @Override
    public BigDecimal generatePrice(OrderRequest orderRequest) {
        // 1. Get coordinates as BigDecimal and convert to double
        double lat1 = orderRequest.getPickupLocation().getLatitude().doubleValue();
        double lon1 = orderRequest.getPickupLocation().getLongitude().doubleValue();
        double lat2 = orderRequest.getDropLocation().getLatitude().doubleValue();
        double lon2 = orderRequest.getDropLocation().getLongitude().doubleValue();

        double distanceKm = haversine(lat1, lon1, lat2, lon2);

        // 2. Calculate volume (in cubic meters)
        double length = orderRequest.getOrderDetails().getDimensions().getLength();
        double width = orderRequest.getOrderDetails().getDimensions().getWidth();
        double height = orderRequest.getOrderDetails().getDimensions().getHeight();
        double volume = (length / 100) * (width / 100) * (height / 100); // cm to m

        // 3. Base price calculation
        double basePrice = 50; // base fee
        double pricePerKm = 10; // per km
        double pricePerCubicMeter = 100; // per m^3

        double price = basePrice + (distanceKm * pricePerKm) + (volume * pricePerCubicMeter);

        // 4. Add fragile item surcharge
        if (orderRequest.getOrderDetails().getIsFragile()) {
            price += 100;
        }

        // 5. Add express delivery surcharge
        if ("Express".equalsIgnoreCase(orderRequest.getDeliveryType())) {
            price *= 1.2; // 20% surcharge
        }

        // 6. Return as BigDecimal, rounded to 2 decimal places
        return BigDecimal.valueOf(price).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    // Haversine formula to calculate distance between two lat/lon points
    private double haversine(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radius of the earth in km
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // Distance in km
    }
}
