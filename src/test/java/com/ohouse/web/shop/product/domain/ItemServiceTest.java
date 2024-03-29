package com.ohouse.web.shop.product.domain;


import com.ohouse.web.shop.product.domain.entity.ItemCategoryCode;
import com.ohouse.web.shop.product.domain.access.ItemCategoryCodeRepository;
import com.ohouse.web.shop.product.domain.entity.Item;
import com.ohouse.web.shop.product.domain.access.ItemRepository;
import com.ohouse.web.shop.product.domain.dto.ItemAllListResponseDto;
import com.ohouse.web.shop.product.domain.dto.ItemSaveRequestDto;
import com.ohouse.web.shop.product.domain.dto.ItemUpdateRequestDto;
import com.ohouse.web.shop.product.domain.ItemService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {
    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemCategoryCodeRepository itemCategoryCodeRepository;
    @Autowired
    private ItemRepository itemRepository;


    @AfterEach
    public void cleanUp() {
        itemRepository.deleteAll();
    }

    @Test
    @DisplayName("물픔등록")
    public void 물품등록() throws Exception{
        //given
        ItemCategoryCode categoryCode = 가구_침대_침대프레임_일반침대_카테고리찾기();
        String itemName = "레인 짜맞춤 원목 평상형 침대 프레임";
        String modelName = "레인 짜맞춤 8각 평상형 원목 침대프레임";
        String brandName = "엔투엔퍼니쳐";
        //when
        Long seq = itemService.save(new ItemSaveRequestDto(
                categoryCode,
                itemName,
                modelName,
                brandName
        ));
        //then
        Item findItem = itemRepository.findById(seq)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Item : " + seq));

        Assertions.assertThat(findItem.getName()).isEqualTo(itemName);
        Assertions.assertThat(findItem.getModelName()).isEqualTo(modelName);
        Assertions.assertThat(findItem.getBrandName()).isEqualTo(brandName);
    }

    @Test
    @DisplayName("물품수정")
    public void 물품수정() throws Exception{
        //given
        Long seq = saveItem();
        ItemCategoryCode categoryCode = 가구_침대_침대프레임_일반침대_카테고리찾기();
        String itemName = "품질이 매우 안좋은 침대(+7)";
        String modelName = "제작자 마음대로";
        String brandName = "JH공방";

        //when
        Long savedSeq = itemService.update(seq, new ItemUpdateRequestDto(
                categoryCode,
                itemName,
                modelName,
                brandName
        ));

        //then
        Item findItem = itemRepository.findById(savedSeq)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Item : " + savedSeq));

        Assertions.assertThat(seq).isEqualTo(savedSeq);
        Assertions.assertThat(findItem.getName()).isEqualTo(itemName);
        Assertions.assertThat(findItem.getModelName()).isEqualTo(modelName);
        Assertions.assertThat(findItem.getBrandName()).isEqualTo(brandName);
    }

    @Test
    @DisplayName("물품제거")
    public void 물품제거() throws Exception{
        //given
        Long seq = saveItem();
        //when
        itemService.delete(seq);

        //then
        List<ItemAllListResponseDto> all = itemService.findAllAsc();

        Assertions.assertThat(all.isEmpty()).isTrue();
    }


    private ItemCategoryCode 가구_침대_침대프레임_일반침대_카테고리찾기() throws Exception{
        ItemCategoryCode categoryCode = ItemCategoryCode.builder()
                .category1("0")
                .category2("22")
                .category3("20")
                .category4("20")
                .build();
        Example<ItemCategoryCode> e = Example.of(categoryCode);
        Optional<ItemCategoryCode> one = itemCategoryCodeRepository.findOne(e);
        return one.orElseThrow(()-> new Exception("해당 CategoryCode 없음"));
    }
    private Long saveItem() throws Exception{
        ItemCategoryCode categoryCode = 가구_침대_침대프레임_일반침대_카테고리찾기();
        String itemName = "레인 짜맞춤 원목 평상형 침대 프레임";
        String modelName = "레인 짜맞춤 8각 평상형 원목 침대프레임";
        String brandName = "엔투엔퍼니쳐";

        return itemService.save(new ItemSaveRequestDto(
                categoryCode,
                itemName,
                modelName,
                brandName));
    }
}
