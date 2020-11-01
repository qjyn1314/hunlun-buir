package com.hulunbuir.clam.parent.tool;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * explain: 解析xml
 * </p>
 *
 * @author wangjunming
 * @since 2020/3/28 11:36
 */
public class XmlUtils {
    private static Logger logger = LoggerFactory.getLogger(XmlUtils.class);

    /**
     * 解析字符串的xml到map
     *
     * @author wangjunming
     * @since 2020/3/28 11:37
     */
    public static Map<String, String> parseXml(String result) {
        Map<String, String> map = new HashMap<String, String>();
        Document document = null;
        try {
            document = DocumentHelper.parseText(result);
        } catch (DocumentException e) {
            logger.error("解析字符串的xml失败!!!" + e);
        }
        Element root = document.getRootElement();
        List<Element> elementList = root.elements();
        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
        }
        return map;
    }

    /**
     * 将request中的InputStream转换成为map
     *
     * @author wangjunming
     * @since 2020/3/28 11:38
     */
    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        InputStream inputStream = request.getInputStream();
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element root = document.getRootElement();
        List<Element> elementList = root.elements();
        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
        }
        if (inputStream != null) {
            inputStream.close();
        }
        inputStream = null;
        return map;
    }

    /**
     * 扩展xstream使其支持CDATA
     */
    private static XStream xstream = new XStream(new XppDriver() {
        @Override
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                boolean cdata = true;

                @Override
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                @Override
                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });

    /**
     * 将对象转换成xml
     */
    public static <T> String strToXml(T t) {
        xstream.alias("xml", t.getClass());
        String result = xstream.toXML(t);
        logger.info("解析后的xml：" + result);
        return result;
    }


//    public static void main(String[] args) {
//        JsonResult jsonResult = JsonResult.error("错误信息");
//        String s = strToXml(jsonResult);
//        System.out.println(s);
//        Map<String, String> stringStringMap = parseXml(s);
//        System.out.println(stringStringMap);
//    }


}
