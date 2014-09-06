package net.acegik.simpleioccontainer;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author pnhung177
 */
public class BeanFactory {

    private List<BeanObject> beanList;
    
    private Map<String, Object> beanMap = new HashMap<String, Object>();
    
    public BeanFactory(String fn) {
        BeanXmlParser parser = new BeanXmlParser(fn);
        this.beanList = parser.getAllBeanObjects();
    }

    public Object getBean(String name) {

        Object result = null;

        if (this.beanMap.containsKey(name)) {
            result = this.beanMap.get(name);
        }

        for (BeanObject bean:beanList) {
            String id = bean.getId();
            if (id.equals(name)) {
                result = createBean(bean);
                this.beanMap.put(id, result);
            }
        }

        return result;
    }

    public Object createBean(BeanObject beanInfo) {
        try {
            String className = beanInfo.getClazz();
            Object beanInstance = Class.forName(className).newInstance();

            List<BeanProperty> propertyList = beanInfo.getProperties();
            if (propertyList.size() > 0) {
                Class beanClass = beanInstance.getClass();

                Map methodMap = new HashMap();
                Method[] methods = beanClass.getMethods();
                for (int n = 0; n < methods.length; n++) {
                    String methodName = methods[n].getName();
                    if (methodName.startsWith("set")) {
                        methodName = Character.toLowerCase(methodName.charAt(3))
                                + methodName.substring(4);
                        methodMap.put(methodName, methods[n]);
                    }
                }

                for (BeanProperty propertyElement:propertyList) {
                    String pName = propertyElement.getName();
                    String pRef = propertyElement.getRef();
                    String pValue = propertyElement.getValue();

                    if (methodMap.containsKey(pName)) {
                        Method method = (Method) methodMap.get(pName);

                        Class[] parameterTypes = method.getParameterTypes();
                        String parameterName = parameterTypes[0].getCanonicalName();
                        Object[] params = new Object[1];
                        if (parameterName.equals("java.lang.String")) {
                            params[0] = pValue;
                        } else if (parameterName.equals("boolean")) {
                            params[0] = Boolean.parseBoolean(pValue);
                        } else if (parameterName.equals("int")) {
                            params[0] = new Integer(pValue);
                        } else if (parameterName.equals("float")) {
                            params[0] = Float.parseFloat(pValue);
                        } else if (parameterName.equals("double")) {
                            params[0] = Double.parseDouble(pValue);
                        } else {
                            if (pRef != null) {
                                params[0] = getBean(pRef);
                            }
                        }
                        method.invoke(beanInstance, params);
                    }
                }
            }
            return beanInstance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
