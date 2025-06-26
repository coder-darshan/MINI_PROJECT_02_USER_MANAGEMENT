package com.darshan.service;

import com.darshan.dto.QuoteApiResponseDTO;
import com.darshan.service.impl.DashBoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DashBoardServiceImpl implements DashBoardService {

    private String quoteUrl="https://dummyjson.com/quotes/random";
    @Override
    public QuoteApiResponseDTO getQuote() {

        RestTemplate template = new RestTemplate();
        ResponseEntity<QuoteApiResponseDTO> forEntity = template.getForEntity(quoteUrl, QuoteApiResponseDTO.class);
        QuoteApiResponseDTO responseDTO=forEntity.getBody();


        return responseDTO;
    }
}
