import com.CacheEvictionPolicy.cache.CheckCacheFactory;
import com.CacheEvictionPolicy.cache.CacheEvictionPolicies;

public class MainClass {

	public static void main(String[] args) {
		
		CheckCacheFactory.getInstance()
		.initCache(CacheEvictionPolicies.LRU)
		.getCache().insert(1, 11)
		.insert(2, 12)
		.insert(3, 13)
		.insert(4, 14)
		.insert(5, 15)
		.insert(6, 16);
		
		// => data was [11,12,13,14,15] while adding 16 -> cache become full and least recently used 11 was removed
		// => new data is now [12,13,14,15,16]
		
		System.out.println(CheckCacheFactory.getInstance().getCache().stateOfCache());
		
		//Now we access 12 so it become most frequently and list is now [13,14,15,16,12] 
		
		System.out.println(CheckCacheFactory.getInstance().getCache().getValue(2));
		
		//=> adding new value will cause threshold again and remove least recently used 13
		
		CheckCacheFactory.getInstance().getCache().insert(7, 17);
		
		System.out.println(CheckCacheFactory.getInstance().getCache().stateOfCache());
	}
}
