package com.rakeshv.networkdiagrams;

import org.neo4j.springframework.data.repository.config.EnableReactiveNeo4jRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableReactiveNeo4jRepositories
public class NetworkDiagramsApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetworkDiagramsApplication.class, args);
    }

}
