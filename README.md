# Quiz Application Project

## Introduction

The goal of this project is to create a quiz application where one master and two or more players can play simultaneously on the same device. Every player has a button assigned. For each question, the fastest player gets to answer. If they do not give the correct answer, the second player gets a chance, and if they also answer incorrectly, the third player will have a turn, and so on. If no one provides the right answer, the game proceeds to the next question.

Each question corresponds to a score:
- **2 points** for the correct answer.
- **-1 point** for each incorrect answer.

## How the Game is Created

There are two different user roles: the **Master** and the **Player**.

The game starts when the Master creates a game. Some API calls will be made to the Trivia API ([Documentation](https://opentdb.com/api_config.php)).

### API Calls:

1. **Retrieve Token:**
   The first API call retrieves a token to avoid question repetitiveness.
   - Endpoint: `https://opentdb.com/api_token.php?command=request`
   - This token must be appended to the API GET call for question retrieval.

2. **Retrieve Questions:**
   The second API call retrieves the questions. The token must be provided in the URL as a parameter to avoid repetitive questions.
   - Endpoint: `https://opentdb.com/api.php?amount=25&token=YOURTOKENHERE`
   - By default, a game is composed of 25 questions (specified by the query string parameter `amount`).

3. **Retrieve Categories:**
   The third call retrieves the available categories.
   - Endpoint: `https://opentdb.com/api_category.php`
   - This call will not be made at every game start but will be done once a day to check if the categories stored in the database have changed. (Categories will be inserted by default if the category table is empty.)


## Technological Stack

### Database
	MySQL - A light, open-source, stable, and fast DBMS. 
	I've chosen MySQL because it has all the features I need 
	for a project of this size, where the business logic
	won't be related to the database (for example, with the usage of 
	PL/SQL constructs like packages, functions, triggers, or stored
	procedures).

## Backend
Java 17 with the Spring Boot framework - Spring Boot is one of
the most efficient frameworks for building an API.
I've chosen Spring Boot because it has many functionalities
to avoid writing boilerplate code. I will use Maven as
the dependency manager, and I will need the following dependencies
to create my API:
	1) Spring Web - Necessary to build a RESTful API with
	   Spring Boot. It also adds Tomcat as an embedded web server to
	   the project.
	2) Spring Data JPA - To interact with the database using 
	   JPA Repositories.
	3) MySQL Driver - To work with the MySQL database.
	4) Validation - To validate input data.
	5) Lombok - To avoid boilerplate code during class creation.
	6) Spring Security - To manage sign-in, sign-up, and method
	   access so players can see their scores and share the code with
	   the game master to join the game, because only master users 
	   can start games.
	7) MapStruct - To improve object-to-DTO and DTO-to-object mapping.
	8) Lombok MapStruct Binding - To make MapStruct and Lombok work
	   together.
	9) JSON Web Tokens (JWT) - To manage JWT token creation and
	decoding.
	10) Spring Reactive Web (WebFlux)** - To manage REST calls between
	 our API and the Trivia API.
	11) Quartz Scheduler - To schedule the job of updating categories once
		a day.


## Frontend
React 18.2.0 - React is an amazing framework when it comes to realize
reusable components. i will use react with the following dependencies
to help me with the implementations of the UI:
1) MaterialUI - the google component library that offers plenty of
 pre-built components.
2)Styled components - a library that helps me to manage the component
 styling directly inside components and to customize MaterialUI pre-built
 ones
 3) React Router - a library that helps me to show components under
 a certain url
 4) React Toastify - a library that helps me to show errors in a most
 attractive design
 5) Redux - a store manager that helps to manage data and dispatch API
 calls to the backend



## API Methods

In this section i want to provide in detail what will be the API methods of the beckend, what parameter they need to retrieve or to change the data and how the response will be formatted.
The base url of the api will be: **/api/v1/quizapp/**

### Sign In method
- URL: `{{HOSTNAME}}/login`
- Role allowed: unlogged
- Parameters: `{username:"username",password:"pwd"}`
- Response: `{token:"jwttoken"}`
- purpose: to let the user sign in and generate a **JWT** that will be stored on frontend localStorage. the JWT will have the following extra claims: username,gamecode.

### Sign up method
- URL: `{{HOSTNAME}}/register`
- Role allowed: unlogged
- Parameters: `{username:"username",password:"pwd",role:"default player"}`
- Response: `{token:"jwttoken"}`
- purpose: to register the user on database and generate a **JWT** that will be stored on frontend localStorage. the JWT will have the following extra claims: username,gamecode. the user will be created inside the database with the **Player** role as default. only a **Master** can update the role into **Master**. The gamecode will be randomly generated.

### Update Role method
- URL: `{{HOSTNAME}}/updateRole/{:userId}
- Role allowed: Master
- Parameters: `userId`
- Response: 
- purpose: to change user role. if the user for whom the update is requested has **Player** role the role will be set to **Master**, vice versa it will be set to **Player**

### Get all users method
- URL: `{{HOSTNAME}}/updateRole/{:userId}
- Role allowed: Master
- Parameters: Paginator, `{username:"username", gamecode:"gamecode"}`
- Response: `[{id:1,username:"username",gameCode:"gameCode"}]`
- purpose: to let the **Master** see all users and filter them


## Pages

In this section i want to provide for every page a summary of each frontend page and it's purpose