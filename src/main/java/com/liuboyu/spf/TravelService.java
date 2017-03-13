package com.liuboyu.spf;

/**
 * service interface. 有服务提供者实现
 * 旅行服务（这里的旅行服务就类似jdbc服务）
 * Created by Tony on 13/03/2017.
 */
public interface TravelService {

    /**
     * 获得旅行服务的车辆
     *
     * @return
     */
    String getVehical();

}
