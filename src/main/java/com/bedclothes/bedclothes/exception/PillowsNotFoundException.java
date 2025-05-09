package com.bedclothes.bedclothes.exception;

public class PillowsNotFoundException extends RuntimeException {
    public PillowsNotFoundException(String pillowNotFound) {
        super(pillowNotFound);
    }
}
