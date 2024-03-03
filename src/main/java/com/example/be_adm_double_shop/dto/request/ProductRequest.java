package com.example.be_adm_double_shop.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductRequest {

    private String name;

    private Long idProduct;

    private Long idBrand;

    private Long idCollar;

    private Long idCategory;

    private Long idSize;

    private Long idMaterial;

    private Long idColor;


}
