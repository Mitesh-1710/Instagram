# Spring Boot Project Doctor App.

- Frameworks and Language used :
  - Spring Boot `SNAPSHOT 3.0.6`
  - Java 17

- Data Flow :
  1. Controller
      <br/>
      > Patient
      - signup : This method is used to call the signup method of the service class to save the patient data into the database.
      - signin : This method is used to call the signin method of the service class which authenticate patient based on their email and password.
      - getAllDoctors : This method is used to call the getAllDoctors method of the service class to get all doctor data from the database.
      - cancelAppointment : This method is used to call the cancelAppointment method of the service class to cancel the appointment.
       
      <br/>
      
      > Appointment
      - bookAppointment : This method is used to call the bookAppointment method of the service class to book an appointment of the patient.
         
      <br/>
      
      > Doctor
      - addDoctors : This method is used to call the addDoctors method of the service class save the doctor data into the database
      - geMyAppointments : This method is used to call the geMyAppointments method of the service class to get all the doctors appointments data from the database
        
  2. Services
      <br/>
      > Patient
      - signup : This method is used to call the save method of the repository class to save the user data into the database.
      - signin :  This method is also used to call the getToken method of the authentication service class to authenticate user based on their email and password.
      - getAllDoctors :  This method is used to call the getAllDoctors method of the doctor service class to get all the the doctor data from the database.
      - cancelAppointment : This method is used to call the cancelAppointment method of the appointment service class to cancel the appointment.
     
      <br/>
      
      > Doctor
      - addDoctors : This method is used to call the save method of the repository class to save the appointment data into the database.
      - geMyAppointments : This method is used to call the deleteById method of the repository class to delete the appointment data from the database.
      
       <br/>
      
      > Appointment
      - bookAppointment : This method is used to call the save method of the repository class to save the doctors data into the database.
      - getAllDoctors : This method is used to call the findAll method of the repository class to get all the doctors data from the database.
      - getMyAppointments : This method is used to call the findByDoctorId method of the repository class to get all the doctor's appointments data from the database.
      
      
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
	    table patient (
	    id bigint not null auto_increment,
	        email varchar(255) not null,
	        first_name varchar(255) not null,
	        last_name varchar(255) not null,
	        password varchar(255) not null,
	        phone_number varchar(255) not null,
	        primary key (id)
	    )
      
      	table doctor (
       	doctor_id bigint not null auto_increment,
        	doctor_name varchar(255),
        	specialization varchar(255),
        	primary key (doctor_id)
    	)
      
       	table authentication (
       	id bigint not null auto_increment,
        	created_date datetime(6) not null,
        	token varchar(255) not null,
        	user_id bigint not null,
        	primary key (id)
    	)
    	
		table appointment (
       	app_id bigint not null,
        	time datetime(6) not null,
        	fk_doctor_doc_id bigint,
        	patient_id bigint,
        	primary key (app_id, time)
    	)
      ```
   
- Data Structure used in project :
  - List

- Project Summary :
```
  This is a Spring Boot project of Doctor App. User(Patient/Doctor) can register themselves by filling the required information.
  Upon registration basic validation applied to the filled data if all the validation passes then and only then a data is registered into the system.
  Once registered User can able to signin into the app, can able fetch their saved information. Patient can book appointment as we as can able to
  see the booked appointment details. Doctor can fetch and see all the appointment data booked under their name.
```
