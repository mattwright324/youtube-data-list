package io.mattw.youtube.datav3.entrypoints;

import java.io.IOException;
import java.io.Serializable;

/**
 * @version 2018-12-30
 * @author mattwright324
 */
public class YouTubeErrorException extends IOException implements Serializable {

    YouTubeError error;
    String requestUrl;

    YouTubeErrorException(YouTubeError error) {
        super(error.getMessage());
        this.error = error;
    }

    public YouTubeError getError() {
        return error;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    void setRequestUrl(String url) {
        this.requestUrl = url;
    }

    public static class YouTubeError implements Serializable {

        public static class Error implements Serializable {
            String domain, reason, message, locationType, location;

            public String getDomain() {
                return domain;
            }

            public String getReason() {
                return reason;
            }

            public String getMessage() {
                return message;
            }

            public String getLocationType() {
                return locationType;
            }

            public String getLocation() {
                return location;
            }
        }

        int code;
        String message;
        Error[] errors;

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public Error[] getErrors() {
            return errors;
        }

    }
}
