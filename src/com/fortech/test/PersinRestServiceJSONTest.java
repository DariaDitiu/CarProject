package com.fortech.test;

import static org.junit.Assert.*;


import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.Test;


public class PersinRestServiceJSONTest {
	

	@Test
	public void testCeva() {
		
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target("http://localhost:9080/CarProject/rest/personjson/marshal");
		Response response = target.request().get();
		assertEquals(200, response.getStatus());
	}

}
