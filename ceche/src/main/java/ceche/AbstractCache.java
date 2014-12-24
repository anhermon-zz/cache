package ceche;

import java.util.concurrent.ExecutionException;

import ceche.fetch.Fetcher;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;



public abstract class AbstractCache<KEY, DATA> implements CacheServer<KEY, DATA>{
	
	private final CacheLoader<KEY, DATA> loader = new CacheLoader<KEY, DATA>() {
		public DATA load(KEY key) {
			return fetcher.load(key);
		}
	};
	
	private Fetcher<KEY, DATA> fetcher;
	public void setFetcher(Fetcher<KEY, DATA> fetcher) {
		this.fetcher = fetcher;
	}
	private LoadingCache<KEY, DATA> cache = defaultCache();
	
	/**
	 * Default {@link LoadingCache}
	 * @return
	 */
	private LoadingCache<KEY, DATA> defaultCache() {
		return CacheBuilder.newBuilder()
				.maximumSize(1000)
				.build(loader);
	}
	
	public void setCache(LoadingCache<KEY, DATA> cache) {
		this.cache = cache;
	}
	
	@Override
	public DATA get(KEY key) throws ExecutionException {
		return cache.get(key);
	}
}
