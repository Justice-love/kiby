/**
 * 
 * @creatTime 下午5:48:15
 * @author Eddy
 */
package org.eddy.xml;

import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Eddy
 * 
 */
public class XmlParser {

	ConcurrentHashMap<String, Rule> cache = new ConcurrentHashMap<String, Rule>();
	Logger logger = LoggerFactory.getLogger(XmlParser.class);

	@PostConstruct
	public void parse() {
		logger.debug("parse rule xml post construct");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder buuilder = dbf.newDocumentBuilder();
		} catch (Exception e) {

		}
	}

}
