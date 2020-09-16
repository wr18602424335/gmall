package com.aryun.gmall.ke.commons.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Iterator;
import java.util.Map;

/**
 * @author: xiajun003
 * @Date: 2019/8/8 20:16
 * @Description:
 */
@Slf4j
public class ExpressionParserUtils {

    private static final ExpressionParser parser = new SpelExpressionParser();

    public static String getExpressionResult(Map<String,String> expressionMap,Map<String,Object> param){
        try {

            StandardEvaluationContext context = new StandardEvaluationContext();
            context.setVariables(param);

            Iterator<String> iterator = expressionMap.keySet().iterator();
            while (iterator.hasNext()){
                String key = iterator.next();
                String val = expressionMap.get(key);
                if (parser.parseExpression(val).getValue(context, Boolean.class)){
                    return key;
                }
            }

        }catch (Exception e){
            log.error("ExpressionParserUtils getExpressionResult error : ",e);
        }
        return null;
    }
}
