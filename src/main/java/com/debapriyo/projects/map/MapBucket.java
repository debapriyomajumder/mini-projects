package com.debapriyo.projects.map;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the bucket holding a list of {@link MapEntry} such that all in a single bucket have the same hashCode().
 *
 * @author Debapriyo Majumder (x086021)
 * @version 1.0
 * @since 2/3/2017
 */
public class MapBucket<K,V> {
    private int bucketId;
    private List<MapEntry<K,V>> bucketEntries = new ArrayList<>();

    public int getBucketId() {
        return this.bucketId;
    }

    public void setBucketId(final int bucketId) {
        this.bucketId = bucketId;
    }

    public List<MapEntry<K,V>> getBucketEntries() {
        return this.bucketEntries;
    }

    public void addBucketEntry(final MapEntry<K,V> bucketEntry) {
        if(null != bucketEntry) {
            this.bucketEntries.add(bucketEntry);
        }
    }
}
