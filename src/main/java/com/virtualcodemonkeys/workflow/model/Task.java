package com.virtualcodemonkeys.workflow.model;

import lombok.Data;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import java.util.List;

@Data
@NodeEntity
public class Task {
    @GraphId
    private Long id;

    @Property(name = "name")
    private String name;

    @Property(name = "eventsemitted")
    private List<String> eventsemitted;

    @Property(name = "eventsrequired")
    private List<String> eventsrequired;
}
