package me.rest.test;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class AllStatesResponse {
    public static final String resourcePath = "http://services.groupkt.com/state/get/USA/all";
    private RestResponse restResponse;

    @JsonProperty("RestResponse")
    public void setRestResponse(RestResponse restResponse) {
        this.restResponse = restResponse;
    }

    private static class RestResponse {
        private String[] messages;
        private State[] result;

        public String[] getMessages() {
            return messages;
        }

        public void setMessages(String[] messages) {
            this.messages = messages;
        }

        public State[] getResult() {
            return result;
        }

        public void setResult(State[] result) {
            this.result = result;
        }
    }

    public String[] getMesgaes() {
        return restResponse.getMessages();
    }

    public State[] getEntity() {
        return restResponse.getResult();
    }

}
