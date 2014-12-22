package ceche;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public abstract class AbstractCache<KEY, DATA> implements CacheServer<KEY, DATA>{
	private Logger logger = LoggerFactory.getLogger(getClass().getClass());
	private final CacheLoader<KEY, DATA> loader = new CacheLoader<KEY, DATA>() {
		public DATA load(KEY key) {
			return fetch(key);
		}
	};
	private LoadingCache<KEY, DATA> cache = defaultCache();

	private LoadingCache<KEY, DATA> defaultCache() {
		return CacheBuilder.newBuilder()
				.maximumSize(1000)
				.build(loader);
	}
	
	public DATA get(KEY key) throws ExecutionException{
		try {
			return cache.get(key);
		} catch (Exception e) {
			logger.warn("Could not retreive data for key:" + key + "!");
			return null;
		}
	}
	/**
	 * Default fetch behavior.
	 * Tries to retrieve data in the following order: from ceche > from file > form remote. 
	 */
	public DATA fetch(KEY key){
		DATA out = null;
		out = fromFile(key);
		if(out!=null)return out;
		out = fromRemote(key);			
		return out;
	}
	
	/**
	 * Retrieve data from remote method
	 * @param key request key
	 * @return 
	 */
	public abstract DATA fromRemote(KEY key);
	/**
	 * Retrieve data from file method
	 * @param key request key
	 * @return 
	 */
	public abstract DATA fromFile(KEY key);

}
