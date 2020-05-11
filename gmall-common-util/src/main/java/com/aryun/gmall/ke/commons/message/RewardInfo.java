package com.aryun.gmall.ke.commons.message;

//import com.ke.newhouse.activity.commons.enums.RewardInfoEnum;
import lombok.Data;

/**
 * @author zhiqiang zhao
 * 2019-06-19 14:49
 **/
@Data
public class RewardInfo {

  /**
   * BOOTH:贝壳币,RewardInfoEnum的code
   */
 // RewardInfoEnum currencyType;
  /**
   * 发币账户
   */
  String paymentAccount;
  /**
   * 奖励金额，单位分 100分=1贝壳币
   */
  Double amount;
  /**
   * 奖励描述,可以将获得奖励的原因写到这里
   * 非必须
   */
  String desc;

}
