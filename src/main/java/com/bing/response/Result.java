package com.bing.response;

public class Result {

  //错误码
  private Integer code;
  //提示信息
  private String msg;
  private Object data;

  public static final Integer SUCCESS_CODE = 12000;
  public static final String SUCCESS_MSG = "请求成功";

  public static final Integer ERROR_CODE = 10000;
  public static final String ERROR_MSG = "请求失败";

  public static final String MSG = "errormsg";
  public static final Integer MSG_CODE = 13000;


  public static final Result TOKENERROR = new Result(11000, "token校验失败");
  public static final Result SUCCESS = new Result(12000, "请求成功");
  public static final Result SEND_SUCCESS = new Result(12000, "短信验证码发送成功");
  public static final Result CANCEL_FOLLOW = new Result(12001, "取关成功");
  public static final Result SUCCESS_FOLLOW = new Result(12002, "关注成功");
  public static final Result FAIL = new Result(13000, "请求失败");
  public static final Result VALIDERROR = new Result(13000, "参数异常");
  public static final Result PHONE_NO_REGISTER = new Result(13001, "该手机号尚未注册");
  public static final Result PHONE_EXISTS = new Result(13002, "手机已注册");
  public static final Result ACCOUNT_NOTBIND_PHONE = new Result(13003, "账号未绑定手机号");
  public static final Result SEND_ERROR = new Result(17000, "短信验证码发送失败");
  public static final Result PWD_NOTMATCH = new Result(18000, "密码不一致");
  public static final Result PWD_WRONG = new Result(19000, "账号或密码错误");
  public static final Result UPDATE_ERROR = new Result(20000, "参数更新失败");
  public static final Result LIST_NULL = new Result(21000, "查询结果为空");
  public static final Result VALIDERROR_NUll = new Result(22000, "必填参数不能为空");
  public static final Result ADD_FAIL = new Result(23000, "新增失败");
  public static final Result REPEAT_SIGN = new Result(24000, "重复签到");
  public static final Result REPEAT_REQ = new Result(25000, "重复请求");
  public static final Result PHONE_NO_BIND = new Result(26000, "手机号未注册或绑定");
  public static final Result LENGTH_MORE = new Result(27000, "标签不能超过三个！");
  public static final Result SET_PAYPWD = new Result(53000, "请设置支付密码");


  public Result() {

  }


  public Result(Integer code, String msg) {
    super();
    this.code = code;
    this.msg = msg;
  }

  public Result(ResultEnum response) {
    this.code = response.getCode();
    this.msg = response.getMsg();
  }


  public Result(ResultEnum response, Object data) {
    this(response);
    this.data = data;
  }


  public Integer getCode() {
    return code;
  }


  public void setCode(Integer code) {
    this.code = code;
  }


  public String getMsg() {
    return msg;
  }


  public void setMsg(String msg) {
    this.msg = msg;
  }


  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public static final Result CAPTCHAFAIL = new Result(14000, "请重新输入");
  public static final Result DELETE_FAIL = new Result(15002, "删除失败");
  public static final Result UPDATE_FAIL = new Result(15003, "更新失败");
  public static final Result INSERT_FAIL = new Result(15001, "插入失败");
  public static final Result PHONE_NOEXISTS = new Result(18001, "手机未注册");
  public static final Result PWD_TYPEWRONG = new Result(19000, "密码格式错误");
  public static final Result NOT_ALLOW_NULL = new Result(20000, "参数存在空值");
  public static final Result DOUBLEDRAW_ERROR = new Result(21000, "重复领取");
  public static final Result Wrong_Paypwd = new Result(23000, "支付密码错误");
  public static final Result Transfer_Validate = new Result(24000, "金额异常，请联系客服");
  public static final Result Amount_Notenough = new Result(25000, "余额不足");
  public static final Result Null_Value = new Result(26000, "返回值为空");

}
