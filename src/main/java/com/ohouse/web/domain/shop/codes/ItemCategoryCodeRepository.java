package com.ohouse.web.domain.shop.codes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemCategoryCodeRepository extends JpaRepository<ItemCategoryCode, Long> {
}