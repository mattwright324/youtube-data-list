package io.mattw.youtube.datav3.entrypoints;

/**
 * @version 2018-12-30
 * @author mattwright324
 */
public final class Response {

    static class Builder {

        private Response response = new Response();

        Builder error(boolean error) {
            response.setError(error);
            return this;
        }

        Builder jsonMessage(String jsonMessage) {
            response.setJsonMessage(jsonMessage);
            return this;
        }

        Builder requestUrl(String requestUrl) {
            response.setRequestUrl(requestUrl);
            return this;
        }

        Response build() {
            return response;
        }
    }

    boolean error = false;
    String jsonMessage;
    String requestUrl;

    public boolean isError() {
        return error;
    }

    private void setError(boolean error) {
        this.error = error;
    }

    public String getJsonMessage() {
        return jsonMessage;
    }

    private void setJsonMessage(String jsonMessage) {
        this.jsonMessage = jsonMessage;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    private void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }
}
