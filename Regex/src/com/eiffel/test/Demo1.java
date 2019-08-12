package com.eiffel.test;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Demo1 {
    /*思路分析：
        (1)、长度为18位
        (2)、前面17位必须为纯数字
        (3)、最后一位可以为X，也可以为数字
	*/
    @Test
    public void test1() throws ParseException {
        String regex1 = "\\d{17}[0-9X]";
        String idCard = "42010620190910235X";
        boolean flag = idCard.matches(regex1);
        System.out.println(flag?"身份证号验证通过":"身份证号有误");

        /*校验出生日期
        * 1.年月日时间不能大于系统时间
        * 2.年份不能太大
        * */

        long sysTime = System.currentTimeMillis();
        Date date = new Date();
        long birthtime = date.getTime();
        String idCardStr = idCard.substring(6, 14);
        System.out.println(idCardStr);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date d = format.parse(idCardStr);
        long time = d.getTime();
        long cha = sysTime - time;

        /*if(flag){
            if(cha < 0){
                System.out.println("格式不准确！");
            }else{
                System.out.println("格式正确");
            }

        }else{
            System.out.println("格式不正确");

        }*/

    }
}
