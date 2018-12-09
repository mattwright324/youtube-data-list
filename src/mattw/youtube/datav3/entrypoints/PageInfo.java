package mattw.youtube.datav3.entrypoints;

import java.io.Serializable;

public class PageInfo implements Serializable {

    int totalResults, resultsPerPage;

    public int getTotalResults() {
        return totalResults;
    }

    public int getResultsPerPage() {
        return resultsPerPage;
    }
}
