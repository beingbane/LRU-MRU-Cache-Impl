package com.CacheEvictionPolicy.cache;

public class LRU<K, V> extends AbstractCache<K, V>{

	public LRU() {
		super();
	}

	@Override
	public V getValue(K key) {
		boolean remove = this.evictionListOfCache.remove(key);
		if(remove) {
			this.evictionListOfCache.addFirst(key);
			return cacheData.get(key);
		}
		return null;
	}

	@Override
	public AbstractCache<K, V> insert(K key, V value) {
		if(isCacheFull()) {
			dataEviction();
		}
		this.evictionListOfCache.addFirst(key);
		this.cacheData.put(key, value);
		return this;
	}

}
