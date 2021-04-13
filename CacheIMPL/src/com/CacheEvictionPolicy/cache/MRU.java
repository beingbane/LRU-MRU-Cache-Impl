package com.CacheEvictionPolicy.cache;

public class MRU<K,V> extends AbstractCache<K, V>{

	public MRU() {
		super();
	}

	@Override
	public V getValue(K key) {
		boolean remove = this.evictionListOfCache.remove(key);
		if(remove) {
			this.evictionListOfCache.addLast(key);
			return this.cacheData.get(key);
		}
		
		return null;
	}

	@Override
	public AbstractCache<K, V> insert(K key, V value) {
		if(isCacheFull()) {
			this.dataEviction();
		}
		this.evictionListOfCache.addLast(key);
		this.cacheData.put(key, value);
		return this;
	}

}
