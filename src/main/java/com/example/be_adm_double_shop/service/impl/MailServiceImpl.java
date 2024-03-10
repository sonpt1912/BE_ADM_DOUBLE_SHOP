package com.example.be_adm_double_shop.service.impl;

import com.example.be_adm_double_shop.dto.request.MailRequest;
import com.example.be_adm_double_shop.service.MailService;
import com.example.be_adm_double_shop.util.Constant;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public Object sendMailFortgotPassword() {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("DOUBLE SHOP");
            helper.setSubject("THAY DOI MAT KHAU");


            Context context = new Context();
            context.setVariable("", "");
            context.setVariable("", "");

            String template = templateEngine.process("", context);
            helper.setText(template, true);

            javaMailSender.send(message);
            return Constant.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }

    @Override
    public void sendMailOtp() {

    }

    @Override
    public String sendMailCreateAccount(MailRequest mailRequest) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(sender,"DOUBLE_SHOP");
            helper.setTo(mailRequest.getReciver());
            helper.setSubject("TÀI KHOẢN ĐƯỢC TẠO THÀNH CONG");

            Context context = new Context();
            context.setVariable("account", mailRequest.getSubContent());
            context.setVariable("password", mailRequest.getContent());

            String processContent = templateEngine.process("create-account", context);

            helper.setText(processContent, true);
            javaMailSender.send(message);
            return Constant.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

}
