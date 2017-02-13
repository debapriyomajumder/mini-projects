package com.debapriyo.projects.map;

/**
 * Class representing individual entry in the Map holding types K -> V.
 *
 * @author Debapriyo Majumder (x086021)
 * @version 1.0
 * @since 2/3/2017
 */
public class MapEntry<K, V> {
    private K key;
    private V value;

    public MapEntry(final K key, final V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    public void setKey(final K key) {
        this.key = key;
    }

    public void setValue(final V value) {
        this.value = value;
    }
}
