package com.onezao.onezao.zhihurss1129;

import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import android.util.Xml;

public class ZhihuXmlPullParserUtils {
	 public static ArrayList<ZhihuBean>  zhiHuPullParser(InputStream  in){
    	 ArrayList<ZhihuBean> list = null;
    	 ZhihuBean  zhiHuBean = null;
    	 ZhihuChannelBean  channelBean = null;
    	 try {
    	 // [1] ��ȡxml �Ľ�����
     	XmlPullParser  parser = Xml.newPullParser();
     	// [2]���ý�����Ҫ����������
     	parser.setInput(in,"utf-8");
     	//[3] ��ȡ�������¼�����
     	int eventType  = parser.getEventType();		
     	//[4] ��ͣ�����½���
    	 while(eventType != XmlPullParser.END_DOCUMENT){
    		 switch (eventType) {
			case XmlPullParser.START_TAG:
				if("rss".equals(parser.getName())){
					list = new ArrayList<ZhihuBean>();
					channelBean  =   new  ZhihuChannelBean();					 
				} else if("item".equals(parser.getName())){
					zhiHuBean = new ZhihuBean();
				} else if("title".equals(parser.getName())){
					if(zhiHuBean == null){
						channelBean.setTitle(parser.nextText());
					}  else {
						zhiHuBean.setTitle(parser.nextText());						
					}
				} else if("link".equals(parser.getName())){
					if(zhiHuBean == null){
						channelBean.setLink(parser.nextText());
					}  else {
						zhiHuBean.setLink(parser.nextText());				
					}
				} else if("description".equals(parser.getName())){
					if(zhiHuBean == null){
						channelBean.setDescription(parser.nextText());
					}  else {
						zhiHuBean.setDescription(parser.nextText());	
					}
				} else if("dcCreator".equals(parser.getName())){
					if(zhiHuBean == null){
						
					}  else {
						zhiHuBean.setDcCreator(parser.getAttributeValue(0)+"__"+parser.nextText());	
					}
				} else if("pubDate".equals(parser.getName())){
					if(zhiHuBean == null){
					}  else {
						zhiHuBean.setPubDate(parser.nextText());	
					}
				} else if("guid".equals(parser.getName())){
					if(zhiHuBean == null){
					}  else {
						zhiHuBean.setGuid(parser.nextText());	
					}
				}
                    break;
			case XmlPullParser.END_TAG:
				if("item".equals(parser.getName())){
					list.add(zhiHuBean);
				}		
			}
    		 
    		 //����Ҫ�������ȡ��һ����������Ȼ�ͻ�����ѭ���ˡ�
    		 eventType = parser.next();
    	 }
    	 } catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
            return  list;
     }
    	 
}
