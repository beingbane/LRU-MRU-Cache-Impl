package com.CacheEvictionPolicy.cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public abstract class AbstractCache<K, V> {

	public static final int sizeOfCache = 5;
	HashMap<K, V> cacheData = null;
	LinkedList<K> evictionListOfCache = null;

	public abstract V getValue(K key);
	public abstract AbstractCache<K, V> insert(K key, V value);
	
	public AbstractCache(){
//		this.sizeOfCache = sizeOfCache;
		cacheData = new HashMap<>(sizeOfCache);
		evictionListOfCache = new LinkedList<>();
	}
	
	void dataEviction() throws NoSuchElementException{
		K key = evictionListOfCache.removeLast();
		cacheData.remove(key);	
	}
	
	boolean isCacheFull() {
		return cacheData.size() == sizeOfCache;
	}
	
	void removeData(K key) {
		this.cacheData.remove(key);
		this.evictionListOfCache.remove(key);
	}
	
	void cleanCache() {
		evictionListOfCache.clear();
		cacheData.clear();
	}
	
	public HashMap<K, V> stateOfCache() {
		return cacheData;
	}
}
