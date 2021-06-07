package com.couchcoding.project.service.restaurantsCategories;

import com.couchcoding.project.domain.restaurantscategories.RestaurantsCategories;
import com.couchcoding.project.domain.restaurantscategories.RestaurantsCategoriesRepository;
import com.couchcoding.project.web.dto.categories.RestaurantsCategoriesSaveRequestDto;
import com.couchcoding.project.web.dto.categories.RestaurantsCategoriesUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantsCategoriesService {

    private final RestaurantsCategoriesRepository restaurantsCategoriesRepository;

    public Long save (RestaurantsCategoriesSaveRequestDto requestDto){
//        return restaurantsCategoriesRepository.save(requestDto.toEntity()).getId();
        RestaurantsCategories restaurantsCategories = restaurantsCategoriesRepository.save(requestDto.toEntity());
        return restaurantsCategories.getId();
    }

    public List<RestaurantsCategories> findAll(){
        return restaurantsCategoriesRepository.findAll();
    }

    public RestaurantsCategories findById(Long id){
        RestaurantsCategories restaurantsCategories = restaurantsCategoriesRepository
                .findById(id).orElseThrow(() -> new IllegalArgumentException("해당 카테고리 정보가 없습니다."));
//                .findById(id).orElseThrow(NoSuchElementException::new);

        return restaurantsCategories;
    }

    @Transactional
    public Long update(Long id, RestaurantsCategoriesUpdateRequestDto requestDto){
        RestaurantsCategories restaurantsCategories = restaurantsCategoriesRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("해당 카테고리 정보가 없습니다. id:"+ id ));

        restaurantsCategories.update(requestDto.getName());
        return id;
    }

    @Transactional
    public void delete(Long id){
        RestaurantsCategories restaurantsCategories = restaurantsCategoriesRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("해당 카테고리 정보가 없습니다. id:" + id));

        restaurantsCategoriesRepository.delete(restaurantsCategories);
    }



}
