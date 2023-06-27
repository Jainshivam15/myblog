package com.myblog.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class PostDto {

    private long id;

    @NotEmpty
    @Size(min = 2, message = "post title should have atleast 2 characters")
    private String title;

    @NotEmpty
    @Size(min = 10, message = "post description should have atleast 10 characters")
    private String description;

    @NotEmpty
    @Size(min = 2, message = "post content should have atleast 10 characters")
    private String content;
}
