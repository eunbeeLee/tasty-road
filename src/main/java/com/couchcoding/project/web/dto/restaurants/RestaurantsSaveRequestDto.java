package com.couchcoding.project.web.dto.restaurants;

import com.couchcoding.project.domain.restaurants.Restaurants;
import com.couchcoding.project.domain.restaurantscategories.RestaurantsCategories;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class RestaurantsSaveRequestDto {

    private String name;
    private String address;
    private Float starRate;
    private Long categoryId;
    private String visitDate;
    private String memo;

    @Builder
    public RestaurantsSaveRequestDto(String name, String address, Float starRate,
                                     Long categoryId, String visitDate, String memo){
        this.name = name;
        this.address = address;
        this.starRate = starRate;
        this.categoryId = categoryId;
        this.visitDate = visitDate;
        this.memo = memo;
    }

    public Restaurants toEntity(RestaurantsCategories categories){
        return Restaurants.builder()
                .name(name)
                .address(address)
                .starRate(starRate)
                .restaurantsCategories(categories)
                .memo(memo)
                .visitDate(LocalDate.parse(visitDate))
                .build();
    }

}
