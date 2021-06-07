package com.couchcoding.project.web.dto;

import com.couchcoding.project.domain.restaurants.RestaurantsRepository;
import com.couchcoding.project.domain.restaurantscategories.RestaurantsCategoriesRepository;
import com.couchcoding.project.web.dto.categories.RestaurantsCategoriesSaveRequestDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestaurantsApiController {


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestaurantsRepository restaurantsRepository;

    @Autowired
    private RestaurantsCategoriesRepository categoriesRepository;

    @Before
    public void 카테고리_데이터_사전_등록(){
        String categoryName = "한식";
        String categoryCode = "KOR";
        RestaurantsCategoriesSaveRequestDto requestDto
                = RestaurantsCategoriesSaveRequestDto.builder()
                .name(categoryName)
                .code(categoryCode)
                .build();

        categoriesRepository.save(requestDto.toEntity());
    }
    @Test
    public void 식당_등록_api_테스트(){

    }
}
