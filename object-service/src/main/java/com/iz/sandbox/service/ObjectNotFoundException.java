package com.iz.sandbox.service;

import java.util.UUID;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(UUID objectId) {
        super("Object not found. ID:" + objectId.toString());
    }
}
