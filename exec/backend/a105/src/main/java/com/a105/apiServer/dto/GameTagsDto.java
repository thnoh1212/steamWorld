package com.a105.apiServer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GameTagsDto {
	private int gametagid;
	private int appid;
	private String genre;
	private int ranking;
}
