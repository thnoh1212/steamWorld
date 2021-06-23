package com.a105.apiServer.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GameInfoDto {
	int appid;
	GamesDto game;
	List<GameLinksDto> gamelinks;
	List<GameTagsDto> gametags;
}
