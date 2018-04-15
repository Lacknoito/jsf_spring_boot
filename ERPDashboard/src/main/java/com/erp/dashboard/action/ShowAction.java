package com.erp.dashboard.action;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Component;

import com.erp.dashboard.model.Test2;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@ManagedBean
@RequestScoped
public class ShowAction {
	private String datas;
	List<Test2> data;
	
	public void onload() throws JsonProcessingException {
		System.out.println("onload");
		
		data = new ArrayList<>();
		data.add(new Test2("1", "2"));
		data.add(new Test2("3", "4"));
		ObjectMapper mapper = new ObjectMapper();
        datas = mapper.writeValueAsString(data);
        
        System.out.println(datas);
	}
	
	public void update() {
		System.out.println("-update-");
		data.add(new Test2("5", "5"));
	}

	public String getDatas() {
		return datas;
	}

	public void setDatas(String datas) {
		this.datas = datas;
	}

	public List<Test2> getData() {
		return data;
	}

	public void setData(List<Test2> data) {
		this.data = data;
	}
	
}
