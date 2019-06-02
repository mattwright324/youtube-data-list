package io.mattw.youtube.datav3;

/**
 * @version 2018-12-08
 * @author mattwright324
 */
public enum Parts {
    ID("id", 0),
    SNIPPET("snippet",2),
    CONTENT_DETAILS("contentDetails", 2),
    TARGETING("targeting", 2),
    AUDIT_DETAILS("auditDetails", 4),
    BRANDING_SETTINGS("brandingSettings", 2),
    CONTENT_OWNER_DETAILS("contentOwnerDetails", 2),
    INVIDEO_PROMOTION("invideoPromotion", 2),
    LOCALIZATIONS("localizations", 2),
    STATISTICS("statistics", 2),
    STATUS("status", 2),
    TOPIC_DETAILS("topicDetails", 2),
    REPLIES("replies", 2),
    PLAYER("player", 0),
    SUBSCRIBER_SNIPPET("subscriberSnippet", 2),
    FILE_DETAILS("fileDetails", 1),
    LIVE_STREAMING_DETAILS("lifeStreamingDetails", 2),
    PROCESSING_DETAILS("processingDetails", 1),
    RECORDING_DETAILS("recordingDetails", 2),
    SUGGESTIONS("suggestions", 1),
    ;

    private String requestId;
    private int cost;

    Parts(String requestId, int cost) {
        this.cost = cost;
        this.requestId = requestId;
    }

    public int getCost() {
        return cost;
    }

    public String getId() {
        return requestId;
    }

    public String toString() {
        return String.format("%s[cost=%s]", getId(), getCost());
    }

}
