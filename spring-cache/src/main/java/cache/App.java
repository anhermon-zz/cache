package cache;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	/**
	 * Test caching
	 * @param args
	 */
	public static void main(String... args){
		try(ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/context.xml")){
			System.out.println(context);
			CacheInterface ci = context.getBean(CacheInterface.class);
			for(int i = 0 ; i<10; i++)
				System.out.println(ci.getOrLoad(5));
		}
	}
}
