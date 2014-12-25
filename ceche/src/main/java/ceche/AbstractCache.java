package ceche;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

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
	 * Default {@link LoadingCache}<br/>
	 * Default maximum size is 1000.
	 * @return
	 */
	private LoadingCache<KEY, DATA> defaultCache() {
		return CacheBuilder.newBuilder()
				.maximumSize(1000)
				.build(loader);
	}
	/**
	 * Set custom {@link LoadingCache}
	 * @param cache
	 */
	public void setCache(LoadingCache<KEY, DATA> cache) {
		this.cache = cache;
	}
	/**
	 * Set Custom {@link LoadingCache} based on specified params.
	 * @param maxSize maximum number of records.
	 * @param duration duration until a record is expires.
	 */
	public void setCache(int maxSize,int duration) {
		this.cache = CacheBuilder.newBuilder()
				.maximumSize(maxSize)
				.expireAfterAccess(duration, TimeUnit.MINUTES)
				.build(loader);
	}
	
	@Override
	public DATA get(KEY key) throws ExecutionException {
		return cache.get(key);
	}
	
	@Override
	public DATA getIfPresent(KEY key) throws ExecutionException {
		return cache.getIfPresent(key);
	}
}
