package com.fjbatresv.callrest.lib.base;

/**
 * Created by javie_000 on 8/29/2016.
 */
public interface EventBus {
    void register(Object sub);
    void unRegister(Object sub);
    void post(Object sub);
}
