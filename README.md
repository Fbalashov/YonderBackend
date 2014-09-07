# Yonder Backend Application

This application provides a REST endpoint for mobile devices to communicate ratings and profiles for the project Yonder application http://immense-anchorage-4417.herokuapp.com/

## API
Substitute <<APP-NAME>> with immense-anchorage-4417
Root to all requests: http://<<APP-NAME>>.herokuapp.com/services/

*Add the listed extension for each request after it. EX for the extension "abba/dabba"
you would query : http://immense-anchorage-4417.herokuapp.com/services/abba/dabba

**Action** || **HTTP request type**  || **Return Type** || **extension**  

* Get all users in database || GET || JSON array || people
* Add user to database, generates & returns private key || POST || Text on success|| people/new/
* Edit user in database, generates & returns private key || POST || Text on success|| people/{{id}}
* Get specific user from db || GET || JSON array on success, error if not found || people/{{id}}
* Remove specific user from db || DELETE || nothing on success or error || people/{{id}}
*

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