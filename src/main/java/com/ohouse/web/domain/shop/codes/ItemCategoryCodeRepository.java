package com.ohouse.web.domain.shop.codes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ItemCategoryCodeRepository extends JpaRepository<ItemCategoryCode, Long> {
    List<ItemCategoryCode> findByCategory1OrderByCategory1Desc(String category1);
    List<ItemCategoryCode> findByCategory1AndCategory2OrderByCategory1DescCategory2Desc(String category1, String category2);
    List<ItemCategoryCode> findByCategory1AndCategory2AndCategory3OrderByCategory1DescCategory2DescCategory3Desc(String category1, String category2,String category3);
}
