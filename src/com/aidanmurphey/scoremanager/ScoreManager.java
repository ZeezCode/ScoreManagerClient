package com.aidanmurphey.scoremanager;

import java.util.ArrayList;

public class ScoreManager {
	
	private ApiManager manager;
	
	public static void main(String[] args) {}

	public ScoreManager(final String apiKey, int appId) {
		this.manager = new ApiManager(apiKey, appId);
	}
	
	public ArrayList<Record> requestRecords() {
		ApiResponse response = manager.sendRequest(ApiManager.GET_RECORDS, null);
		if (response.getFailed()) {
			throw new APIException(response.getError());
		}
		
		return response.getRecords();
	}
	
	public void submitRecord(String name, int score) {
		ApiResponse response = manager.sendRequest(ApiManager.ADD_RECORD, "name=" + name + "&score=" + score);
		if (response.getFailed()) {
			throw new APIException(response.getError());
		}
	}

}
