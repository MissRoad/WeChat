package com.bing.utils.utilstool;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by hurl on 2018-08-19
 */
public class MathRandom {
     /*随机生成数字
     * @param x需要产生数字个数
     * @param n随机范围 0-（n-1）
     *
     * */

    public static int[] MyRandom(int amount,int range) {
        int[] a = new int[amount];
        Random rand = new Random();//新建一个随机类
        boolean[] bool = new boolean[range];
        int randInt = 0;
        for (int i = 0; i < amount; i++) {
            do {
                randInt = rand.nextInt(range);//产生一个随机数
            } while (bool[randInt]);
            bool[randInt] = true;
            System.out.println(randInt);
            a[i] = randInt;
        }
        return a;
    }

    public static double RedRandom(double totalMoney, int peopleNum){
        if(peopleNum==1){
            return totalMoney;
        }else{
            List<Double> array = new ArrayList<>();
            Random r = new Random();
            double sum = 0;
            for (int i = 0; i < peopleNum; i++) {
                array.add(r.nextDouble()*totalMoney + 0.01 * peopleNum * totalMoney);//经过小小的计算，这样使最小的钱尽量接近0.01；num越大，此计算越没有用
                sum += array.get(i);
            }
            for (int i = 0; i < array.size(); i++) {
                array.set(i, array.get(i) / sum*totalMoney);
            }
            Collections.sort(array);
            for (int i = 0; i < array.size(); i++) {//将钱进行分配；
                if(array.get(i)<=0.01){//不足0.01的直接给0.01；
                    //z-=0.01;
                    array.set(i, 0.01);
                }
                else if(i==array.size()-1){
                    //array.set(i, (int)(z*100)*1.0/100);//将剩余的一点money给最后一个人，因为排序，最后一个人最大份，所以最后分配的肯定是正数
                    BigDecimal he =new BigDecimal("0");
                    for(int j=0;j<array.size()-1;j++){
                        BigDecimal b =new BigDecimal(Double.toString(array.get(j)));
                        he=he.add(b);
                    }
                    BigDecimal moneyb =new BigDecimal(Double.toString(totalMoney));
                    array.set(i, moneyb.subtract(he).doubleValue());
                }
                else{
                    array.set(i, (int)(array.get(i)*100)*1.0/100);
                    //z-=array.get(i);
                }
            }
            Collections.shuffle(array);
           /* for(Double a:array){
                System.out.println(a);
            }*/
            //        return array;
            return array.get(0);
        }
    }
}
