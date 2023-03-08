package com.stupin.analyticCentre.entity.util;

import com.github.javafaker.Faker;
import com.stupin.analyticCentre.entity.Subscriber;
import com.stupin.analyticCentre.entity.Tariff;
import com.stupin.analyticCentre.entity.Device;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class GenerateSubscriber {
    public static void main(String[] args) {
        String subscribersFileName = "ThirdModuleWork\\src\\main\\resources\\subscribers.sql";
        for (int i = 0; i < 2000; i++) {
            writeToSQLFile(subscribersFileName);
        }
    }

    private static void writeToSQLFile(String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.write("insert into subscribers values (");
            writer.write("'" + createRandomSubscriber().getId() + "'" + ", ");
            writer.write("'" + createRandomSubscriber().getFirstName() + "'" + ", ");
            writer.write("'" + createRandomSubscriber().getLastName() + "'" + ", ");
            writer.write("'" + createRandomSubscriber().getPhoneNumber() + "'" + ",");
            writer.write("'" + createRandomSubscriber().getTariff().getTariff() + "'" + ",");
            writer.write("'" + createRandomSubscriber().getDevice().getModel() + "'" + ");");
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Subscriber createRandomSubscriber() {
        Faker faker = new Faker();
        Random random = new Random();
        int randomNumber = random.nextInt(9000000) + 1000000;
        Subscriber subscriber = new Subscriber();
        subscriber.setId(UUID.randomUUID().toString());
        subscriber.setFirstName(faker.name().firstName());
        subscriber.setLastName(faker.name().lastName());
        subscriber.setPhoneNumber("+38093" + randomNumber);
        subscriber.setDevice(createDevicesList().get(random.nextInt(30)));
        subscriber.setTariff(createTariffsList().get(random.nextInt(10)));
        return subscriber;
    }

    private static List<Device> createDevicesList() {
        List<Device> deviceList = new ArrayList<>();
        Device device1 = new Device("asus 1", "laptop");
        Device device2 = new Device("asus 2", "laptop");
        Device device3 = new Device("asus 3", "laptop");
        Device device4 = new Device("asus 4", "laptop");
        Device device5 = new Device("asus 5", "laptop");
        Device device6 = new Device("asus 6", "laptop");
        Device device7 = new Device("asus 7", "laptop");
        Device device8 = new Device("asus 8", "laptop");
        Device device9 = new Device("asus 9", "laptop");
        Device device10 = new Device("asus 10", "laptop");
        Device device11 = new Device("iphone 1", "phone");
        Device device12 = new Device("iphone 2", "phone");
        Device device13 = new Device("iphone 3", "phone");
        Device device14 = new Device("iphone 4", "phone");
        Device device15 = new Device("iphone 5", "phone");
        Device device16 = new Device("iphone 6", "phone");
        Device device17 = new Device("iphone 7", "phone");
        Device device18 = new Device("iphone 8", "phone");
        Device device19 = new Device("iphone 9", "phone");
        Device device20 = new Device("iphone 10", "phone");
        Device device21 = new Device("nvidia shield 1", "tablet");
        Device device22 = new Device("nvidia shield 2", "tablet");
        Device device23 = new Device("nvidia shield 3", "tablet");
        Device device24 = new Device("nvidia shield 4", "tablet");
        Device device25 = new Device("nvidia shield 5", "tablet");
        Device device26 = new Device("nvidia shield 6", "tablet");
        Device device27 = new Device("nvidia shield 7", "tablet");
        Device device28 = new Device("nvidia shield 8", "tablet");
        Device device29 = new Device("nvidia shield 9", "tablet");
        Device device30 = new Device("nvidia shield 10", "tablet");
        deviceList.add(device1);
        deviceList.add(device2);
        deviceList.add(device3);
        deviceList.add(device4);
        deviceList.add(device5);
        deviceList.add(device6);
        deviceList.add(device7);
        deviceList.add(device8);
        deviceList.add(device9);
        deviceList.add(device10);
        deviceList.add(device11);
        deviceList.add(device12);
        deviceList.add(device13);
        deviceList.add(device14);
        deviceList.add(device15);
        deviceList.add(device16);
        deviceList.add(device17);
        deviceList.add(device18);
        deviceList.add(device19);
        deviceList.add(device20);
        deviceList.add(device21);
        deviceList.add(device22);
        deviceList.add(device23);
        deviceList.add(device24);
        deviceList.add(device25);
        deviceList.add(device26);
        deviceList.add(device27);
        deviceList.add(device28);
        deviceList.add(device29);
        deviceList.add(device30);
        return deviceList;
    }
    private static List<Tariff> createTariffsList() {
        List<Tariff> tariffList = new ArrayList<>();
        Tariff tariff1 = new Tariff("all inclusive", 100);
        Tariff tariff2 = new Tariff("cheap call", 32);
        Tariff tariff3 = new Tariff("cheap calls + internet", 52);
        Tariff tariff4 = new Tariff("cheap internet", 24);
        Tariff tariff5 = new Tariff("cheap sms", 20);
        Tariff tariff6 = new Tariff("cheap sms + calls", 42);
        Tariff tariff7 = new Tariff("cheap sms + internet", 55);
        Tariff tariff8 = new Tariff("no limit calls", 65);
        Tariff tariff9 = new Tariff("no limit gb internet", 68);
        Tariff tariff10 = new Tariff("no limit sms", 66);
        tariffList.add(tariff1);
        tariffList.add(tariff2);
        tariffList.add(tariff3);
        tariffList.add(tariff4);
        tariffList.add(tariff5);
        tariffList.add(tariff6);
        tariffList.add(tariff7);
        tariffList.add(tariff8);
        tariffList.add(tariff9);
        tariffList.add(tariff10);
        return tariffList;
    }
}
