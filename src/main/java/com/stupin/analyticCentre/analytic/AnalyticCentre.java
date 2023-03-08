package com.stupin.analyticCentre.analytic;

import org.apache.commons.collections4.map.LinkedMap;
import com.stupin.analyticCentre.entity.Subscriber;
import com.stupin.analyticCentre.repository.ActivityRepository;
import com.stupin.analyticCentre.repository.DeviceRepository;
import com.stupin.analyticCentre.repository.SubscriberRepository;
import com.stupin.analyticCentre.repository.hibernate.ActivityRepositoryImpl;
import com.stupin.analyticCentre.repository.hibernate.DeviceRepositoryImpl;
import com.stupin.analyticCentre.repository.hibernate.SubscriberRepositoryImpl;

import java.util.Random;
import java.util.Scanner;

public class AnalyticCentre {
    private static final ActivityRepository ACTIVITY_REPOSITORY = new ActivityRepositoryImpl();
    private static final SubscriberRepository SUBSCRIBER_REPOSITORY = new SubscriberRepositoryImpl();
    private static final DeviceRepository DEVICE_REPOSITORY = new DeviceRepositoryImpl();
    private static final Scanner SCANNER = new Scanner(System.in);

    public void getAnalyticCentreFunction() {
        final String scanners = SCANNER.nextLine();
        switch (scanners) {
            case "1" -> {
                System.out.println("Top five subscribers which consume most of sms:");
                SUBSCRIBER_REPOSITORY.getTopFiveSubscribersWhichConsumeMostOfCallsSmsInternetSeparately("sms")
                        .forEach(System.out::println);
                System.out.println("Top five subscribers which consume most of call:");
                SUBSCRIBER_REPOSITORY.getTopFiveSubscribersWhichConsumeMostOfCallsSmsInternetSeparately("call")
                        .forEach(System.out::println);
                System.out.println("Top five subscribers which consume most of internet activity:");
                SUBSCRIBER_REPOSITORY.getTopFiveSubscribersWhichConsumeMostOfCallsSmsInternetSeparately("internet activity")
                        .forEach(System.out::println);
            }
            case "2" -> System.out.println(ACTIVITY_REPOSITORY.getMostPopularService().toString());
            case "3" -> System.out.println(DEVICE_REPOSITORY.getMostPopularDeviceWhichIsUsedOnTheNetwork());
            case "4" -> {
                System.out.println("Enter any combination of words");
                SUBSCRIBER_REPOSITORY.searchThroughSmsStorage(SCANNER.nextLine()).forEach(System.out::println);
            }
            case "5" -> {
                setTariff();
            }
            case "0" -> SCANNER.close();
            default -> {
                System.out.println("Error. Wrong char");
                SCANNER.close();
            }
        }
    }

    public Subscriber getRandomSubscriberFromDataBase() {
        Random random = new Random();
        Subscriber subscriber = SUBSCRIBER_REPOSITORY.getAll().get(random.nextInt(2000));
        return subscriber;
    }

    public void setTariff() {
        Subscriber subscriber = getRandomSubscriberFromDataBase();
        LinkedMap<String, String> tariffsMap = createMapWithTariffs();
        System.out.println("Hello " + subscriber.getFirstName() + " " +
                subscriber.getLastName() + ". ");
        System.out.println("Chose new tariff:");
        System.out.println("1: all inclusive");
        System.out.println("2: cheap call");
        System.out.println("3: cheap calls + internet");
        System.out.println("4: cheap internet");
        System.out.println("5: cheap sms");
        System.out.println("6: cheap sms + calls");
        System.out.println("7: cheap sms + internet");
        System.out.println("8: no limit calls");
        System.out.println("9: no limit gb internet");
        System.out.println("10: no limit sms");
        final String scanners = SCANNER.nextLine();
        if (subscriber.getTariff().getTariff().equals(tariffsMap.getValue(Integer.parseInt(scanners) - 1))) {
            System.out.println("It is your current tariff");
            SCANNER.close();
        } else {
            SUBSCRIBER_REPOSITORY.updateTariff(tariffsMap.getValue(Integer.parseInt(scanners) - 1)
                    , subscriber.getId());
            System.out.println(SUBSCRIBER_REPOSITORY.getSubscriberByIdAfterUpdateTariff(subscriber.getId()));
        }
    }

    public LinkedMap<String, String> createMapWithTariffs() {
        LinkedMap<String, String> stringLinkedMap = new LinkedMap<>();
        stringLinkedMap.put("1", "all inclusive");
        stringLinkedMap.put("2", "cheap call");
        stringLinkedMap.put("3", "cheap calls + internet");
        stringLinkedMap.put("4", "cheap internet");
        stringLinkedMap.put("5", "cheap sms");
        stringLinkedMap.put("6", "cheap sms + calls");
        stringLinkedMap.put("7", "cheap sms + internet");
        stringLinkedMap.put("8", "no limit calls");
        stringLinkedMap.put("9", "no limit gb internet");
        stringLinkedMap.put("10", "no limit sms");
        return stringLinkedMap;
    }
}
