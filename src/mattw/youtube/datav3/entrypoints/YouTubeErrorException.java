package mattw.youtube.datav3.entrypoints;

import java.io.Serializable;

/**
 * @version 2018-12-08
 * @author mattwright324
 */
public class YouTubeErrorException extends Exception implements Serializable {

    YouTubeError error;
    String requestUrl;

    YouTubeErrorException(YouTubeError error) {
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

    public class YouTubeError implements Serializable {

        public class Error implements Serializable {
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
