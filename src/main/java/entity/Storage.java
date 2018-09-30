package entity;

import java.io.Serializable;

public class Storage  implements Serializable {

    private int id;
    private String name;
    private int type;
    private int dataType;
    private int sourceNodes;
    private int sourceType;
    private String dataSegment;
    private String config;
    private String alias;
    private String addOn;
    private String topic;
    private int pause;
    private int random;
    private int filterType;

    public int getSourceNodes() {
        return sourceNodes;
    }

    public void setSourceNodes(int sourceNodes) {
        this.sourceNodes = sourceNodes;
    }

    public int getSourceType() {
        return sourceType;
    }

    public void setSourceType(int sourceType) {
        this.sourceType = sourceType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public String getDataSegment() {
        return dataSegment;
    }

    public void setDataSegment(String dataSegment) {
        this.dataSegment = dataSegment;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAddOn() {
        return addOn;
    }

    public void setAddOn(String addOn) {
        this.addOn = addOn;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getPause() {
        return pause;
    }

    public void setPause(int pause) {
        this.pause = pause;
    }

    public int getRandom() {
        return random;
    }

    public void setRandom(int random) {
        this.random = random;
    }

    public int getFilterType() {
        return filterType;
    }

    public void setFilterType(int filterType) {
        this.filterType = filterType;
    }
}
