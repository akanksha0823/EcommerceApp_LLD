package exception;

import model.ErrorCode;

public class EcommerceException extends RuntimeException{
    ErrorCode errorCode;
    String errorMessage;

    public EcommerceException(ErrorCode errorCode,String message) {
        super(message);
        this.errorCode = errorCode;
        this.errorMessage = message;
     }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
