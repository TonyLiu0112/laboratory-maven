package com.liuboyu.designmodel.java8.strategy;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 压缩数据策略接口(这里没有说的是策略模板方法)
 * <p>
 * Created by Tony on 4/13/16.
 */
public interface CompressionStrategy {

    OutputStream compress(OutputStream data) throws IOException;

}
