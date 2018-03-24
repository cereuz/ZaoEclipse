package com.onezao.onezao.htmlcodeviewer1124;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class HtmlCodeUtils {

	public static int timeout = 10000;

	public static String getStringFromStream(InputStream inputStream) {
             ByteArrayOutputStream  baos = new ByteArrayOutputStream();
             int len = -1;
             byte[]  buffer = new byte[1024];
             try {
				while((len=inputStream.read(buffer)) != -1){
					 baos.write(buffer,0,len);
				 }  
				inputStream.close();
				byte[]  byteArray  = baos.toByteArray();
				return  new String(byteArray);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
  }

	public static String getStringFromPath(String path) {
		try {
		File  file  =  new  File(path);
		FileInputStream  fis =  new  FileInputStream(file);
        ByteArrayOutputStream  baos = new ByteArrayOutputStream();
        int len = -1;
        byte[]  buffer = new byte[1024];        
			while((len=fis.read(buffer)) != -1){
				 baos.write(buffer,0,len);
			 }  
			fis.close();
			byte[]  byteArray  = baos.toByteArray();
			return  new String(byteArray);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
