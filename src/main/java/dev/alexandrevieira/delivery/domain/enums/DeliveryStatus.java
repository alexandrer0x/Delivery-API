package dev.alexandrevieira.delivery.domain.enums;

public enum DeliveryStatus {
    CANCELED(0),
    PENDING(1),
    SHIPPED(2),
    DELIVERED(3);

    private final int code;

    DeliveryStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static DeliveryStatus byCode(int code) {
        for (DeliveryStatus status : DeliveryStatus.values()) {
            if (code == status.getCode()) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid code");
    }
}
