package pl.piasta.acmanagement.domain.misc;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

    CUSTOMER_ALREADY_EXISTS("C01", "Customer already exists"),
    CUSTOMER_NOT_EXISTS("C02", "Customer not exists"),
    UNIT_NOT_EXISTS("ACU01", "AC Unit not exists"),
    SYSTEM_NOT_EXISTS("ACS01", "AC System not exists"),
    VALIDATION_FAILED("V06", "Validation failed");

    private final String code;
    private final String message;
}
