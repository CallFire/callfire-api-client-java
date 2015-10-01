package com.callfire.api.client.api;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.JsonConverter;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.message.BasicStatusLine;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Base api test class
 */
public class AbstractApiTest {
    protected CallfireClient client;
    protected JsonConverter jsonConverter;

    public AbstractApiTest() {
        client = new CallfireClient("login", "password");
        jsonConverter = client.getRestApiClient().getJsonConverter();
    }

    protected String getJsonPayload(String path) {
        try {
            StringBuilder result = new StringBuilder();
            for (String line : Files.readAllLines(Paths.get(this.getClass().getResource(path).toURI()))) {
                line = StringUtils.trim(line);
                line = line.replaceAll(": ", ":");
                result.append(line);
            }
            return result.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected StatusLine getStatus200_Ok() {
        return new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), 200, "OK");
    }
}
