package com.github.baloise.rocketchatrestclient;

import static java.lang.String.format;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.github.baloise.rocketchatrestclient.model.Message;
import com.github.baloise.rocketchatrestclient.model.Room;
import com.github.baloise.rocketchatrestclient.model.Rooms;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class RocketChatClient {

	private Configuration config;
	private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper;
	
	public RocketChatClient(String serverUrl, String user, String password) {
		
		config = new Configuration(serverUrl, user, password);
		jacksonObjectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
		jacksonObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	Map<String, Room> roomCache = new HashMap<>();
	public Set<Room> getPublicRooms() throws IOException {
		 Rooms rooms = authenticatedGet("publicRooms", Rooms.class);
		 HashSet<Room> ret = new HashSet<>();
		 roomCache.clear();
		 for ( Room r : rooms.rooms) {
			ret.add(r);
			roomCache.put(r.name, r);
		}
		return ret;
	}

	private <T> T authenticatedGet(String method, Class<T> reponseClass) throws IOException {
		try {
		HttpResponse<String> ret = Unirest
				 .get(config.getServerUrl()+method)
				.header("X-Auth-Token", config.getxAuthToken())
				.header("X-User-Id", config.getxUserId())
				.asString();
		if(ret.getStatus() == 401) {
			login();
			return authenticatedGet(method, reponseClass);
		}
		return jacksonObjectMapper.readValue(ret.getBody(), reponseClass);
		} catch (UnirestException e) {
			throw new IOException(e);
		}
	}
	
	private void authenticatedPost(String method, Object request) throws IOException {
		authenticatedPost(method, request, null);
	}
	
	private <T> T authenticatedPost(String method, Object request, Class<T> reponseClass) throws IOException {
		try {
			HttpResponse<String> ret = Unirest.post(config.getServerUrl()+method)
					.header("X-Auth-Token", config.getxAuthToken())
					.header("X-User-Id", config.getxUserId())
					.header("Content-Type", "application/json")
					.body(jacksonObjectMapper.writeValueAsString(request))
					.asString();
			if(ret.getStatus() == 401) {
				login();
				return authenticatedPost(method, request, reponseClass);
			}
			return reponseClass == null ? null :jacksonObjectMapper.readValue(ret.getBody(), reponseClass);
		} catch (UnirestException e) {
			throw new IOException(e);
		}
	}

	void login() throws UnirestException {
		HttpResponse<JsonNode> asJson = Unirest.post(config.getServerUrl() +"login")
				.field("user", config.getUser())
				.field("password", config.getPassword())
				 .asJson();
		if(asJson.getStatus() == 401) {
			throw new UnirestException("401 - Unauthorized");
		}
		JSONObject data = asJson.getBody().getObject().getJSONObject("data");
		config.setxAuthToken(data.getString("authToken"));
		config.setxUserId(data.getString("userId"));
	}

	public void logout() throws IOException {
		try {
			Unirest.post(config.getServerUrl()+"logout")
					.header("X-Auth-Token", config.getxAuthToken())
					.header("X-User-Id", config.getxUserId())
					.asJson();
		} catch (UnirestException e) {
			throw new IOException(e);
		}
	}

	public String getApiVersion() throws IOException {
		return getVersions().getString("api");
	}

	public String getRocketChatVersion() throws IOException {
		return getVersions().getString("rocketchat");
	}
	
	JSONObject lazyVersions;

	private JSONObject getVersions() throws IOException {
		if (lazyVersions == null) {
			try {
				lazyVersions = Unirest.get(config.getServerUrl() + "version").asJson().getBody().getObject()
						.getJSONObject("versions");
			} catch (UnirestException e) {
				throw new IOException(e);
			}
		}
		return lazyVersions;
	}

	public void send(String roomName, String message) throws IOException {
		Room room = getRoom(roomName);
		if(room == null) throw new IOException(format("unknown room : %s", roomName));
		send(room, message);
	}

	public void send(Room room, String message) throws IOException {
		authenticatedPost("rooms/"+room._id+"/send" , new Message(message));
	}

	public Room getRoom(String room) throws IOException {
		Room ret = roomCache.get(room);
		if(ret == null) {
			getPublicRooms();
			ret = roomCache.get(room);
		}
		return ret;
	}

}
