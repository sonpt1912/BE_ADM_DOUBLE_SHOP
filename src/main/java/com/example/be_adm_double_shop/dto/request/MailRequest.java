package com.example.be_adm_double_shop.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MailRequest {

    private String subject;

    private String content;

    private String reciver;

}
