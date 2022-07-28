package com.ohouse.web.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter


public class CommentDto {
    private String title;
    private String content;


}
