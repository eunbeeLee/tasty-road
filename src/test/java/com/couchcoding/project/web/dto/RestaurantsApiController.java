package com.couchcoding.project.web.dto;

import com.couchcoding.project.domain.restaurants.RestaurantsRepository;
import com.couchcoding.project.domain.restaurantscategories.RestaurantsCategories;
import com.couchcoding.project.domain.restaurantscategories.RestaurantsCategoriesRepository;
import com.couchcoding.project.service.restaurants.RestaurantsService;
import com.couchcoding.project.web.dto.categories.RestaurantsCategoriesSaveRequestDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    private RestaurantsService restaurantsService;

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
    public void 식당_조회_api_테스트(){
//        String categoryCode = "KOR";
//        String categoryName = "한식";
//
//        Long categoryId = categoriesRepository.save(RestaurantsCategories.builder().code(categoryCode).name(categoryName).build()).getId();
//
//        String restaurantsName = "서울식당";
//        String restaurantsAddress = "서울시 강남구";
//        Float starRate = 3.6f;
//        String visitDate = "2020-04-13";
//        String memo = "맛있음";


        String url = "http://localhost:" + port + "/api/v1/restaurants";


        ResponseEntity<RestaurantsCategories[]> responseEntity = testRestTemplate.getForEntity(url+"?name={1}", RestaurantsCategories[].class, "서울");

        Map<String, String> params = new HashMap<>();
        params.put("name", "서울");
        ResponseEntity<RestaurantsCategories[]> responseEntity2 = testRestTemplate.getForEntity(url+"?name={name}", RestaurantsCategories[].class, params);


    }
}
