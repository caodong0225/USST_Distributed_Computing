package class2;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * @author jyzxc
 */
public class Agent {
    public Vehicle getVehicle(String name) {
        // 读取xml配置文件，获取name到class的映射关系
        // 通过反射机制创建对象
        // 返回对象
        try {
            // 读取xml配置文件
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("agent.xml");

            // 获取name到class的映射关系
            NodeList nodeList = doc.getElementsByTagName("vehicle");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                if (element.getAttribute("name").equals(name)) {
                    String className = element.getAttribute("class");
                    // 通过反射机制创建对象
                    Class<?> clazz = Class.forName(className);
                    return (Vehicle) clazz.getDeclaredConstructor().newInstance();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
