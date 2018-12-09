package mattw.youtube.datav3.entrypoints;

import java.io.Serializable;

class BaseItem implements Serializable {

    String kind, etag, id;

    public String getKind() {
        return kind;
    }

    public String getEtag() {
        return etag;
    }

    public String getId() {
        return id;
    }
}
