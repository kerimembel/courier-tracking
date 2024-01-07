package com.migrosone.couriertracking.exception;


/**
 * UserAlreadyExistsException for existing emails.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String email) {
        super(String.format("Email [ %s ] is already in use ", email));
    }
}
