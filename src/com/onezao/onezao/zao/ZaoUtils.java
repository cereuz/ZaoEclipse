package com.onezao.onezao.zao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.os.Environment;

/**
 * @author ��ţ
 *  pathSD  :   ��ȡSD��·��
 */
public class ZaoUtils {
       public static String ONEZAO = "onezao";
       public static String loginToast = "�û����������벻��Ϊ�գ�";
       public static String loginToast2 = "��¼�ɹ���";
       public static String saveSucc = "����ɹ���";
       public static String selectCB = "û�й�ѡCheckBoxŶ";
       public static String loginToastSave = "���ڱ����û��������룡";
       public static String loginToastSaveSucc = "�����û���������ɹ���";
       public static String loginToastSaveFail = "�����û���������ʧ�ܣ�";       
       public static  String  pathSD = Environment.getExternalStorageDirectory().getPath();
	   public static String SELECTGENDERANDNAME = "������������ѡ���Ա�";
     
       
     //��ȡϵͳʱ�䡣  
   	@SuppressLint("SimpleDateFormat")
   	public  static String getSystemTime(){
   	//	SimpleDateFormat    formatter    =   new    SimpleDateFormat    ("yyyy��MM��dd�� HH:mm:ss ");       
   		SimpleDateFormat    formatter    =   new    SimpleDateFormat    ("yyyy-MM-dd HH:mm:ss ");       
   		Date    curDate    =   new    Date(System.currentTimeMillis());//��ȡ��ǰʱ��       
   		String    str    =    formatter.format(curDate);
   		return str;  		
   	}
   	
   	//ʱ��ת��
	public static String  tranTime(String    time){
 		SimpleDateFormat    formatter    =   new    SimpleDateFormat    ("yyyy-MM-dd HH:mm:ss ");       
 		Date    curDate    =   new    Date(Long.valueOf(time));//��ȡ��ǰʱ�� 
   		String    str    =    formatter.format(curDate);
   		return str;  		
   	}
   	
   	//�����ļ�
   	public static void copyFile(String path1,String path2) { 
	    // TODO Auto-generated method stub         		 
	    //��װ����Դ 
	    FileInputStream fis;
		try {
			fis = new FileInputStream(new File(path1));		        		     
	    //��װĿ�ĵ�      		    
	    FileOutputStream fos = new FileOutputStream(new File(path2)); 
	     
	    int by = 0; 
	    while((by = fis.read()) != -1){ 
	      fos.write(by); 
	    } 
	    //�ͷ���Դ(�ȹ�˭����) 
	    fos.close(); 
	    fis.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	  } 
   	
   	//��ȡ�ļ�
   	public static String readFile(String  path){
   		 File  file =  new  File(path);
   		 try {
			BufferedReader  bf = new  BufferedReader(new  FileReader(file));
			String  content  =  "";
			StringBuilder  sb =  new  StringBuilder();
			while(content != null){
				content = bf.readLine();
				if(content == null){
					break;
				}
				sb.append(content.trim());
			}
			bf.close();
			return sb.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return  null;
		}   	
   	}
   	
   	//˯��
	 public static void  sleep(long  time){
			try {
	            Thread.sleep(time);
	        } catch (InterruptedException e) {
	            e.printStackTrace(); 
	        }
	 }
}
