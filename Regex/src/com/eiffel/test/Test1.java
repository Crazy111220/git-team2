package com.eiffel.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class Test1 {
    @Test
    public void test1(){
        String phone = "15902693094";
        /*
         * 1.长度为11位
         * 2.第一位字符为1
         * 3.第2为3/5/7/8/9
         * */
        //String regex1 = "[1][35789][0-9]{9}";
        /*
        * [0,9]:代表纯数字
        * \d：代表纯数字
        *\w:单词字符，等价于[0-9a-zA-Z_]
        * .:代表任意字符
        * */


        /*
        * 量词：指定某个整体出现的数量
        * X{n}:表示X恰好为n
        * X{n,}:X出现的次数>=n
        * X{n,m}:n<="X出现的次数"<=m
        * */
        String regex1 = "[1][35789]\\d{9}";

        boolean flag = phone.matches(regex1);
        System.out.println(flag ? "格式正确" : "格式错误");

        String regex2 = ".{9}";
        System.out.println("123abc789".matches(regex2));

        String regex3 = "\\d{3}\\.\\d{3}";
        System.out.println("123.789".matches(regex3));

        /*int len = phone.length();
        boolean flag = true;//格式完全正确
        if(len == 11){
            if(phone.startsWith("13") || phone.startsWith("15") || phone.startsWith("17") || phone.startsWith("18") || phone.startsWith("19")){}
            char[] chars = phone.toCharArray();
            for(int i =2; i < chars.length; i++){
                if(!('0' < chars[i] && chars[i] < '9')){
                    flag = false;
                    break;
                }
            }

        }else{
            System.out.println("手机号格式不对");
        }
        if(flag){
            System.out.println("手机号格式完全正确");
        }*/
    }

    /*切割*/
    @Test
    public void test2(){
        String str1 = "张三.李四.王五";
        String[] arr1 = str1.split("\\.");//切割的时候正则符号都添加
        for(String temp : arr1){
            System.out.println(temp);
        }
    }

    /*
    替换：
    */
    @Test
    public void test3(){
       //评论：垂直电商
        String discuss = "联系电话：18803697091，星幻摆台，买一送一";
        String newDiscuss = discuss.replaceAll("1[35789]\\d{9}", "15225869255");
        System.out.println(newDiscuss);

        //替换：去掉叠字
        String str = "aaabbbcdefgggghh哈哈哈哈";
        //规则：多个字符挨在一起，并且字符一样
        //使用（模块）：形成一个组，组有组编号
        String regex = "(.)\\1+";
        String str2 = str.replaceAll(regex, "$1");
        System.out.println(str2);



        }
    /*1.练习：去叠字* */
    @Test
    public void test4(){
        String str = "我...我我...我...喜喜...喜....欢....欢欢....你";
        //1.使用替换：将"."替换成""
        str = str.replaceAll("\\.+", "");
        //2.去叠字
        str = str.replaceAll("(.)\\1+","$1");
        System.out.println(str);
    }

    /* 2.邮箱验证：428308399@qq.com
     * 第一个模块：可以为数字，字母，下划线，长度3-12位
     * 第二个模块：要么为纯数字，要么为纯字母，长度2-6
     * 第三个模块：.com，可以出现的次数为1-3*/
    @Test
    public void test5(){
       String email = "abc123@163.com.cn";

       String regex1 = "\\w{3,12}@[a-zA-z]{2,6}(\\.[a-zA-Z]{2,3}){1,3}";
       String regex2 = "\\w{3,12}@\\d{2,6}(\\.[a-zA-Z]{2,3}){1,3}";
       String regex = "\\w{3,12}@([a-zA-z]{2,6}|\\d{2,6})(\\.[a-zA-Z]{2,3}){1,3}";
       //boolean falg = email.matches(regex1) || email.matches(regex2);
       boolean flag = email.matches(regex);
        System.out.println("flag:" + flag);

    }


    /*
    思路分析：
    * 对id地址归类：A、B、C、D A-D的区间范围在1-255之间
    * 102.254.38.12
    * 11.12.122.25
    * 123.123.45.12
    * */
    @Test
    public void test7(){
        String strs = "102.254.38.12,11.12.122.25,123.123.45.12";
       //1.将每个ip地址中的位数添加两个0
        strs = strs.replaceAll("(\\d{1,3})","00$1");

        //2.将ip字符串中的每个模块变成3个数字
        strs = strs.replaceAll("0+(\\d{3})","$1");
        System.out.println("strs=" + strs);

        //3.将给定的字符串切割成数组
        String[] attr = strs.split("\\,");

        //4.创建一个TreeSet集合，存放字符串
        Set<String> ipSet = new TreeSet<>();
        for(String s :attr){
            ipSet.add(s);
        }
        System.out.println("ipSet=" + ipSet);
        //5.遍历TreeSet集合，将每次取到的字符串首先去除多余的0，然后在添加到List集合中去
        ArrayList<String> ipList = new ArrayList<>();
        for(String ip : ipSet){
            String temp = ip.replaceAll("0*(\\d{1,3})","$1");
            ipList.add(temp);
        }
        //6.遍历ArrayList集合，输出结果
        for(String t : ipList){
            System.out.println("t =" + t);
        }
    }
    @Test
    public void lambdaTest(){
        //jdk8：lambda表达式，default
        //lambda表达式：主要简化集合的遍历操作
        //lambda遍历单列集合：List，Set
        ArrayList<String> aList = new ArrayList<>();
        aList.add("1");
        aList.add("2");
        aList.add("3");
        //语法规则：单列集合名.forEach(temp->{代码块})
        aList.forEach(temp-> System.out.println(temp));
        //遍历双列集合：map.forEach((k,v)->{代码块})
        HashMap<String, Object> aMap = new HashMap<>();
        aMap.put("id",1L);
        aMap.put("uName","李四");
        aMap.put("age",18);
        aMap.forEach((a,b)-> System.out.println(a + ":" + b));

    }


}
