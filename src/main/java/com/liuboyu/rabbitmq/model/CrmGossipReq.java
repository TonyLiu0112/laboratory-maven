package com.liuboyu.rabbitmq.model;

public class CrmGossipReq {

    /**
     * 1|禁言;2|取消禁言
     */
    private Integer type;

    /**
     * 被禁言的客户id
     * 客户基金账号或者手机账号
     */
    private String customer_id;

    /**
     * 0|注册用户 -> 手机号
     * 1|资金账户客户 -> 基金账号
     * 2|理财用户 -> 暂无
     */
    private Integer customer_type;

    /**
     * 1|投顾
     * 2|管理员
     */
    private Integer operator_type;

    /**
     * 投顾id 或管理员编号，都是员工号
     */
    private Long operator_id;

    /**
     * yyyyMMdd
     */
    private String start_time;

    /**
     * yyyyMMdd
     */
    private String end_time;

    /**
     * yyyyMMdd
     */
    private String operate_time;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public Integer getCustomer_type() {
        return customer_type;
    }

    public void setCustomer_type(Integer customer_type) {
        this.customer_type = customer_type;
    }

    public Integer getOperator_type() {
        return operator_type;
    }

    public void setOperator_type(Integer operator_type) {
        this.operator_type = operator_type;
    }

    public Long getOperator_id() {
        return operator_id;
    }

    public void setOperator_id(Long operator_id) {
        this.operator_id = operator_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getOperate_time() {
        return operate_time;
    }

    public void setOperate_time(String operate_time) {
        this.operate_time = operate_time;
    }
}
