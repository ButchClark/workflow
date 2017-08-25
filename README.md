# workflow
Neo4j-based Workflow project including Order/Provider/Task hierarchy


## Structure
This POC project models three objects as nodes:
* Order
* Providers
* Task

An order can have 0-n Providers
A provider can have 0-n Tasks
A Task has embedded List<String>'s for
* EventsRequired
* EventsEmitted

## Usage
### Neo4j
It is assumed you have neo4j running locally with the following creds:
*  Username:  neo4j
*  Password:  neo4j

Any deviations can be handled by updating the settings in the application.properties file.

### Start service
To start the service, execute the following:

```.\gradlew bootRun```

### Initialize the database
To initialize the database, hit the following endpoint:

```http://localhost:8080/init```

You can see in the controller that it will delete all info in the database, and populate two orders, a couple of providers, and some tasks.

### Verifing the code
You can retrieve a single order by hitting the following endpoint:

```http://localhost:8080/order?name=ORDER1```

In addition, I recommend using the Neo4j interface at:

```http://localhost:7474```


