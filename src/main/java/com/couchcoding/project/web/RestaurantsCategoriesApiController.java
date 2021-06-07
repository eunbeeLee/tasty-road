package com.couchcoding.project.web;

import com.couchcoding.project.domain.restaurantscategories.RestaurantsCategories;
import com.couchcoding.project.service.restaurantsCategories.RestaurantsCategoriesService;
import com.couchcoding.project.web.dto.categories.RestaurantsCategoriesSaveRequestDto;
import com.couchcoding.project.web.dto.categories.RestaurantsCategoriesUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RestaurantsCategoriesApiController {

    private final RestaurantsCategoriesService restaurantsCategoriesService;

    @PostMapping("/api/v1/restaurants-categories")
    public Long save(@RequestBody RestaurantsCategoriesSaveRequestDto requestDto){
        return restaurantsCategoriesService.save(requestDto);
    }


    @GetMapping("/api/v1/restaurants-categories")
    public List<RestaurantsCategories> findAll(){
        return restaurantsCategoriesService.findAll();
    }

    @GetMapping("/api/v1/restaurants-categories/{id}")
    public RestaurantsCategories findById(@PathVariable Long id){
        return restaurantsCategoriesService.findById(id);
    }

    @PutMapping("/api/v1/restaurants-categories/{id}")
    public Long update(@PathVariable Long id, @RequestBody RestaurantsCategoriesUpdateRequestDto requestDto){
        return restaurantsCategoriesService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/restaurants-categories/{id}")
    public void delete(@PathVariable Long id){
        restaurantsCategoriesService.delete(id);
    }
}
