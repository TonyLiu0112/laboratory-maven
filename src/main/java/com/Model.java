package com;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Model {

    /**店铺名称*/
    @JSONField(name="store_name")
    private String shopName;

    /**店铺编号*/
    @JSONField(name= "store_code")
    private String shopCode;
    @JSONField(name= "stat_time")
    private String statTime;
    @JSONField(name= "flow_all")
    private Integer humanFlow;
    @JSONField(name= "flow_around")
    private Integer trafficOutOfDoor;
    @JSONField(name= "flow_in")
    private Integer inStoreFlow;
    @JSONField(name= "customer_new")
    private Integer newCustomers;
    @JSONField(name= "customer_old")
    private Integer oldCustomers;
    @JSONField(name= "remain_duration_avg")
    private Integer remainDurationAvg;
    @JSONField(name= "flow_in_rate")
    private BigDecimal inStoreRate;
    @JSONField(name= "flow_in_depth_rate")
    private BigDecimal depthInterviewsRate;
    @JSONField(name= "bounce_rate")
    private BigDecimal bounceRate;
    @JSONField(name= "end_time")
    private String endTime;

}
