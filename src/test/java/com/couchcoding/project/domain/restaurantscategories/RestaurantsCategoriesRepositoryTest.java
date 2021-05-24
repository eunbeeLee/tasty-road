package com.couchcoding.project.domain.restaurantscategories;

import com.couchcoding.project.Application;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RestaurantsCategoriesRepositoryTest {

    @Autowired
    RestaurantsCategoriesRepository restaurantsCategoriesRepository;

    @After
    public void cleanUp() {
        restaurantsCategoriesRepository.deleteAll();
    }

    @Test
    public void 카테고리_저장() throws Exception{
        String name = "한식";
        String code = "KOR";

        // 디비에 데이터 저장하기
        restaurantsCategoriesRepository.save(RestaurantsCategories.builder()
                .code(code)
                .name(name)
                .build());

        // 디비의 모든 categories 데이터 가져오기
        List<RestaurantsCategories> categoriesList = restaurantsCategoriesRepository.findAll();

        RestaurantsCategories categories = categoriesList.get(0);

        // 디비에 실제로 들어간 값과, 주어진 데이터의 값을 비교한다.
        assertThat(categories.getCode()).isEqualTo(code);
        assertThat(categories.getName()).isEqualTo(name);
    }
}

