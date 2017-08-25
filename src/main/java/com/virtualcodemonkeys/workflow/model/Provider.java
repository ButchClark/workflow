package com.virtualcodemonkeys.workflow.model;

import lombok.Data;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

@Data
@NodeEntity
public class Provider {
    @GraphId
    private Long id;

    @Property(name = "name")
    private String name;

    @Relationship(type = "Task")
    Set<Task> tasks;
}
