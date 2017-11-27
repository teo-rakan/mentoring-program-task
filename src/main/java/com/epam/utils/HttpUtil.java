package com.epam.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class HttpUtil {

    private static final Logger LOGGER = LogManager.getLogger(HttpUtil.class);

    public static int getStatusCode(String link) {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(link);
        int responseCode = 0;

        try {
            HttpResponse response = client.execute(get);
            get.releaseConnection();
            responseCode = response.getStatusLine().getStatusCode();
        } catch (IOException e) {
            LOGGER.error("Cannot execute HTTP get request: " + e.getMessage());
        }
        return responseCode;
    }
}
