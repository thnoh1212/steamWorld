package com.a105.apiServer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GameLinksDto {
	private int linkid;
	private int appid;
	private String url;
}
