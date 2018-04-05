package com.gateway.server.dto;

import org.bson.BSON;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "reward")
public class RewardDTO {

	@Id
	private String _id;
	private String userId;
	private BSON rewardData;
	
}