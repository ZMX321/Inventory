package com.rei.interview.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache<K, V> extends ConcurrentHashMap<K, V> {

    private static final int MAX_SIZE = 2;

    @Override
    public V put(K key, V value) {
        return super.size() < MAX_SIZE ? super.put(key, value) : null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        if(MAX_SIZE > (super.size() + m.size())){
            super.putAll(m);
        }
    }
}
