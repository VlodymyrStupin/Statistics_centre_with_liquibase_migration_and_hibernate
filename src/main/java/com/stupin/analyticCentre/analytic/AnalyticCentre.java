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

public class AnalyticCentreUtil {
    private static final ActivityRepository activityRepository = new ActivityRepositoryImpl();
    private static final SubscriberRepository subscriberRepository = new SubscriberRepositoryImpl();
    private static final DeviceRepository deviceRepository = new DeviceRepositoryImpl();
    private static final Scanner scanner = new Scanner(System.in);
    private static LinkedMap<String, String> tariffsMap = new LinkedMap<>();

    public void getAnalyticCentreFunction() {
        final String scanners = scanner.nextLine();
        switch (scanners) {
            case "1" -> {
                System.out.println("Top five subscribers which consume most of sms:");
                subscriberRepository.getTopFiveSubscribersWhichConsumeMostOfCallsSmsInternetSeparately("sms")
                        .forEach(System.out::println);
                System.out.println("Top five subscribers which consume most of call:");
                subscriberRepository.getTopFiveSubscribersWhichConsumeMostOfCallsSmsInternetSeparately("call")
                        .forEach(System.out::println);
                System.out.println("Top five subscribers which consume most of internet activity:");
                subscriberRepository.getTopFiveSubscribersWhichConsumeMostOfCallsSmsInternetSeparately("internet activity")
                        .forEach(System.out::println);
            }
            case "2" -> System.out.println(activityRepository.getMostPopularService().toString());
            case "3" -> System.out.println(deviceRepository.getMostPopularDeviceWhichIsUsedOnTheNetwork());
            case "4" -> {
                System.out.println("Enter any combination of words");
                subscriberRepository.searchThroughSmsStorage(scanner.nextLine()).forEach(System.out::println);
            }
            case "5" -> {
                setTariff();
            }
            case "0" -> scanner.close();
            default -> {
                System.out.println("Error. Wrong char");
                scanner.close();
            }
        }
    }

    public Subscriber getRandomSubscriberFromDataBase() {
        Random random = new Random();
        Subscriber subscriber = subscriberRepository.getAll().get(random.nextInt(2000));
        return subscriber;
    }

    public void setTariff() {
        Subscriber subscriber = getRandomSubscriberFromDataBase();
        tariffsMap = createMapWithTariffs();
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
        final String scanners = scanner.nextLine();
        if (subscriber.getTariff().getTariff().equals(tariffsMap.getValue(Integer.parseInt(scanners) - 1))) {
            System.out.println("It is your current tariff");
            scanner.close();
        } else {
            subscriberRepository.updateTariff(tariffsMap.getValue(Integer.parseInt(scanners) - 1)
                    , subscriber.getId());
            System.out.println(subscriberRepository.getSubscriberByIdAfterUpdateTariff(subscriber.getId()));
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
