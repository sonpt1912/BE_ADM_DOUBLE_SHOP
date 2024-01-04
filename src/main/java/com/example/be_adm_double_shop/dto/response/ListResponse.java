package com.example.be_adm_double_shop.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListResponse<T> {

    private List<T> listData;
    private Integer totalRecord;

}
