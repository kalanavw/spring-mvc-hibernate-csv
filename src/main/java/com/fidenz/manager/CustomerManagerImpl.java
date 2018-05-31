package com.fidenz.manager;

import com.fidenz.entity.Customers;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * @author Kalana on May, 2018
 */
@Repository
public class CustomerManagerImpl implements CustomerManager {
    public static final Logger LOGGER = Logger.getLogger(CustomerManagerImpl.class.getName());

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.openSession();
    }


    @Override
    public List<Customers> findAllCustomers() {
        List<Customers> resultList = null;
        try {
            Session session = getSession();
            Query query = session.createQuery("FROM Customers c");
            resultList = query.getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }
        return resultList;
    }

    @Override
    public List<Customers> findAllCustomersById(String id) {
        List<Customers> customers = null;
        try {
            Session session = getSession();
            Query query = session.createQuery("FROM Customers c WHERE c.id=:id");
            query.setParameter("id", id);
            customers = query.getResultList();
        } catch (NoResultException e) {
            LOGGER.info("NO Result for findAllCustomersById");
        }
        return customers;
    }

    @Override
    public List<Customers> findAllCustomersByName(String name) {
        List<Customers> customers = null;
        try {
            Session session = getSession();
            Query query = session.createQuery("FROM Customers c WHERE c.name=:name");
            query.setParameter("name", name);
            customers = query.getResultList();
        } catch (NoResultException e) {
            e.printStackTrace();
            LOGGER.info("NO Result for findAllCustomersByName");
        }
        return customers;
    }

    @Override
    public List<Customers> findAllCustomersGroupByZipCode(String zipCode) {
        List<Customers> resultList = null;
        try {
            Session session = getSession();
            Query query = session.createQuery("FROM Customers c GROUP BY :zipCode");
            query.setParameter("zipCode", zipCode);
            resultList = query.getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }
        return resultList;
    }
}
