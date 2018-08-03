# Webstore

Project based on this from book: "Spring MVC: Beginner's Guide" by Amuthan G.
Because of newer Spring dependencies (5.0.2), there are some changes in comparision to code from book, to make project works.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

* Tomcat version 7 or higher
* Maven

### Installing and running

##### Go to project folder

Prepare war file by runing maven command:
```
mvn package
```

Copy webstore.war file from /target folder

##### Open Apache Tomcat folder
Navigate to webapps folder and there put war file.
If Tomcat is already running just wait a while until it will automatically unpack war file.
If Tomcat is not running, start it (e.g go to bin folder and run startup.sh or startup.bat file).

## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Handling multipart/form-data request
multipart/form-data request are used when uploading files to server, to use that feature, Tomcat serve must allow parsing multipart/form-data request.
To do that edit Tomcat context.xml, and set:
```
<Context allowCasualMultipartParsing="true">
```

see: https://tomcat.apache.org/tomcat-8.0-doc/config/context.html

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Acknowledgments

* Changes between Spring Security 3.x and 4.x: [Spring doc](https://docs.spring.io/spring-security/site/migrate/current/3-to-4/html5/migrate-3-to-4-xml.html#m3to4-xmlnamespace-form-login)
