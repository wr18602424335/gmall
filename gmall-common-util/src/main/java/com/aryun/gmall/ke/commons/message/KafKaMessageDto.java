package com.aryun.gmall.ke.commons.message;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author zhiqiang zhao
 * 2019-06-19 14:41
 **/
@Data
public class KafKaMessageDto {

  /**
   * 业务方提供的消息唯一ID
   */
  @JSONField(ordinal = 0)
  String msgId;
  /**
   * 标识业务方
   */
  @JSONField(ordinal = 1)
  String source;
  /**
   * 时间戳
   */
  @JSONField(ordinal = 2)
  Long time;

  /**
   * 经纪人ID
   */
  @JSONField(ordinal = 3)
  Long proposer;


  /**
   * 业务方备注，json格式
   * 非必填
   */
  @JSONField(ordinal = 4)
  Map<String, String> reason;
  /**
   * 分配的消息token，由红星提供
   */
  @JSONField(ordinal = 5)
  String eventKey;
  /**
   * 消息内容,没有可以不填,json格式
   * 非必填
   */
  @JSONField(ordinal = 6)
  Map<String, String> data;
  /**
   * 非必填,但是rewardInfo是在自定义奖励金额的时候才必须传递
   */
  @JSONField(ordinal = 7)
  List<RewardInfo> rewardInfo;

}
