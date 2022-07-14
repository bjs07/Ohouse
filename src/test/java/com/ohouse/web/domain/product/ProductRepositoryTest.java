package com.ohouse.web.domain.product;

import com.ohouse.web.domain.items.codes.ItemCategoryCode;
import com.ohouse.web.domain.items.codes.ItemCategoryCodeRepository;
import com.ohouse.web.domain.items.item.Item;
import com.ohouse.web.domain.items.item.ItemRepository;
import com.ohouse.web.domain.items.product.Product;
import com.ohouse.web.domain.items.product.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemCategoryCodeRepository itemCategoryCodeRepository;
    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void updatePreviously() throws Exception{
        //Find Category
        Long categoryId = 100L;
        Optional<ItemCategoryCode> itemCategoryCode = itemCategoryCodeRepository.findById(categoryId);//침대

        //Set Item
        String itemName1 = "이케아침대";
        Item savedItem1 = itemRepository.save(Item.builder()
                .categoryCode(itemCategoryCode.orElseThrow(()->new Exception("등록된 100번 Category 없음")))
                .name(itemName1)
                .build());
        //Set Item2
        String itemName2 = "한샘침대";
        Item savedItem2 = itemRepository.save(Item.builder()
                .categoryCode(itemCategoryCode.orElseThrow(()->new Exception("등록된 100번 Category 없음")))
                .name(itemName2)
                .build());
        //Set Item3
        String itemName3 = "시몬스침대";
        Item savedItem3 = itemRepository.save(Item.builder()
                .categoryCode(itemCategoryCode.orElseThrow(()->new Exception("등록된 100번 Category 없음")))
                .name(itemName3)
                .build());

        itemRepository.save(savedItem1);
        itemRepository.save(savedItem2);
        itemRepository.save(savedItem3);

    }

    @AfterEach
    public void cleanUp() {
        productRepository.deleteAll();
        itemRepository.deleteAll();
    }

    @Test
    @DisplayName("제품 등록")
    public void 제품등록() {
        //given
        String itemName1 = "이케아침대";
        String itemName2 = "한샘침대";
        String itemName3 = "시몬스침대";

        List<Item> itemList1 = itemRepository.findByName(itemName1);
        List<Item> itemList2 = itemRepository.findByName(itemName2);
        List<Item> itemList3 = itemRepository.findByName(itemName3);

        List<String> productNames = new ArrayList<>();
        productNames.add("멜로우 하단 메트리스 (SS/Q)");
        productNames.add("알렌 F 원목 침대");
        productNames.add("피아프 LED 침대");

        Product product1 = Product.builder()
                .item(itemList1.get(0))
                .productName(productNames.get(0))
                .price(440000)
                .stock(100)
                .rateDiscount(44)
                .freeDeliveryYn('Y')
                .specialOrder('N')
                .build();
        Product product2 = Product.builder()
                .item(itemList2.get(0))
                .productName(productNames.get(1))
                .price(600000)
                .stock(50)
                .rateDiscount(60)
                .freeDeliveryYn('Y')
                .specialOrder('N')
                .build();
        Product product3 = Product.builder()
                .item(itemList3.get(0))
                .productName(productNames.get(2))
                .price(300000)
                .stock(100)
                .rateDiscount(20)
                .freeDeliveryYn('Y')
                .specialOrder('N')
                .build();
        //when
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        //then
        List<Product> all = productRepository.findAll();

        for(String productName : productNames){
            boolean is_exist = false;
            for(Product p : all) {
                if(p.getProductName().equals(productName)) {
                    is_exist = true;
                    break;
                }
            }
            Assertions.assertThat(is_exist).isTrue();
        }
    }

    @Test
    @DisplayName("등록된 제품 제거")
    public void 등록된제품제거() {
        //given
        String itemName1 = "이케아침대";
        List<Item> itemList1 = itemRepository.findByName(itemName1);

        String productName1 = "알렌 F 원목 침대";
        Product product1 = Product.builder()
                .item(itemList1.get(0))
                .productName(productName1)
                .price(440000)
                .stock(100)
                .rateDiscount(44)
                .freeDeliveryYn('Y')
                .specialOrder('N')
                .build();
        Product saved = productRepository.save(product1);

        //when
        List<Product> all = productRepository.findAll();
        Long productId = all.get(0).getProductSeq();

        productRepository.delete(saved);

        //then
        Optional<Product> findId = productRepository.findById(productId);
        Assertions.assertThat(findId.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("등록된 제품 수정")
    public void 등록된제품수정() throws Exception{
        //given
        String itemName1 = "이케아침대";
        List<Item> itemList1 = itemRepository.findByName(itemName1);

        String productName1 = "알렌 F 원목 침대";
        Product product1 = Product.builder()
                .item(itemList1.get(0))
                .productName(productName1)
                .price(440000)
                .stock(100)
                .rateDiscount(44)
                .freeDeliveryYn('Y')
                .specialOrder('N')
                .build();
        Product saved = productRepository.save(product1);

        //when
        String changedName = "빨간침대";
        saved.setProductName(changedName);
        productRepository.save(saved);

        //then
        Optional<Product> findId = productRepository.findById(saved.getProductSeq());
        Assertions.assertThat(findId.orElseThrow(() -> new Exception("Fail to find")).getProductName()).isEqualTo(changedName);
    }
}
