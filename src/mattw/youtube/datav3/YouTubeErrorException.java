package mattw.youtube.datav3;

public class YouTubeErrorException extends Exception {

    public YouTubeError error;
    public String requestUrl;

    public YouTubeErrorException(YouTubeError error) {
        this.error = error;
    }

    public YouTubeError getError() { return error; }

    public String getRequestUrl() { return requestUrl; }
    protected void setRequestUrl(String url) { this.requestUrl = url; }

    public class YouTubeError {
        public int code;
        public String message;
        public Error[] errors;

        public class Error {
            public String domain;
            public String reason;
            public String message;
            public String locationType;
            public String location;
        }
    }
}
