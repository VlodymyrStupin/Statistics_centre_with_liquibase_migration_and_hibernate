package ua.stupin.analyticCentre.repository.hibernate;

import org.hibernate.Session;
import ua.stupin.analyticCentre.config.HibernateUtils;
import ua.stupin.analyticCentre.entities.Activity;
import ua.stupin.analyticCentre.repository.ActivityRepository;

import java.util.ArrayList;
import java.util.List;

public class ActivityRepositoryImpl extends AbstractRepository<Activity> implements ActivityRepository {
    protected void init() {
        aClass = Activity.class;
    }
    private static final String getMostPopularService = "select activity.type " +
            "FROM activities activity " +
            "GROUP BY activity.type " +
            "ORDER BY activity.type DESC";

    @Override
    public List<String> getMostPopularService() {
        List<String> mostPopularService = new ArrayList<>();
        Session session = null;
        session = HibernateUtils.getFactory().openSession();
        session.beginTransaction();
        mostPopularService = (List<String>) session.createQuery(getMostPopularService).setMaxResults(1).list();
        session.getTransaction().commit();
        return mostPopularService;

    }
}
