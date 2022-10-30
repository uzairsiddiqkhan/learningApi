package com.example.apilearning;

public class deleteresponse {
    String status;
    String message;

    public String getStatus() {
        return status;
    }

    public deleteresponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
