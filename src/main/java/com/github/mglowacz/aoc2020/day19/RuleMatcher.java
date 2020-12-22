package com.github.mglowacz.aoc2020.day19;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class RuleMatcher {

    private final List<String> messages = new LinkedList<>();
    private final Map<String, String> rules = new HashMap<>();

    public RuleMatcher(List<String> strings) {
        String ruleString;
        int i = 0;
        while (!(ruleString = strings.get(i)).trim().isEmpty()) {
            this.rules.put(ruleString.split(":")[0].trim(), ruleString.split(":")[1].trim().replace("\"", ""));
            i++;
        }

        i++;

        while (i < strings.size()) {
            messages.add(strings.get(i));
            i++;
        }
    }

    boolean found = false;

    public int matchesRuleCount(String s) {
        int cnt = 0;
        for (String message : messages) {
            found = false;
            this.matchesRule(s, message);
            if (found) {
                cnt++;
            }
        }
        return cnt;
    }


    public void matchesRule(String ruleName, String message0) {
        Deque<Rule> q = new LinkedList<>(Collections.singletonList(new Rule(rules.get(ruleName), message0)));
        while (!q.isEmpty()) {
            Rule pop = q.pop();
            if (pop.subrules.isEmpty() && pop.message.isEmpty()) {
                found = true;
                //return;
            }
            List<String> subrules = pop.subrules;
            String message = pop.message;
            if (subrules.isEmpty() || message.isEmpty()) continue;
            String firstGroupValue = rules.get(subrules.get(0));
            if (firstGroupValue.matches("[ab]")) {
                if (message.startsWith(firstGroupValue)) {
                    message = message.substring(1).trim();
                    subrules = subrules.stream().skip(1).collect(Collectors.toList());
                    q.push(new Rule(subrules, message));
                }
            } else {
                for (String r : rules.get(subrules.get(0)).split("\\|")) {
                    LinkedList<String> nSublist = new LinkedList<>(subrules);
                    nSublist.remove(0);
                    nSublist.addAll(0, asList(r.trim().split(" ")));
                    q.push(new Rule(nSublist, message));
                }
            }

        }
    }


    public void updateRule(String updateRule) {
        rules.put(updateRule.split(":")[0].trim(), updateRule.split(":")[1].trim());
    }
}
