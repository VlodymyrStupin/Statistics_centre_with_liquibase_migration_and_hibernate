package ua.stupin.analyticCentre.repository;

import ua.stupin.analyticCentre.entities.Subscriber;

import java.util.List;

public interface SubscriberRepository extends GenericRepository<Subscriber>{
    List<Subscriber> getTopFiveSubscribersWhichConsumeMostOfCallsSmsInternetSeparately(String type);
    List<Subscriber> searchThroughSmsStorage(String message);
    void updateTariff(String tariff, String subscriberId);
    List<Subscriber> getSubscriberByIdAfterUpdateTariff(String id);
}
