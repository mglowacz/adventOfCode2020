package com.github.mglowacz.aoc2020.day16;

import java.util.*;
import java.util.stream.Collectors;

public class TicketChecker {
    Map<String, List<Integer>> attributes = new HashMap<>();
    List<Integer> myTicket = new LinkedList<>();
    List<List<Integer>> nearbyTickets = new LinkedList<>();
    List<List<Integer>> validTickets = new LinkedList<>();
    Map<String, Set<Integer>> fieldsIndexes = new HashMap<>();

    public TicketChecker(List<String> strings) {
        String line;
        int i = 0;
        while ((line = strings.get(i++)).length() > 0) {
            attributes.put(line.split(":")[0], Arrays.stream(line.split(":")[1].split("or")).flatMap(s -> Arrays.stream(s.split("or")).flatMap(s1 -> Arrays.stream(s1.split("-"))).map(String::trim).map(Integer::parseInt)).collect(Collectors.toList()));
        }

        while ((line = strings.get(i++)).length() > 0) {
            if (line.equals("your ticket:")) continue;
            Arrays.stream(line.split(",")).map(Integer::parseInt).forEach(myTicket::add);
        }

        while (i < strings.size() && (line = strings.get(i++)).length() > 0) {
            if (line.equals("nearby tickets:")) continue;
            List<Integer> ticket = new LinkedList<>();
            Arrays.stream(line.split(",")).map(Integer::parseInt).forEach(ticket::add);
            nearbyTickets.add(ticket);
        }
    }

    public void getFields() {
        attribute : for(Map.Entry<String, List<Integer>> e : attributes.entrySet()) {
            field : for (int i = 0 ; i < validTickets.get(0).size(); i++) {
                for (List<Integer> ticket : validTickets) {
                    List<Integer> values = e.getValue();
                    if (ticket.get(i) < values.get(0) || ticket.get(i) > values.get(3) || (ticket.get(i) > values.get(1) && ticket.get(i) < values.get(2)))  {
                        continue field;
                    }
                }
                fieldsIndexes.putIfAbsent(e.getKey(), new HashSet<>());
                fieldsIndexes.get(e.getKey()).add(i);
            }
        }
    }

    public int checkTicketsNote() {
        validTickets.addAll(nearbyTickets);
        List<Integer> invalidValues = new LinkedList<>();

        for (List<Integer> ticket : nearbyTickets) {
            field:
            for (Integer field : ticket) {
                for (List<Integer> attribute : attributes.values()) {
                    if ((field >= attribute.get(0) && field <= attribute.get(1)) || (field >= attribute.get(2)) && field <= attribute.get(3)) {
                        continue field;
                    }
                }
                invalidValues.add(field);
                validTickets.remove(ticket);
            }
        }
        return invalidValues.stream().reduce(Integer::sum).orElse(0);
    }
}
