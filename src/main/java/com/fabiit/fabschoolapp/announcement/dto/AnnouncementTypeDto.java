package com.fabiit.fabschoolapp.announcement.dto;

import com.fabiit.fabschoolapp.announcement.entity.AnnouncementTypeEntity;
import com.fabiit.fabschoolapp.utils.FabEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnnouncementTypeDto {
	private int announceTypeId;
	private String announceType;
	private FabEnum.state state;

	public AnnouncementTypeDto(AnnouncementTypeEntity announce) {
		this.announceType = announce.getAnnouncementType();
		this.announceTypeId = announce.getAnnouncementTypeId();
		this.state = announce.getState();

	}
}
