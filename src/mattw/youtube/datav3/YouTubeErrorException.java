package mattw.youtube.datav3;

public class YouTubeErrorException extends Exception {
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
