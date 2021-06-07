package com.couchcoding.project.web.dto;

import com.couchcoding.project.domain.restaurantscategories.RestaurantsCategories;
import com.couchcoding.project.domain.restaurantscategories.RestaurantsCategoriesRepository;
import com.couchcoding.project.web.dto.categories.RestaurantsCategoriesSaveRequestDto;
import com.couchcoding.project.web.dto.categories.RestaurantsCategoriesUpdateRequestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
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

//    @After
//    public void tearDown() throws Exception{
//        categoriesRepository.deleteAll();
//    }

    @Test
    public void 카테고리_등록_api_테스트() {
        String name= "한식";
        String code = "KOR";

        RestaurantsCategoriesSaveRequestDto requestDto =
                RestaurantsCategoriesSaveRequestDto.builder().code(code).name(name).build();

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


    @Test
    public void 카테고리_전체_조회_api_테스트(){

        // given
        String code = "KOR";
        String name = "한식";
        categoriesRepository.save(RestaurantsCategories.builder()
                .code(code)
                .name(name)
                .build());

        String url = "http://localhost:" + port + "/api/v1/restaurants-categories";

        ResponseEntity<RestaurantsCategories[]> responseEntity = testRestTemplate.getForEntity(url, RestaurantsCategories[].class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        RestaurantsCategories restaurantsCategories = Arrays.asList(responseEntity.getBody()).get(0);

        assertThat(restaurantsCategories.getName()).isEqualTo(name);
        assertThat(restaurantsCategories.getCode()).isEqualTo(code);
    }

    @Test
    public void 카테고리_id로_조회_api_테스트(){
        // given
        String code = "KOR";
        String name = "한식";
        RestaurantsCategories restaurantsCategories =  categoriesRepository.save(RestaurantsCategories.builder()
                .code(code)
                .name(name)
                .build());

        Long givenDataId = restaurantsCategories.getId();

        String url = "http://localhost:" + port + "/api/v1/restaurants-categories/" + givenDataId;

        ResponseEntity<RestaurantsCategories> responseEntity = testRestTemplate.getForEntity(url, RestaurantsCategories.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getCode()).isEqualTo(code);
        assertThat(responseEntity.getBody().getName()).isEqualTo(name);
    }


    @Test
    public void 카테고리_수정_api_테스트() {
        // given
        String code = "KOR";
        String name = "한식";
        RestaurantsCategories saveRestaurantsCategories =  categoriesRepository.save(RestaurantsCategories.builder()
                .code(code)
                .name(name)
                .build());

        Long updateId = saveRestaurantsCategories.getId();

        String expectedName = "한정식";
        RestaurantsCategoriesUpdateRequestDto updateRequestDto =
                RestaurantsCategoriesUpdateRequestDto.builder().name(expectedName).build();

        String url = "http://localhost:" + port + "/api/v1/restaurants-categories/" + updateId;

        // when
        ResponseEntity<Long> responseEntity = testRestTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(updateRequestDto), Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<RestaurantsCategories> all = categoriesRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    public void 카테고리_삭제_테스트() {
        // given
        String name = "한식";
        String code = "KOR";
        Long removeId = categoriesRepository.save(RestaurantsCategories.builder().code(code).name(name).build()).getId();
        String url = "http://localhost:" + port + "/api/v1/restaurants-categories/" + removeId;

        // when
        ResponseEntity<Long> responseEntity = testRestTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<>(removeId), Long.class);

        // then
        List<RestaurantsCategories> allRestaurantsCategories = categoriesRepository.findAll();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(allRestaurantsCategories.size()).isEqualTo(0);
    }
}
