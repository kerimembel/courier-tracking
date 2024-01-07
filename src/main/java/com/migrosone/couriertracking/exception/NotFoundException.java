package com.migrosone.couriertracking.exception;

import java.util.UUID;

/**
 * NotFoundException for 404 responses.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

public class NotFoundException extends RuntimeException {

    public NotFoundException(Class<?> clazz, UUID id) {
        super(String.format("Entity %s with id [ %s ] not found", clazz.getSimpleName(), id));
    }
}
