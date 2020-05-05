package com.memory.netty;


import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

public class UserChannelRelation {
    private static ConcurrentHashMap<Integer, Channel> relation = new ConcurrentHashMap<>();

    public static void add(Integer userId, Channel channel) {
        relation.put(userId, channel);
    }

    public static Channel get(Integer userId) {
        return relation.get(userId);
    }
}
