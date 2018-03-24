package com.onezao.onezao.login1114;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import com.onezao.onezao.zao.ZaoUtils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.Toast;

public class LoginUtils {
	public static String   urlCo  ="data/data/com.onezao.onezao.zao/info.txt";
	public static String   urlSD  ="mnt/sdcard/info.txt";
    public static String  fileName = "/info.txt";     //����Ǿ���·�����ʹ�õ��ļ�������
    public static String  fileNames = "info.txt";    //�����ֱ�ӻ�ȡ�ļ��е�·�����ļ������֡���Ҫ����/
	
	
      /**
       * �����û���������
     * @param username    �û���
     * @param password     ����
     * @return
     */
    //1.1��¼�Ĳ����߼�,ͨ���̶�·��
		public static void loginMT(Context context,String username,String password,CheckBox cb_login_rem,String url){
		 if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
			Toast.makeText(context, ZaoUtils.loginToast, Toast.LENGTH_LONG).show();
		}   else  {
			//ͨ��checkBox��״̬���ж��Ƿ񱣴�
			boolean checked = cb_login_rem.isChecked();
			if(checked){
				//��ӡ��־����ʾ���ڱ����û���������
				Log.d("LoginActivity", "�����û�����"+username+"�����룺"+password);
				Toast.makeText(context, ZaoUtils.loginToastSave, Toast.LENGTH_LONG).show();
				//�����û����������룬���ж��Ƿ񱣴�ɹ�
				boolean saveInfo = LoginUtils.saveInfo(username,password,url);
				if(saveInfo){
					Toast.makeText(context, ZaoUtils.loginToastSaveSucc, Toast.LENGTH_LONG).show();
				}   else {
					Toast.makeText(context, ZaoUtils.loginToastSaveFail, Toast.LENGTH_LONG).show();
				}

			}   
			Toast.makeText(context, ZaoUtils.loginToast2, Toast.LENGTH_LONG).show();
			Log.d("LoginActivity", "��ʼ��¼");
		              }
		}
		
		//1.2��¼�Ĳ����߼�,ͨ��Context
		public static void loginMTByContext(Context context,String username,String password,CheckBox cb_login_rem){
			if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
				Toast.makeText(context, ZaoUtils.loginToast, Toast.LENGTH_LONG).show();
			}   else  {
				//ͨ��checkBox��״̬���ж��Ƿ񱣴�
				boolean checked = cb_login_rem.isChecked();
				if(checked){
					//��ӡ��־����ʾ���ڱ����û���������
					Log.d("LoginActivity", "�����û�����"+username+"�����룺"+password);
					Toast.makeText(context, ZaoUtils.loginToastSave, Toast.LENGTH_LONG).show();
					//�����û����������룬���ж��Ƿ񱣴�ɹ�
					boolean saveInfo = LoginUtils.saveInfoByContext(context,username,password);
					if(saveInfo){
						Toast.makeText(context, ZaoUtils.loginToastSaveSucc, Toast.LENGTH_LONG).show();
					}   else {
						Toast.makeText(context, ZaoUtils.loginToastSaveFail, Toast.LENGTH_LONG).show();
					}
					
				}   
				Toast.makeText(context, ZaoUtils.loginToast2, Toast.LENGTH_LONG).show();
				Log.d("LoginActivity", "��ʼ��¼");
			}
		}
	
	//1.3  �����¼ע����Ϣ��ͨ��Environment���������߼�
		public static void loginMTByEnvironment(Context  context,String username,String password,CheckBox  cb_login_rem){
			if(TextUtils.isEmpty(username)|| TextUtils.isEmpty(password)){
				Toast.makeText(context, ZaoUtils.loginToast, Toast.LENGTH_LONG).show();
			}   else  {
				boolean checked = cb_login_rem.isChecked();
				if(checked){
					//�����û���������
					//��ȡSD��״̬�����״̬��MEDIA_MOUNTED  ,  ˵��SD������ʹ�á�
					if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
						//���浽SD��
						boolean saveInfoToSD = saveInfo2SDcardByEnvironment(username, password);
						Toast.makeText(context, saveInfoToSD?ZaoUtils.loginToastSaveSucc:ZaoUtils.loginToastSaveFail, Toast.LENGTH_LONG).show();
					}   else {
						LoginUtils.saveInfoByContext2(context, username, password);
					}
				}  else {
					Toast.makeText(context, ZaoUtils.selectCB, Toast.LENGTH_LONG).show();
				}
			}
		}
		
	
		
		
	//2.1  �����û��������룬�̶�·��
    public static  boolean  saveInfo(String username ,String password,String url){
    	     String info = username  + "##" + password;
    	     File file = new  File(url);
    	     //��Ϊ�ֻ�    mnt/shell/emulated/0/com.onezao.onezao.zao/info.txt
    	     FileOutputStream fos;
			try {
				fos = new FileOutputStream(file);
				fos.write(info.getBytes());
				fos.close();
				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
      }
    
    /**
     *2.2    ��ȡ�û��������� ���̶�·��
     * @return   ���顣 ��һ��Ϊ�û������ڶ���Ϊ���룬���Ϊ�գ�˵����ȡʧ��
     */
    public static String[]  readInfo(String url){
    	File file =  new File(url);    	
    	try {
    	    FileInputStream fis = new FileInputStream(file);
			BufferedReader  br = new BufferedReader(new InputStreamReader(fis));
			String temp = br.readLine();
			String[]  result = temp.split("##");
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    
    
	//3.1  �����û��������룬ͨ��Context������
    public static  boolean  saveInfoByContext(Context context ,String username ,String password){
    	     String info = username  + "##" + password;
             //Context    ������
    	     File file =  new File(context.getFilesDir().getAbsolutePath()+fileName);
    	     FileOutputStream fos;
			try {
				fos = new FileOutputStream(file);
				fos.write(info.getBytes());
				fos.close();
				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
      }    
    
    /**
     *3.2  ��ȡ�û���������   ͨ��������
     * @return   ���顣 ��һ��Ϊ�û������ڶ���Ϊ���룬���Ϊ�գ�˵����ȡʧ��
     */
    public static String[]  readInfoByContext(Context context){
//    	File file =  new File(url);
	     File file =  new File(context.getFilesDir().getAbsolutePath()+fileName);
    	try {
    	    FileInputStream fis = new FileInputStream(file);
			BufferedReader  br = new BufferedReader(new InputStreamReader(fis));
			String temp = br.readLine();
			String[]  result = temp.split("##");
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    
    
  //4.1  �����û��������룬ͨ��Context������
    public static  boolean  saveInfoByContext2(Context context ,String username ,String password){
    	     String info = username  + "##" + password;
             //Context    ������
    	     FileOutputStream fos;
			try {
				fos = context.openFileOutput("", Context.MODE_PRIVATE);
				fos.write(info.getBytes());
				fos.close();
				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
      }    
    
    /**
     *4.2  ��ȡ�û���������   ͨ��������
     * @return   ���顣 ��һ��Ϊ�û������ڶ���Ϊ���룬���Ϊ�գ�˵����ȡʧ��
     */
    public static String[]  readInfoByContext2(Context context){
    	try {
    	    FileInputStream fis = context.openFileInput(fileNames);
			BufferedReader  br = new BufferedReader(new InputStreamReader(fis));
			String temp = br.readLine();
			String[]  result = temp.split("##");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }   
    
    
    /*
     * 5.1  �����û��������룬ʹ��Environment  �����浽�ڴ濨
     */
    public static boolean saveInfo2SDcardByEnvironment(String username,String password){    	
    	String  temp = username + "##" + password;
    	try {
    		File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+fileName);
			FileOutputStream  fos = new FileOutputStream(file);
			fos.write(temp.getBytes());
			fos.close();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    }
    
    
    /**
     *5.2  ��ȡ�û���������   ͨ��Environment
     * @return   ���顣 ��һ��Ϊ�û������ڶ���Ϊ���룬���Ϊ�գ�˵����ȡʧ��
     */
    public static String[]  readInfoByEnvironment(Context context,String fileName){
    	try {
    		File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+fileName);
    		FileInputStream   fis = new  FileInputStream(file);
    		BufferedReader  br = new BufferedReader(new InputStreamReader(fis));
    		String temp = br.readLine();
    		String[]  result = temp.split("##");
    		return result;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
}
