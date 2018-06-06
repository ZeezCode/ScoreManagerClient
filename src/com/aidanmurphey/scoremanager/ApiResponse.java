package com.aidanmurphey.scoremanager;
import java.util.ArrayList;
import java.util.Map;

public class ApiResponse {
	public boolean failed;
	public String error;
	public Map<String, String>[] records;

	public boolean getFailed() {
		return failed;
	}

	public String getError() {
		return error;
	}
	
	public ArrayList<Record> getRecords() {
		ArrayList<Record> result = new ArrayList<>();
		for (Map<String, String> rawRecord : records) {
			result.add(new Record(
				Integer.parseInt(rawRecord.get("id")),
				rawRecord.get("name"),
				Double.parseDouble(rawRecord.get("score")),
				rawRecord.get("created_at")
			));
		}
		
		return result;
	}
}
