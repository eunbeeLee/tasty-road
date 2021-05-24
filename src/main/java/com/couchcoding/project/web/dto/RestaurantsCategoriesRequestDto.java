package com.couchcoding.project.web.dto;

import com.couchcoding.project.domain.restaurantscategories.RestaurantsCategories;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RestaurantsCategoriesRequestDto {
    private String name;
    private String code;

    @Builder
    public RestaurantsCategoriesRequestDto(String name, String code){
        this.name = name;
        this.code = code;
    }

    public RestaurantsCategories toEntity() {
        return RestaurantsCategories.builder()
                .name(name)
                .code(code)
                .build();
    }
}
