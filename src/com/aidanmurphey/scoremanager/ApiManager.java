package com.aidanmurphey.scoremanager;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

public class ApiManager {
	private final String apiKey;
	private final int appId;
	public static final int GET_RECORDS = 0, ADD_RECORD = 1;
	
	
	public ApiManager(final String apiKey, final int appId) {
		this.apiKey = apiKey;
		this.appId = appId;
	}
	
	public ApiResponse sendRequest(int action, String parameters) {
		try {
			URL url = new URL("http://score.man:80/api/record/" + appId);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			if (action == GET_RECORDS)
				conn.setRequestMethod("GET");
			else if (action == ADD_RECORD) {
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
				conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
			}
			
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Api-Key", apiKey);
			
			if (action == ADD_RECORD) {
				byte[] postData = parameters.getBytes();
				int length = postData.length;
				conn.setRequestProperty( "Content-Length", Integer.toString(length));
				conn.setUseCaches( false );
				try(DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
					wr.write( postData );
				}
			}

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Request returned HTTP error code: " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String output = br.readLine();
			Gson gson = new Gson();
			ApiResponse response = gson.fromJson(output, ApiResponse.class);

			conn.disconnect();
			return response;
		  } catch(Exception e) {
			e.printStackTrace();
		  }
		return null;
	}

}
