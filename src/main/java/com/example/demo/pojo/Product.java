package com.example.demo.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private String id;
    private String name;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
 
    public String getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
}
