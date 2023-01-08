package az.et.springrest.enums;

public enum ErrorCodeEnum {
    EMPLOYEE_NOT_FOUND(1001, "Cannot find employee with given id"),

    VALIDATION_ERROR(1002, " is not valid"),
    ACCESS_DENIED(1003,"Access is denied"),
    UNKNOWN_ERROR(1000, "unknown error");
    private final int code;
    private final String message;

    ErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
