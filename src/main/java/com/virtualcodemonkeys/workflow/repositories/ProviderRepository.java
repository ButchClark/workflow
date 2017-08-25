package com.virtualcodemonkeys.workflow.repositories;

import com.virtualcodemonkeys.workflow.model.Provider;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ProviderRepository extends Neo4jRepository<Provider, Long> {
    Provider findByName(String name);
}
