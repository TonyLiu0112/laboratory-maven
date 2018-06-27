package com.liuboyu.implement;

import com.liuboyu.implement.biz.LiveRoomComment;
import com.liuboyu.implement.res.GiftRes;
import com.liuboyu.implement.res.RootRes;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        LiveRoomComment liveRoomComment = new LiveRoomComment();
        Optional<GiftRes> giftRes = liveRoomComment.find();
        giftRes.ifPresent(res -> System.out.println(res.getContent() + " " + res.getCode()));
    }

}
