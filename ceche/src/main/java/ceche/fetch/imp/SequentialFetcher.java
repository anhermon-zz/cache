package ceche.fetch.imp;

import java.util.LinkedHashSet;

import ceche.fetch.Fetcher;

/**
 * Attempts data retrieval from multiple {@link Fetcher}.
 * Fetch order is based on insertion order. 
 * @author Angel
 *
 * @param <KEY>
 * @param <DATA>
 */
public class SequentialFetcher<KEY, DATA> implements Fetcher<KEY, DATA>{
	private SequentialFetcher(){}
	private SequentialFetcher(LinkedHashSet<Fetcher<KEY, DATA>> fetchers){
		this.fetchers = fetchers;
	}
	private LinkedHashSet<Fetcher<KEY, DATA>> fetchers = new LinkedHashSet<>();
	
	/**
	 * {@link SequentialFetcher} builder
	 * @author Angel
	 */
	public final static class Builder<KEY, DATA>{
		LinkedHashSet<Fetcher<KEY, DATA>> fetchers = new LinkedHashSet<>();
		/**
		 * Add {@link Fetcher} to {@link SequentialFetcher}
		 * @param fetcher
		 * @return {@link Builder}
		 */
		public Builder<KEY, DATA> addFetcher(Fetcher<KEY, DATA> f){
			fetchers.add(f);
			return this;
		}
		/**
		 * Returns an instance of {@link SequentialFetcher}
		 * @return instance of {@link SequentialFetcher}
		 * @throws NullPointerException
		 */
		public SequentialFetcher<KEY, DATA> build(){
			return new SequentialFetcher<KEY, DATA>(fetchers);
		}
	}

	@Override
	public DATA load(KEY key) {
		DATA out = null;
		for (Fetcher<KEY, DATA> f : fetchers){
			out = f.load(key);
			if (out!= null) return out;
		}
		return null;
	}
	

}
