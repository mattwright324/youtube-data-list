package io.mattw.youtube.datav3.entrypoints;

import java.io.Serializable;

/**
 * @since 2018-12-30
 * @author mattwright324
 */
public class PageInfo implements Serializable {

    int totalResults, resultsPerPage;

    public int getTotalResults() {
        return totalResults;
    }

    public int getResultsPerPage() {
        return resultsPerPage;
    }
}
