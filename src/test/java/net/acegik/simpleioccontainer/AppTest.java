package net.acegik.simpleioccontainer;

import org.junit.Test;

public class AppTest {

    @Test
    public void testApp() {
        BeanFactory factory = new BeanFactory("config.xml");
        StoreService myService = (StoreService) factory.getBean("storeService");

        System.out.println("=================================================");
        System.out.println("Store Name:" + myService.getName());
        System.out.println("Total number of products:" + myService.countProducts());
        System.out.println("Total Assets:" + myService.totalAssets());
    }
}
