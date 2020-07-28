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
