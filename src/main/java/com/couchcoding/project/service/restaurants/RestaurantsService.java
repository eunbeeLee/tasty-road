package com.couchcoding.project.service.restaurants;

import com.couchcoding.project.domain.restaurants.Restaurants;
import com.couchcoding.project.domain.restaurants.RestaurantsRepository;
import com.couchcoding.project.domain.restaurantscategories.RestaurantsCategories;
import com.couchcoding.project.service.restaurantsCategories.RestaurantsCategoriesService;
import com.couchcoding.project.web.dto.restaurants.RestaurantsSaveRequestDto;
import com.couchcoding.project.web.dto.restaurants.RestaurantsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class RestaurantsService {

    private final RestaurantsRepository restaurantsRepository;
    private final RestaurantsCategoriesService categoriesService;

    public Long save(RestaurantsSaveRequestDto requestDto){
        RestaurantsCategories categories = categoriesService.findById(requestDto.getCategoryId());

        return restaurantsRepository.save(requestDto.toEntity(categories)).getId();
    }

    public Restaurants findById(Long id){
        return restaurantsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 식당 정보가 없습니다."));
    }

    public List<Restaurants> getList(String nameFilter, String addressFilter ){
        if(!Objects.isNull(nameFilter)){
            return restaurantsRepository.findByNameContaining(nameFilter);
        }
        if(!Objects.isNull(addressFilter)){
            return restaurantsRepository.findByAddressContaining(addressFilter);
        }

        return restaurantsRepository.findAll();
    }

    @Transactional
    public Long update(Long id, RestaurantsUpdateRequestDto requestDto){
        Restaurants restaurants = restaurantsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 식당 정보가 없습니다."));

        RestaurantsCategories categories = categoriesService.findById(requestDto.getCategoryId());

        restaurants.update(requestDto.getName(), requestDto.getAddress(), requestDto.getStarRate(),
                categories, requestDto.getVisitDate(), requestDto.getMemo());

        return restaurants.getId();
    }

    @Transactional
    public void delete(Long id){
        Restaurants restaurants = restaurantsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 식당 정보가 없습니다."));

        restaurantsRepository.delete(restaurants);
    }
}
