package com.drive.hire.driverhire.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoDbConfig {

    private final String mongoDbConnectionUri;

    public MongoDbConfig(@Value("${driver.hire.mongo.db.uri:}") String mongoDbConnectionUri) {
        this.mongoDbConnectionUri = mongoDbConnectionUri;

    }

    @Bean
    public MongoTemplate mongoTemplate() {

//        Map<String, Object> map = new HashMap<>();
//        map.put("name", "John");
//        map.put("age", 25);

        String uri = mongoDbConnectionUri;
        MongoClient client = MongoClients.create(uri);
        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(client, "driverHire"));
//        template.insert(new Document(map), "driverHire");
    }
}
