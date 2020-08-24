## Getting Started

This is a step by step guide how to up and run this  application using spring boot and gradle build system.

### Prerequisites

* Java 1.8 or later
* Gradle 4.6 or later 
* PostgreSQL   
[TODO: Installation Detais]


### Running the Application

* Install all the required softwares
* Clone project from github
* Update the ./app/src/main/resources/application-dev.properties file - 

    ```
    spring.datasource.url=jdbc:mysqls://localhost/<your_db_name>
    spring.datasource.username=<your_user_name>
    spring.datasource.password=<password>
    ```
    
*  Now run the following gradle commands - 


```
$ ./gradlew clean
$ ./gradlew build
$ ./gradlew bootRun
```


* For the first time it may take a while to download and install the dependency.
* After doing all the stuffs you may succesfully access the app using the base path http://localhost:8080/kantell
* Using curl from command prompt or 'Rest Client' or 'Postman' from browser plugin you could test your application now
* Some example of API: 
    * Create organizationId using post, endpoint: http://localhost:8080/cmed/api/organizations
    * Sample data: 

        ```
        {"name": "Impel It solutions Limited"}
        ```
        
    * Create survey using post, endpoint: http://localhost:8080/kantell/api/surveys  
    * Sample data:
        
        ```
        {"title" : "Customer Satisfaction Survey", "organizationId" : "2" } 
        ```
