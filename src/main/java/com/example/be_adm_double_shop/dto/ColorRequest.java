package com.example.be_adm_double_shop.dto;

import com.example.be_adm_double_shop.entity.Color;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ColorRequest {

    @NotBlank(message = "khong trong")
    private String code;

    @NotBlank(message = "khong trong")
    private String name;

    @NotBlank(message = "khong trong")
    private String description;

    @NotNull(message = "khong trong")
    private Integer status;

    public Color map(Color color){
        color.setCode(this.getCode());
        color.setName(this.getName());
        color.setDescription(this.getDescription());
        color.setStatus(this.getStatus());
        return color;
    }

}
