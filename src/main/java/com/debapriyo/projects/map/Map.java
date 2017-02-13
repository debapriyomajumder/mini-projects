package com.debapriyo.projects.map;

import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of {@link java.util.Map} interface (all the methods have not been implemented, though) <br/>
 * The data structure consists of a list of {@link MapBucket}s<br/>
 * where each {@link MapBucket} has an id and a list of {@link MapEntry}s<br/>
 * where all {@link MapEntry} in a single {@link MapBucket} are such that {@link MapEntry}.getKey().hashCode()<br/>
 * for all {@link MapEntry} in that {@link MapBucket}, produces the same response, equal to the id of the {@link MapBucket}.
 *
 * @author Debapriyo Majumder (x086021)
 * @version 1.0
 * @since 2/3/2017
 */
public class Map<K, V> {
    private final List<MapBucket<K, V>> mapBuckets = new ArrayList<>();

    public int size() {
        return this.mapBuckets.size();
    }

    /**
     * Gets the mapped value V for a key K, or null if there is no value V for key K
     **/
    public V get(final K key) {
        return mapBuckets.stream() //
                        .filter(mapBucket -> key.hashCode() == mapBucket.getBucketId()) //
                        .findFirst() //
                        .filter(mapBucket -> mapBucket.getBucketEntries().stream() //
                                        .filter(mapEntry -> mapEntry.getKey().equals(key)) //
                                        .findAny() //
                                        .isPresent()) //
                        .map(mapBucket -> mapBucket.getBucketEntries().stream() //
                                        .filter(mapEntry -> mapEntry.getKey().equals(key)) //
                                        .findFirst() //
                                        .map(MapEntry::getValue) //
                                        .orElse(null)) //
                        .orElse(null);
    }

    /**
     * Checks whether Map contains the key K, if so then replaces the existing value V. Otherwise creates a new MapEntry K->V
     *
     * May throw IllegalStateException when the state of the Map has been changed during the execution of this method.
     */
    public void put(final K key, final V value) throws IllegalStateException {
        synchronized (this.mapBuckets) {
            V existingValue = get(key);
            if (existingValue != null) {
                // Key already exists, need to replace the value with the new one
                // But first need to find that corresponding MapEntry
                MapEntry<K, V> existingMapEntry = mapBuckets.stream() //
                                .filter(mapBucket -> key.hashCode() == mapBucket.getBucketId()) //
                                .findFirst() //
                                .filter(mapBucket -> mapBucket.getBucketEntries().stream() //
                                                .filter(mapEntry -> mapEntry.getKey().equals(key)) //
                                                .findAny() //
                                                .isPresent()) //
                                .map(mapBucket -> mapBucket.getBucketEntries().stream() //
                                                .filter(mapEntry -> mapEntry.getKey().equals(key)) //
                                                .findFirst() //
                                                .orElse(null)) //
                                .orElse(null);

                if (null == existingMapEntry) {
                    throw new IllegalStateException("This cannot be !!! God help me ! A map entry was supposed to be here, cannot find it any more. Probably as a result of concurreny !");
                }
                else {
                    // Now that the map entry has been found, its simple matter of swapping the previous value with the new value
                    existingMapEntry.setValue(value);
                }
            }
            else {
                // Key does not exist, need to first check whether a bucket exists for this key
                // If so then dump this key value pair in that bucket
                // Otherwise need to creat a new MapBucket with id = key.hashCode() and dump value in that bucket.
                MapBucket<K, V> existingMapBucket = mapBuckets.stream() //
                                .filter(mapBucket -> key.hashCode() == mapBucket.getBucketId()) //
                                .findFirst() //
                                .orElse(null);
                MapEntry<K, V> newMapEntry = new MapEntry<>(key, value);
                if (null == existingMapBucket) {
                    // OK, map bucket does not exist. So need to create a new one, and add it to the list of map buckets
                    existingMapBucket = new MapBucket<>();
                    existingMapBucket.setBucketId(key.hashCode());
                    mapBuckets.add(existingMapBucket);
                }
                existingMapBucket.addBucketEntry(newMapEntry);
            }
        }
    }
}
