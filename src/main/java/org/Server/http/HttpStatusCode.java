package org.Server.http;

public enum HttpStatusCode {

    // ---------------CLIENT ERRORS -------------
   CLIENT_ERROR_400_BAD_REQUEST(400,"BAD REQUEST") ,
    CLIENT_ERROR_401_METHOD_NOT_ALLOWED(401,"METHOD NOT ALLOWED") ,
    CLIENT_ERROR_414_BAD_REQUEST(414,"URI TO LONG") ,
    // ---------------SERVER ERRORS -------------
    SERVER_ERROR_500_INTERNAL_SERVER_ERROR(500,"INTERNAL SERVER ERROR") ,
    SERVER_ERROR_501_NOT_IMPLEMENTED(501,"NOT IMPLEMENTED") ;



   public int STATUS_CODE;
   public final String MESSAGE;
    HttpStatusCode(int STATUS_CODE,String MESSAGE){

        this.STATUS_CODE=STATUS_CODE;
        this.MESSAGE=MESSAGE;
    }
}
