package com.example.be_adm_double_shop.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailProductRequest {

    private Long id;

    private Long quantity;

    private Long price;

}
