package com.a105.apiServer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SizeDto {
	private int start;
	private int size;
	
	public SizeDto() {
		// TODO Auto-generated constructor stub
	}

	public SizeDto(int start, int size) {
		super();
		this.start = start;
		this.size = size;
	}
	
	
	
}
