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
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Eddy
 * 
 */
@Component("xmlParser")
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
				String name = "", type = "", exception = "";
				Map<Algorithm, String> algorithms = new HashMap<Algorithm, String>();
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
						algorithms.put(Algorithm.getByName(map.item(j).getNodeName()), map.item(j).getNodeValue());
						break;
					}
				}
				Param p = new Param(name, type, exception, algorithms);
				//
				parseExpression(param, p);
				
				//添加验证表达式对象
				paramMap.put(name, p);
			}
		}
		cache.put(orderName, new Rule(orderName, paramMap));

	}
	
	/**
	 * 解析表达式 notnull|regx|morethan|lessthan|equal
	 * @param param
	 * @param p
	 * @creatTime 上午9:13:58
	 * @author Eddy
	 */
	private void parseExpression(Node param, Param p) {
		NodeList children = param.getChildNodes();
		if (children != null) {
			for (int i = 0; i < children.getLength(); i++) {
				Node node = children.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					Algorithm algorithm = Algorithm.getByName(element.getTagName());
					if (element.hasAttribute("exception")) {
						algorithm.setException(element.getAttribute("exception"));
					}
					p.put(algorithm, element.getAttribute("value"));
				}
			}
		}
	}

	public static Rule tableRule(String name) {
		Rule rule = cache.get(name);
		if (null == rule) {
			throw new IllegalArgumentException("rule not found, please check the rule.xml");
		}
		return rule;
	}
	
}
