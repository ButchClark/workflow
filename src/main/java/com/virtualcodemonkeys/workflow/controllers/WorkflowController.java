package com.virtualcodemonkeys.workflow.controllers;

import com.virtualcodemonkeys.workflow.model.Order;
import com.virtualcodemonkeys.workflow.model.Provider;
import com.virtualcodemonkeys.workflow.model.Task;
import com.virtualcodemonkeys.workflow.repositories.OrderRepository;
import com.virtualcodemonkeys.workflow.repositories.ProviderRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

@RestController
public class WorkflowController {
    private OrderRepository orderRepository;
    private ProviderRepository providerRepository;

    public WorkflowController(
            OrderRepository orderRepository,
            ProviderRepository providerRepository
    ) {
        this.orderRepository = orderRepository;
        this.providerRepository = providerRepository;
    }

    @RequestMapping(value = "/workflow", method = RequestMethod.GET)
    public String handleGet() {
        return "Hello there";
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String handleOrder(@RequestParam(value = "name") String name) {
        Order order = orderRepository.findByName(name);
        if (order == null) {
            return "Order with id(" + name + ") was not found";
        } else {
            return "Order lookup if(" + name + "): " + order.toString();
        }
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public void initState() {
        System.out.println(">>> /init");
        System.out.println(" .. calling orderRepository.deleteAll()...");
        orderRepository.deleteAll();

        Provider pAAA = createProvider("ProviderAAA");
        Provider pBBB = createProvider("ProviderBBB");

        Task t1 = createTask("Task1", emptyList(), asList("Event1"));
        Task t2 = createTask("Task2", asList("Event1","Event2"), emptyList());
        Task t3 = createTask("Task3", emptyList(), emptyList());

        System.out.println(" .. calling orderRepository.save(ORDER1)...");
        Order o1 = new Order();
        o1.setName("ORDER1");
        HashSet<Provider> provs = new HashSet<>();
        provs.add(pAAA);
        provs.add(pBBB);
        o1.setProviders(provs);
        Order ret = orderRepository.save(o1);
        System.out.println("INIT: orderRepo.save() returned: " + ret.toString());

        System.out.println(" .. calling orderRepository.save(ORDER2)...");
        Order o2 = new Order();
        o2.setName("ORDER2");
        ret = orderRepository.save(o2);
        System.out.println("INIT: orderRepo.save() returned: " + ret.toString());

        // Add relationships to a provider
        HashSet<Task> tasksA = new HashSet<>();
        tasksA.add(t1);
        tasksA.add(t2);
        pAAA.setTasks(tasksA);
        providerRepository.save(pAAA);

        // Bdd relationships to a provider
        HashSet<Task> tasksB = new HashSet<>();
        tasksB.add(t3);
        pBBB.setTasks(tasksB);
        providerRepository.save(pBBB);

    }
    private Task createTask(String name, List<String> eventsemitted, List<String> eventsrequired){
        Task t = new Task();
        t.setName(name);
        t.setEventsemitted(eventsemitted);
        t.setEventsrequired(eventsrequired);
        return t;
    }

    private Provider createProvider(String name){
        Provider p = new Provider();
        p.setName(name);
        providerRepository.save(p);
        return p;
    }
}
