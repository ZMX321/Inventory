package com.rei.interview.search.index;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class is intended to be a no-op implementation
 * of interacting with an external Search index service.
 */
@Service
public class SearchIndexProxy {

    /**
     * Push a set of documents to an collection in an external Search server,
     * where they are staged for indexing.
     *
     * @param documents the documents to index
     * @param collectionName the collection to index the documents to.
     */
    public void indexDocuments(List<SearchIndexInputDocument> documents, String collectionName) {
        // do not implement
    }

    /**
     * Commits a staged collection to be indexed.
     *
     * @param collectionName the collection to commit.
     */
    public void commitIndex(String collectionName) {
        // do not implement
    }
}
