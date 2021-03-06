REQUIREMENTS
============
.- First of all, install 'docker' in your computer.
.- Rename file src/main/resources/static/products.js_s to .js
.- This is an Spring Boot Application with all needed dependencies included in the POM file.
.- You have a docker file to run the Application. The command is

docker-compose up


EXERCISES
=========
1.- Implement REST API to Create, Retrieve (all, byId, byEmail), Update & Delete Account from / To Database.
The account can have multiple addresses & they (the addresses) have to be showed in the data returned (only for retrieve methods).
Account entity should have (at least) the next attributes:
    name   	    (Alphanumeric)
    email	    (Alphanumeric)
    age		    (Number)
    addresses   (Collection of account address)

Fields of Address:
    address (Alphanumeric)

3.- Create REST service to retrieve Pokemons (the name of the endpoint must be '/pokemon') that its name starts with {parameter}. The result is a JSON containing any field from the pokemon.
4.- Implement any Basic Security for the Pokemons REST service.
5.- We want to execute 3 free processes in paralel & we want to execute a 4th process when the others 3 has finished.
Could you implement a sample REST API (only GET method with endpoint "/thread") to demostrate this behaviour ?
Could you implement a Basic Security for the prior REST method ?

6.- Create Unit Testing for any class of the Implemented Service Layer.
7.- Optional --> create the WEB page to manage (CRUD) the accounts.

RESULT COMMENTS
===============

1. Only the UI, /pokemon and /thread endpoints have authentication. They have simple authentication with User= user and Password= password
2. I have added the Postman configuration I've used to test created endpoints
3. Regarding /thread endpoint, the result is a list of 4 dates. The first 3 dates are the ones generated by the 3 first threads. The 4th date is the one generated by last thread. Each thread
should wait the same amount of time. The idea is that the first 3 dates should be similar, and the 4th should show the time when the other 3 are finished.
4. I have created a UI to manage the accounts based on the product UI. I have added some new features in order to deal with addresses.
