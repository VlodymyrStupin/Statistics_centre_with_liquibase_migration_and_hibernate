package com.stupin.analyticCentre.repository;

import com.stupin.analyticCentre.entity.Device;

import java.util.List;

public interface DeviceRepository {
    List<Device> getMostPopularDeviceWhichIsUsedOnTheNetwork();
}
