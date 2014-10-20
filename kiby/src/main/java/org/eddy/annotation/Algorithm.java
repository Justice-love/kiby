/**
 * 
 * @creatTime 下午5:16:05
 * @author Eddy
 */
package org.eddy.annotation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 算法枚举
 * 
 * @author Eddy
 * 
 */
public enum Algorithm {

	/**
	 * 正则表达式规则
	 * @author Eddy
	 *
	 */
	REGX {
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eddy.annotation.Algorithm#match(java.lang.Object)
		 */
		@Override
		public boolean match(Object obj, Object expression) {
			super.checkParam(obj, expression);
			Pattern pattern = Pattern.compile(expression.toString());
			Matcher match = pattern.matcher(obj.toString());
			return match.matches();
		}
	},
	/**
	 * 大于规则
	 * @author Eddy
	 *
	 */
	MORETHAN {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eddy.annotation.Algorithm#match(java.lang.Object,
		 * java.lang.Object)
		 */
		@Override
		public boolean match(Object obj, Object expression) {
			super.checkParam(obj, expression);
			return Integer.parseInt(obj.toString()) > Integer.parseInt(expression.toString());
		}

	},
	/**
	 * 小于规则
	 * @author Eddy
	 *
	 */
	LESSTHAN {
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eddy.annotation.Algorithm#match(java.lang.Object,
		 * java.lang.Object)
		 */
		@Override
		public boolean match(Object obj, Object expression) {
			super.checkParam(obj, expression);
			return Integer.parseInt(obj.toString()) < Integer.parseInt(expression.toString());
		}
	},
	/**
	 * equal规则
	 * @author Eddy
	 *
	 */
	EQUAL {
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eddy.annotation.Algorithm#match(java.lang.Object,
		 * java.lang.Object)
		 */
		@Override
		public boolean match(Object obj, Object expression) {
			super.checkParam(obj, expression);
			return obj.equals(expression);
		}
	},
	/**
	 * 不为空规则
	 * @author Eddy
	 *
	 */
	NOTNULL {
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eddy.annotation.Algorithm#match(java.lang.Object,
		 * java.lang.Object)
		 */
		@Override
		public boolean match(Object obj, Object expression) {
			return obj != null;
		}
	},
	
	/**
	 * in规则
	 * @author Eddy
	 *
	 */
	IN {
		/* (non-Javadoc)
		 * @see org.eddy.annotation.Algorithm#match(java.lang.Object, java.lang.Object)
		 */
		@Override
		public boolean match(Object obj, Object expression) {
			if (null == expression) throw new NullPointerException();
			Set<String> set = new HashSet<>(Arrays.asList(expression.toString().split(",")));
			if (obj == null) return false;
			return set.contains(obj);
		}
	};

	public static Algorithm getByName(String name) {
		switch (name) {
			case "lessthan":
				return LESSTHAN;
			case "morethan":
				return MORETHAN;
			case "equal":
				return EQUAL;
			case "regx":
				return REGX;
			case "notnull":
				return NOTNULL;
			case "in":
				return IN;
			default:
				return null;
		}
	}

	/**
	 * 算法具体的验证函数 true: 验证通过; false: 验证失败
	 * 
	 * @param obj
	 * @param expression
	 * @return
	 * @creatTime 下午5:38:27
	 * @author Eddy
	 */
	public boolean match(Object obj, Object expression) {
		throw new IllegalArgumentException("未支持的调用");
	}

	private void checkParam(Object obj, Object expression) {
		if (null == obj || null == expression) {
			throw new NullPointerException("参数为空");
		}
	}

	private String exception = null;

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getException() {
		return exception;
	}

}
