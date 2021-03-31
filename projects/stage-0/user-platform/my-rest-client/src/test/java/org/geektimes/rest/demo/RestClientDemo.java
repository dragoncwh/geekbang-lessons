package org.geektimes.rest.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestClientDemo {

    public static void main(String[] args) throws Exception {
        // testGet();
        testPost();
    }

    private static void testPost() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.put("name", "dragon");

        // String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
        // System.out.println(jsonString);

        MediaType mediaType = new MediaType("application", "json", "utf-8");
        Entity entity = Entity.entity(rootNode, mediaType);

        Client client = ClientBuilder.newClient();
        Response response = client
            .target("http://127.0.0.1:8080/hello/world")      // WebTarget
            .request() // Invocation.Builder
            .post(entity);                                     //  Response

        String content = response.readEntity(String.class);

        System.out.println(content);
    }

    private static void testGet() {
        Client client = ClientBuilder.newClient();
        Response response = client
            .target("http://127.0.0.1:8080/hello/world")      // WebTarget
            .request() // Invocation.Builder
            .get();                                     //  Response

        String content = response.readEntity(String.class);

        System.out.println(content);
    }
}
