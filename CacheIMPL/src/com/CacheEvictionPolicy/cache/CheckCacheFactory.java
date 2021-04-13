package com.CacheEvictionPolicy.cache;

@SuppressWarnings("unchecked")
public class CheckCacheFactory<K, V> {
	@SuppressWarnings("rawtypes")
	private static CheckCacheFactory cacheFactory = null;
	
//	private int DEFAULT_CACHE_SIZE = 30 * 1024 * 1024;
	
	private AbstractCache<K, V> cache = null;
	

	private CheckCacheFactory() {

	}

//	private MetCacheFactory(int size) {
//		DEFAULT_CACHE_SIZE = size;
//	}

	public CheckCacheFactory<K, V> initCache(CacheEvictionPolicies policy) {
		if (policy == CacheEvictionPolicies.LRU)
			cache = new LRU<K, V>();
		else if (policy == CacheEvictionPolicies.MRU)
			cache = new MRU<K, V>();
		else
			throw new IllegalArgumentException("Invalid Eviction policy");
		return cacheFactory;
	}

	public static <K, V> CheckCacheFactory<K, V> getInstance() {
		if (cacheFactory == null) {
			synchronized (CheckCacheFactory.class) {
				if (cacheFactory == null)
					cacheFactory = new CheckCacheFactory<K, V>();
			}
		}
		return cacheFactory;
	}
	
	public AbstractCache<K, V> getCache() {
		return cache;
	}
	
//	public static <K, V> MetCacheFactory<K, V> getInstance(int cacheSize) {
//		if (cacheFactory == null) {
//			synchronized (MetCacheFactory.class) {
//				if (cacheFactory == null)
//					cacheFactory = new MetCacheFactory<K, V>(cacheSize);
//			}
//		}
//		return cacheFactory;
//	}
}
