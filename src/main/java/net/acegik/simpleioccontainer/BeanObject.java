package net.acegik.simpleioccontainer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pnhung177
 */
public class BeanObject {
    private String id;
    private String clazz;
    private List<BeanProperty> properties = new ArrayList<BeanProperty>();

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<BeanProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<BeanProperty> properties) {
        this.properties = properties;
    }
}
