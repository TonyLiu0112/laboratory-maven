package com;
import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;

public class Test {
    public static void main(String[] args) {

        Model model = new Model();
        model.setShopName("1");
        model.setShopCode("2");
        model.setStatTime("3");
        model.setHumanFlow(0);
        model.setTrafficOutOfDoor(0);
        model.setInStoreFlow(0);
        model.setNewCustomers(0);
        model.setOldCustomers(0);
        model.setRemainDurationAvg(0);
        model.setInStoreRate(new BigDecimal("0"));
        model.setDepthInterviewsRate(new BigDecimal("0"));
        model.setBounceRate(new BigDecimal("0"));
        model.setEndTime("1");

        System.out.println(JSON.toJSONString(model, true));


    }
}
