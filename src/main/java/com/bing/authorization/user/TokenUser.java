package com.bing.authorization.user;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class TokenUser implements Serializable {

  private static final long serialVersionUID = 6440162098170421062L;

  private Integer uid;
  private String name;
  private Integer loginType;


  public TokenUser() {
    super();
  }

  public TokenUser(Integer uid, String name, Integer loginType) {
    super();
    this.uid = uid;
    this.name = name;
    this.loginType = loginType;
  }

  public Integer getUid() {
    return uid;
  }

  public void setUid(Integer uid) {
    this.uid = uid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getLoginType() {
    return loginType;
  }

  public void setLoginType(Integer loginType) {
    this.loginType = loginType;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

}
