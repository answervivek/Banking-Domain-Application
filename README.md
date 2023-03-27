# Banking-Domain-Application
Project Description: This project will simulate a simple banking application where customers can open accounts, deposit and withdraw money, and transfer money to other accounts. The application will be divided into microservices, and Kafka will be used to handle inter-service communication.
Microservices:
1. Account Service - Responsible for managing accounts, including creating new accounts, getting account information, and updating account balances.
2. Transaction Service - Responsible for managing transactions, including deposit, withdrawal, and transfer operations.
3. Notification Service - Responsible for sending notifications to customers about their account activities.
Technologies:
1. Java 11
2. Spring Boot
3. Kafka
4. MySQL
5. Maven
Project Setup:
1. Create a new Spring Boot project using the Spring Initializr.
2. Add the necessary dependencies, including Spring Web, Spring Data JPA, and Kafka.
3. Set up a MySQL database and configure Spring Data JPA to use it.
4. Create a Kafka topic for each microservice.
Microservice Implementation:
1. Account Service:
• Create an Account entity with an ID, balance, and customer name.
• Create a REST API for creating new accounts, getting account information, and updating account balances.
• Use Spring Data JPA to interact with the database.
• Use Kafka to send account creation and balance update events to the Transaction Service.
2. Transaction Service:
• Create a Transaction entity with an ID, type (deposit, withdrawal, or transfer), amount, source account ID, and destination account ID.
• Create a REST API for processing transactions.
• Use Spring Data JPA to interact with the database.
• Use Kafka to listen for account creation and balance update events from the Account Service and update transaction records accordingly.
• Use Kafka to send notification events to the Notification Service.
3. Notification Service:
• Create a Notification entity with an ID, type (email, SMS, etc.), message, and recipient.
• Create a REST API for sending notifications.
• Use Kafka to listen for notification events from the Transaction Service and send notifications accordingly.
Once the microservices are implemented, they can be deployed and tested independently. Kafka will ensure that events are propagated between the services in a scalable and fault-tolerant manner.
![image](https://user-images.githubusercontent.com/85492276/227886550-563322ec-22c7-48ef-afec-b6d9a53d072d.png)
