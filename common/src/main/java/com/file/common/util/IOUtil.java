package com.file.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * IO Util.
 *
 * @author Volodymyr Lykhvar
 */
public final class IOUtil {

    private static final int BUFFER_SIZE = 1024;
    private static final int BYTES_IN_MEGABYTE = 1024 * 1024;

    private IOUtil() {
    }

    public static void copyStream(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[BUFFER_SIZE];
        int numberOfBytes;
        while ((numberOfBytes = in.read(buffer)) > 0) {
            out.write(buffer, 0, numberOfBytes);
        }
    }

    public static byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        copyStream(input, output);
        return output.toByteArray();
    }

    public static long convertBytesToMegabytes(long bytes) {
        return bytes / BYTES_IN_MEGABYTE;
    }
}
