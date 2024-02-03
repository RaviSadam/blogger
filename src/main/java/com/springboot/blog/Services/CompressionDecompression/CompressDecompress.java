package com.springboot.blog.Services.CompressionDecompression;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;



public class CompressDecompress {
    
	//compressing the blog data using .zip
	public static String compress(String str) throws IOException{
        if (str == null || str.length() == 0) {
            return null;
        }
        ByteArrayOutputStream obj1 = new ByteArrayOutputStream();
    	DeflaterOutputStream dos1 = new DeflaterOutputStream(obj1, new Deflater());
    	dos1.write(str.getBytes());
    	dos1.close();
    	obj1.toByteArray();
    	return new String(obj1.toByteArray());
    }

	//decompressing BG data
    public static String decompress(String str) throws Exception {
		byte bytes[]=str.getBytes();
        ByteArrayInputStream bis1 = new ByteArrayInputStream(bytes);
    	InflaterInputStream iis1 = new InflaterInputStream(bis1);
    	StringBuilder sb1 = new StringBuilder();
    	int ch;
    	while ((ch = iis1.read()) != -1) {
    	    sb1.append((char)ch);
    	}
		return sb1.toString();
    }
}
