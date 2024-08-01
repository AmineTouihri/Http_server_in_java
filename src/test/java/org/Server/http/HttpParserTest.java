package org.Server.http;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HttpParserTest {
private HttpParser httpParser;
    @BeforeAll
    public void beforeClass(){
        httpParser= new HttpParser();

    }
    @Test
    void parseHttpRequest() {
        try {
            HttpRequest request=httpParser.parseHttpRequest(generateValidTestCase());
        } catch (HttpParsingException e) {
           fail(e);
        }
    }

    @Test
    void parseHttpRequestInvalidMethodLength() {
        try {
            HttpRequest request= httpParser.parseHttpRequest(generateInvalidMethodeNameLength());
        } catch (HttpParsingException e) {
            assertEquals(e.getHttpStatusCode(),HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
        }
    }
    @Test
    void parseHttpRequestInvalidMethodName() {
        try {
            HttpRequest request= httpParser.parseHttpRequest(generateInvalidMethodeNameTestCase());
        } catch (HttpParsingException e) {
            assertEquals(e.getHttpStatusCode(),HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
        }
    }
    public InputStream generateValidTestCase(){
        String rawData = "GET / HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Connection: keep-alive\r\n" +
                "sec-ch-ua: \"Google Chrome\";v=\"125\", \"Chromium\";v=\"125\", \"Not.A/Brand\";v=\"24\"\r\n" +
                "sec-ch-ua-mobile: ?0\r\n" +
                "sec-ch-ua-platform: \"Linux\"\r\n" +
                "Upgrade-Insecure-Requests: 1\r\n" +
                "User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7 \r\n"+
                "\r\n";
        InputStream inputStream= new ByteArrayInputStream(rawData.getBytes(StandardCharsets.US_ASCII));
        return inputStream;
    }


    public InputStream generateInvalidMethodeNameLength(){
        String rawData = "GeeeeeT / HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "\r\n";
        InputStream inputStream= new ByteArrayInputStream(rawData.getBytes(StandardCharsets.US_ASCII));
        return inputStream;
    }
    public InputStream generateInvalidMethodeNameTestCase(){
        String rawData = "GeT / HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "\r\n";
        InputStream inputStream= new ByteArrayInputStream(rawData.getBytes(StandardCharsets.US_ASCII));
        return inputStream;
    }

}