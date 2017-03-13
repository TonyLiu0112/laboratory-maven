package com.liuboyu.spf;

import com.liuboyu.spf.bus.BusProvider;
import com.liuboyu.spf.train.TrainProvider;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务注册api
 * <p>
 * Created by Tony on 13/03/2017.
 */
public class ProviderRegistration {

    private ProviderRegistration() {
    }

    /**
     * 注册的服务列表
     */
    private static final Map<String, Provider> providers = new ConcurrentHashMap<>();

    /**
     * 注册一个服务
     *
     * @param name
     * @param provider
     */
    public static void registerProvider(String name, Provider provider) {
        providers.put(name, provider);
    }

    public static TravelService newService(String name) {
        Provider provider = providers.get(name);
        if (provider == null) {
            throw new IllegalArgumentException("Provider not found");
        }
        return provider.createService();
    }
}
