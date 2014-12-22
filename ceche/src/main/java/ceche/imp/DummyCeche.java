package ceche.imp;

import java.util.concurrent.ExecutionException;

import ceche.AbstractCache;
import ceche.CacheServer;

public class DummyCeche extends AbstractCache<Integer, String>{

	@Override
	public String fromRemote(Integer key) {
		return null;
	}

	@Override
	public String fromFile(Integer key) {
		return null;
	}
	
	public static void main(String[] args) throws ExecutionException{
		CacheServer<Integer, String> cs = new DummyCeche();
		test(cs,1);
		test(cs,2);
		test(cs,1);
		
		test(cs,1);
		test(cs,2);
		test(cs,2);
		
	}

	private static void test(CacheServer<Integer, String> cs , int key)throws ExecutionException {
		System.out.println(cs.get(key));
	}

}
