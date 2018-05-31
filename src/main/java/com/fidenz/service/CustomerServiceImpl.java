package com.fidenz.service;

import com.fidenz.dao.SearchFilter;
import com.fidenz.entity.Customers;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Kalana on May, 2018
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    public static final Logger LOGGER = Logger.getLogger(CustomerServiceImpl.class.getName());

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<Customers> findAllCustomers(SearchFilter filter) {
        List<Customers> resultList = null;
        try {
            Session session = getSession();

            StringBuilder queryStr = new StringBuilder();
            queryStr.append("FROM Customers c ");
            if (filter != null && filter.getId() != null) {
                queryStr.append(" WHERE c.id = :id ");
            } else if (filter != null && filter.getName() != null) {
                queryStr.append(" WHERE c.name = :name ");
            }

            Query query = session.createQuery(queryStr.toString());

            if (filter != null && filter.getId() != null) {
                query.setParameter("id", filter.getId());
            } else if (filter != null && filter.getName() != null) {
                query.setParameter("name", filter.getName());
            }

            resultList = query.getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }
        return resultList;
    }

}
