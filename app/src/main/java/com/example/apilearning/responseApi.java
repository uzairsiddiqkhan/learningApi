package com.example.apilearning;

import java.util.List;

public class responseApi {
    public String getStatus() {
        return status;
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

    public List<modifiedModelApi> getData() {
        return data;
    }

    public void setData(List<modifiedModelApi> data) {
        this.data = data;
    }

    public responseApi(String status, String message, List<modifiedModelApi> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    String status ,message ;
    List <modifiedModelApi> data;
}
