package ceche;

import java.io.IOException;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;

public class Main {
	private static final CacheLoader<String, String> loader = new CacheLoader<String, String>() {
		public String load(String key) {
			return key.toUpperCase();
		}
	};

	private static final CacheLoader<String, String> checkedLoader = new CacheLoader<String, String>() {
		public String load(String key) throws IOException {
			return loadFromDisk(key);
		}

		private String loadFromDisk(String key) {
			// TODO Auto-generated method stub
			return null;
		}
	};

	public static void main(String[] args) {
		LoadingCache<String, String> cache = CacheBuilder.newBuilder()
				.recordStats()
				.maximumSize(200)
				.build(loader);
		simpleTest(cache);
		simpleTest(cache);
		simpleTest(cache);
		CacheStats stats = cache.stats();
		System.out.println(cache.asMap());
		System.out.println(stats);
	}

	private static void simpleTest(LoadingCache<String, String> cache) {
		printCecheSize(cache);
		// cache miss, invokes CacheLoader
		System.out.println(cache.getUnchecked("simple test"));
		printCecheSize(cache);
		System.out.println("\n");
	}

	private static void printCecheSize(LoadingCache<String, String> cache) {
		System.out.println("cache size:" + cache.size());
	}

}
