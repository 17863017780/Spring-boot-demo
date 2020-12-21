package com.example.demo.Common.utils;


import java.util.List;

/**
 * Class desc:
 * <p>
 * Created by sunzhihao on 2020-02-23
 */

public class AttributFristObj {

        private Integer attrId;
        private String attrValues;
        private Integer index;
        private Integer level;
        private Integer inputType;
        private List<String> attrValuesName;

    public Integer getAttrId() {
        return attrId;
    }

    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }

    public String getAttrValues() {
        return attrValues;
    }

    public void setAttrValues(String attrValues) {
        this.attrValues = attrValues;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getInputType() {
        return inputType;
    }

    public void setInputType(Integer inputType) {
        this.inputType = inputType;
    }

    public List<String> getAttrValuesName() {
        return attrValuesName;
    }

    public void setAttrValuesName(List<String> attrValuesName) {
        this.attrValuesName = attrValuesName;
    }
}
