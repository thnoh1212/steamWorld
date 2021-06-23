package com.a105.apiServer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResultDto {
	private int resultid;
	private String userid;
	private int appid;
	private String name;
	private int score;
	private int vote;
	private String storelink;
	private String imgsrc;
	private String shortDes;
	private String dev;
	private String fullDes;
}
