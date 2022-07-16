package com.ohouse.web.domain.shop.codes;


import com.ohouse.web.domain.shop.codes.ItemCategoryCode;
import com.ohouse.web.domain.shop.codes.ItemCategoryCodeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
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
}
