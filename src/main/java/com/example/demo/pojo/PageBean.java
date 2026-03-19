package com.example.demo.pojo;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageBean {
	private Long total;
	private List rows;
	
	public PageBean(Long total, List rows) {
        this.total = total;
        this.rows = rows;
    }
}
