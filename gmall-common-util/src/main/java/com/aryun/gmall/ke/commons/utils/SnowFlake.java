package com.aryun.gmall.ke.commons.utils;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;

/**
 * twitter的snowflake算法 -- java实现
 * 0 - 41位时间戳 - 5位数据中心标识 - 5位机器标识 - 12位序列号
 *
 * @author beyond
 */
@Slf4j
public class SnowFlake {

  /**
   * 起始的时间戳
   */
  private static final long START_STMP = 1480166465631L;

  /**
   * 每一部分占用的位数
   */
  //序列号占用的位数
  private final static long SEQUENCE_BIT = 12;
  //机器标识占用的位数
  private final static long MACHINE_BIT = 5;
  //数据中心占用的位数
  private final static long DATACENTER_BIT = 5;

  /**
   * 每一部分的最大值
   */
  private final static long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);
  private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
  private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

  /**
   * 每一部分向左的位移
   */
  private final static long MACHINE_LEFT = SEQUENCE_BIT;
  private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
  private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;
  private static final Long DIVIDE_NUMBER = 32L;
  private static Long localDatacenterId = 0L;
  private static Long localMachineId = 0L;

  static {
    try {
      InetAddress addr = InetAddress.getLocalHost();
      String hostAddress = addr.getHostAddress();
      String[] split = StringUtils.split(hostAddress, ".");
      long preDatacenterId = NumberUtils.toLong(split[2]);
      long preMachineId = NumberUtils.toLong(split[3]);
      long chuShuDatacenter = preDatacenterId / DIVIDE_NUMBER;
      long yuShuDatacenter = preDatacenterId % DIVIDE_NUMBER;
      localDatacenterId =
          NumberUtils.toLong(chuShuDatacenter + "" + yuShuDatacenter) % DIVIDE_NUMBER;
      long chuShuMachineId = preMachineId / DIVIDE_NUMBER;
      long yuShuMachineId = preMachineId % DIVIDE_NUMBER;
      localMachineId = NumberUtils.toLong(chuShuMachineId + "" + yuShuMachineId) % DIVIDE_NUMBER;
    } catch (UnknownHostException e) {
      log.error("错误数据是：{}", e);
    }

  }

  //数据中心
  private long datacenterId;
  //机器标识
  private long machineId;
  //序列号
  private long sequence = 0L;
  //上一次时间戳
  private long lastStmp = -1L;

  public SnowFlake(long datacenterId, long machineId) {
    if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
      throw new IllegalArgumentException(
          "datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
    }
    if (machineId > MAX_MACHINE_NUM || machineId < 0) {
      throw new IllegalArgumentException(
          "machineId can't be greater than MAX_MACHINE_NUM or less than 0");
    }
    this.datacenterId = datacenterId;
    this.machineId = machineId;
  }

  private SnowFlake() {
  }

  public static SnowFlake getInstance() {
    return SingletonClassInstance.instance;
  }

  public static void main(String[] args) {
    System.out.println(localDatacenterId + "===" + localMachineId);
    System.out.println(Long.toBinaryString(281072967911862290L));

    SnowFlake snowFlake = SnowFlake.getInstance();
    HashMap<Long, Long> longLongHashMap = Maps.newHashMap();
    for (int i = 0; i < (1 << 12); i++) {
      long hhh = snowFlake.nextId();
      System.out.println(hhh);
      if (longLongHashMap.containsKey(hhh)) {
        throw new RuntimeException("sdcscdsc");
      }
      longLongHashMap.put(hhh, 1L);
    }


  }

  /**
   * 产生下一个ID
   *
   * @return
   */
  public synchronized long nextId() {
    long currStmp = getNewstmp();
    if (currStmp < lastStmp) {
      throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
    }

    if (currStmp == lastStmp) {
      //相同毫秒内，序列号自增
      sequence = (sequence + 1) & MAX_SEQUENCE;
      //同一毫秒的序列数已经达到最大
      if (sequence == 0L) {
        currStmp = getNextMill();
      }
    } else {
      //不同毫秒内，序列号置为0
      sequence = 0L;
    }

    lastStmp = currStmp;

    return (currStmp - START_STMP) << TIMESTMP_LEFT //时间戳部分
        | datacenterId << DATACENTER_LEFT       //数据中心部分
        | machineId << MACHINE_LEFT             //机器标识部分
        | sequence;                             //序列号部分
  }

  private long getNextMill() {
    long mill = getNewstmp();
    while (mill <= lastStmp) {
      mill = getNewstmp();
    }
    return mill;
  }

  private long getNewstmp() {
    return System.currentTimeMillis();
  }

  private static class SingletonClassInstance {

    private static final SnowFlake instance = new SnowFlake(localDatacenterId, localMachineId);
  }
}
