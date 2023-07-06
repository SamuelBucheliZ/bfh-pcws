# PrintCodeWebService (PC-WS)

Main service for the BFH AppTrans exercise.

Live demo (with mock external systems) at https://bfh-pcws-mock.herokuapp.com/swagger-ui/index.html

See also https://github.com/SamuelBucheliZ/bfh-apptrans-exercise/blob/master/README.md

## Versions

PC-WS is known to work with the following versions

- Java: 11
- Tomcat: 9
- PostgreSQL: 10

## Installation

The relevant artifacts can be found here https://github.com/SamuelBucheliZ/bfh-pcws/releases/tag/latest-war

1. Set up database using [setup_db.sql](https://github.com/SamuelBucheliZ/bfh-pcws/releases/download/latest-war/setup_db.sql).
2. Set up cronjob to run [transfer_logs.sh](https://github.com/SamuelBucheliZ/bfh-pcws/releases/download/latest-war/transfer_logs.sh) every minute.
3. Deploy [pcws.war](https://github.com/SamuelBucheliZ/bfh-pcws/releases/download/latest-war/pcws.war) in Tomcat.

Note: PC-WS provides a "mock" configuration (see below) which allows to run it in a demo mode without need for a database or external systems. This can be used to initially test the Tomcat setup, i.e., to directly start with step 3 above.

## Configuration

The PrintCode-WebService can be configured using the following environment variables
- `SPRING_PROFILES_ACTIVE`: Either `real` (to use the actual external services and database)  or `mock` (to use mocked services). For the exercise, eventually, the `real` configuration must be used.
- `PCWS_DATALOG_DIRECTORY`: Absolute path to the directory the service should write the datalogs into, e.g., `/datalogDirectory`.
- `PCWS_ACCOUNT_SERVICE_URL`: URL of the account service, you can use `https://bfh-paketblitz-account-service.herokuapp.com`.
- `PCWS_DATASOURCE_JDBC_URL`: JDBC URL of the database, this has the form `jdbc:postgresql://<hostname>:<port>/<database-name>`.
- `PCWS_DATASOURCE_USERNAME` and `PCWS_DATASOURCE_PASSWORD`: Credentials to access the database.

The shell script to transfer the logs can be configured using the following environment variables
- `PCWS_DATALOG_DIRECTORY`: Location of the datalog files to send. 
- `PCWS_DATALOG_SERVICE_URL`: URL of the datalog service, you can use `https://bfh-paketblitz-datalog-service.herokuapp.com`.

## How to build

Build war for Tomcat deployment (for the exercise)
```
mvn clean package -Pwar
``` 

Build standalone jar (for demo on Heroku)
```
mvn clean package -Pjar
```