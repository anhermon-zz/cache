package ceche.main;

import java.util.concurrent.ExecutionException;

import ceche.AbstractCache;
import ceche.factory.CacheFactory;
import ceche.fetch.Fetcher;
/**
 * Example of using {@link AbstractCache} and {@link CacheFactory}
 * @author Angel
 *
 */
public class Main {
public static void main(String[] args) throws ExecutionException{
	CacheFactory<Integer, Integer> factory = new CacheFactory<Integer, Integer>();
	Fetcher<Integer, Integer> fetcher = new Fetcher<Integer, Integer>(){

		@Override
		public Integer load(Integer key) {
			return key * key;
		}
		
	};
	AbstractCache<Integer, Integer> cache = factory.init(fetcher);
	for(int i =1 ; i<10 ; i++)
		for(int j =1 ; j<10 ; j++)
			System.out.println(i>1?cache.getIfPresent(j):cache.get(j));
}
}
