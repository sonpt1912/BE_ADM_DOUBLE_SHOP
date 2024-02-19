package com.example.be_adm_double_shop.service;


import com.example.be_adm_double_shop.dto.request.MailRequest;

public interface MailService {

    Object sendMailFortgotPassword();

    void sendMailOtp();

    String sendMailCreateAccount(MailRequest mailRequest);

}
