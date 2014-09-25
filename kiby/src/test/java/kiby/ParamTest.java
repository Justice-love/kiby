/**
 * 
 * @creatTime 上午11:08:23
 * @author Eddy
 */
package kiby;

import java.util.Map;

import org.eddy.param.ParamParser;
import org.junit.Test;

/**
 * @author Eddy
 *
 */
@SuppressWarnings("all")
public class ParamTest {

	@Test
	public void test1() {
		Map map= new ParamParser().param(ClassTest.class);
		System.out.println(map);
	}
}
