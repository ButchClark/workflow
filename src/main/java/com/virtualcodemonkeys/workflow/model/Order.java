package com.virtualcodemonkeys.workflow.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

@Data
//@Builder
@NoArgsConstructor
@NodeEntity
public class Order {
    @GraphId
    private Long id;

    @Property(name = "name")
    String name;

    @Relationship(type = "Provider")
    Set<Provider> providers;
}
