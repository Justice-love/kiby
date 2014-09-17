/**
 * 
 * @creatTime 下午3:53:11
 * @author Eddy
 */
package org.eddy.cache;

import java.util.LinkedHashMap;

/**
 * @author Eddy
 *
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2128080999455999546L;
	
	/**
	 * 构造函数
	 * @creatTime 下午3:53:44
	 * @author Eddy
	 */
	public LRUCache() {
		// TODO Auto-generated constructor stub
		super(50, 0.75f, true);
		
		
	}

	@Override
	protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
		// TODO Auto-generated method stub
		return super.removeEldestEntry(eldest);
	}

}
