# Backend Web Forum

Video Web:
https://youtu.be/AshNwH_3GtY

## Request Articles (get, post, put, delete)

to get a response in this api you need Postman, so we can start

to make a GET request:
Method: GET, Request: http://localhost:8080/api/posts

To make a GET request for each post:
Method: GET, Request: http://localhost:8080/api/posts/id

To add an article we will use POST:
Method: POST, Request: http://localhost:8080/api/posts

to update an article we will use PUT:
Method: PUT, Request: http://localhost:8080/api/posts

To delete an article we will use DELETE:
Method: PUT, Request: http://localhost:8080/api/posts/id

## Auth User (Login, Sign up)

To create a user you will need the POST method and when creating an article it will 
give you a JWT that you will have to put in the BEARER

Method: POST, Request: http://localhost:8080/api/auth/signup
    Bearer: "JWT" created

then to access your articles with the POST method you will need your JWT
    Bearer: "JWT" implement
    
## Tech Stack:
Backend:
Spring Boot 3.0.2, jpa, security, jwt
FrontEnd:
React Js
DB:
MySQL

