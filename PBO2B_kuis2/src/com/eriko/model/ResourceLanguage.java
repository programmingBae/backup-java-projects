package com.eriko.model;
/**
 * Eriko Agustino
 * 1972041
 * 6 January 2022
 */
import java.util.ResourceBundle;

public class ResourceLanguage {
    private ResourceBundle resource;

    public ResourceLanguage(String name){
        resource = ResourceBundle.getBundle(name);
    }

    public ResourceBundle getResource() {
        return resource;
    }

    @Override
    public String toString() {
        return resource.getString("name.language");
    }
}
