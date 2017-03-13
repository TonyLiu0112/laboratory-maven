package com.liuboyu.spf.bus;

import com.liuboyu.spf.Provider;
import com.liuboyu.spf.TravelService;

/**
 * Created by Tony on 13/03/2017.
 */
public class BusProvider implements Provider {
    
    @Override
    public TravelService createService() {
        return new BusService();
    }
}
