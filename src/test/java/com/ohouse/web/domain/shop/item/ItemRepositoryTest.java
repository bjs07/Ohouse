package com.ohouse.web.domain.shop.item;


import com.ohouse.web.domain.shop.codes.ItemCategoryCode;
import com.ohouse.web.domain.shop.codes.ItemCategoryCodeRepository;
import com.ohouse.web.domain.shop.item.Item;
import com.ohouse.web.domain.shop.item.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemCategoryCodeRepository itemCategoryCodeRepository;

    @AfterEach
    public void cleanUp() {
        itemRepository.deleteAll();
    }

    @Test
    @DisplayName("물품 등록")
    public void 물품등록() throws Exception{
        //given
        Long categoryId = 100L;
        String itemName = "시몬스침대";
        Optional<ItemCategoryCode> itemCategoryCode = itemCategoryCodeRepository.findById(categoryId);//침대
        Item savedItem = itemRepository.save(Item.builder()
                .categoryCode(itemCategoryCode.orElseThrow(()->new Exception("등록된 100번 Category 없음")))
                .name(itemName)
                .build());

        //when
        List<Item> list = itemRepository.findAll();

        //then
        Item item = list.get(0);

        Assertions.assertThat(item.getCategoryCode().getCategoryCode()).isEqualTo(categoryId);
        Assertions.assertThat(item.getName()).isEqualTo(savedItem.getName());
    }

    @Test
    @DisplayName("등록된 물품 제거")
    public void 등록된물품제거() throws Exception{
        //given
        Long categoryId = 100L;
        String itemName = "이케아침대";
        Optional<ItemCategoryCode> itemCategoryCode = itemCategoryCodeRepository.findById(categoryId);//침대
        Item savedItem = itemRepository.save(Item.builder()
                .categoryCode(itemCategoryCode.orElseThrow(()->new Exception("등록된 100번 Category 없음")))
                .name(itemName)
                .build());

        //when
        itemRepository.delete(savedItem);

        List<Item> all = itemRepository.findAll();

        //then
        Assertions.assertThat(all.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("등록된 물품 수정")
    public void 등록된물품수정() throws Exception{
        //given
        Long categoryId = 100L;
        String itemName = "이케아침대";
        Optional<ItemCategoryCode> itemCategoryCode = itemCategoryCodeRepository.findById(categoryId);//침대
        Item savedItem = itemRepository.save(Item.builder()
                .categoryCode(itemCategoryCode.orElseThrow(() -> new Exception("등록된 100번 Category 없음")))
                .name(itemName)
                .build());
        //when
        List<Item> all = itemRepository.findAll();
        Item item = all.get(0);
        item.setName("한샘침대");
        itemRepository.save(item);

        Optional<Item> itemForCompare = itemRepository.findById(savedItem.getItemSeq());

        //then
        Assertions.assertThat(itemForCompare.orElseThrow(()->new Exception("Fail to find 등록된 한샘침대")).getName()).isEqualTo(item.getName());
    }
}
