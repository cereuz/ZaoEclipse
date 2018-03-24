package com.onezao.onezao.mobilesafe0207.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamUtil {

	/**
	 * @param is   ������
	 * @return   ת���ɵ��ַ���������null�����쳣
	 */
	public static String streamToString(InputStream is) {
		//1. �ڶ�ȡ�Ĺ����У�����ȡ�����ݴ洢�ڻ����У�Ȼ��һ���Ե�ת�����ַ�������
		ByteArrayOutputStream bos = new  ByteArrayOutputStream();
		//2. ��������������û��Ϊֹ��ѭ����
		byte[]  buffer  =  new  byte[1024];
		//3. ��¼��ȡ���ݵ���ʱ����
		int  temp = -1;
		try {
			while((temp = is.read(buffer))!= -1){
				bos.write(buffer,0,temp);
			}
			//���ض�ȡ������
			return  bos.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally  {
			try {
				//�ر�
				is.close();
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return  null;
	}

}