package com.qasdet.exam;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import me.rest.test.AllStatesResponse;
import me.rest.test.RestClient;
import me.rest.test.State;
import me.rest.test.StateDirectory;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=QasdetApplicationTests.class)
public class QasdetApplicationTests {

	@Test
	public void processName()
	  throws  IOException {
	  
	   // Given
	   String name = "In";
	   RestClient client = new RestClient();
	   StateDirectory directory = client.getStateDirectory();
	 
	   // When
	   State state = directory.getState(name);
	   // Then
	   assertEquals(state.getName(), "Indiana");
	}
	
	@Test
	public void processNameFail()
	  throws  IOException {
	  
	   // Given
	   String name = "In";
	   RestClient client = new RestClient();
	   StateDirectory directory = client.getStateDirectory();
	 
	   // When
	   State state = directory.getState(name);
	   // Then
	   assertNotEquals(state.getName(), "Indians");
	}
	@Test
	public void processStatusPass()
	  throws  IOException {
	  
	   // Given
	   String name = "In";
	   RestClient restClient = new RestClient();
	   ClientConfig clientConfig = new DefaultClientConfig();
       clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
       Client client = Client.create(clientConfig);
       WebResource resource = client.resource(AllStatesResponse.resourcePath);
       ClientResponse response = resource.get(ClientResponse.class);
       int status = response.getStatus();
	   // Then
	  
	   assertEquals(status, 200);
	}

}
