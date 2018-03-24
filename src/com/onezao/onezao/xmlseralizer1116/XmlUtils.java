package com.onezao.onezao.xmlseralizer1116;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import com.onezao.onezao.zao.ZaoUtils;

public class XmlUtils {
	     public static  String xmlFile = "sms.xml";
	     public static  String xmlFileSD = "/sms.xml";
	     public static  String seralizerFileSD = "/smsSeralizerSD.xml";
	     public static  String pathSD =Environment.getExternalStorageDirectory().getPath();
	   
	  //文件写到内存中,
       public static void XmlSeralizerIn(Context context,String  xml){
    	   try {
				FileOutputStream fos = context.openFileOutput(xmlFile, Context.MODE_APPEND);
				fos.write(xml.getBytes());
				fos.close();
				Toast.makeText(context, ZaoUtils.saveSucc, Toast.LENGTH_LONG).show();
			} catch (IOException e) {
				e.printStackTrace();
			}
       }
         	       
      
       //文件序列化到内存卡上
       public static void XmlSeralizerSD(Context context,String  xml){
    	   try {    		   
    		   File file  =  new  File(pathSD+xmlFileSD);
    		   FileOutputStream fos = new FileOutputStream(file);
    		   fos.write(xml.getBytes());
    		   fos.close();
    		   Toast.makeText(context, ZaoUtils.saveSucc, Toast.LENGTH_LONG).show();   		   
    	   } catch (IOException e) {
    		   e.printStackTrace();
    	   }
       }
}
