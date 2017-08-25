package com.virtualcodemonkeys.workflow.repositories;

import com.virtualcodemonkeys.workflow.model.Order;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface OrderRepository extends Neo4jRepository<Order, Long> {
    Order findByName(String name);
}
