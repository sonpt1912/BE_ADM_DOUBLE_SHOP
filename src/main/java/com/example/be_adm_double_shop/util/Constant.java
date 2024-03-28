package com.example.be_adm_double_shop.util;

public class Constant {

    public static final String SUCCESS = "SUCCESS";


    public static final String FAIL = "FAIL";

    public static final String ROOT_FOLDER = "double_shop";

    public static final String PRODUCT_FOLDER = "product";

    public static final Integer ACTIVE = 0;


    public static final String UNAUTHORIZED = "401";

    public static final String USERNAME_OR_PASSWORD = "Tài khoản hoặc mật khẩu không chính xác";

    public static final Integer IN_ACTIVE = 1;

    public static final Integer IS_ADMIN = 0;

    public static final Integer IS_EMPLOYEE = 1;

    public static final String API001 = "API001";

    public static final String DEFAULT_PASSWORD = "hello";

    public interface DETAIL_PRODUCT {

        String CATEGORY = "CATEGORY";
        String SIZE = "SIZE";
        String COLLAR = "COLLAR";

        String BRAND = "BRAND";

    }

    public interface PRODUCT {

        String PRODUCT = "PRODUCT";

        Long ACTIVE = 0L;

        Long INACTIVE = 1L;

        Long PAUSE = 2L;
    }

    public interface BILL {

        // cho xac nhan, huy, thanh toan, chua thanh toan,

        Long WAIT_CONFIRM = 0L;

        Long CONFIRM = 1L;

        Long WAIT_DEVERILY = 2L;

        Long DEVERELU = 3L;

        Long DONE = 4L;

        Long CANCELLATION = 5L;


    }


}
