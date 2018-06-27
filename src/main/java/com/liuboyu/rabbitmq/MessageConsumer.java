package com.liuboyu.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.liuboyu.rabbitmq.model.*;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

public class MessageConsumer {

    public static void main(String[] args) throws IOException, TimeoutException {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("www.tony666.com");
//        factory.setUsername("admin");
//        factory.setPassword("admin");
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//
//        AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
//        AMQP.BasicProperties basicProperties = builder.headers(new HashMap<String, Object>() {{
//            put("name", "Tony");
//        }}).build();

        // 删除评论测试 advisor_exchange_work_commentdelete
//        String msgBody = buildCommentDeleteMsg();

        // 评论设置测试 advisor_exchange_subscribe_commentset
//        String msgBody = buildCommentSetMsg();

        // 评论禁言 advisor_exchange_subscribe_customerbanspeak
//        String msgBody = buildGossipMsg();

        // 评论回复 advisor_exchange_subscribe_commentreply
//        String msgBody = buildCommentReply();

        // 观点创建 advisor_exchange_subscribe_pointestablish
//        String msgBody = buildTheme();

//        channel.basicPublish("advisor_exchange_subscribe_customerbanspeak", "#", basicProperties, msgBody.getBytes());
//        channel.close();
//        connection.close();


        System.out.println(JSON.toJSONString(new CrmAdviserThemeReq() {{
            setViewpoint_id("91029991");
            setProduct_id("1929101");
            setTitle("浙江温州：皮革厂倒闭了！");
            setViewpoint_type(1);
            setBasis("依据是跑路啦");
            setSource("华尔街财经");
            setAbstracts("皇家马德里主场5比0大胜塞维利亚，纳乔开场3分钟进球，C罗梅开二度");
            setRegistrant(99996415L);
            setOpen_content(
                    "<img src=\"http://tony666.cn/sports/transform/w360h184/20171210/111.png\" alt=\"皇马闪电破门\" data-link=\"\">" +
                            "<p>　　北京时间12月9日23:15(西班牙当地时间16:15)，2017/18赛季西甲第15轮一场焦点战在伯纳乌球场展开争夺，皇家马德里主场5比0大胜塞维利亚，纳乔开场3分钟进球，C罗梅开二度，克罗斯和阿什拉夫锦上添花。皇马落后少赛一场的巴萨5分。</p>" +
                            "<img src=\"http://f.sinaimg.cn/sports/transform/w360h184/20171210/222.png\" alt=\"皇马闪电破门\" data-link=\"\">" +
                            "<p>　　双方近24场联赛交锋无平局，皇马近8个联赛主场对阵塞维利亚取得全胜。双方联赛历史交锋146场，皇马77胜26平43负占据上风，其中主场54胜11平8负。本泽马、阿森西奥、摩德里奇、克罗斯、巴列霍、马塞洛和阿什拉夫轮换出场。</p>" +
                            "<img src=\"http://f.sinaimg.cn/sports/transform/w360h184/20171210/333.png\" alt=\"皇马闪电破门\" data-link=\"\">");

            setClose_content(
                    "<img src=\"http://f.sinaimg.cn/sports/transform/w360h184/20171210/444.png\" alt=\"皇马闪电破门\" data-link=\"\">" +
                            "<p>　　<strong>皇马开场仅3分钟取得领先，阿森西奥传球，卢卡斯禁区弧内弧线球射门被扑出。克亚尔角球混战中冒顶，皮球打在穆里尔头上偏转，纳乔近距离破门。</strong>" +
                            "随后克罗斯传球，C罗禁区右侧左脚抽射被皮萨罗挡出近角。纳瓦斯禁区右侧射门被封堵，皮萨罗角球混战中外围射门偏出。</p>" +
                            "<img src=\"http://f.sinaimg.cn/sports/transform/w360h184/20171210/555.png\" alt=\"皇马闪电破门\" data-link=\"\">" +
                            "<img src=\"http://tony666.cn/sports/transform/w360h184/20171210/666.png\" alt=\"皇马闪电破门\" data-link=\"\">");
            setRisk_level(2);
            setIs_chargeable("1");
            setAudio_url("http://f.sinaimg.cn/sports/transform/w360h184/20171210/cccc.mp3");
            setIs_show_audio(1);
            setIs_top(1);
            setOperate_time("20171210");
        }}, true));

    }

    /**
     * 评论删除
     * advisor_exchange_work_commentdelete
     *
     * @return
     */
    public static String buildCommentDeleteMsg() {
        return JSON.toJSONString(new CrmCommentRemoveReq() {{
            setComment_id("10059-1-10002");
            setAdvisor_id(1L);
            setOperate_time("2017-12-05 18:50:11");
        }});
    }

