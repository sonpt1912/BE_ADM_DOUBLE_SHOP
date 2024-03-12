package com.example.be_adm_double_shop.dto.response;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductResponse {


    private String productName;

    private Long quantity;

    private Long price;

    private List<String> images;

}
