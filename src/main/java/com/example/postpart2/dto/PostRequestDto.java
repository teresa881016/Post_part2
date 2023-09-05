package com.example.postpart2.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostRequestDto{
    private Long id;
    private String username;
    private String title;
    private String contents;

}
