package com.couchcoding.project.web.dto;

import com.couchcoding.project.domain.restaurantscategories.RestaurantsCategories;
import com.couchcoding.project.domain.restaurantscategories.RestaurantsCategoriesRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestaurantsCategoriesApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestaurantsCategoriesRepository categoriesRepository;

    @After
    public void tearDown() throws Exception{
        categoriesRepository.deleteAll();
    }

    @Test
    public void 카테고리_등록_api_테스트() {
        String name= "한식";
        String code = "KOR";

        RestaurantsCategoriesRequestDto requestDto =
                RestaurantsCategoriesRequestDto.builder().code(code).name(name).build();

        String url = "http://localhost:" + port + "/api/v1/restaurants-categories";

        ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url, requestDto, Long.class);

        Long restaurantsCategoryId = responseEntity.getBody();
        HttpStatus httpStatus = responseEntity.getStatusCode();

        assertThat(httpStatus).isEqualTo(HttpStatus.OK);
        assertThat(restaurantsCategoryId).isGreaterThan(0L);

        List<RestaurantsCategories> categoriesList =  categoriesRepository.findAll();
        assertThat(categoriesList.get(0).getCode()).isEqualTo(code);
        assertThat(categoriesList.get(0).getName()).isEqualTo(name);
    }

}
