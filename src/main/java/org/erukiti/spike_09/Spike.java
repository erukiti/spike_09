package org.erukiti.spike_09;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

public class Spike {
    @Argument(required = true)
    String filename = null;

    public static void main(String[] args) {
        Spike spike = new Spike();
        spike.run(args);
    }

    public void run(String[] args) {
        try {
            new CmdLineParser(this). parseArgument(args);
            Validator validator = new Validator("specs/schema.json");
            System.out.println(validator.isValidated(filename));
        } catch (CmdLineException e) {
            e.printStackTrace();
        }
    }
}
