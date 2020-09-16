package com.aryun.gmall.ke.commons.utils;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
//import com.ke.newhouse.activity.commons.enums.WeekConvert;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * java8时间工具类
 *
 * @author zhiqiang zhao
 * 2018-11-05 11:43
 **/
public class DateUtil {

  public static final String DATE_FMT = "yyyy-MM-dd HH:mm:ss";
  public static final String DATE_FMT_1 = "yyyy-MM-dd";
  public static final String DATE_FMT_2 = "yyyyMMdd";
  public static final String TIME_FMT = "yyyy年MM月dd日HH点";
  public static final String SPLIT_SPACE = " ";

  public static void main(String[] args) {
//    String s = DateUtil.dateToString(new Date(), DateUtil.TIME_FMT);
//    System.out.println(s);
//    LocalDate startLocalDate = DateUtil.getNowLocalDateTime()
//        .minusDays(Constants.THRESHOLD_COUNT + 1).toLocalDate();
//
//    List<String> dateSeries = DateUtil.getDateSeries(startLocalDate, Constants.THRESHOLD_COUNT);
//    System.out.println(dateSeries);

    System.out
        .println(DateUtil.stringToDate("2019-01-12 12", "yyyy-MM-dd HH"));

//    LocalDateTime localDateTime = DateUtil
//        .stringToLocalDateTime("2019-01-02", "yyyy-MM-dd");
//    System.out.println("查询得：" + DateUtil
//        .localDateTimeToString(splitDatetimeWithThirtyMinute(localDateTime),
//            "yyyy-MM-dd HH:mm:ss"));
//    System.out.println("生成得：" + DateUtil
//        .localDateTimeToString(splitDatetimeWithMinuteReturnFive(localDateTime),
//            "yyyy-MM-dd HH:mm:ss"));

//    System.out
//        .println(isBeforeNowNMinute(dateToLong(localDateTimeConvertToDate(localDateTime, true))));
//    System.out.println(isValidDate(1541054819000L));
//    ;
//    Preconditions
//        .checkArgument(DateUtil.isValidDate(1546186102000L, 2),
//            "数据错误，请求时间距离服务器时间超过2天,请求时间是：{} ",
//            1542511764000L);
//    System.out.println(DateUtil.isBeforeNowDate(1543766421059L));
//    System.out.println(DateUtil.isBeforeNowDate(1544544113000L));
//    LocalDate nowLocalDate = DateUtil.getNowLocalDate();
//    //req_base_info基础表保存9 * 24 * 3600  Constants.REQ_BASE_INFO_BASE_COLLECTION_NAME
//    //statistics_base_info_half_day半天聚合表保存31 * 24 * 3600L  Constants.STATISTICS_BASE_INFO_BASE_COLLECTION_NAME + "_" + Constants.HALF_DAY
//    //statistics_base_info_hour小时聚合表保存9 * 24 * 3600L  Constants.STATISTICS_BASE_INFO_BASE_COLLECTION_NAME + "_" + Constants.HOUR
//    Date endDate = DateUtil
//        .localDate2Date(DateUtil.localDateMinusDay(nowLocalDate, 9L), true);
//    Date startDate = DateUtil
//        .localDate2Date(DateUtil.localDateMinusDay(nowLocalDate, 31L), true);
//
//    Date hourStartDate = DateUtil
//        .localDate2Date(DateUtil.localDateMinusDay(nowLocalDate, 60L), true);
//    List<String> reqBaseInfoCollectionNames = MongoCollectionUtil
//        .getCollectionNames(Constants.REQ_BASE_INFO_BASE_COLLECTION_NAME, startDate, endDate);
//
//    List<String> statisticsBaseInfoHourCollectionNames = MongoCollectionUtil
//        .getCollectionNames(
//            Constants.STATISTICS_BASE_INFO_BASE_COLLECTION_NAME + "_" + Constants.HOUR,
//            startDate, endDate);
//
//    List<String> statisticsBaseInfoHalfDayCollectionNames = MongoCollectionUtil
//        .getCollectionNames(
//            Constants.STATISTICS_BASE_INFO_BASE_COLLECTION_NAME + "_" + Constants.HALF_DAY,
//            hourStartDate, startDate);
//    System.out.println(reqBaseInfoCollectionNames);
//    System.out.println(statisticsBaseInfoHourCollectionNames);
//    System.out.println(statisticsBaseInfoHalfDayCollectionNames);
//    reqBaseInfoCollectionNames.addAll(statisticsBaseInfoHourCollectionNames);
//    reqBaseInfoCollectionNames.addAll(statisticsBaseInfoHalfDayCollectionNames);
//    System.out.println(reqBaseInfoCollectionNames);

  }

