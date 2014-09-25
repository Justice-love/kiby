/**
 * 
 * @creatTime 下午5:48:15
 * @author Eddy
 */
package org.eddy.xml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eddy.annotation.Algorithm;
import org.eddy.xml.Rule.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Eddy
 * 
 */
public class XmlParser {

	static ConcurrentHashMap<String, Rule> cache = new ConcurrentHashMap<String, Rule>();
	Logger logger = LoggerFactory.getLogger(XmlParser.class);
	private final String NAME = "name";
	private final String TYPE = "type";
	private final String EXCEPTION = "exception";

	@PostConstruct
	public void parse() {
		logger.debug("parse rule xml post construct");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = dbf.newDocumentBuilder();
			InputStream inputString = XmlParser.class.getResourceAsStream("/rule.xml");
			Document doc = builder.parse(inputString);
			Element root = doc.getDocumentElement();
			if (root == null)
				throw new NullPointerException("根节点为空");
			// Node.ELEMENT_NODE
			NodeList children = root.getChildNodes();
			for (int i = 0; i < children.getLength(); i++) {
				Node order = children.item(i);
				if (order.getNodeType() == Node.ELEMENT_NODE) {
					makeRule(order);
				}
			}
		} catch (Exception e) {
			logger.error("some error", e);
		}
	}

	/**
	 * 
	 * @param order
	 * @creatTime 下午4:01:32
	 * @author Eddy
	 */
	private void makeRule(Node order) {
		String orderName = order.getAttributes().getNamedItem("name").getNodeValue();
		NodeList children = order.getChildNodes();
		if (null == children)
			throw new NullPointerException();
		Map<String, Param> paramMap = new HashMap<String, Param>();
		for (int i = 0; i < children.getLength(); i++) {
			Node param = children.item(i);
			if (param.getNodeType() == Node.ELEMENT_NODE) {
				String name = "", type = "", exception = "", expect="";
				Algorithm algo = null;
				NamedNodeMap map = param.getAttributes();
				for (int j = 0; j < map.getLength(); j++) {
					switch (map.item(j).getNodeName()) {
					case NAME:
						name = map.item(j).getNodeValue();
						break;
					case TYPE:
						type = map.item(j).getNodeValue();
						break;
					case EXCEPTION:
						exception = map.item(j).getNodeValue();
						break;
					default:
						algo = Algorithm.getByName(map.item(j).getNodeName());
						expect = map.item(j).getNodeValue();
						break;
					}
				}
				paramMap.put(name, new Param(name, type, algo, exception, expect));
			}
		}
		cache.put(orderName, new Rule(orderName, paramMap));

	}
	
	public static Rule tableRule(String name) {
		Rule rule = cache.get(name);
		if (null == rule) {
			throw new IllegalArgumentException("rule not found, please che the rule.xml");
		}
		return rule;
	}
	
}
