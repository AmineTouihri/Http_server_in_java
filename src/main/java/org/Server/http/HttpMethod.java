package org.Server.http;

public enum HttpMethod {
    GET,POST,PUT,DELETE,HEAD,CONNECTION ;

    static int MAX_METHOD_LENGTH =0 ;

    static {
        for(HttpMethod method : HttpMethod.values()){
           if(method.toString().length()>MAX_METHOD_LENGTH) MAX_METHOD_LENGTH=method.toString().length();
        }
    }
}