  /**
   * 判断时间是否是有效的，即距离今天是否在days天之内
   *
   * @param timeStamp
   * @param days
   * @return true:在days天之内
   * false:不在days天之内
   */
  public static boolean isValidDate(Long timeStamp, Integer days) {
    Date requestDate = new Date(timeStamp);
    LocalDateTime localDateTime = dateConvertToLocalDateTime(requestDate, true);
    long distance = ChronoUnit.DAYS.between(localDateTime, DateUtil.getNowLocalDateTime());
    return distance < days;

  }

  /**
   * 判断当前日期是否在服务器日期之前
   * 在服务器时间前，返回true
   *
   * @param timeStamp
   * @return
   */
  public static boolean isBeforeNowDate(Long timeStamp) {
    //进行绝对匹配时间
    return timeStamp < System.currentTimeMillis();
//    Date requestDate = new Date(timeStamp);
//    LocalDate localDate = date2LocalDate(requestDate, true);
//    LocalDate nowLocalDate = DateUtil.getNowLocalDate();
//    return localDate.isBefore(nowLocalDate) || localDate.isEqual(nowLocalDate);
  }

  public static boolean isBeforeNowNMinute(Long timeStamp) {
    if (Objects.isNull(timeStamp)) {
      return false;
    }
    LocalDateTime localDateTime = dateConvertToLocalDateTime(new Date(timeStamp), true);
    LocalDateTime nowLocalDateTime = getNowLocalDateTime();
    return nowLocalDateTime.minusMinutes(1L).isAfter(localDateTime);
  }

  /**
   * 得到两个日期间隔的所有日期
   *
   * @param startLocalDateTime 格式“2018-11-08 14:00:01”
   * @param endLocalDateTime   格式“2018-11-08 14:00:01”
   * @return
   */
  /**
   * 返回startlocalDate开始后的日期，截止日期是startlocalDate+distance
   *
   * @param startlocalDate
   * @param distance
   * @return
   */
  public static List<String> getDateSeries(LocalDate startlocalDate,
      long distance) {
    List<String> list = Lists.newArrayList();
    Stream.iterate(startlocalDate, d -> {
      return d.plusDays(1);
    }).limit(distance + 1).forEach(f -> {
      list.add(f.format(getDateFormat("yyyyMMdd")));
    });
    return list;

  }

  /**
   * 将时间转换为大数据Pt格式，形如20190805000000
   *
   * @param localDate
   * @return
   */
  public static Long dateToPtLongFormat(LocalDate localDate) {
    //20190805000000
    String yyyyMMdd = DateUtil.localDateToString(localDate, DATE_FMT_2);
    return NumberUtils.toLong(yyyyMMdd + "000000");
  }

