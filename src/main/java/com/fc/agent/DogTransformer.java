package com.fc.agent;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.ClassFileTransformer;
import java.nio.file.Files;
import java.security.ProtectionDomain;

/**
 *
 *
 * @author
 * @date 2024-08-21 14:12
 */
public class DogTransformer implements ClassFileTransformer {

    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        System.out.println("className:" + className);
        if (!className.equalsIgnoreCase("com/fc/agent/Dog")) {
            return null;
        }
        return getBytesFromFile("/Users/since/git/vamei/target/classes/com/fc/agent/Dog.class");// 新的 Dog
    }

    public static byte[] getBytesFromFile(String fileName) {
        File file = new File(fileName);
        try (InputStream is = Files.newInputStream(file.toPath())) {
            // precondition

            long length = file.length();
            byte[] bytes = new byte[(int) length];

            // Read in the bytes
            int offset = 0;
            int numRead = 0;
            while (offset <bytes.length
                    && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }

            if (offset < bytes.length) {
                throw new IOException("Could not completely read file"
                        + file.getName());
            }
            is.close();
            return bytes;
        } catch (Exception e) {
            System.out.println("error occurs in _ClassTransformer!"
                    + e.getClass().getName());
            return null;
        }
    }

}
