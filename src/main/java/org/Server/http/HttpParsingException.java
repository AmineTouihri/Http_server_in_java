package org.Server.http;

public class HttpParsingException extends Exception{
    private final HttpStatusCode httpStatusCode;
HttpParsingException(HttpStatusCode httpStatusCode){
    super(httpStatusCode.MESSAGE);
    this.httpStatusCode=httpStatusCode;
}

    public HttpStatusCode getHttpStatusCode() {
        return httpStatusCode;
    }
}
