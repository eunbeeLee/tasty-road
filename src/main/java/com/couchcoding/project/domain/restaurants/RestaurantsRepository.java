package com.couchcoding.project.domain.restaurants;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantsRepository extends JpaRepository<Restaurants, Long> {
    List<Restaurants> findByNameContaining(String name);
    List<Restaurants> findByAddressContaining(String address);
}
