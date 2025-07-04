package com.amazonaws.webfluxapp;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.amazonaws.serverless.proxy.model.HttpApiV2HttpContext;
import com.amazonaws.serverless.proxy.model.HttpApiV2ProxyRequest;
import com.amazonaws.serverless.proxy.model.HttpApiV2ProxyRequestContext;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

public class LambdaHandlerTest {

    private static final String EXPECTED_GET_DOUBLE_OUTPUT = "{\"statusCode\":200,\"headers\":{\"Content-Type\":\"text/plain; charset=UTF-8\"},\"multiValueHeaders\":{\"Content-Type\":[\"text/plain; charset=UTF-8\"]},\"body\":\"HelloHello\",\"isBase64Encoded\":false}";

    @Test
    void contextLoads() throws IOException {
        LambdaHandler lambdaHandler = new LambdaHandler();

        var body = new HttpApiV2ProxyRequest();
        body.setRawPath("/double");
        body.setVersion("1.0");
        HttpApiV2ProxyRequestContext requestContext = new HttpApiV2ProxyRequestContext();
        HttpApiV2HttpContext http = new HttpApiV2HttpContext();
        http.setMethod("GET");
        requestContext.setHttp(http);
        body.setRequestContext(requestContext);
        var inputStream = new ByteArrayInputStream(new ObjectMapper().findAndRegisterModules().writeValueAsBytes(body));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        lambdaHandler.handleRequest(inputStream, outputStream, null);


        assertEquals(EXPECTED_GET_DOUBLE_OUTPUT, outputStream.toString(UTF_8));
    }
}
