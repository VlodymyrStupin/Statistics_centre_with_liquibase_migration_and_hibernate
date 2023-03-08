package ua.stupin.analyticCentre.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ua.stupin.analyticCentre.config.HibernateUtils;
import ua.stupin.analyticCentre.entities.Device;
import ua.stupin.analyticCentre.repository.DeviceRepository;

import java.util.ArrayList;
import java.util.List;

public class DeviceRepositoryImpl extends AbstractRepository<Device> implements DeviceRepository {
    protected void init() {
        aClass = Device.class;
    }
    private static final String getMostPopularDeviceWhichIsUsedOnTheNetwork
            = "SELECT devices.model, devices.`type`, COUNT(devices.model) AS devices_frequency \n" +
            "FROM devices\n" +
            "INNER JOIN subscribers\n" +
            "ON devices.model = subscribers.device\n" +
            "GROUP BY devices.model\n" +
            "ORDER BY devices_frequency DESC\n" +
            "LIMIT 1";
    @Override
    public List<Device> getMostPopularDeviceWhichIsUsedOnTheNetwork() {
        List<Device> deviceList = new ArrayList<>();
        Session session = null;
        session = HibernateUtils.getFactory().openSession();
        session.beginTransaction();
        Query query = session.createSQLQuery(getMostPopularDeviceWhichIsUsedOnTheNetwork)
                .addEntity(aClass);
        deviceList = query.list();
        session.getTransaction().commit();
        return deviceList;
    }
}
