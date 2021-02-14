package com.sda.todo_list.dao.impl;

import com.sda.todo_list.config.HibernateUtils;
import com.sda.todo_list.dao.TaskDao;
import com.sda.todo_list.domain.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;

public class TaskDaoImpl implements TaskDao {

    @Override
    public void saveOrUpdate(Task task) {

        try (SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();
            session.saveOrUpdate(task);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Task> getTasks() {
        try (SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();
            String queryString = "SELECT t FROM Task t";
            Query query = session.createQuery(queryString, Task.class);
            return query.getResultList();
        }
    }

    @Override
    public void delete(Task task) {

        try (SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();
            session.delete(task);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Task> getTasksByCompleted(boolean completed) {
        try (SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();
            String queryString = "SELECT t FROM Task as t WHERE t.completed = :completedParam";
            Query query = session.createQuery(queryString, Task.class);
            query.setParameter("completedParam", completed);
            return query.getResultList();
        }
    }
}
