package com.EsMgExam;

import java.util.Arrays;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;

public class ElsPerson {
	private final String name;
	private final String addr;
	public ElsPerson(String name, String addr) {
        this.name = name;
        this.addr = addr;
    }
    public String toJson() {
        return String.format("{\"name\":\"%s\",\"addr\":\"%s\"}", name,
        		addr);
    }
    

}
