package com.cts.bigdata.snanalytics;

public class Indicators {
    
    private String label;
    private String value;
    private String link;
    
    public Indicators(String label, String value, String link) {
        this.label = label;
        this.value = value;
        this.link = link;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    
  

}
