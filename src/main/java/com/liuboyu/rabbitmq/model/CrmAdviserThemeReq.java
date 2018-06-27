package com.liuboyu.rabbitmq.model;

public class CrmAdviserThemeReq {

    /**
     * 观点编号
     */
    private String viewpoint_id;

    /**
     * 产品编号
     */
    private String product_id;

    /**
     * 标题
     */
    private String title;

    /**
     * 观点类型
     */
    private Integer viewpoint_type;

    /**
     * 投资依据
     */
    private String basis;

    /**
     * 投资依据
     */
    private String source;

    /**
     * 投资依据
     */
    private String abstracts;

    /**
     * 投顾id
     */
    private Long registrant;

    /**
     * 公开内容
     */
    private String open_content;

    /**
     * 私密内容
     */
    private String close_content;

    /**
     * 私密内容风险等级
     */
    private Integer risk_level;

    /**
     * 私密内容是否收费
     */
    private String is_chargeable;

    /**
     * 上传音频
     */
    private String audio_url;

    /**
     * 是否公开音频
     */
    private Integer is_show_audio;

    /**
     * 是否置顶
     */
    private Integer is_top;

    /**
     * 观点发布时间 yyyyMMdd
     */
    private String operate_time;

    public String getViewpoint_id() {
        return viewpoint_id;
    }

    public void setViewpoint_id(String viewpoint_id) {
        this.viewpoint_id = viewpoint_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getViewpoint_type() {
        return viewpoint_type;
    }

    public void setViewpoint_type(Integer viewpoint_type) {
        this.viewpoint_type = viewpoint_type;
    }

    public String getBasis() {
        return basis;
    }

    public void setBasis(String basis) {
        this.basis = basis;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public Long getRegistrant() {
        return registrant;
    }

    public void setRegistrant(Long registrant) {
        this.registrant = registrant;
    }

    public String getOpen_content() {
        return open_content;
    }

    public void setOpen_content(String open_content) {
        this.open_content = open_content;
    }

    public String getClose_content() {
        return close_content;
    }

    public void setClose_content(String close_content) {
        this.close_content = close_content;
    }

    public Integer getRisk_level() {
        return risk_level;
    }

    public void setRisk_level(Integer risk_level) {
        this.risk_level = risk_level;
    }

    public String getIs_chargeable() {
        return is_chargeable;
    }

    public void setIs_chargeable(String is_chargeable) {
        this.is_chargeable = is_chargeable;
    }

    public String getAudio_url() {
        return audio_url;
    }

    public void setAudio_url(String audio_url) {
        this.audio_url = audio_url;
    }

    public Integer getIs_show_audio() {
        return is_show_audio;
    }

    public void setIs_show_audio(Integer is_show_audio) {
        this.is_show_audio = is_show_audio;
    }

    public Integer getIs_top() {
        return is_top;
    }

    public void setIs_top(Integer is_top) {
        this.is_top = is_top;
    }

    public String getOperate_time() {
        return operate_time;
    }

    public void setOperate_time(String operate_time) {
        this.operate_time = operate_time;
    }
}
