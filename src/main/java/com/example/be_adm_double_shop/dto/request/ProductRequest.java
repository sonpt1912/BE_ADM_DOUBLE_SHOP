package com.example.be_adm_double_shop.dto.request;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductRequest {

    private String code;
    
    private String name;

    private Long idProduct;

    private Long idBrand;

    private Long idCollar;

    private Long idCategory;

    private Long idSize;

    private Long idMaterial;

    private Long idColor;

    private Integer page;

    private Integer pageSize;

    private Integer status;

    private List<SizeRequest> listSize;


}
