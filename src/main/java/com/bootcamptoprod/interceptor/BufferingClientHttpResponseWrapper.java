package com.bootcamptoprod.interceptor;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class BufferingClientHttpResponseWrapper implements ClientHttpResponse {

    private final ClientHttpResponse response;  // Original response
    private byte[] responseBody;  // Buffered response body

    public BufferingClientHttpResponseWrapper(ClientHttpResponse response) throws IOException {
        this.response = response;
        this.responseBody = streamToByteArray(response.getBody());  // Buffer the response body
    }

    private byte[] streamToByteArray(InputStream inputStream) throws IOException {
        // Read the input stream into a byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        inputStream.transferTo(baos);
        return baos.toByteArray(); // Return the byte array
    }

    @Override
    public InputStream getBody() {
        // Return an InputStream from the buffered response body
        return new ByteArrayInputStream(responseBody);
    }

    @Override
    public HttpStatusCode getStatusCode() throws IOException {
        return response.getStatusCode();  // Delegate to the original response
    }

    @Override
    public int getRawStatusCode() throws IOException {
        return response.getRawStatusCode();  // Delegate to the original response
    }

    @Override
    public String getStatusText() throws IOException {
        return response.getStatusText();  // Delegate to the original response
    }

    @Override
    public void close() {
        response.close();  // Close the original response
    }

    @Override
    public org.springframework.http.HttpHeaders getHeaders() {
        return response.getHeaders();  // Delegate to the original response
    }
}