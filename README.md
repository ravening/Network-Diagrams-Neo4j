# Network diagrams using Ne04j

This is a project to drar network diagrams using neo4j

## Start neo4j
```
docker run -it -d --publish=7474:7474 --publish=7687:7687 -e 'NEO4J_AUTH=neo4j/secret' neo4j:4.0.4
```

## start the application
```
./mvnw spring-boot:run
```

## Navigate to ui
```
http://localhost:7474/browser/
```

## Build docker image
```
./mvnw spring-boot:build-image
```

## Running the application
```
./mvnw spring-boot:run
```

## Navigate to link in your browser
```
http://localhost:8080/upload
```

Now you need to upload several files to create a proper diagrams

First upload the equipments by clicking on `Upload Equipments` and select `equipments.csv` from `csv` folder in this repo

Once that is done, upload the interfaces by clicking on `Upload interfaces` and select `interfaces.csv` file.

Now upload the connections by clicking on `Upload Connections` and select `connections.csv`.\
Similarly you need to upload another connections file `anotherconnections.csv`

Optionally you can upload vlans by selecting the `vlan.csv` file.connections

## Viewing the diagrams

Navigate to `http://localhost:7474/browser/` which gives you the neo4j ui.\
Click on the database icon. Watch the below video to see how it looks when all connections are created.


![Video](https://media.giphy.com/media/lrtW3W9bGnU3p2QykN/giphy.gif)

## REST api's


There are several api's provided to manage the equipments, interfaces and vlans.\
Head over to `http://localhost:8080/swagger-ui.html` to execute several end points.

