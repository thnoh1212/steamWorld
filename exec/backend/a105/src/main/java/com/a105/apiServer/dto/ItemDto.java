package com.a105.apiServer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemDto {
	private int itemid;
	private String userid;
	private int appid;
	private int playtime_forever;
	private int playtime_2weeks;
	private int issteam;
}
