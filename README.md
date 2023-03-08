Implementation an analytic system based on Hibernate to manage the activity of mobile operator and analyze results of it to get some statistical indicators.

System allow to keep data about:
-Abonents
-Tariffs
-Type of activity for every abonent (rings, sms, internet activities)
-Used cell phones or any other equipment.
-Log of activities (storing sms)

Initial set of data fulfilled by liquibase migration. This set count 2000 abonents.
  • System allow to search through sms storage by any combination of words
  • System allow to get following statistical indicators about activities of abonents:
     - Top 5 abonents which consume most of calls, sms, internet (separately)
     - Which service is most popular?
     - Most popular device which is used on the network.
     
Interaction with these features using console.
