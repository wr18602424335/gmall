package com.aryun.gmall.ke.commons.exception;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 类描述 Created by sun on 2018/3/5.
 *
 * @author anonymous
 */
public class ApiException extends RuntimeException implements Serializable {

  @Getter
  @Setter
  private Integer code;

  private ApiException() {
    super();
  }

  private ApiException(Integer code, String message) {
    super(message);
    this.code = code;
  }

  private ApiException(Integer code, String message, Throwable cause) {
    super(message, cause);
    this.code = code;
  }

  /**
   * CoinException
   */
  public static class CoinException extends ApiException {

    public CoinException() {
      super();
    }

    public CoinException(Integer code, String message) {
      super(code, message);
    }

    public CoinException(Integer code, String message, Throwable cause) {
      super(code, message, cause);
    }
  }

  /**
   * TagException
   */
  public static class TagException extends ApiException {

    public TagException() {
      super();
    }

    public TagException(Integer code, String message) {
      super(code, message);
    }

    public TagException(Integer code, String message, Throwable cause) {
      super(code, message, cause);
    }
  }

  /**
   * AlertException
   */
  public static class AlertException extends ApiException {

    public AlertException() {
      super();
    }

    public AlertException(Integer code, String message) {
      super(code, message);
    }

    public AlertException(Integer code, String message, Throwable cause) {
      super(code, message, cause);
    }
  }

  /**
   * 校验ValidationException
   */
  public static class ValidationException extends ApiException {

    public ValidationException() {
      super();
    }

    public ValidationException(Integer code, String message) {
      super(code, message);
    }

    public ValidationException(Integer code, String message, Throwable cause) {
      super(code, message, cause);
    }
  }

  /**
   * 统计exception
   */
  public static class StatisticsException extends ApiException {

    public StatisticsException() {
      super();
    }

    public StatisticsException(Integer code, String message) {
      super(code, message);
    }

    public StatisticsException(Integer code, String message, Throwable cause) {
      super(code, message, cause);
    }
  }

  /**
   * 系统注册exception
   */
  public static class RegisterException extends ApiException {

    public RegisterException() {
      super();
    }

    public RegisterException(Integer code, String message) {
      super(code, message);
    }

    public RegisterException(Integer code, String message, Throwable cause) {
      super(code, message, cause);
    }
  }

  /**
   * 配置ConfigureException
   */
  public static class ConfigureException extends ApiException {

    public ConfigureException() {
      super();
    }

    public ConfigureException(Integer code, String message) {
      super(code, message);
    }

    public ConfigureException(Integer code, String message, Throwable cause) {
      super(code, message, cause);
    }
  }

  public static class AuthException extends ApiException {

    public AuthException() {
      super();
    }

    public AuthException(Integer code, String message) {
      super(code, message);
    }

    public AuthException(Integer code, String message, Throwable cause) {
      super(code, message, cause);
    }
  }

  public static class MissionException extends ApiException {

    public MissionException() {
      super();
    }

    public MissionException(Integer code, String message) {
      super(code, message);
    }

    public MissionException(Integer code, String message, Throwable cause) {
      super(code, message, cause);
    }
  }


}
