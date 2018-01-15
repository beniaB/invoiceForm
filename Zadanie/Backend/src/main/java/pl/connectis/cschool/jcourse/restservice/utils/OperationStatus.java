package pl.connectis.cschool.jcourse.restservice.utils;

public class OperationStatus {

    private String message;
    private boolean success;

    public OperationStatus(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public OperationStatus() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
