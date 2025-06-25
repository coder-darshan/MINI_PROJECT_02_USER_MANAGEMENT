package com.darshan.dto;

import lombok.Data;

@Data
public class QuoteApiResponseDTO {

    private Integer id;
    private String quote;
    private  String author;
//    https://dummyjson.com/quotes/random
}
