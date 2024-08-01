package org.Server.http;

public class HttpRequest extends HttpMessage{
    private HttpMethod method;
    private String httpversion;
    private String requestTarget;

    HttpRequest() {
    }

    HttpMethod getMethod() {
        return method;
    }

    void setMethod(String method) throws HttpParsingException {
        if(method.length()>HttpMethod.MAX_METHOD_LENGTH)
            throw new HttpParsingException(HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
        for(HttpMethod httpMethod : HttpMethod.values()){
           if(method.equals(httpMethod.toString()))
           {
               this.method=httpMethod;
               return;
           }
        }

        throw new HttpParsingException(HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
    }

    String getHttpversion() {
        return httpversion;
    }

    void setHttpversion(String httpversion) {
        this.httpversion = httpversion;
    }

    String getRequestTarget() {
        return requestTarget;
    }

    void setRequestTarget(String requestTarget) {
        this.requestTarget = requestTarget;
    }

}
