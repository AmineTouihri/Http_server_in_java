package org.Server.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class HttpParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpParser.class);
    private final static int  SP=0x20; // space code ascci
    private final static int  CR=0x0D; // space code ascci
    private final static int LF =0x0A; // space code ascci

    public HttpRequest parseHttpRequest(InputStream inputStream) throws HttpParsingException {
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);
        HttpRequest httpRequest = new HttpRequest();
        try {
            parseRequestLine(reader,httpRequest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        parseHeaders(reader,httpRequest);
//        parseBody(reader,httpRequest);
        return httpRequest;
    }

    private void parseBody(InputStreamReader inputStream, HttpRequest httpRequest) {
    }

    private void parseHeaders(InputStreamReader inputStream, HttpRequest httpRequest) {
    }

    private void parseRequestLine(InputStreamReader inputStream, HttpRequest httpRequest) throws IOException, HttpParsingException {
        StringBuilder processingDataBuffer= new StringBuilder();
        boolean isMethodeParsed=false;
        boolean isTargetRootParsed=false;
        boolean isHttpVersionParsed=false;
        int _Byte;
        while((_Byte=inputStream.read())>=0){

            if(_Byte==CR){
                _Byte=inputStream.read();
                if(_Byte==LF  && isMethodeParsed && isTargetRootParsed ){
                    LOGGER.debug("reqest line "+processingDataBuffer.toString());
                    httpRequest.setHttpversion(processingDataBuffer.toString());
//                    parseHeaders(inputStream,httpRequest);
                }else {
                    // TODO throw error because there is not an cr without in the request line
                    throw new HttpParsingException(HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
                }

            }
            if(_Byte==SP){

                if(! isMethodeParsed){

                    httpRequest.setMethod(processingDataBuffer.toString());
                    isMethodeParsed=true;
                }else if( ! isTargetRootParsed){
                    httpRequest.setRequestTarget(processingDataBuffer.toString());
                    isTargetRootParsed=true;
                }

                processingDataBuffer.delete(0,processingDataBuffer.length());
            }else {
                processingDataBuffer.append((char)_Byte);
            }
        }
    }
}
