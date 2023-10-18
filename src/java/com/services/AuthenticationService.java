/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;


import com.sun.org.apache.xml.internal.security.utils.Base64;
import java.util.StringTokenizer;

/**
 *
 * @author Mahmoud
 */
public class AuthenticationService {
 public boolean isAuthenticate (String authenticateString) {

         if (authenticateString==null) return false;
        String encodedUsernamePassword=authenticateString.replaceFirst ("Basic", "");

           try {byte [] decodedUserPass=Base64.decode (encodedUsernamePassword);
            String decodeUserPass=new String (decodedUserPass,"UTF-8");
            StringTokenizer tokenizer=new StringTokenizer (decodeUserPass,":"); 
            String userName=tokenizer.nextToken ();
            String password=tokenizer.nextToken ();
            if(userName.equals("mvr")&&password.equals("@durga"))return true;
           else return false;  }

           catch (Exception e) {
           e.printStackTrace();
            return false; } }  
}

