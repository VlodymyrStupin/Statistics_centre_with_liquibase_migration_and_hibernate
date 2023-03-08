package com.stupin.analyticCentre.repository;

import com.stupin.analyticCentre.entity.Subscriber;

import java.util.List;

public interface SubscriberRepository extends GenericRepository<Subscriber>{
    List<Subscriber> getTopFiveSubscribersWhichConsumeMostOfCallsSmsInternetSeparately(String type);
    List<Subscriber> searchThroughSmsStorage(String message);
    void updateTariff(String tariff, String subscriberId);
    List<Subscriber> getSubscriberByIdAfterUpdateTariff(String id);
}
