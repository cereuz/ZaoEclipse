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
    public static String  fileName = "/info.txt";     //这个是绝对路径配合使用的文件夹名字
    public static String  fileNames = "info.txt";    //这个是直接获取文件夹的路径的文件夹名字。需要区分/
	
	
      /**
       * 保存用户名和密码
     * @param username    用户名
     * @param password     密码
     * @return
     */
    //1.1登录的操作逻辑,通过固定路径
		public static void loginMT(Context context,String username,String password,CheckBox cb_login_rem,String url){
		 if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
			Toast.makeText(context, ZaoUtils.loginToast, Toast.LENGTH_LONG).show();
		}   else  {
			//通过checkBox的状态，判断是否保存
			boolean checked = cb_login_rem.isChecked();
			if(checked){
				//打印日志，提示正在保存用户名和密码
				Log.d("LoginActivity", "保存用户名："+username+"，密码："+password);
				Toast.makeText(context, ZaoUtils.loginToastSave, Toast.LENGTH_LONG).show();
				//保存用户名或者密码，并判断是否保存成功
				boolean saveInfo = LoginUtils.saveInfo(username,password,url);
				if(saveInfo){
					Toast.makeText(context, ZaoUtils.loginToastSaveSucc, Toast.LENGTH_LONG).show();
				}   else {
					Toast.makeText(context, ZaoUtils.loginToastSaveFail, Toast.LENGTH_LONG).show();
				}

			}   
			Toast.makeText(context, ZaoUtils.loginToast2, Toast.LENGTH_LONG).show();
			Log.d("LoginActivity", "开始登录");
		              }
		}
		
		//1.2登录的操作逻辑,通过Context
		public static void loginMTByContext(Context context,String username,String password,CheckBox cb_login_rem){
			if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
				Toast.makeText(context, ZaoUtils.loginToast, Toast.LENGTH_LONG).show();
			}   else  {
				//通过checkBox的状态，判断是否保存
				boolean checked = cb_login_rem.isChecked();
				if(checked){
					//打印日志，提示正在保存用户名和密码
					Log.d("LoginActivity", "保存用户名："+username+"，密码："+password);
					Toast.makeText(context, ZaoUtils.loginToastSave, Toast.LENGTH_LONG).show();
					//保存用户名或者密码，并判断是否保存成功
					boolean saveInfo = LoginUtils.saveInfoByContext(context,username,password);
					if(saveInfo){
						Toast.makeText(context, ZaoUtils.loginToastSaveSucc, Toast.LENGTH_LONG).show();
					}   else {
						Toast.makeText(context, ZaoUtils.loginToastSaveFail, Toast.LENGTH_LONG).show();
					}
					
				}   
				Toast.makeText(context, ZaoUtils.loginToast2, Toast.LENGTH_LONG).show();
				Log.d("LoginActivity", "开始登录");
			}
		}
	
	//1.3  保存登录注册信息，通过Environment来操作的逻辑
		public static void loginMTByEnvironment(Context  context,String username,String password,CheckBox  cb_login_rem){
			if(TextUtils.isEmpty(username)|| TextUtils.isEmpty(password)){
				Toast.makeText(context, ZaoUtils.loginToast, Toast.LENGTH_LONG).show();
			}   else  {
				boolean checked = cb_login_rem.isChecked();
				if(checked){
					//保存用户名和密码
					//获取SD卡状态，如果状态是MEDIA_MOUNTED  ,  说明SD卡可以使用。
					if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
						//保存到SD卡
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
		
	
		
		
	//2.1  保存用户名和密码，固定路径
    public static  boolean  saveInfo(String username ,String password,String url){
    	     String info = username  + "##" + password;
    	     File file = new  File(url);
    	     //华为手机    mnt/shell/emulated/0/com.onezao.onezao.zao/info.txt
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
     *2.2    读取用户名和密码 ，固定路径
     * @return   数组。 第一个为用户名，第二个为密码，如果为空，说明获取失败
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
    
    
	//3.1  保存用户名和密码，通过Context上下文
    public static  boolean  saveInfoByContext(Context context ,String username ,String password){
    	     String info = username  + "##" + password;
             //Context    上下文
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
     *3.2  读取用户名和密码   通过上下文
     * @return   数组。 第一个为用户名，第二个为密码，如果为空，说明获取失败
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
    
    
  //4.1  保存用户名和密码，通过Context上下文
    public static  boolean  saveInfoByContext2(Context context ,String username ,String password){
    	     String info = username  + "##" + password;
             //Context    上下文
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
     *4.2  读取用户名和密码   通过上下文
     * @return   数组。 第一个为用户名，第二个为密码，如果为空，说明获取失败
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
     * 5.1  保存用户名和密码，使用Environment  来保存到内存卡
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
     *5.2  读取用户名和密码   通过Environment
     * @return   数组。 第一个为用户名，第二个为密码，如果为空，说明获取失败
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
