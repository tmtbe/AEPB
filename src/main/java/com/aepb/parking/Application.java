package com.aepb.parking;

import com.aepb.parking.repo.*;

import java.util.HashMap;

public enum Application {
    app;

    private final HashMap<Class, Object> componentHashMap = new HashMap<>();

    public <T> T getComponent(Class<T> tClass) {
        if (componentHashMap.containsKey(tClass)) {
            return (T) componentHashMap.get(tClass);
        } else {
            T t = null;
            try {
                t = tClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            componentHashMap.put(tClass, t);
            return t;
        }
    }
}
