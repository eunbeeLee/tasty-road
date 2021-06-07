package com.couchcoding.project.web;


import com.couchcoding.project.domain.restaurants.Restaurants;
import com.couchcoding.project.service.restaurants.RestaurantsService;
import com.couchcoding.project.web.dto.restaurants.RestaurantsSaveRequestDto;
import com.couchcoding.project.web.dto.restaurants.RestaurantsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RestaurantsApiController {

    private final RestaurantsService restaurantsService;

    @PostMapping("/api/v1/restaurants")
    public Long save(@RequestBody RestaurantsSaveRequestDto requestDto){
        return restaurantsService.save(requestDto);
    }

    @GetMapping("/api/v1/restaurants/{id}")
    public Restaurants findById(@PathVariable Long id){
        return restaurantsService.findById(id);
    }

    @GetMapping("/api/v1/restaurants")
    public List<Restaurants> getList(@RequestParam(name = "name", required = false) String name,
                                     @RequestParam(name = "address", required = false) String address){
        return restaurantsService.getList(name, address);
    }

    @PutMapping("/api/v1/restaurants/{id}")
    public Long update(@PathVariable Long id, @RequestBody RestaurantsUpdateRequestDto requestDto){
        return restaurantsService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/restaurants/{id}")
    public void delete(@PathVariable Long id){
        restaurantsService.delete(id);
    }
}
