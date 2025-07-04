package com.amazonaws.webfluxapp;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.model.HttpApiV2ProxyRequest;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class LambdaHandler implements RequestStreamHandler {
    private static final SpringBootLambdaContainerHandler<HttpApiV2ProxyRequest, AwsProxyResponse> httpApiHandler;

    static {
        try {
            httpApiHandler = SpringBootLambdaContainerHandler.getHttpApiV2ProxyHandler(WebFluxTestApplication.class);
        } catch (ContainerInitializationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        httpApiHandler.proxyStream(inputStream, outputStream, context);
    }
}
