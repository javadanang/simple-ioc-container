package net.acegik.simpleioccontainer;

import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author pnhung177
 */
public class BeanXmlParser {

    private String xmlFilename = null;

    public BeanXmlParser(String fn) {
        xmlFilename = fn;
    }

    public List<BeanObject> getAllBeanObjects() {
        try {
            SAXParserFactory parserFactor = SAXParserFactory.newInstance();
            SAXParser parser = parserFactor.newSAXParser();
            SAXHandler handler = new SAXHandler();
            parser.parse(ClassLoader.getSystemResourceAsStream(xmlFilename), handler);
            return handler.getResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    class SAXHandler extends DefaultHandler {

        List<BeanObject> beanList = new ArrayList<BeanObject>();
        BeanObject bean = null;
        BeanProperty property = null;
        String content = null;

        @Override
        public void startElement(String uri, String localName,
                String qName, Attributes attributes) throws SAXException {

            if ("bean".equals(qName)) {
                bean = new BeanObject();
                bean.setId(attributes.getValue("id"));
                bean.setClazz(attributes.getValue("class"));
            } else if ("property".equals(qName)) {
                property = new BeanProperty();
                property.setName(attributes.getValue("name"));
                property.setRef(attributes.getValue("ref"));
                property.setValue(attributes.getValue("value"));
            }
        }

        @Override
        public void endElement(String uri, String localName,
                String qName) throws SAXException {
            if ("bean".equals(qName)) {
                beanList.add(bean);
            } else if ("property".equals(qName)) {
                bean.getProperties().add(property);
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            content = String.copyValueOf(ch, start, length).trim();
        }

        public List<BeanObject> getResult() {
            return beanList;
        }
    }
}
