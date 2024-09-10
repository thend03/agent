package com.fc.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.Date;

/**
 *
 *
 * @author
 * @date 2024-08-21 13:42
 */
public class GreetingTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if ("com/fc/agent/Dog".equals(className)) {
            System.out.println("Dog's method invoke at\t" + new Date());
        }
        return null;
    }
}
