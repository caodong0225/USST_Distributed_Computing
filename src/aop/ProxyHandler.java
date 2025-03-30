package aop;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author jyzxc
 */
public class ProxyHandler implements InvocationHandler {
    private Object target;

    public ProxyHandler(Object target) {
        this.target = target;
    }

    public Boolean getMethodsFromXML(String method) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse("methods.xml");
        NodeList nodeList = doc.getElementsByTagName("method");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            if (element.getAttribute("name").equals(method)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 执行切面的方法
        MyAspect aspect = new MyAspect();
        // 读取methods.xml的方法
        for(Method m : aspect.getClass().getMethods()){
            if(getMethodsFromXML(m.getName())){
                m.invoke(aspect);
            }
        }
        Object result = method.invoke(target, args);
        return result;
    }
}
