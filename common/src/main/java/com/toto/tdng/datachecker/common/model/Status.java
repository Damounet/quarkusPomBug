package com.toto.tdng.datachecker.common.model;

public class Status {
    public static final int NULL = -1;
    public static final int OK = 0;
    public static final int WARNING = 1;
    public static final int ERROR = 2;
    public static final int NOT_APPLICABLE = 3;

    public static String toString(int status) {
        switch (status) {
            case OK:
                return "OK";
            case WARNING:
                return "WARNING";
            case ERROR:
                return "ERROR";
            case NOT_APPLICABLE:
                return "NOT_APPLICABLE";
            case NULL:
                return "NULL";
            default:
                return "UNKNOWN";
        }
    }
}
