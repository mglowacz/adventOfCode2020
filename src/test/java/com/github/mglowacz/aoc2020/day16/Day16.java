package com.github.mglowacz.aoc2020.day16;

import com.github.mglowacz.aoc2020.FileInputSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day16 {
    @Test
    public void test() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day16/test");
        TicketChecker ticketChecker = new TicketChecker(strings);
        assertEquals(71, ticketChecker.checkTicketsNote());
    }

    @Test
    public void data() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day16/data");
        TicketChecker ticketChecker = new TicketChecker(strings);
        assertEquals(20231, ticketChecker.checkTicketsNote());
    }


    @Test
    public void test2() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day16/test2");
        TicketChecker ticketChecker = new TicketChecker(strings);
        ticketChecker.checkTicketsNote();
        ticketChecker.getFields();
        ticketChecker.fieldsIndexes.size();

        while(ticketChecker.fieldsIndexes.values().stream().map(integers -> integers.size() - 1).reduce(Integer::sum).orElseThrow() > 0) {
            ticketChecker.fieldsIndexes.values().stream().filter(integers -> integers.size() == 1).map(integers -> integers.iterator().next()).forEach(integer0 ->
                    ticketChecker.fieldsIndexes.values().forEach(integers -> {
                            if (integers.size() != 1) {
                                integers.remove(integer0);
                            }
                    })
            );
        }

        ticketChecker.fieldsIndexes.forEach((key, value) -> System.out.println(key + " is " + ticketChecker.myTicket.get(value.iterator().next())));
        //assertEquals(71, ticketChecker.fieldsIndexes.entrySet().stream().filter(stringSetEntry -> stringSetEntry.getKey().startsWith("departure ")).map(stringSetEntry -> stringSetEntry.getValue().iterator().next()).map(ticketChecker.myTicket::get).reduce((integer, integer2) -> {return integer * integer2;}).orElseThrow());
    }

    @Test
    public void data2() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day16/data");
        TicketChecker ticketChecker = new TicketChecker(strings);
        ticketChecker.checkTicketsNote();
        ticketChecker.getFields();
        assertEquals(ticketChecker.fieldsIndexes.size(), ticketChecker.myTicket.size());

        while(ticketChecker.fieldsIndexes.values().stream().map(integers -> integers.size() - 1).reduce(Integer::sum).orElseThrow() > 0) {
            ticketChecker.fieldsIndexes.values().stream().filter(integers -> integers.size() == 1).map(integers -> integers.iterator().next()).forEach(integer0 ->
                    ticketChecker.fieldsIndexes.values().forEach(integers -> {
                        if (integers.size() != 1) {
                            integers.remove(integer0);
                        }
                    })
            );
        }

        ticketChecker.fieldsIndexes.forEach((key, value) -> System.out.println(key + " is " + ticketChecker.myTicket.get(value.iterator().next())));

        assertEquals(1940065747861L,
                ticketChecker.fieldsIndexes.entrySet().stream().filter(stringSetEntry -> stringSetEntry.getKey().startsWith("departure ")).map(stringSetEntry -> stringSetEntry.getValue().iterator().next()).map(ticketChecker.myTicket::get).map(Long::valueOf).reduce((l0, l1) -> l0 * l1).orElseThrow()
        );
    }

}
