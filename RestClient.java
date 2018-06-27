package me.rest.test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class RestClient {

    public <T> T getEntity(String resourcePath, Class<T> entityType) {
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(clientConfig);
        WebResource resource = client.resource(resourcePath);
        ClientResponse response = resource.get(ClientResponse.class);
        int status = response.getStatus();
        if (status != 200) {
            throw new RuntimeException("There are some problems to retrieve content. Status code: " + status);
        }
        return response.getEntity(entityType);
    }

    public StateDirectory getStateDirectory() {
        AllStatesResponse resp = getEntity(AllStatesResponse.resourcePath, AllStatesResponse.class);
        return new StateDirectory (resp.getEntity());
    }


    public static void main (String[] args ) throws IOException {
        RestClient client = new RestClient();
        StateDirectory directory = client.getStateDirectory();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.print("Please enter US state name or state abbreviation. (Type exit to exit this program) :");
                String in = br.readLine();
                if (in.toLowerCase().equals("exit")) {
                    System.out.println("Ending this program");
                    System.exit(0);
                }
                State state = directory.getState(in);
                if (state == null) {
                    System.out.println("There is no state for " + in);
                } else {
                    System.out.println(state);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

   }

}






