# PrintCodeWebService

Main service for the BFH AppTrans exercise.

Live demo (with mock external systems) at https://bfh-pcws-mock.herokuapp.com/swagger-ui.html

See also https://github.com/SamuelBucheliZ/bfh-apptrans-exercise/blob/master/README.md

## How to build

Build war for Tomcat deployment (for the exercise)
```
mvn clean package -Pwar
``` 

Build standalone jar (for demo and Heroku)
```
mvn clean package -Pjar
```