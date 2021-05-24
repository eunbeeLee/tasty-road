package com.couchcoding.project.service.restaurantsservice;

import com.couchcoding.project.domain.restaurantscategories.RestaurantsCategoriesRepository;
import com.couchcoding.project.web.dto.RestaurantsCategoriesRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RestaurantsCategoriesService {

    private final RestaurantsCategoriesRepository restaurantsCategoriesRepository;

    public Long save (RestaurantsCategoriesRequestDto requestDto){
        return restaurantsCategoriesRepository.save(requestDto.toEntity()).getId();
    }

}
