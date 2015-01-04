package cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Example of using @Cacheable
 * 
 * @author Angel
 *
 */
@Component
public class CacheInterfaceImpl implements CacheInterface{
	
	@Transactional
	@Cacheable(value = "default", key = "#id")//value = cache name
	@Override
	public int getOrLoad(int id) {
		System.out.println("inside");
		return id;
	}

}
