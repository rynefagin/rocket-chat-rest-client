package com.github.baloise.rocketchatrestclient;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.baloise.rocketchatrestclient.model.Room;
import com.github.baloise.rocketchatrestclient.model.ServerInfo;
import com.github.baloise.rocketchatrestclient.model.User;

/**
* This IT tests basic functionality of the client.
*
* @author Ryne Fagin (rynefagin)
* @since 0.1.1
* @version 0.0.1
*/
public class RocketChatClientTestIT {
	
	String serverUrl = "http://localhost/api/";
	String user = "admin";
	String password = "supersecret";
	RocketChatClient rc = new RocketChatClient(serverUrl, user, password);

	@Test
	public void testRocketCatExists() throws Exception {
		
		ServerInfo info = rc.getServerInformation();
		assertTrue("The Rocket.Chat Version is empty, when it shouldn't be.", !info.getVersion().isEmpty());

		User rocketCat = rc.getUser("rocket.cat");
		assertTrue("The Rocket.Cat user's id doesn't match what it should be.", "rocket.cat".equals(rocketCat.getId()));

	}

	@Test
	public void testCreateChannel() throws Exception {
		Room room = rc.createChannel("test1234");
		assertTrue( "Room Id shouldn't be null if the room was created", (room.getId() !=null && !room.getId().isEmpty()));
	}
}
