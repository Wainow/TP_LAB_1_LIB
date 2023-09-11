package io.bidmachine.media.domain;

public class Inter {

    public int getVar() {
        var value = 5;
        return value;
    }

    public String getData() {
        return new Data("a", "v").toString();
    }
}
