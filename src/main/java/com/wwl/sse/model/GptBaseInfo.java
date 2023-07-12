package com.wwl.sse.model;

/**
 * @author wuweilong
 */
public class GptBaseInfo {

    private boolean stream = false;
    private  Integer max_tokens=600;
    private  float temperature=0;
    private  Integer frequency_penalty=0;
    private  Integer presence_penalty=0;
    private  float top_p=1;
//    private  Integer best_of=1;

    public boolean isStream() {
        return stream;
    }

    public void setStream(boolean stream) {
        this.stream = stream;
    }

    public Integer getMax_tokens() {
        return max_tokens;
    }

    public void setMax_tokens(Integer max_tokens) {
        this.max_tokens = max_tokens;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public Integer getFrequency_penalty() {
        return frequency_penalty;
    }

    public void setFrequency_penalty(Integer frequency_penalty) {
        this.frequency_penalty = frequency_penalty;
    }

    public Integer getPresence_penalty() {
        return presence_penalty;
    }

    public void setPresence_penalty(Integer presence_penalty) {
        this.presence_penalty = presence_penalty;
    }

    public float getTop_p() {
        return top_p;
    }

    public void setTop_p(float top_p) {
        this.top_p = top_p;
    }

//    public Integer getBest_of() {
//        return best_of;
//    }
//
//    public void setBest_of(Integer best_of) {
//        this.best_of = best_of;
//    }
}
