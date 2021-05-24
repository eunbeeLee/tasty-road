package com.couchcoding.project.web;

import com.couchcoding.project.service.restaurantsservice.RestaurantsCategoriesService;
import com.couchcoding.project.web.dto.RestaurantsCategoriesRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RestaurantsCategoriesApiController {

    private final RestaurantsCategoriesService restaurantsCategoriesService;

    @PostMapping("/api/v1/restaurants-categories")
    public Long save(@RequestBody RestaurantsCategoriesRequestDto requestDto){
        return restaurantsCategoriesService.save(requestDto);
    }
}
