package com.ohouse.web.shop.product.domain;

import com.ohouse.web.shop.product.domain.dto.ProductSaveRequestDto;
import com.ohouse.web.shop.product.domain.dto.ProductUpdateRequestDto;
import com.ohouse.web.shop.product.domain.entity.ItemCategoryCode;
import com.ohouse.web.shop.product.domain.access.ItemCategoryCodeRepository;
import com.ohouse.web.shop.product.domain.entity.Item;
import com.ohouse.web.shop.product.domain.access.ItemRepository;
import com.ohouse.web.shop.product.domain.entity.Product;
import com.ohouse.web.shop.product.domain.access.ProductRepository;
import com.ohouse.web.shop.product.domain.ProductService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.assertj.core.api.Assertions;

import java.util.NoSuchElementException;
import java.util.Optional;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ItemCategoryCodeRepository itemCategoryCodeRepository;
    @Autowired
    private ItemRepository itemRepository;

    private Item savedItem;
    private Long savedProductSeq;
    private String productName = "레인 짜맞춤 8각 평상형 원목 침대프레임";
    private Integer price = 850000;
    private Integer stock = 100;
    private Integer rateDiscount = 50;
    private String size = "SS";
    private String color = "애쉬 브라운/브라운";

    @BeforeAll
    void updatePreviously() throws Exception {
        String itemName = "레인 짜맞춤 원목 평상형 침대 프레임";
        String modelName = "레인 짜맞춤 8각 평상형 원목 침대프레임";
        String brandName = "엔투엔퍼니쳐";
        savedItem = saveItem(itemName, modelName, brandName);
    }

    @AfterAll
    public void cleanAll() {
        productRepository.deleteAll();
        itemRepository.deleteAll();
    }

    @Test
    @Order(1)
    @DisplayName("제품등록")
    void save() throws Exception {
        //given

        //when
        Long seq = productService.save(ProductSaveRequestDto.builder()
                .item(this.savedItem)
                .productName(productName)
                .price(price)
                .stock(stock)
                .rateDiscount(rateDiscount)
                .size(size)
                .color(color)
                .build());
        savedProductSeq = seq;

        //then
        Product findProduct = productRepository.findById(seq).orElseThrow(() -> new Exception("해당 제품 Seq 없음"));
        Assertions.assertThat(findProduct.getProductName()).isEqualTo(productName);
        Assertions.assertThat(findProduct.getPrice()).isEqualTo(price);
        Assertions.assertThat(findProduct.getSize()).isEqualTo(size);


    }

    @Test
    @Order(2)
    @DisplayName("제품수정")
    void update() throws Exception {
        //given
        Integer updatedPrice = 500000;
        Integer updatedStock = 200;
        String updatedSize = "M";

        Product findProduct1 = productRepository.findById(savedProductSeq).orElseThrow(() -> new Exception("해당 제품 Seq 없음"));

        //when
        productService.update(savedProductSeq, ProductUpdateRequestDto.builder()
                .price(updatedPrice)
                .stock(updatedStock)
                .size(updatedSize)
                .build());

        //then
        Product findProduct2 = productRepository.findById(savedProductSeq).orElseThrow(() -> new Exception("해당 제품 Seq 없음"));
        Assertions.assertThat(findProduct2.getProductName()).isEqualTo(productName);
        Assertions.assertThat(findProduct2.getColor()).isEqualTo(color);
        Assertions.assertThat(findProduct2.getPrice()).isEqualTo(updatedPrice);
        Assertions.assertThat(findProduct2.getStock()).isEqualTo(updatedStock);
        Assertions.assertThat(findProduct2.getSize()).isEqualTo(updatedSize);
    }

    @Test
    @Order(3)
    @DisplayName("제품제거")
    void delete()  throws Exception{
        //given
        //when
        productService.delete(savedProductSeq);

        //then
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchElementException.class,
                ()->productRepository.findById(savedProductSeq).orElseThrow(()->new NoSuchElementException()));
    }

    private ItemCategoryCode 가구_침대_침대프레임_일반침대_카테고리찾기() throws Exception {
        ItemCategoryCode categoryCode = ItemCategoryCode.builder()
                .category1("0")
                .category2("22")
                .category3("20")
                .category4("20")
                .build();
        Example<ItemCategoryCode> e = Example.of(categoryCode);
        Optional<ItemCategoryCode> one = itemCategoryCodeRepository.findOne(e);
        return one.orElseThrow(() -> new Exception("해당 CategoryCode 없음"));
    }

    private Item saveItem(String itemName, String modelName, String brandName) throws Exception {
        ItemCategoryCode categoryCode = 가구_침대_침대프레임_일반침대_카테고리찾기();

        Item savedItem = itemRepository.save(Item.builder()
                .categoryCode(categoryCode)
                .name(itemName)
                .modelName(modelName)
                .brandName(brandName)
                .build());

        return savedItem;
    }
}