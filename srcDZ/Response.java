package com.company;

import java.io.DataOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;


public abstract class Response {
    protected int code = 0;
    protected String reasonPhrase = "";
    protected Resource responseResource;
    protected DataOutputStream responseOut;
    protected File tempLogFile;
    protected File serverFile;
    protected HashMap <String, String> responseHeaders = new HashMap<>();

    public Response(){}

    abstract void send(OutputStream out);

    public void getGeneralHeaders(){
        Date date = new Date();
        responseHeaders.put("Date: ", date.toString());

        String s = "Derek & Zack";
        responseHeaders.put("Server Owners: ", s);
    }

    public int getCode(){ return code; }
    public String getReasonPhrase(){ return reasonPhrase; }

}
