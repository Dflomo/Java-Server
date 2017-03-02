package com.company;

import java.awt.*;
import java.io.*;
import java.util.Iterator;
import java.util.Map;

public class OKResponse extends Response{

    OKResponse(Resource okResource){
        code = 200;
        reasonPhrase = "Request Success";
        responseResource = okResource;
    }

    public void send(OutputStream out) {
    //create an if statement here to check if html/text or other content type
//        if(//get document root with appended index.html) {
//            serverFile = new File("C:\\Users\\DORIS\\Documents\\Programming_WEB_CPP_JAVA\\DZ_WebServer\\WebServerProject-TheByteForge-DZSRC-Testing\\srcDZ\\conf\\public_html\\index.html");
//
//            try {
//                Desktop.getDesktop().browse(serverFile.toURI());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        else if(//script alias){
//            serverFile = new File("C:\\Users\\DORIS\\Documents\\Programming_WEB_CPP_JAVA\\DZ_WebServer\\WebServerProject-TheByteForge-DZSRC-Testing\\srcDZ\\conf\\public_html\\cgi-bin\\perl_env.pl");
//            try {
//                Desktop.getDesktop().browse(serverFile.toURI());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        else(//non aliased file){
//                //handle accordingly
//                //}

        try {

            System.out.print("(1) Here! But what happened?" + "\n");
            Iterator it = null;
            responseOut = new DataOutputStream(out);
            BufferedReader dataFromFile = new BufferedReader(new FileReader(serverFile));

            String tempHeaderLine = "HTTP/1.1 " + this.getCode() + " " + this.getReasonPhrase() + "\r\n";
            int statusLineByteLength = tempHeaderLine.getBytes("UTF-8").length;
            byte[] statusLineBytes = tempHeaderLine.getBytes("UTF-8");
            if(statusLineByteLength != 0) {
                responseOut.write(statusLineBytes, 0, statusLineByteLength);
            }
            getGeneralHeaders();

            responseHeaders.put("Content-Length: ", String.valueOf(serverFile.length()));

            it = responseHeaders.entrySet().iterator();
            System.out.print("(2) Here! But what happened? (Before While Loop!)" + "\n");
            while (it.hasNext()) {

                Map.Entry temp = (Map.Entry) it.next();

                tempHeaderLine = temp.getKey().toString() + temp.getValue().toString() + "\r\n";
                statusLineByteLength = tempHeaderLine.getBytes("UTF-8").length;
                statusLineBytes = tempHeaderLine.getBytes("UTF-8");
                if(statusLineByteLength != 0) {
                    responseOut.write(statusLineBytes, 0, statusLineByteLength);
                }
            }

            String tempLine;
            while ((tempLine = dataFromFile.readLine()) != null){
                System.out.print("This is the orgiinal String from the file: " + tempLine + "\n");
                statusLineByteLength = tempLine.getBytes("UTF-8").length;
//                System.out.print("This is the Line byte Length: " + statusLineByteLength + "\n");
                statusLineBytes = tempLine.getBytes("UTF-8");
//                System.out.print("This is the Line of the File in Bytes: " + statusLineBytes + "\n");
                if(statusLineByteLength != 0) {
                    responseOut.write(statusLineBytes, 0, statusLineByteLength);
                }

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
