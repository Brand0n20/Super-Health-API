# Super Health Inc. API

## Description
This API will catch all the endpoint requests from the front-end of Super Health Inc. 
There is no need to install anything like in the front-end. It contains controller classes to catch the endpoints, service classes to move the data coming through and 
has two repositories to connect to the database.

### Start the Server

Right click AppRunner, and select "Run 'AppRunner.main()'"
You can also manually go to the AppRunner file and click on its green triangle to the left side of the class and it will start the server

### Connections

By default, this service starts up on port 8085 and accepts cross-origin requests from `*`.
Make sure the front-end is using this port in its constants file

### Dependencies
- Spring Boot Framework was used which includes other dependencies such as: spring-boot-starter-web, spring-boot-starter-test
- Validation-api, persistence-api
- For testing, JUnit was used, along with Mockito
- For databases, postgresql was used and h2 database for testing
- These are all included in the project already

#### JDK

You must have a JDK installed on your machine.

#### Postgres

This server requires that you have Postgres installed and running on the default Postgres port of
5432. It requires that you have a database created on the server with the name of `postgres`

- Your username should be `postgres`
- Your password should be `root`

##### Postman Collection

Here's the link to the Postman collection: It should contain all the 200 and 400 requests required: https://www.getpostman.com/collections/2904ed8289dd64bfc324
They're running on port8085

###### Testing

- There's a 'test' folder which includes different tests for the encounter and patient classes.
- To see the total coverage of everything, right-click on the domains folder that's inside the 'test' folder and there should be an
option that will say something along the lines of'Run tests in domains with coverage'
- You will get a coverage report as well
- To run single unit/integration tests, click on the green triangle that's next to any of the tests in the file.
- To run all the tests for that class at once, click on the green triangle that's right next to the class name.
- A test passed if there's a green checkmark on the green triangle, the test failed if there's a red checkmark on the green triangle.