package com.bing.utils.utilstool;

import com.bing.entity.question.Scoreinfo;
import java.util.Comparator;

/**
 * Created by hurl on 2018-08-26
 * 先按分数  再按类型排序
 * 认知<味道<态度<行动<克制
 */
public class MyComparator implements Comparator<Object> {
    @Override
    public int compare(Object o1, Object o2) {
        Scoreinfo com1 = (Scoreinfo) o1;
        Scoreinfo com2 = (Scoreinfo) o2;
        if (com2.getScore()>com1.getScore()) { //如果分数不一样
            return 1;
        }else if(com1.getScore()==com2.getScore()){
            if(com1.getLevel()>(com2.getLevel())){
                return -1;
            }
        }else {
            return -1;
        }
        return 0;
    }
}
