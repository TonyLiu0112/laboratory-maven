package com.liuboyu.implement.biz;

import com.liuboyu.implement.DefaultComment;
import com.liuboyu.implement.res.GiftRes;
import com.liuboyu.implement.res.RootRes;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

public class LiveRoomComment extends DefaultComment {

    @Override
    public Optional<GiftRes> find() {
        Optional<RootRes> rootRes = super.find();
        GiftRes giftRes = new GiftRes();
        BeanUtils.copyProperties(rootRes.get(), giftRes);
        giftRes.setCode("123");
        return Optional.of(giftRes);
    }
}
