package com.entisy.techniq.core.util;

public class Duo<K, V> {

    private final K k;
    private final V v;

    public Duo(K key, V value) {
        this.k = key;
        this.v = value;
    }

    public K getKey() {
        return k;
    }

    public V getValue() {
        return v;
    }
}
