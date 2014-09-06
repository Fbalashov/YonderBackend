# Yonder Backend Application

This application provides a REST endpoint for mobile devices to communicate ratings and profiles for the project Yonder application http://immense-anchorage-4417.herokuapp.com/services/people

## API



## Architecture
* Hosted on a tomcat7 in the cloud (heroku platform)
** Rest API services to declare People, Sex, and Encounters

## Technologies
* Rest services
* JPA
    
## Running the application locally
Run your application

To build your application simply run:

$ mvn package
And then run your app using the java command:

$ java -jar target/dependency/webapp-runner.jar target/*.war