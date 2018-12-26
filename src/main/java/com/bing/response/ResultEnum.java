package com.bing.response;

public enum ResultEnum {

  SUCCESS(12000, "请求成功"), //
  FAIL(13000, "请求失败"), //
  UNKONW_ERROR(-1, "未知错误"),//
  UN_AUTHORIZATION(54000, "令牌已过期,请重新登陆"),
  NEED_BIND_WX(31000, "没有绑定微信账号不能提现"), //
  NEED_BIND_ALIPAY(32000, "没有绑定支付宝账号不能提现"), //
  WX_ERROR(10000,"获取微信授权失败"),
  BALANCE_NOT_ENOUGH(13000, "账户余额不足");

  private Integer code;
  private String msg;

  private ResultEnum(Integer code, String message) {
    this.code = code;
    this.msg = message;
  }

  public Integer getCode() {
    return code;
  }

  public String getMsg() {
    return msg;
  }
}
