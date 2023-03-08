package com.stupin.analyticCentre;

import com.stupin.analyticCentre.analytic.AnalyticCentre;

public class Application {
    private static final AnalyticCentre analyticCentre = new AnalyticCentre();

    public static void main(String[] args) {
        System.out.println("For invoke function from analytic centre input the number:");
        System.out.println("1: Get top five subscribers which consume most of calls sms internet separately");
        System.out.println("2: Get most popular service");
        System.out.println("3: Get most popular device which is used on the network");
        System.out.println("4: Search in sms storage");
        System.out.println("5: Change tariff");
        System.out.println("0: Close console");
        analyticCentre.getAnalyticCentreFunction();
    }
}
