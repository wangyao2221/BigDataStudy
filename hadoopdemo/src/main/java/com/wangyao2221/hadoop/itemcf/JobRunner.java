package com.wangyao2221.hadoop.itemcf;

import com.wangyao2221.hadoop.itemcf.step1.Step1;
import com.wangyao2221.hadoop.itemcf.step2.Step2;
import com.wangyao2221.hadoop.itemcf.step3.Step3;
import com.wangyao2221.hadoop.itemcf.step4.Step4;
import com.wangyao2221.hadoop.itemcf.step5.Step5;

public class JobRunner {
    public static void main(String[] args) {
        int status1 = -1;
        int status2 = -1;
        int status3 = -1;
        int status4 = -1;
        int status5 = -1;

        status1 = new Step1().run();
        if (status1 == 1){
            System.out.println("step1 run success!to run step2");
            status2 = new Step2().run();
        }else {
            System.out.println("step1 run fail!");
        }

        if (status2 == 1){
            System.out.println("step2 run success!to run step3");
            status3 = new Step3().run();
        }else {
            System.out.println("step2 run fail!");
        }

        if (status3 == 1){
            System.out.println("step3 run success!to run step4");
            status4 = new Step4().run();
        }else {
            System.out.println("step3 run fail!");
        }

        if (status4 == 1){
            System.out.println("step4 run success!to run step5");
            status5 = new Step5().run();
        }else {
            System.out.println("step4 run fail!");
        }

        if (status5 == 1){
            System.out.println("step5 run success!");
        }else {
            System.out.println("step5 run fail!");
        }
    }
}
