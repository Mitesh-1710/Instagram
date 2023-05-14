# Spring Boot Project Instagram App.

- Frameworks and Language used :
  - Spring Boot `SNAPSHOT 3.0.6`
  - Java 17

- Data Flow :
  1. Controller
      <br/>
      > User
      - signup : This method is used to call the signup method of the service class to save the user data into the database.
      - signin : This method is used to call the signin method of the service class which authenticate user based on their email and password.
      - updateUser : This method is used to call the updateUser method of the service class to updated user data in the database based on selected user id.
       
      <br/>
      
      > Post
      - savePost : This method is used to call the savePost method of the service class to save the post data into the database before saving user will be authenticated.
      - getPost :  This method is used to call the getPost method of the service class to get the post data from the database based on selected post id before fetching user will be authenticated.
        
  2. Services
      <br/>
      > User
      - signup : This method is used to call the save method of the repository class to save the user data into the database.
      - signin :  This method is also used to call the getToken method of the authentication service class to authenticate user based on their email and password.
      - updateUser :  This method is also used to call the save method of the repository class to updated the user data in the database based on selected user id before updating user will be authenticated.
     
      <br/>
      
      > Post
      - addAddress : This method is used to call the save method of the repository class to save the post data into the database.
      - getPost :  This method is used to call the findById method of the repository class to get the post data from the database based on selected post id.
      
      <br/>
      
      > Authentication
      - saveToken : This method is used to call the save method of the repository class to save the authentication data into the database.
      - getToken :  This method is also used to call the findByUser method of the repository class to get the token data from the database based on signin user.
      - authenticate : This method is used to call the findFirstByToken method of the repository class to get the token data from the database based on selected email and token to authenticate user.
      
  3. Repository
      - Used `Predefined` JpaRepository methods such as findById , save for basic CRUD operations.
      
      <br/>
      
      > User
      - findFirstByEmail `Userdefined` : This method is used to get the user data from the database based on user email.
       
      <br/>
      
      > Authentication
      - findByUser `Userdefined` : This method is used to get the authentication data from the database based on user.
      - findFirstByToken `Userdefined` : This method is used to get the token data from the database based on token.
      
  4. Database Design
      - Used MySQL Database
      ```
      	table user (
       	id bigint not null auto_increment,
        	age integer not null,
        	email varchar(255) not null,
        	first_name varchar(255) not null,
        	last_name varchar(255) not null,
        	password varchar(255) not null,
        	phone_number varchar(255) not null,
        	primary key (id)
    	)
      
       	table authentication (
       	id bigint not null auto_increment,
        	created_date datetime(6) not null,
        	token varchar(255) not null,
        	user_id bigint not null,
        	primary key (id)
    	)
    	
		table post (
       	id bigint not null auto_increment,
        	created_date datetime(6) not null,
        	post_data varchar(255) not null,
        	updated_date datetime(6) not null,
        	user_id bigint,
        	primary key (id)
    	)
      ```
   
- Data Structure used in project :
  - List

- Project Summary :
```
  This is a Spring Boot project of Instagram App. User can register themselves by filling the required information.
  Upon registration basic validation applied to the filled data if all the validation passes then and only then a data is registered into the system.
  Once registered User can able to signin into the app, can able fetch their saved information , can able to update the information ,
  as well as can able to created new post and able to see the post's information.
```
