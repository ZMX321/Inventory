package com.rei.interview.search.index;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SearchIndexInputDocument {

    // fieldName -> fieldValue
    private Map<String, Object> fields;

    public SearchIndexInputDocument() {
        fields = new HashMap<>();
    }

    public void addField(String fieldName, Object fieldValue) {
        fields.put(fieldName, fieldValue);
    }

    public Map<String, Object> getFields() {
        return fields.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
    }
}
