package com.fc.agent;

import java.lang.instrument.Instrumentation;

/**
 *
 *
 * @author
 * @date 2024-08-21 13:43
 */
public class GreetingAgent {
    public static void premain(String options, Instrumentation ins) {
        if (options != null) {
            System.out.printf("I've been called with options: \"%s\"\n", options);
        } else {
            System.out.println("I've been called with no options.");
        }
        ClazzDumpCustomTransformer transformer = getTransformer(options);
        //更换不同的transformer
        ins.addTransformer(transformer);
    }

    public static ClazzDumpCustomTransformer getTransformer(String options) {
        String exportDir = null;
        String filterStr = null;
        boolean recursiveDir = false;
        if (options != null) {
            if (options.contains(";")) {
                String[] args = options.split(";");
                for (String param1 : args) {
                    String[] kv = param1.split("=");
                    if ("-d".equalsIgnoreCase(kv[0])) {
                        exportDir = kv[1];
                    } else if ("-f".equalsIgnoreCase(kv[0])) {
                        filterStr = kv[1];
                    } else if ("-r".equalsIgnoreCase(kv[0])) {
                        recursiveDir = true;
                    }
                }
            } else {
                filterStr = options;
            }
        }
        System.out.println("getTransformer, exportDir: " + exportDir + ", filterStr: " + filterStr + ", recursiveDir: " + recursiveDir);
        return new ClazzDumpCustomTransformer(exportDir, filterStr, recursiveDir);

    }
}
