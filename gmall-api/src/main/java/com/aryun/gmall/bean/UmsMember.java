package com.aryun.gmall.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员表
 * @since 2019/11/10
 * @author wr
 */
@Slf4j
@Data
public class UmsMember extends Model<UmsMember> implements Serializable {
    @TableId
    private String id;
    //
    private String memberLevelId;
    //用户名
    private String username;
    //密码
    private String password;
    //昵称
    private String nickname;
    //手机号码
    private String phone;
    //帐号启用状态:0->禁用；1->启用
    private int status;
    //注册时间
    private Date createTime;
    //头像
    private String icon;
    //性别：0->未知；1->男；2->女
    private int gender;
    //生日
    private Date birthday;
    //所做城市
    private String city;
    //职业
    private String job;
    //个性签名
    private String personalizedSignature;
    //用户来源
    private int sourceType;
    //积分
    private int integration;
    //成长值
    private int growth;
    //剩余抽奖次数
    private int luckeyCount;
    //历史积分数量
    private int historyIntegration;
    //排除表字段的3种方式
    //1.transient阻止序列化
    private  transient String userM;
    //2.静态的userM
    private static String userM2;

    public static String getUserM2() {
        return userM2;
    }
    public static void setUserM2(String userM2) {
        UmsMember.userM2 = userM2;
    }
    //3.注解,表示不是数据库表的字段
    @TableField(exist = false)
    private String userM3;


}
