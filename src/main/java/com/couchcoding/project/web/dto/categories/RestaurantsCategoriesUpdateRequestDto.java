package com.couchcoding.project.web.dto.categories;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RestaurantsCategoriesUpdateRequestDto {
    private String name;

    @Builder
    public RestaurantsCategoriesUpdateRequestDto(String name){
        this.name = name;
    }
}
