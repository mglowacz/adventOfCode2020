package com.github.mglowacz.aoc2020.day2;

public class OTCPValidator implements Validator {

    @Override
    public boolean validate(String policy, String pass) {
        char c = policy.split(" ")[1].charAt(0);
        char pos0 = pass.charAt(-1 + Integer.parseInt(policy.split(" ")[0].split("-")[0]));
        char pos1 = pass.charAt(-1 + Integer.parseInt(policy.split(" ")[0].split("-")[1]));

        return pos0 == c ^ pos1 == c;
    }
}
