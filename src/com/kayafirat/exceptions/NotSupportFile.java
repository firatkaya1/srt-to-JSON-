package com.kayafirat.exceptions;

public class NotSupportFile extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotSupportFile(String exception) {
        super("This file path is not contain .srt extension. "+exception);

    }

}
