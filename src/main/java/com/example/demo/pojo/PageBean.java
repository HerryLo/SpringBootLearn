package com.example.demo.pojo;

import java.util.List;

import lombok.Data;

@Data
public class PageBean {
	private Long total;
	private List rows;
}
