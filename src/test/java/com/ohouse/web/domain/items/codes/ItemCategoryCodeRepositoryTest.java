package com.ohouse.web.domain.items.codes;


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
    @DisplayName("Read CategoryCode(100, '침대')")
    public void ReadItemCategoryCodeEntity() {
        //given
        Long code1 = 100L;
        String codeCategory1 = "침대";


        //when
        ItemCategoryCode entityObject = new ItemCategoryCode(code1, codeCategory1);

        Example<ItemCategoryCode> example = Example.of(entityObject);
        List<ItemCategoryCode> codeList = itemCategoryCodeRepository.findAll(example);
        ItemCategoryCode code = codeList.get(0);

        //then
        Assertions.assertThat(code.getCategoryCode()).isEqualTo(code1);
        Assertions.assertThat(code.getCategory()).isEqualTo(codeCategory1);
    }
}
