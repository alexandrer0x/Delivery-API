package dev.alexandrevieira.delivery.common;

import lombok.Value;

@Value
public class EventDescriptions {
    public static final String DELIVERED_TO_RECEIVER = "Delivered to receiver";
    public static final String DELIVERY_CANCELED = "Delivery canceled";
    public static final String DELIVERY_SHIPPED = "Delivery shipped";
    public static final String DELIVERY_REQUESTED = "Delivery requested";
}
