package com.a105.apiServer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserItemDto {
	private int steamid;
	private int appid;
	private int playtime_forever;
	private int playtime_2weeks;
}
