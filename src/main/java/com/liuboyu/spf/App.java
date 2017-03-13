package com.liuboyu.spf;

import com.liuboyu.spf.bus.BusProvider;
import com.liuboyu.spf.train.TrainProvider;

/**
 * application run.
 * Created by Tony on 13/03/2017.
 */
public class App {

    public static void main(String[] args) {
        // 注册服务
        ProviderRegistration.registerProvider("bus", new BusProvider());
        ProviderRegistration.registerProvider("train", new TrainProvider());
        // 获得服务
        TravelService busService = ProviderRegistration.newService("bus");
        System.out.println(busService.getVehical());
        TravelService trainService = ProviderRegistration.newService("train");
        System.out.println(trainService.getVehical());
    }

}
