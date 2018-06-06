package com.aidanmurphey.scoremanager;
public class Record {
	
	private int id;
	private String name, createdAt;
	private double score;
	
	public Record(int id, String name, double score, String createdAt) {
		this.id = id;
		this.name = name;
		this.score = score;
		this.createdAt = createdAt;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public double getScore() {
		return score;
	}
	
	public String getCreatedAt() {
		return createdAt;
	}
	
}
