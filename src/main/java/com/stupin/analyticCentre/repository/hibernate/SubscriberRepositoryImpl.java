package ua.stupin.analyticCentre.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ua.stupin.analyticCentre.config.HibernateUtils;
import ua.stupin.analyticCentre.entities.Subscriber;
import ua.stupin.analyticCentre.repository.SubscriberRepository;

import java.util.ArrayList;
import java.util.List;

public class SubscriberRepositoryImpl extends AbstractRepository<Subscriber> implements SubscriberRepository {
    protected void init() {
        aClass = Subscriber.class;
    }

    private static final String getTopFiveSubscribersWhichConsumeMostOfCallsSmsInternetSeparately
            = "SELECT subscribers.subscriber_id, first_name, last_name, phone_number, tariff, device, " +
            "count(activities.subscriber_id) AS subscriber_sms_activity\n" +
            "FROM subscribers\n" +
            "INNER JOIN activities\n" +
            "ON subscribers.subscriber_id = activities.subscriber_id\n" +
            "WHERE type_of_activities = ? \n" +
            "GROUP BY activities.subscriber_id\n" +
            "ORDER BY subscriber_sms_activity DESC \n" +
            "LIMIT 5";
    private static final String getSearchInActivity
            = "SELECT subscribers.subscriber_id, subscribers.first_name, subscribers.last_name, " +
            "subscribers.phone_number, subscribers.tariff, subscribers.device \n" +
            "FROM subscribers\n" +
            "INNER JOIN activities \n" +
            "WHERE log_of_activities = ? \n" +
            "GROUP BY subscribers.subscriber_id";
    private final static String updateTariff = "UPDATE subscribers " +
            "SET subscribers.tariff = ? " +
            "WHERE subscribers.subscriber_id = ?; ";
private final static String getSubscriberByIdAfterUpdateTariff = "SELECT *\n" +
        "FROM subscribers\n" +
        "WHERE subscribers.subscriber_id = ? ";
    @Override
    public List<Subscriber> getTopFiveSubscribersWhichConsumeMostOfCallsSmsInternetSeparately(String type) {
        List<Subscriber> subscriberList = new ArrayList<>();
        Session session = null;
        session = HibernateUtils.getFactory().openSession();
        session.beginTransaction();
        Query query = session.createSQLQuery(getTopFiveSubscribersWhichConsumeMostOfCallsSmsInternetSeparately)
                .addEntity(aClass);
        query.setParameter(1, type);
        subscriberList = query.list();
        session.getTransaction().commit();
        return subscriberList;
    }

    @Override
    public List<Subscriber> searchThroughSmsStorage(String message) {
        List<Subscriber> activityList = new ArrayList<>();
        Session session = null;
        session = HibernateUtils.getFactory().openSession();
        session.beginTransaction();
        Query query = session.createSQLQuery(getSearchInActivity).addEntity(aClass);
        query.setParameter(1, message);
        activityList = query.list();
        session.getTransaction().commit();
        return activityList;
    }

    @Override
    public void updateTariff(String tariff, String subscriberId) {
        Session session = null;
        session = HibernateUtils.getFactory().openSession();
        session.beginTransaction();
        Query query = session.createSQLQuery(updateTariff).addEntity(aClass);
        query.setParameter(1, tariff);
        query.setParameter(2, subscriberId);
        query.executeUpdate();
        session.getTransaction().commit();
    }
    @Override
    public List<Subscriber> getSubscriberByIdAfterUpdateTariff(String id){
        List<Subscriber> subscriberList = new ArrayList<>();
        Session session = null;
        session = HibernateUtils.getFactory().openSession();
        session.beginTransaction();
        Query query = session.createSQLQuery(getSubscriberByIdAfterUpdateTariff).addEntity(aClass);
        query.setParameter(1, id);
        subscriberList = query.list();
        session.getTransaction().commit();
        return subscriberList;
    }
}
