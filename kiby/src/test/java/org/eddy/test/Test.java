/**
 * 
 * @creatTime 下午3:23:52
 * @author Eddy
 */
package org.eddy.test;

import org.eddy.annotation.Algorithm;
import org.eddy.annotation.ClearValidate;
import org.eddy.annotation.Validate;
import org.eddy.annotation.ValidateRule;
import org.springframework.stereotype.Component;

/**
 * @author Eddy
 *
 */
@Component("test")
public class Test {

	@ValidateRule(name="defaultRule2")
	public void test123(@Validate(algorithm = Algorithm.NOTNULL, expect = "") String str1, @Validate(algorithm = Algorithm.REGX, expect = "\\w*")String str2) {
		System.out.println("参数验证框架测试" + str1 + "   " + str2);
	}
	
	@ValidateRule(name="defaultRule2")
	public void test124(@ClearValidate @Validate(algorithm = Algorithm.NOTNULL, expect = "") String str1) {
		System.out.println("参数验证框架测试" + str1);
	}
	
	@ValidateRule(name="defaultRule3")
	public void test2(String in) {
		System.out.println("参数验证框架测试" + in);
	}
	
	public void test3(@Validate(algorithm=Algorithm.IN, exception="must in a,b,c", expect = "a,b,c") String in) {
		System.out.println("参数验证框架测试" + in);
	}
}
