package com.ohouse.web.comment;

import com.ohouse.web.Post.Post;
import com.ohouse.web.User.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentRequestDto {
    private long id;
    private String comment;
    private String createDate;
    private String modifyDate;
    private User user;
    private Post post;

    //public Comment comments = Comment.builder();
}

