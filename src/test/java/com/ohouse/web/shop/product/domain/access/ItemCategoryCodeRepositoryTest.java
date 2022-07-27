package com.ohouse.web.shop.product.domain.access;


import com.ohouse.web.shop.product.domain.access.ItemCategoryCodeRepository;
import com.ohouse.web.shop.product.domain.entity.ItemCategoryCode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ItemCategoryCodeRepositoryTest {
    @Autowired
    ItemCategoryCodeRepository itemCategoryCodeRepository;

    @Test
    @DisplayName("Read CategoryCode")
    public void ReadItemCategoryCodeEntity() {
        //given
        String codeCategory1 = "0";
        String codeCategory2 = "22";
        String codeCategory3 = "20";
        String codeCategory4 = "20"; //가구_침대_침대프레임_일반침대
        //when
        List<ItemCategoryCode> byCategory1 = itemCategoryCodeRepository.findByCategory1OrderByCategory1Desc(codeCategory1);
        List<ItemCategoryCode> list0All = itemCategoryCodeRepository.findByCategory1AndCategory2AndCategory3OrderByCategory1DescCategory2DescCategory3Desc(codeCategory1, codeCategory2, codeCategory3);


        //then
        Assertions.assertThat(byCategory1.get(0).getCategory1()).isEqualTo(codeCategory1);
        Assertions.assertThat(list0All.get(0).getCategory4()).isEqualTo(codeCategory4);
    }

    @Test
    @DisplayName("Category1 리스트 얻기")
    public void GetCategory1List(){
        //given

        //when
        //In DB, Set 0 is 침대, 1 is 패브릭
        List<String> category1All = itemCategoryCodeRepository.findDistinctCategory1ListOrderByAsc();

        //then
        Assertions.assertThat(category1All.get(0)).isEqualTo("0");
        Assertions.assertThat(category1All.get(1)).isEqualTo("1");
    }

    @Test
    @DisplayName("Category2 리스트 얻기")
    public void GetCategory2List(){
        //givin

        //when
        //In DB, Set 0 is 침대, 1 is 패브릭
        //In DB, if category 1 == 0 then category has 22:침대 and 23:매트리스.토퍼 and so on.
        List<String> category1All = itemCategoryCodeRepository.findDistinctCategory2ListOrderByAsc("0");

        //then
        Assertions.assertThat(category1All.get(0)).isEqualTo("22"); //22 : 침대
        Assertions.assertThat(category1All.get(1)).isEqualTo("23"); //23 : 매트리스
    }

    @Test
    @DisplayName("Category3 리스트 얻기")
    public void GetCategory3List(){
        //givin

        //when
        //In DB, Set 0 is 침대, 1 is 패브릭
        //In DB, if category 1 == 0 then category has 22:침대 and 23:매트리스.토퍼 and so on.
        //In DB, if category 1 == 0 && category 2 == 22 then category3 is one that start from 20, 20 : 침대프레임, 21 : 침대+매트리스
        List<String> category1All = itemCategoryCodeRepository.findDistinctCategory3ListOrderByAsc("0", "22");

        //then
        Assertions.assertThat(category1All.get(0)).isEqualTo("20"); //20 : 침대프레임
        Assertions.assertThat(category1All.get(1)).isEqualTo("21"); //21 : 침대+매트리스
    }

    @Test
    @DisplayName("모든 ItemCategoryCode 찾기. Asc으로")
    public void GetItemCategoryServiceListAsc(){
        //given
        List<ItemCategoryCode> allAsc = itemCategoryCodeRepository.findDistinctAllAsc();
        //when

        //then
        /*
        NOTE: 다음 코드는 Assertion이 없어서 무조건 성공으로 끝남

        좋은 방법은 아님을 알고 있지만 정해지지 않은 Table에 대해서 모든 성공 케이스를 검사하는 좋은 방법이 없었음
         */
        for (ItemCategoryCode i : allAsc) {
            System.out.println(i.getCategory1() +" " + i.getCategory2() + " " + i.getCategory3() + " " + i.getCategory4());
        }
    }
}
