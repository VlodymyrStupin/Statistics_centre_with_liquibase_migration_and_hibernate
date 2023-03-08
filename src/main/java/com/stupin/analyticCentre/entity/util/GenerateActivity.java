package com.stupin.analyticCentre.entity.utill;


import com.github.javafaker.Faker;
import com.stupin.analyticCentre.entity.Activity;
import com.stupin.analyticCentre.entity.Subscriber;
import com.stupin.analyticCentre.repository.hibernate.SubscriberRepositoryImpl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;


public class GenerateActivity {
    private static final SubscriberRepositoryImpl subscriberRepository = new SubscriberRepositoryImpl();

    public static void main(String[] args) {
        List<Subscriber> subscriberList = subscriberRepository.getAll();
        Random random = new Random();
        Faker faker = new Faker();
        String activitiesFileName = "ThirdModuleWork\\src\\main\\resources\\META-INF\\sqlQuery\\activities.sql";
        for (int i = 0; i < 2000; i++) {
            String message = faker.chuckNorris().fact().replace("'", "");
            writeToSQLFile(activitiesFileName, subscriberList.get(random.nextInt(1999)), message);
        }
    }
    public static Activity makeActivity(Subscriber subscriber, String message) {
        Activity activity = new Activity();
        List<String> listOfActivityTypes = new ArrayList<>();
        Random random = new Random();
        listOfActivityTypes.add("sms");
        listOfActivityTypes.add("call");
        listOfActivityTypes.add("internet activity");
        activity.setId(UUID.randomUUID().toString());
        activity.setDate(LocalDateTime.now());
        activity.setType(listOfActivityTypes.get(random.nextInt(3)));
        if (activity.getType().equals("sms")) {
            activity.setLogOfActivities(message);
        } else {
            activity.setLogOfActivities("");
        }
        activity.setSubscriber(subscriber);
        return activity;
    }
    private static void writeToSQLFile(String fileName, Subscriber subscriber, String message) {
        Activity activity = makeActivity(subscriber, message);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.write("insert into activities values (");
            writer.write("'" + activity.getId() + "'" + ", ");
            writer.write("'" + activity.getDate() + "'" + ", ");
            writer.write("'" + activity.getType() + "'" + ", ");
            writer.write("'" + activity.getLogOfActivities() + "'" + ",");
            writer.write("'" + subscriber.getId() + "'" + ");");
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
