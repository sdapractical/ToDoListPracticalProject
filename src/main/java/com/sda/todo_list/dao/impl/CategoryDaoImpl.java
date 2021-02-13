package com.sda.todo_list.dao.impl;

import com.sda.todo_list.config.HibernateUtils;
import com.sda.todo_list.dao.CategoryDao;
import com.sda.todo_list.domain.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    @Override
    public void saveOrUpdate(Category category) {

        try (SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();
            session.saveOrUpdate(category);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Category> getCategories() {

        try (SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();
            String queryString = "SELECT c FROM Category c";
            Query query = session.createQuery(queryString, Category.class);
            return query.getResultList();
        }
    }

    @Override
    public void deleteCategoryByName(String categoryName) {

        try (SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();
            String queryString = "DELETE FROM Category WHERE name = :categoryNameParam";
            Query query = session.createQuery(queryString);
            query.setParameter("categoryNameParam", categoryName);
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteCategory(Category category) {

        try (SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();
            session.delete(category);
            session.getTransaction().commit();
        }
    }
}
