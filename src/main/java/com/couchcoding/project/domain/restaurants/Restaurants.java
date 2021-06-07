package com.couchcoding.project.domain.restaurants;

import com.couchcoding.project.domain.BaseTimeEntity;
import com.couchcoding.project.domain.restaurantscategories.RestaurantsCategories;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@NoArgsConstructor
@Entity
public class Restaurants extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Float starRate;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private RestaurantsCategories restaurantsCategories;

    @Column(nullable = false)
    private LocalDate visitDate;

    @Column(nullable = true, length = 500)
    private String memo;

    @Builder
    public Restaurants (String name, String address, Float starRate,
                        RestaurantsCategories restaurantsCategories,
                        LocalDate visitDate, String memo){
        this.name = name;
        this.address = address;
        this.starRate = starRate;
        this.restaurantsCategories = restaurantsCategories;
        this.visitDate = visitDate;
        this.memo = memo;
    }

    public void update(String name, String address, Float starRate,
                       RestaurantsCategories categories,
                       String visitDate, String memo){
        if (!Objects.isNull(name)){
            this.name = name;
        }
        if(!Objects.isNull(address)){
            this.address = address;
        }
        if (!Objects.isNull(starRate)){
            this.starRate = starRate;
        }
        if (!Objects.isNull(categories)){
            this.restaurantsCategories = categories;
        }
        if(!Objects.isNull(visitDate)){
            this.visitDate = LocalDate.parse(visitDate);
        }
        if (!Objects.isNull(memo)){
            this.memo = memo;
        }
    }
}
