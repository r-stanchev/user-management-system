# user-management-system
A relatively simple system for viewing, adding, editing and deleting users. I am using Spring and MySQL. 


## Pre-requisites
1. A Linux OS (preferably Ubuntu)
1. Java Runtime Environment installed (sudo apt install default-jre)
2. MySQL (sudo apt install mysql)


## To run the project and be able to use it normally execute the following steps:
1. Go to the ***UserManagement*** folder 
2. Execute ***./mvnw package***
3. After the project has been packaged, execute the following command:  
    ***java -jar UserManagement-0.0.1-SNAPSHOT.jar --spring.datasource.username=XXX --spring.datasource.password=YYY***    
where ***XXX*** is the username of a pre-existing user with all privileges granted and ***YYY*** is the password of the same user.
4. Go to http://localhost:8080/
