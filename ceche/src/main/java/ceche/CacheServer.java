package ceche;

import java.util.concurrent.ExecutionException;

/**
 * Cache Server Interface.<br/>
 * Retrieves data from cache
 * @author Angel
 *
 * @param <KEY> key type, key is used to request data from cache.
 * @param <DATA> data type, data is retrieved from cache based on specified key.
 */
public interface CacheServer<KEY,DATA> {
		/**
		 * Request data from cache using key
		 * @param key - request key
		 * @return data
		 * @throws ExecutionException
		 */
		public DATA get(KEY key) throws ExecutionException;

		DATA getIfPresent(KEY key) throws ExecutionException;
}
