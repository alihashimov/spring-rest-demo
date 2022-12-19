package az.et.springrest.exception;

import az.et.springrest.enums.ErrorCodeEnum;

public class CustomNotFoundException extends RuntimeException{
    private final int code;
    private final String message;

    public CustomNotFoundException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMessage());
        this.code=errorCodeEnum.getCode();
        this.message=errorCodeEnum.getMessage();
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
