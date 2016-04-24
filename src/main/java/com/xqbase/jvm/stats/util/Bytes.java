package com.xqbase.jvm.stats.util;

/**
 * Bytes utility.
 *
 * @author Tony He
 */
public class Bytes {

    /**
     * Size of bytes in string. (KB, MB, GB, TB etc.)
     */
    public static String bytesToSize(long bytes)
    {
        long kilobyte = 1024L;
        long megabyte = kilobyte * 1024L;
        long gigabyte = megabyte * 1024L;
        long terabyte = gigabyte * 1024L;

        if ((bytes >= 0L) && (bytes < kilobyte)) {
            return bytes + " B";
        }
        if ((bytes >= kilobyte) && (bytes < megabyte)) {
            return String.format("%.0f", (double) (bytes / kilobyte)) + " KB";
        }
        if ((bytes >= megabyte) && (bytes < gigabyte)) {
            return String.format("%.2f", (double) (bytes / megabyte)) + " MB";
        }
        if ((bytes >= gigabyte) && (bytes < terabyte)) {
            return String.format("%.2f", (double) (bytes / gigabyte)) + " GB";
        }
        if (bytes >= terabyte) {
            return String.format("%.2f", (double) (bytes / terabyte)) + " TB";
        }

        return bytes + " B";
    }
}
