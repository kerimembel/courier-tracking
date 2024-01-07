package com.migrosone.couriertracking.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Generic payload definition to keep similar response structure.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@Data
@Builder
@AllArgsConstructor
public class GenericResponse<T> {
    private boolean success;
    private String message;
    private T data;

    public static <T> GenericResponse<T> success(T data) {
        return GenericResponse.<T>builder()
                .message("success")
                .data(data)
                .success(true)
                .build();
    }

    public static <T> GenericResponse<T> error(String message, T data) {
        return GenericResponse.<T>builder()
                .message(message)
                .data(data)
                .success(false)
                .build();
    }

    public static <T> GenericResponse<T> error(String message) {
        return GenericResponse.<T>builder()
                .message(message)
                .success(false)
                .build();
    }
}