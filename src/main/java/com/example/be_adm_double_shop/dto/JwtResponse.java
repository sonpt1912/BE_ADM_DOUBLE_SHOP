package com.example.be_adm_double_shop.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JwtResponse {

    private String access_token;

    private String token_type;

}
