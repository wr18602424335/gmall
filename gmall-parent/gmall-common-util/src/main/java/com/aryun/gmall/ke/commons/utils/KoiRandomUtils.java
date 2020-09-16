package com.aryun.gmall.ke.commons.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
//import com.ke.newhouse.activity.commons.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

/**
 * 锦鲤抽取
 * @author wr
 * 2019-06-19 17:52
 **/
@Slf4j
public class KoiRandomUtils {

  public static Set<String> generateKoiAgents(Set<String> correctSet) {
    Set<String> resultAgentIds = Sets.newHashSet();
    if (CollectionUtils.isEmpty(correctSet)) {
      return resultAgentIds;
    }
    ArrayList<String> agentIds = Lists.newArrayList(correctSet);
    log.info("before shuffle is {}", agentIds);
    //先shuffle
    Collections.shuffle(agentIds);
    log.info("after shuffle is {}", agentIds);
    // 作为list的下标
    for (int i = 0; i < 100 && i < agentIds.size(); i++) {
      resultAgentIds.add(agentIds.get(i));
    }
    return resultAgentIds;

  }

  public static void main(String[] args) {
    Set<String> strings = generateKoiAgents(
        Sets.newHashSet("aaaaaaaa", "bbbbbbbb","ccccccccc","ddddddddddddd","汪瑞","陈晓静"));

    System.out.println(strings);
  }
}
