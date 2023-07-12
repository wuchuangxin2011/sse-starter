package com.wwl.sse.model.sse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {
    private  ChoicesWrapper[] choices;

    public  ChoicesWrapper[] getChoices() {
        return choices;
    }

    public void setChoices( ChoicesWrapper[] choices) {
        this.choices = choices;
    }
}