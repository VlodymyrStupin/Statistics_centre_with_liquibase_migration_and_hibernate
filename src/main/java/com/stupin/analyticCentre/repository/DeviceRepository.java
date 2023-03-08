package ua.stupin.analyticCentre.repository;

import ua.stupin.analyticCentre.entities.Device;

import java.util.List;

public interface DeviceRepository {
    List<Device> getMostPopularDeviceWhichIsUsedOnTheNetwork();
}
