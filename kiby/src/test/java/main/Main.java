/**
 * 
 * @creatTime 下午3:26:32
 * @author Eddy
 */
package main;

import org.eddy.test.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Eddy
 *
 */
public class Main {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[] { "classpath:/spring-common.xml" });
		Test test = (Test) applicationContext.getBean("test");
		test.test123("123", "456");
	}
}
