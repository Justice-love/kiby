/**
 * 
 * @creatTime 下午3:26:32
 * @author Eddy
 */
package main;

import java.util.regex.Pattern;

import org.eddy.test.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Eddy
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring-common.xml" })
public class Main {

	@Autowired
	@Qualifier("test")
	private Test test;


//	@org.junit.Test
	public void test() {
		test.test123("str", "123");
		test.test123("123", "str");
	}
	
	@org.junit.Test
	public void test2() {
		test.test("1", "2");
	}

	public void setTest(Test test) {
		this.test = test;
	}
	
	public static void main(String[] args) {
		System.out.println(Pattern.compile("[a-zA-Z_0-9]*").matcher("s").matches());
	}

}
