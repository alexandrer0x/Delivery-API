package dev.alexandrevieira.delivery.config;

import lombok.Value;

import static dev.alexandrevieira.delivery.domain.enums.DeliveryStatus.*;

@Value
public class Messages {
    public static final String EMAIL_ALREADY_USED = "Email already used";
    public static final String FIELD_ERROR = "One or more fields are invalid";
    public static final String CLIENT_NOT_FOUND = "Client not found";
    public static final String CANNOT_BE_FINISHED = "To finish a delivery, its status must be " + SHIPPED;
    public static final String CANNOT_BE_CANCELED = "To cancel a delivery, its status must not be " + DELIVERED;
    public static final String CANNOT_BE_SHIPPED = "To ship a delivery, its status must be " + PENDING;
    public static final String CANNOT_CHANGE_TO_PENDING = PENDING + " status is just used on delivery creation";
    public static final String INVALID_STATUS = "Invalid status";
    public static final String SAME_STATUS = "New and old status are the same";
}

