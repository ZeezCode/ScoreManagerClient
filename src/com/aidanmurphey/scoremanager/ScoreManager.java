package com.aidanmurphey.scoremanager;

public class ScoreManager {
	
	private ApiManager manager;
	
	public static void main(String[] args) {}

	public ScoreManager(final String apiKey, int appId) {
		this.manager = new ApiManager(apiKey, appId);
	}
	
	public ApiResponse requestRecords() {
		return manager.sendRequest(ApiManager.GET_RECORDS, null);
	}
	
	public ApiResponse submitRecord(String name, int score) {
		return manager.sendRequest(ApiManager.ADD_RECORD, "name=" + name + "&score=" + score);
	}

}
