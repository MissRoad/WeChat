package com.bing.constant;

public interface Constant {

    public static final String SMS = "xz_sms:";// 短信验证码头部

    public static final String APP_ID = "wxd92cd7ace7c06a1c";
    public static final String CAPTCHA = "captcha_";
    public static final String APP_SERCET = "9ecc68872cd7c4d0d380710a71f7fc82";//a179c7e164fd77dae9242ac50807251a
    public static final int PAGESIZE = 30;//
    // DES秘钥
    String IMG_OSSKEY = "llIU0x01";

    String FOLDER = "lemo/app";
    String PAY = "pay:";
    String PAY_ERROR = "pay_error";
    int PAY_MAX_ERROR = 3;
    String WITHDRAW = "withdraw:";
    String PAY_CODE_ERROR = "pay_code_error";

    String WORDFILTER_SET = "wf_wordfilter_set";//敏感词集合
    String WORDFILTER_MAP = "wf_wordfilter_map";//敏感词库
    String LOGIN = "login:";
    String USER="wx_user";
}