    /**
     * 评论消息设置
     * advisor_exchange_subscribe_commentset
     *
     * @return
     */
    public static String buildCommentSetMsg() {
        return JSON.toJSONString(new CrmCommentSetReq() {{
            setComment_id("10059-1-10002");
            setAdvisor_id(1L);
            setType(1);
            setOperate_time("2017-12-08 00:00:00");
        }});
    }

    /**
     * 用户禁言
     * advisor_exchange_subscribe_customerbanspeak
     *
     * @return
     */
    public static String buildGossipMsg() {
        return JSON.toJSONString(new CrmGossipReq() {{
            setType(1); // 1 禁言 2 取消禁言
            setCustomer_id("18721778933");
            setCustomer_type(1);
            setOperator_type(1);
            setOperator_id(60L);
            setStart_time("2017-12-15 00:00:00");
            setEnd_time("2017-12-16 00:00:00");
            setOperate_time("2017-12-10 00:00:00");
        }});
    }

    /**
     * 投顾回复评论
     *
     * @return
     */
    public static String buildCommentReply() {
        return JSON.toJSONString(new CrmCommentReplyReq() {{
            setComment_id(10003L);
            setAdvisor_id(99996415L);
            setFormer_comment_id("10061-2-10000");
            setContent("投顾回复你内容啦！！！！");
            setViewpoint_id(10000L);
            setOperate_time("2017-12-10 09:00:00");
        }});
    }

    /**
     * 投顾观点
     *
     * @return
     */
    public static String buildTheme() {
        return JSON.toJSONString(new CrmAdviserThemeReq() {{
            setViewpoint_id("91029991");
            setProduct_id("1929101");
            setTitle("浙江温州：皮革厂倒闭了！");
            setViewpoint_type(1);
            setBasis("依据是跑路啦");
            setSource("华尔街财经");
            setAbstracts("皇家马德里主场5比0大胜塞维利亚，纳乔开场3分钟进球，C罗梅开二度");
            setRegistrant(99996415L);
            setOpen_content(
                    "<img src=\"http://tony666.cn/sports/transform/w360h184/20171210/111.png\" alt=\"皇马闪电破门\" data-link=\"\">" +
                            "<p>　　北京时间12月9日23:15(西班牙当地时间16:15)，2017/18赛季西甲第15轮一场焦点战在伯纳乌球场展开争夺，皇家马德里主场5比0大胜塞维利亚，纳乔开场3分钟进球，C罗梅开二度，克罗斯和阿什拉夫锦上添花。皇马落后少赛一场的巴萨5分。</p>" +
                            "<img src=\"http://f.sinaimg.cn/sports/transform/w360h184/20171210/222.png\" alt=\"皇马闪电破门\" data-link=\"\">" +
                            "<p>　　双方近24场联赛交锋无平局，皇马近8个联赛主场对阵塞维利亚取得全胜。双方联赛历史交锋146场，皇马77胜26平43负占据上风，其中主场54胜11平8负。本泽马、阿森西奥、摩德里奇、克罗斯、巴列霍、马塞洛和阿什拉夫轮换出场。</p>" +
                            "<img src=\"http://f.sinaimg.cn/sports/transform/w360h184/20171210/333.png\" alt=\"皇马闪电破门\" data-link=\"\">");

            setClose_content(
                    "<img src=\"http://f.sinaimg.cn/sports/transform/w360h184/20171210/444.png\" alt=\"皇马闪电破门\" data-link=\"\">" +
                            "<p>　　<strong>皇马开场仅3分钟取得领先，阿森西奥传球，卢卡斯禁区弧内弧线球射门被扑出。克亚尔角球混战中冒顶，皮球打在穆里尔头上偏转，纳乔近距离破门。</strong>" +
                            "随后克罗斯传球，C罗禁区右侧左脚抽射被皮萨罗挡出近角。纳瓦斯禁区右侧射门被封堵，皮萨罗角球混战中外围射门偏出。</p>" +
                            "<img src=\"http://f.sinaimg.cn/sports/transform/w360h184/20171210/555.png\" alt=\"皇马闪电破门\" data-link=\"\">" +
                            "<img src=\"http://tony666.cn/sports/transform/w360h184/20171210/666.png\" alt=\"皇马闪电破门\" data-link=\"\">");
            setRisk_level(2);
            setIs_chargeable("1");
            setAudio_url("http://f.sinaimg.cn/sports/transform/w360h184/20171210/cccc.mp3");
            setIs_show_audio(1);
            setIs_top(1);
            setOperate_time("20171210");
        }});
    }

}
