package com.example.demo.Common.utils;


/**
 * Class desc:
 * <p>
 * Created by sunzhihao on 2020-02-23
 */
public class Source {
    private String id;
    private String vendorName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    @Override
    public String toString() {
        return "Source{" +
                "id='" + id + '\'' +
                ", vendorName='" + vendorName + '\'' +
                '}';
    }
}
