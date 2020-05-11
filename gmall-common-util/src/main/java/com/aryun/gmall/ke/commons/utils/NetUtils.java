package com.aryun.gmall.ke.commons.utils;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;

/**
 * @author zhiqiang zhao
 * 2018-12-07 上午 10:45
 **/
@Slf4j
public class NetUtils {

  public static boolean isTimerIp(String timerIp) {
    try {
      InetAddress addr = InetAddress.getLocalHost();
      return timerIp.equals(addr.getHostAddress());
    } catch (Exception e) {
      log.error("is timer ip error {}", e);
      return false;
    }
  }
}
