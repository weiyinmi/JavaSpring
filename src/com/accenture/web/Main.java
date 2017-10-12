package com.accenture.web;

import java.util.ArrayList;
import java.util.List;

import com.accenture.web.dto.JosephCircleRequest;

public class Main {

	public static void main(String[] args) {
		String nullStr = null;
		String emptyStr ="";	
		
		System.out.println(nullStr.isEmpty());
		System.out.println(emptyStr.isEmpty());
        
		List<String> nullList = null;
		nullList.isEmpty();
		
		List<String> emptyList = new ArrayList<>();
		System.out.println(emptyList.isEmpty());
		
		JosephCircleRequest request = null;
		JosephCircleRequest emptyRequest = new JosephCircleRequest();
	}

}
