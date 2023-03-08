package com.stupin.analyticCentre.repository.hibernate;

import com.stupin.analyticCentre.config.HibernateUtil;
import com.stupin.analyticCentre.entity.Activity;
import com.stupin.analyticCentre.repository.ActivityRepository;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class ActivityRepositoryImpl extends AbstractRepository<Activity> implements ActivityRepository {
    protected void init() {
        aClass = Activity.class;
    }
    private static final String GET_MOST_POPULAR_SERVICE = "select activity.type " +
            "FROM activities activity " +
            "GROUP BY activity.type " +
            "ORDER BY activity.type DESC";

    @Override
    public List<String> getMostPopularService() {
        List<String> mostPopularService = new ArrayList<>();
        Session session = null;
        session = HibernateUtil.getFactory().openSession();
        session.beginTransaction();
        mostPopularService = (List<String>) session.createQuery(GET_MOST_POPULAR_SERVICE).setMaxResults(1).list();
        session.getTransaction().commit();
        return mostPopularService;

    }
}
