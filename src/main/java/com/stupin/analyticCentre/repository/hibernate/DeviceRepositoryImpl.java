package com.stupin.analyticCentre.repository.hibernate;

import com.stupin.analyticCentre.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import com.stupin.analyticCentre.entity.Device;
import com.stupin.analyticCentre.repository.DeviceRepository;

import java.util.ArrayList;
import java.util.List;

public class DeviceRepositoryImpl extends AbstractRepository<Device> implements DeviceRepository {
    protected void init() {
        aClass = Device.class;
    }
    private static final String GET_MOST_POPULAR_DEVICE_WHICH_IS_USED_ON_THE_NETWORK
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
        session = HibernateUtil.getFactory().openSession();
        session.beginTransaction();
        Query query = session.createSQLQuery(GET_MOST_POPULAR_DEVICE_WHICH_IS_USED_ON_THE_NETWORK)
                .addEntity(aClass);
        deviceList = query.list();
        session.getTransaction().commit();
        return deviceList;
    }
}
