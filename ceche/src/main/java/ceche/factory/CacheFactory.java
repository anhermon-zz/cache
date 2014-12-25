package ceche.factory;

import ceche.AbstractCache;
import ceche.fetch.Fetcher;

public class CacheFactory<KEY, DATA> {
	public AbstractCache<KEY, DATA> init(Fetcher<KEY, DATA> fetcher){
		AbstractCache<KEY, DATA> ac =  new AbstractCache<KEY, DATA>(){};
		ac.setFetcher(fetcher);
		return ac;
	}
		
}
