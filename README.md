## Getting Started

This is a step by step guide how to up and run this  application using spring boot and gradle build system.


### Running the Application

*  Required softwares : java 8, node 10.12.0,angular cli 6.2.5

* Clone project from github

* Create a mysql database with name 'cmedhealth';

* For sample data please run the sql dump file from project directory.

* Update the ./app/src/main/resources/application-dev.properties file - 

    ```
    spring.datasource.username=<your_user_name>
    spring.datasource.password=<password>
    ```
    
*  Now run the following gradle commands from terminal under project root- 


```
$ ./gradlew clean
$ ./gradlew build
$ ./gradlew bootRun
```
* go to web-client directory under project root. 

```
 $ npm install 
 $ ng serve

```


* After doing all the stuffs you may successfully access the app using the base path http://localhost:4200

* For the first time it may take a while to download and install the dependency.

* Using curl from command prompt or 'Rest Client' or 'Postman' from browser plugin you could test your application now

* Some example of API: 
    * authentication , endpoint: http://localhost:8080/cmed/auth
    
    * Sample data: 

        ```
       {
       	"username" : "comillatanvir@gmail.com",
       	"password" : "admin"
       }
        ```
        
    * get Prescription list, endpoint: http://localhost:8080/cmed/api/prescriptions
    * Sample output:
        
        ```
        [
            {
                "id": 1,
                "prescriptionDate": "2020-08-20",
                "patientName": "fakrul",
                "age": 12,
                "gender": null,
                "diagnosis": "test",
                "medicine": "test",
                "nextVisitDate": "2020-08-30"
            }
        ] 
        ```
