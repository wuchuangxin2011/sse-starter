package com.wwl.sse.model.sse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChoicesWrapper {
    private DetailContent detailContent;

    public DetailContent getDetailContent() {
        return detailContent;
    }

    public void setDetailContent(DetailContent detailContent) {
        this.detailContent = detailContent;
    }
}