package org.dcs.testcase.pogo.weather;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnauthorizedErrorResponse {
    private String timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    @Override
    public String toString() {
        return "ClassPojo [path = " + path + ", status = " + status + " error = " + error + " message = " + message + "]";
    }

}
