package me.kamilkorzeniewski.conference;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/*
    *ApplicationContextAware implementation to have access to spring beans in non spring classes.
 */
@Component
public class SpringContext implements ApplicationContextAware {

    private static ApplicationContext context;


    public static <T> T getBean(Class<T> beanClass){
        return context.getBean(beanClass);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContext.context = applicationContext;
    }
}
