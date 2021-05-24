package com.couchcoding.project.domain.restaurantscategories;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class RestaurantsCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true, unique = true)
    private String code;

    @Builder
    public RestaurantsCategories (String name, String code){
        this.name = name;
        this.code= code;
    }
}
