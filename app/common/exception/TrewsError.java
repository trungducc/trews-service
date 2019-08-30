package common.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = TrewsErrorSerializer.class)
public enum TrewsError {

    USERNAME_EXISTS(409, "Username already exists."),

    AUTHENTICATION_ERROR(401, "Username or password is wrong."),

    MISSING_TOKEN_ERROR(401, "Token is missing."),

    INVALID_TOKEN_ERROR(401, "Invalid token"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error.");

    private int code;

    private String message;

    TrewsError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
