# Super Health Inc. API

## Description
This API will catch all the endpoint requests from the front-end of Super Health Inc. 
There is no need to install anything like in the front-end. 

### Start the Server

Right click AppRunner, and select "Run 'AppRunner.main()'"
You can also manually go to the AppRunner file and click on its green triangle to the left side of the class and it will start the server

### Connections

By default, this service starts up on port 8085 and accepts cross-origin requests from `*`.
Make sure the front-end is using this port in its constants file

### Dependencies

#### JDK

You must have a JDK installed on your machine.

#### Postgres

This server requires that you have Postgres installed and running on the default Postgres port of
5432. It requires that you have a database created on the server with the name of `postgres`

- Your username should be `postgres`
- Your password should be `root`

##### Postman Collection

Here's the link to the Postman collection: It should contain all the 200 and 400 requests required: https://www.getpostman.com/collections/c9ae5ebd0d796203bb40
They're running on port8085