package com.liuboyu.spf.train;

import com.liuboyu.spf.Provider;
import com.liuboyu.spf.TravelService;

/**
 * Created by Tony on 13/03/2017.
 */
public class TrainProvider implements Provider {
    @Override
    public TravelService createService() {
        return new TrainService();
    }

}
