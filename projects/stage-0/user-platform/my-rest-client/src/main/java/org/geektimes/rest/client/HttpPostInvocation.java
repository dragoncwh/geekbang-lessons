package org.geektimes.rest.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import org.geektimes.rest.core.DefaultResponse;

public class HttpPostInvocation implements Invocation {

  private final URI uri;

  private final URL url;

  private final MultivaluedMap<String, Object> headers;

  private final Entity<?> entity;

  HttpPostInvocation(URI uri, MultivaluedMap<String, Object> headers, Entity<?> entity) {
    this.uri = uri;
    this.headers = headers;
    this.entity = entity;
    try {
      this.url = uri.toURL();
    } catch (MalformedURLException e) {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public Invocation property(String s, Object o) {
    return this;
  }

  @Override
  public Response invoke() {
    HttpURLConnection connection = null;
    try {
      connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod(HttpMethod.POST);
      setRequestHeaders(connection);

      connection.setDoOutput(true);
      try (
          OutputStream os = connection.getOutputStream()
      ) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(os, entity.getEntity());
      }

      int statusCode = connection.getResponseCode();
      DefaultResponse response = new DefaultResponse();
      response.setConnection(connection);
      response.setStatus(statusCode);
      return response;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  private void setRequestHeaders(HttpURLConnection connection) {
    for (Map.Entry<String, List<Object>> entry : headers.entrySet()) {
      String headerName = entry.getKey();
      for (Object headerValue : entry.getValue()) {
        connection.setRequestProperty(headerName, headerValue.toString());
      }
    }
  }


  @Override
  public <T> T invoke(Class<T> aClass) {
    Response response = invoke();
    return response.readEntity(aClass);
  }

  @Override
  public <T> T invoke(GenericType<T> genericType) {
    Response response = invoke();
    return response.readEntity(genericType);
  }

  @Override
  public Future<Response> submit() {
    return null;
  }

  @Override
  public <T> Future<T> submit(Class<T> aClass) {
    return null;
  }

  @Override
  public <T> Future<T> submit(GenericType<T> genericType) {
    return null;
  }

  @Override
  public <T> Future<T> submit(InvocationCallback<T> invocationCallback) {
    return null;
  }
}
