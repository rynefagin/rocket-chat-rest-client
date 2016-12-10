package com.github.baloise.rocketchatrestclient;

import static java.lang.String.format;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONObject;

import com.github.baloise.rocketchatrestclient.model.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.baloise.rocketchatrestclient.model.Channel;
import com.github.baloise.rocketchatrestclient.model.Message;
import com.github.baloise.rocketchatrestclient.model.Room;
import com.github.baloise.rocketchatrestclient.model.Rooms;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class RocketChatClient extends RocketChatClientAbstract {

	private Configuration config;
	private Cache cache;

	public RocketChatClient(String serverUrl, String user, String password) {
		cache = new Cache();
		config = new Configuration(serverUrl, user, password);
		super.jacksonObjectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
		jacksonObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public Set<Room> getPublicRooms() throws IOException {
		Rooms rooms = super.authenticatedGet("publicRooms", Rooms.class, config);
		HashSet<Room> ret = new HashSet<>();
		cache.roomCache.clear();
		for (Room r : rooms.rooms) {
			ret.add(r);
			cache.roomCache.put(r.name, r);
		}
		return ret;
	}

	void login() throws UnirestException {
		HttpResponse<JsonNode> asJson = Unirest.post(config.getServerUrl() + "login").field("user", config.getUser())
				.field("password", config.getPassword()).asJson();
		if (asJson.getStatus() == 401) {
			throw new UnirestException("401 - Unauthorized");
		}
		JSONObject data = asJson.getBody().getObject().getJSONObject("data");
		config.setxAuthToken(data.getString("authToken"));
		config.setxUserId(data.getString("userId"));
	}

	public void logout() throws IOException {
		try {
			Unirest.post(config.getServerUrl() + "logout").header("X-Auth-Token", config.getxAuthToken())
					.header("X-User-Id", config.getxUserId()).asJson();
		} catch (UnirestException e) {
			throw new IOException(e);
		}
	}

	public String getApiVersion() throws IOException {
		if(cache.version == null)
			getVersion();
		
		return cache.version.getApi();
	}

	public String getRocketChatVersion() throws IOException {
		if(cache.version == null)
			getVersion();
		
		return cache.version.getRocketchat();
	}

	private void getVersion() throws IOException {

		if (cache.version == null) {
			try {
				JSONObject versionJSON = super.Get("version", config).getJSONObject("versions");
				Version version = new Version();
				version.setApi(versionJSON.getString("api"));
				version.setRocketchat(versionJSON.getString("rocketchat"));
				cache.version = version;

			} catch (IOException e) {
				throw new IOException(e);
			}
		}

	}
	
	public void createChannel(String channelName) throws IOException {
		if(!cache.roomCache.containsKey(channelName))
		{
			this.sendChannel(channelName);
		}
		this.getPublicRooms();
		
	}
	
	private void sendChannel(String channelName) throws IOException {
		String method = "v1/channels.create/";
		super.authenticatedPost(method, new Channel(channelName), null, config);
	}
	

	@Deprecated
	public void send(String roomName, String message) throws IOException {
		Room room = getRoom(roomName);
		if (room == null)
			throw new IOException(format("unknown room : %s", roomName));
		send(room, message);
	}

	@Deprecated
	public void send(Room room, String message) throws IOException {
		super.authenticatedPost("rooms/" + room._id + "/send", new Message(message), null, config);
	}

	@Deprecated
	public Room getRoom(String room) throws IOException {
		Room ret = cache.roomCache.get(room);
		if (ret == null) {
			getPublicRooms();
			ret = cache.roomCache.get(room);
		}
		return ret;
	}

}
