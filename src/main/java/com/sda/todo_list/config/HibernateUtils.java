package com.sda.todo_list.config;

import com.sda.todo_list.domain.Category;
import com.sda.todo_list.domain.Task;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    public static SessionFactory getSessionFactory() {

        return new Configuration()
                .configure("hibernate.cfg.xml")
//                .addAnnotatedClass(Task)
//                .addAnnotatedClass(Category)
                .buildSessionFactory();
    }
}
