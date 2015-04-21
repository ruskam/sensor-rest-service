Sensor-Up Challenge: REST web service application
================
The application is implemented using Spring Boot and Spring libraries for REST web services. 

There are four packages, namely *.app, *.controllers, *.dao and *.models.

The Models package contains a Java bean for a Sensor object (Sensor.java). The Sensor object holds all parameters related to a sensor: location, heart rate, body temperature and ID. Location values are stored in the Location object (Location.java).
To deal with concurrency, the Models package also contains Callable objects generate location, heart rate and temperature. Callable objects are used because they return a value after completion.
Generation of the heart rate and temperature values is trivial and based on the values of an average dog. However, to generate dog's location we simulate movements within Saddledome area. We assume that Saddledome is represented by a circle shape. The location coordinates are generated in the UTM projected coordinate system. Calgary is located within UTM zone 11 North. Then the coordinates are converted to lat/long values for subsequent visualization. We also assume that dogs hang out together, thus dog objects are clustered.

The DAO package contains only one class, SensorDAO.java which is responsible for generating a list of sensors and executing Callable objects to assign certain values for every sensor object in the list.

The SensorController.java (Controllers package) uses Spring MVC controllers to handle GET requests. It allows to request data for all sensors ("/service/sensor") or get a sensor by its ID ("/{rfid}").

This app is purely server-side application. 
The services are availabel at the following URLs: 
http://localhost:8080/sensor-rest-service/service/sensor
http://localhost:8080/sensor-rest-service/service/sensor/id{sensor id 001 - 100}

#### Service publishing values of all sensors
![services1](https://cloud.githubusercontent.com/assets/7506777/7259422/71b3cae4-e820-11e4-9137-a9fbc6cd6da0.png)

#### Service publishing values of one sensor
![services2](https://cloud.githubusercontent.com/assets/7506777/7259423/71cb755e-e820-11e4-9246-a0a208b31577.png)

