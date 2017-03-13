package com.liuboyu.spf.bus;

import com.liuboyu.spf.TravelService;

/**
 * Created by Tony on 13/03/2017.
 */
public class BusService implements TravelService {
    /**
     * 获得旅行服务的车辆
     *
     * @return
     */
    @Override
    public String getVehical() {
        return "bus";
    }
}
