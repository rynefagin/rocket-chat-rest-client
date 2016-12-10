package com.github.baloise.rocketchatrestclient;

import java.io.IOException;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public abstract class RocketChatClientAbstract {

	protected com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper;
	
	abstract void login() throws UnirestException;
	abstract void logout() throws IOException;
	
	protected <T> T authenticatedPost(String method, Object request, Class<T> reponseClass, Configuration config) throws IOException {
		try {
			HttpResponse<String> ret = Unirest.post(config.getServerUrl() + method)
					.header("X-Auth-Token", config.getxAuthToken()).header("X-User-Id", config.getxUserId())
					.header("Content-Type", "application/json").body(jacksonObjectMapper.writeValueAsString(request))
					.asString();
			if (ret.getStatus() == 401) {
				login();
				return authenticatedPost(method, request, reponseClass, config);
			}
			return reponseClass == null ? null : jacksonObjectMapper.readValue(ret.getBody(), reponseClass);
		} catch (UnirestException e) {
			throw new IOException(e);
		}
	}
	
	protected <T> T Post(String method, Object request, Class<T> reponseClass, Configuration config) throws IOException {
		try {
			HttpResponse<String> ret = Unirest.post(config.getServerUrl() + method)
					.header("Content-Type", "application/json").body(jacksonObjectMapper.writeValueAsString(request))
					.asString();
			
			return reponseClass == null ? null : jacksonObjectMapper.readValue(ret.getBody(), reponseClass);
		} catch (UnirestException e) {
			throw new IOException(e);
		}
	}
	
	protected JSONObject Put(String method, Configuration config) throws IOException {
		try {
			return Unirest.post(config.getServerUrl() + method).asJson().getBody().getObject();
			
		} catch (UnirestException e) {
			throw new IOException(e);
		}
	}
	
	protected <T> T authenticatedGet(String method, Class<T> reponseClass, Configuration config) throws IOException {
		try {
			HttpResponse<String> ret = Unirest.get(config.getServerUrl() + method)
					.header("X-Auth-Token", config.getxAuthToken())
					.header("X-User-Id", config.getxUserId())
					.asString();
			if (ret.getStatus() == 401) {
				login();
				return authenticatedGet(method, reponseClass, config);
			}
			return jacksonObjectMapper.readValue(ret.getBody(), reponseClass);
		} catch (UnirestException e) {
			throw new IOException(e);
		}
	}
	
	protected <T> T Get(String method, Class<T> reponseClass, Configuration config) throws IOException {
		try {
			HttpResponse<String> ret = Unirest.get(config.getServerUrl() + method)
					.asString();
			
			return jacksonObjectMapper.readValue(ret.getBody(), reponseClass);
		} catch (UnirestException e) {
			throw new IOException(e);
		}
	}
	
	protected JSONObject Get(String method, Configuration config) throws IOException {
		try {
			return Unirest.get(config.getServerUrl() + method).asJson().getBody().getObject();
			
		} catch (UnirestException e) {
			throw new IOException(e);
		}
	}
	
}
