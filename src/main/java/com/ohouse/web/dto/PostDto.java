package com.ohouse.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity

public class PostDto {
    @Id
    private long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String writer;

    @Column(columnDefinition = "integer default 0")
    private int view;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    private UserDto userDto;

    @OneToMany(mappedBy = "postDto",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<CommentDto> commentDtos;

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
