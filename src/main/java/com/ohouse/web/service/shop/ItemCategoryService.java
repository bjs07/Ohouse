package com.ohouse.web.service.shop;

import com.ohouse.web.domain.shop.codes.ItemCategoryCodeRepository;
import com.ohouse.web.web.shop.dto.item.ItemCategoryListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ItemCategoryService {
    private final ItemCategoryCodeRepository itemCategoryCodeRepository;

    @Transactional
    public List<ItemCategoryListResponseDto> getItemCategoryCodeListAll(){

        return itemCategoryCodeRepository.findDistinctAllAsc()
                .stream().map(ItemCategoryListResponseDto::new)
                .collect(Collectors.toList());
    }
}
