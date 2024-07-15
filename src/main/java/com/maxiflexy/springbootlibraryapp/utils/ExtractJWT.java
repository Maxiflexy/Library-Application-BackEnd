package com.maxiflexy.springbootlibraryapp.utils;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class ExtractJWT {

    public static String payloadJWTExtraction(String token, String extraction){

        token.replace( "Bearer ", "");

        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        String payload = new String(decoder.decode(chunks[1]));

        String[] entries = payload.split(",");
        Map<String, String> map = new HashMap<>();

        for(String entry : entries){
            String[] kayValue = entry.split(":");
            if(kayValue[0].equals(extraction)) {

                int remove = 1;
                if (kayValue[1].endsWith("}")) {
                    remove = 2;
                }
                kayValue[1] = kayValue[1].substring(0, kayValue[1].length() - remove);
                kayValue[1] = kayValue[1].substring(1);

                map.put(kayValue[0], kayValue[1]);
            }
        }
        if(map.containsKey(extraction)){
            return map.get(extraction);
        }
        return null;
    }
}
