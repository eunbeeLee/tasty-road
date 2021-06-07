package com.couchcoding.project.web.dto.restaurants;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class RestaurantsUpdateRequestDto {
    private String name;
    private String address;
    private Float starRate;
    private Long categoryId;
    private String visitDate;
    private String memo;

    @Builder
    public RestaurantsUpdateRequestDto (String name, String address, Float starRate,
                                     Long categoryId, String visitDate, String memo){
        this.name = name;
        this.address = address;
        this.starRate = starRate;
        this.categoryId = categoryId;
        this.visitDate = visitDate;
        this.memo = memo;
    }
}
