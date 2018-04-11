package com.cdyoue.oddJobs.dto.account;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
/**
 * PersonAccount
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-07T12:12:19.725Z")

public class PersonAccount   {
  @JsonProperty("username")
  private String username = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("password")
  private String password = null;

  public PersonAccount username(String username) {
    this.username = username;
    return this;
  }

   /**
   * 昵称
   * @return username
  **/
  @ApiModelProperty(example = "叮当", value = "昵称")
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public PersonAccount email(String email) {
    this.email = email;
    return this;
  }

   /**
   * 邮箱
   * @return email
  **/
  @ApiModelProperty(example = "admin@gmail.com", value = "邮箱")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public PersonAccount password(String password) {
    this.password = password;
    return this;
  }

   /**
   * 密码（8位及8位以上，数字字母特殊字符组合）
   * @return password
  **/
  @ApiModelProperty(example = "xxxxx", value = "密码（8位及8位以上，数字字母特殊字符组合）")
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PersonAccount personAccount = (PersonAccount) o;
    return Objects.equals(this.username, personAccount.username) &&
        Objects.equals(this.email, personAccount.email) &&
        Objects.equals(this.password, personAccount.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, email, password);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PersonAccount {\n");
    
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