  /**
   * 得到两个日期间隔的所有日期
   *
   * @param startDate
   * @param endDate
   * @return
   */
  public static List<String> getBetweenDateSeries(Date startDate, Date endDate) {
    LocalDateTime startLocalDateTime = dateConvertToLocalDateTime(startDate, true);
    LocalDateTime endLocalDateTime = dateConvertToLocalDateTime(endDate, true);
    Preconditions.checkArgument(
        startLocalDateTime.isBefore(endLocalDateTime) || startLocalDateTime
            .isEqual(endLocalDateTime),
        "开始时间不能晚于结束时间");
    LocalDate startlocalDate = startLocalDateTime.toLocalDate();
    LocalDate endLocalDate = endLocalDateTime.toLocalDate();
    List<String> list = Lists.newArrayList();
    long distance = ChronoUnit.DAYS.between(startlocalDate, endLocalDate);
    if (distance < 1) {
      //证明同一天，返回开始日期
      list.add(localDateTimeToString(startLocalDateTime, "yyyyMMdd"));
      return list;
    }
    Stream.iterate(startlocalDate, d -> {
      return d.plusDays(1);
    }).limit(distance + 1).forEach(f -> {
      list.add(f.toString());
    });
    return list;

  }

  /**
   * 得到线程安全的TimeFormatter
   *
   * @param pattern 时间格式如yyyy-MM-dd HH:mm:ss
   * @return
   */
  private static DateTimeFormatter getDateFormat(String pattern) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
    return formatter;
  }


  /**
   * date转化为格式化时间
   *
   * @param date
   * @param pattern
   * @return
   */
  public static String dateToString(Date date, String pattern) {
    return localDateTimeToString(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()),
        pattern);
  }


  /**
   * date转化为时间戳
   *
   * @param date
   * @return
   */
  public static Long dateToLong(Date date) {
    LocalDateTime localDateTime = dateConvertToLocalDateTime(date, true);
    long timeStamp = LocalDateTime.from(localDateTime).atZone(ZoneId.systemDefault()).toInstant()
        .toEpochMilli();
    return timeStamp;
  }

  /**
   * 格式化字符串转为Date
   *
   * @param date    格式化时间
   * @param pattern 格式
   * @return
   */
  public static Date stringToDate(String date, String pattern) {
    //判断pattern模式
    if (StringUtils.containsNone(pattern, "HHmmss")) {
      //不包含时分秒
      return localDate2Date(stringToLocalDate(date, pattern), true);
    } else {
      //包含时分秒的其中一个或者都包含
      return Date
          .from(stringToLocalDateTime(date, pattern).atZone(ZoneId.systemDefault()).toInstant());
    }


  }

  public static Date stringToDate2(String date, String pattern) {
    SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
    try {
      return dateFormat.parse(date);
    } catch (ParseException e) {
      throw new IllegalArgumentException("日期格式不正确");
    }
  }

  /**
   * 格式化字符串转为LocalDateTime
   *
   * @param date    格式化时间
   * @param pattern 格式
   * @return
   */
  public static LocalDateTime stringToLocalDateTime(String date, String pattern) {
    DateTimeFormatter formatter = getDateFormat(pattern);
    return LocalDateTime.parse(date, formatter);
  }

  public static LocalDate stringToLocalDate(String date, String pattern) {
    DateTimeFormatter formatter = getDateFormat(pattern);
    return LocalDate.parse(date, formatter);
  }

  /**
   * localDateTime转换为格式化时间
   *
   * @param localDateTime localDateTime
   * @param pattern       格式
   * @return
   */
  public static String localDateTimeToString(LocalDateTime localDateTime, String pattern) {
    DateTimeFormatter formatter = getDateFormat(pattern);
    return localDateTime.format(formatter);
  }

  /**
   * localDateTime转换为格式化时间
   *
   * @param localDate localDate
   * @param pattern   格式
   * @return
   */
  public static String localDateToString(LocalDate localDate, String pattern) {
    DateTimeFormatter formatter = getDateFormat(pattern);
    return localDate.format(formatter);
  }

  /**
   * string转化为LocalDateTime
   *
   * @param datetime
   * @param formatter
   * @return
   */
  public static LocalDateTime stringToLocalDateTimeByFormat(String datetime,
      DateTimeFormatter formatter) {
    LocalDateTime localDateTime = LocalDateTime
        .parse(datetime, formatter);
    return localDateTime;
  }

  /**
   * localDateTime转化为string
   *
   * @param localDateTime
   * @param pattern       如"yyyy-MM-dd HH:mm:ss"
   * @return
   */
  public static String localDateTimeToStringByFormat(LocalDateTime localDateTime, String pattern) {
    return getDateFormat(pattern).format(localDateTime);
  }

  /**
   * localDate 转化为string
   *
   * @param localDate
   * @param pattern   如"yyyy-MM-dd"
   * @return
   */
  public static String localDateToStringByFormat(LocalDate localDate, String pattern) {
    return getDateFormat(pattern).format(localDate);
  }

  public static long getTimestampOfDateTime(LocalDateTime localDateTime) {
    ZoneId zone = ZoneId.systemDefault();
    Instant instant = localDateTime.atZone(zone).toInstant();
    return instant.toEpochMilli();
  }

  public static Long getNowTimeStamp() {
    return Instant.now().toEpochMilli();
  }

  public static Date getNowDate() {
    return localDateTimeConvertToDate(LocalDateTime.now(), true);
  }

  public static String getNowDateString() {
    return DateUtil.localDateTimeToString(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss");
  }

  /**
   * 当前日期和时间
   *
   * @return
   */
  public static LocalDateTime getNowLocalDateTime() {
    return LocalDateTime.now();
  }

  /**
   * 当前日期
   *
   * @return
   */
  public static LocalDate getNowLocalDate() {
    return LocalDate.now();
  }

  /**
   * 当前日期
   *
   * @return
   */
  public static LocalDate localDateMinusDay(LocalDate localDate, Long day) {
    return localDate.minusDays(day);
  }

  /**
   * 将string转化为LocalDateTime格式
   *
   * @param datetime 传入待转化的"yyyy-MM-dd HH:mm:ss"格式的时间串，例如2018-11-05 12:00:01
   * @return
   */
  public static LocalDateTime stringToLocalDateTime(String datetime) {
    LocalDateTime localDateTime = stringToLocalDateTimeByFormat(datetime,
        getDateFormat("yyyy-MM-dd HH:mm:ss"));
    return localDateTime;
  }


  /**
   * 将timestamp转化为LocalDateTime，以半天时间分割
   * 转化规则:
   * 时间>12，将时间设置为12:00:00
   * 时间<12，将时间设置为00:00:00
   *
   * @param timestamp
   * @return
   */
  public static Date splitDatetimeWithHalfDay(Long timestamp) {
    Date date = new Date(timestamp);
    LocalDateTime localDateTime = dateConvertToLocalDateTime(date, true);
    return dateAbandonMinuteAndSecondAndProcessHour(localDateTime, true);
  }

  /**
   * 将timestamp转化为LocalDateTime，以天时间分割
   * 转化规则:
   * 舍弃时分秒
   *
   * @param timestamp
   * @return
   */
  public static Date splitDatetimeWithDay(Long timestamp) {
    Date date = new Date(timestamp);
    LocalDateTime localDateTime = dateConvertToLocalDateTime(date, true);
    return dateAbandonHourAndMinuteAndSecond(localDateTime, true);
  }

  public static LocalDateTime splitDatetimeWithThirtyMinute(LocalDateTime localDateTime) {
    //当前分钟
    int minute = localDateTime.getMinute();
    int nowMinute = minute - minute % 30;
    return LocalDateTime
        .of(localDateTime.toLocalDate(), LocalTime.of(localDateTime.getHour(), nowMinute, 0));
  }

  /**
   * 每隔5分钟分割时间
   *
   * @param localDateTime
   * @return
   */
  public static LocalDateTime splitDatetimeWithFiveMinute(LocalDateTime localDateTime) {
    //当前分钟
    int minute = localDateTime.getMinute();
    int nowMinute = minute - minute % 5;
    return LocalDateTime
        .of(localDateTime.toLocalDate(), LocalTime.of(localDateTime.getHour(), nowMinute, 0));
  }

  /**
   * 以天时间分割
   * 转化规则:
   * 舍弃时分秒
   *
   * @param localDateTime
   * @return
   */
  public static LocalDateTime splitDatetimeWithDay(LocalDateTime localDateTime) {
    return dateAbandonHourAndMinuteAndSecond(localDateTime);
  }

  /**
   * 将localDateTime以天时间分割
   * 转化规则:
   * 舍弃时分秒
   *
   * @param localDateTime
   * @param defaultZone
   * @return
   */
  public static LocalDateTime splitLocalDateTimeWithDay(LocalDateTime localDateTime,
      boolean defaultZone) {
    String s = localDateTime.format(getDateFormat("yyyy-MM-dd 00:00:00"));
    return stringToLocalDateTime(s);
  }


  /**
   * 将timestamp转化为LocalDateTime，以半天时间分割(UTC时区)
   * 转化规则:
   * 时间>12，将时间设置为12:00:00
   * 时间<12，将时间设置为00:00:00
   *
   * @param timestamp
   * @return
   */
  public static Date splitDatetimeWithHalfDayUTC(Long timestamp) {
    Date date = new Date(timestamp);
    LocalDateTime localDateTime = dateConvertToLocalDateTime(date, false);
    return dateAbandonMinuteAndSecondAndProcessHour(localDateTime, false);
  }

  /**
   * 将timestamp转化为LocalDateTime，以小时分割
   * 转化规则:
   * 舍弃分钟和秒
   *
   * @param timestamp
   * @return
   */
  public static Date splitDatetimeWithHour(Long timestamp) {
    Date date = new Date(timestamp);
    LocalDateTime localDateTime = dateConvertToLocalDateTime(date, true);
    return dateAbandonMinuteAndSecond(localDateTime, true);
  }

  public static boolean isTodayDate(Date timedate) {
    LocalDate localDate = date2LocalDate(timedate, true);
    return localDate.isEqual(getNowLocalDate());
  }

  /**
   * 将date转化为LocalDateTime，以小时分割
   * 转化规则:
   * 舍弃分钟和秒
   *
   * @param date
   * @return
   */
  public static Date splitDatetimeWithHour(Date date) {
    LocalDateTime localDateTime = dateConvertToLocalDateTime(date, true);
    return dateAbandonMinuteAndSecond(localDateTime, true);
  }

  /**
   * 以小时分割
   * 转化规则:
   * 舍弃分钟和秒
   *
   * @param localDateTime
   * @return
   */
  public static LocalDateTime splitDatetimeWithHour(LocalDateTime localDateTime) {
    return dateAbandonMinuteAndSecond(localDateTime);
  }

  /**
   * 将timestamp转化为LocalDateTime，以小时分割(UTC时区)
   * 转化规则:
   * 舍弃分钟和秒
   *
   * @param timestamp
   * @return
   */
  public static Date splitDatetimeWithHourUTC(Long timestamp) {
    Date date = new Date(timestamp);
    LocalDateTime localDateTime = dateConvertToLocalDateTime(date, false);
    return dateAbandonMinuteAndSecond(localDateTime, false);
  }

  /**
   * 将timestamp转化为LocalDateTime，以分钟分割
   * 转化规则:
   * 舍弃秒
   *
   * @param timestamp
   * @return
   */
  public static Date splitDatetimeWithMinute(Long timestamp) {
    Date date = new Date(timestamp);
    LocalDateTime localDateTime = dateConvertToLocalDateTime(date, true);
    return dateAbandonSecond(localDateTime, true);
  }

  /**
   * 将date转化为LocalDateTime，以分钟分割
   * 转化规则:
   * 舍弃秒
   *
   * @param date
   * @return
   */
  public static Date splitDatetimeWithMinute(Date date) {
    LocalDateTime localDateTime = dateConvertToLocalDateTime(date, true);
    return dateAbandonSecond(localDateTime, true);
  }

  /**
   * 以分钟分割
   * 转化规则:
   * 舍弃秒,同时转化为5分钟的数据
   *
   * @param localDateTime
   * @return
   */
  public static LocalDateTime splitDatetimeWithMinuteReturnFive(LocalDateTime localDateTime) {
    //当前分钟
    int minute = localDateTime.getMinute();
    int nowMinute = minute + 5 - minute % 5;
    int reduceValue = nowMinute - minute;
    LocalDateTime fixLocalDateTime = localDateTime.plusMinutes(reduceValue);
    return LocalDateTime
        .of(fixLocalDateTime.toLocalDate(),
            LocalTime.of(fixLocalDateTime.getHour(), fixLocalDateTime.getMinute(), 0));
  }

  /**
   * 以分钟分割
   * 转化规则:
   * 舍弃秒
   *
   * @param localDateTime
   * @return
   */
  public static LocalDateTime splitDatetimeWithMinute(LocalDateTime localDateTime) {
    return dateAbandonSecond(localDateTime);
  }

  /**
   * 将timestamp转化为LocalDateTime，以分钟分割(UTC时区)
   * 转化规则:
   * 舍弃秒
   *
   * @param timestamp
   * @return
   */
  public static Date splitDatetimeWithMinuteUTC(Long timestamp) {
    Date date = new Date(timestamp);
    LocalDateTime localDateTime = dateConvertToLocalDateTime(date, false);
    return dateAbandonSecond(localDateTime, false);
  }


  private static Date dateAbandonMinuteAndSecondAndProcessHour(LocalDateTime localDateTime,
      boolean defaultZone) {
    LocalDateTime baseLocalDate = LocalDateTime
        .of(localDateTime.toLocalDate(), LocalTime.of(12, 0, 0));
    LocalDateTime result;
    if (localDateTime.isBefore(baseLocalDate)) {
      result = LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.of(0, 0, 0));
    } else {
      result = LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.of(12, 0, 0));
    }
    Date date = localDateTimeConvertToDate(result, defaultZone);
    return date;
  }

  private static Date dateAbandonHourAndMinuteAndSecond(LocalDateTime localDateTime,
      boolean defaultZone) {
    String s = localDateTime.format(getDateFormat("yyyy-MM-dd 00:00:00"));
    return localDateTimeConvertToDate(stringToLocalDateTime(s), defaultZone);
  }

  private static LocalDateTime dateAbandonHourAndMinuteAndSecond(LocalDateTime localDateTime) {
    String s = localDateTime.format(getDateFormat("yyyy-MM-dd 00:00:00"));
    return stringToLocalDateTime(s);
  }

  private static Date dateAbandonMinuteAndSecond(LocalDateTime localDateTime, boolean defaultZone) {
    String s = localDateTime.format(getDateFormat("yyyy-MM-dd HH:00:00"));
    return localDateTimeConvertToDate(stringToLocalDateTime(s), defaultZone);
  }

  private static LocalDateTime dateAbandonMinuteAndSecond(
      LocalDateTime localDateTime) {
    String s = localDateTime.format(getDateFormat("yyyy-MM-dd HH:00:00"));
    return stringToLocalDateTime(s);
  }


  private static Date dateAbandonSecond(LocalDateTime localDateTime, boolean defaultZone) {
    String s = localDateTime.format(getDateFormat("yyyy-MM-dd HH:mm:00"));
    return localDateTimeConvertToDate(stringToLocalDateTime(s), defaultZone);
  }

  private static LocalDateTime dateAbandonSecond(LocalDateTime localDateTime) {
    String s = localDateTime.format(getDateFormat("yyyy-MM-dd HH:mm:00"));
    return stringToLocalDateTime(s);
  }

  /**
   * 将java.util.Date 转换为java8的java.time.LocalDateTime
   *
   * @param date
   * @return
   */
  /**
   * @param date
   * @param defaultZone 是否为默认时区，否，则为UTC时区
   * @return
   */
  public static LocalDateTime dateConvertToLocalDateTime(Date date, boolean defaultZone) {
    return defaultZone ? LocalDateTime
        .ofInstant(date.toInstant(), ZoneId.systemDefault()) : LocalDateTime
        .ofInstant(date.toInstant(), getUTCTimeZone());
  }

  /**
   * 得到UTC时区
   *
   * @return
   */
  private static ZoneId getUTCTimeZone() {
    return ZoneId.of("UTC");
  }

  /**
   * 将java8 的 java.time.LocalDateTime 转换为 java.util.Date
   *
   * @param localDateTime
   * @return
   */
  public static Date localDateTimeConvertToDate(LocalDateTime localDateTime, boolean defaultZone) {
    return defaultZone ? Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant())
        : Date.from(localDateTime.atZone(getUTCTimeZone()).toInstant());
  }


  /**
   * Date转LocalDate
   *
   * @param date
   * @param defaultZone
   * @return
   */
  public static LocalDate date2LocalDate(Date date, boolean defaultZone) {
    if (null == date) {
      return null;
    }
    return defaultZone ? date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() :
        date.toInstant().atZone(getUTCTimeZone()).toLocalDate();
  }


  /**
   * LocalDate转Date
   *
   * @param localDate
   * @param defaultZone
   * @return
   */
  public static Date localDate2Date(LocalDate localDate, boolean defaultZone) {
    if (null == localDate) {
      return null;
    }
    return defaultZone ? Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()) :
        Date.from(localDate.atStartOfDay(getUTCTimeZone()).toInstant());
  }


  /**
   * 获取后天的终止时间
   *
   * @return Date 默认null
   */
  public static Date getTomorrowEndDate(Date date) {
    LocalDateTime localDateTime = dateConvertToLocalDateTime(date, true);
    LocalDateTime endTime = LocalDateTime
        .of(localDateTime.plusDays(1).toLocalDate(), LocalTime.MAX);
    Date res = localDateTimeConvertToDate(endTime, true);
    return res;
  }

  /**
   * 获取昨天的开始时间
   *
   * @return Date 默认null
   */
  public static Date getYesterdayStartDate(Date date) {
    LocalDateTime localDateTime = dateConvertToLocalDateTime(date, true);
    LocalDateTime endTime = LocalDateTime
            .of(localDateTime.minusDays(1).toLocalDate(), LocalTime.MIN);
    Date res = localDateTimeConvertToDate(endTime, true);
    return res;
  }

  /**
   * 获取昨天的终止时间
   *
   * @return Date 默认null
   */
  public static Date getYesterdayEndDate(Date date) {
    LocalDateTime localDateTime = dateConvertToLocalDateTime(date, true);
    LocalDateTime endTime = LocalDateTime
        .of(localDateTime.minusDays(1).toLocalDate(), LocalTime.MAX);
    Date res = localDateTimeConvertToDate(endTime, true);
    return res;
  }

  /**
   * 获取date的开始时间
   *
   * @return Date 默认null
   */
  public static Date getDateStartTime(Date date) {
    LocalDateTime localDateTime = dateConvertToLocalDateTime(date, true);
    LocalDateTime startTime = LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.MIN);
    Date res = localDateTimeConvertToDate(startTime, true);
    return res;
  }

  /**
   * 获取时间的下周一的终止时间
   *
   * @return Date 默认null
   */
  public static Date getNextWeekMonday(Date date) {
    LocalDateTime localDateTime = dateConvertToLocalDateTime(date, true);
    LocalDateTime endTime = LocalDateTime
        .of(localDateTime.plusWeeks(1).with(DayOfWeek.MONDAY).toLocalDate(), LocalTime.MAX);
    Date res = localDateTimeConvertToDate(endTime, true);
    return res;
  }
}
