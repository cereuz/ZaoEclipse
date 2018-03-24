package com.onezao.onezao.newstoclient1113;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

public class XmlParserUtils {	
     public void yeeyanPullParser(InputStream  in) throws XmlPullParserException, IOException{
    	 ArrayList<Yeeyan> list = null;
    	 Yeeyan yeeyan = null;
     	// [1] ��ȡxml �Ľ�����
     	XmlPullParser  parser = Xml.newPullParser();
     	// [2]���ý�����Ҫ����������
     	parser.setInput(in,"utf-8");
     	//[3] ��ȡ�������¼�����
     	int eventType = parser.getEventType();
     	//[4] ��ͣ�����½���
    	 while(eventType != XmlPullParser.END_DOCUMENT){
    		 switch (eventType) {
			case XmlPullParser.START_TAG:
				if("feed".equals(parser.getName())){
					list = new ArrayList<Yeeyan>();
				} else if("entry".equals(parser.getName())){
					yeeyan = new Yeeyan();
				} else if("title".equals(parser.getName())){
					yeeyan.setTitle(parser.nextText());
				} else if("link".equals(parser.getName())){
					yeeyan.setLink(parser.getAttributeValue(0));
				} else if("created".equals(parser.getName())){
					yeeyan.setCreated(parser.nextText());
				} else if("issued".equals(parser.getName())){
					yeeyan.setIssued(parser.nextText());
				} else if("modified".equals(parser.getName())){
					yeeyan.setModified(parser.nextText());
				} else if("publish".equals(parser.getName())){
					yeeyan.setPublished(parser.nextText());
				} else if("id".equals(parser.getName())){
					yeeyan.setId(parser.nextText());
				} else if("name".equals(parser.getName())){
					yeeyan.setName(parser.nextText());
				} else if("uri".equals(parser.getName())){
					yeeyan.setUri(parser.nextText());
				} else if("content".equals(parser.getName())){
					yeeyan.setContent(parser.nextText());
				}
				break;
			case XmlPullParser.END_TAG:
				if("entry".equals(parser.getName())){
					list.add(yeeyan);
				}
				break;
			}
    		 
    		 //����Ҫ�������ȡ��һ����������Ȼ�ͻ�����ѭ���ˡ�
    		 eventType = parser.next();
    	 }
    	 for (Yeeyan yeeyanRss : list) {
			System.out.println(yeeyanRss);
		}
     }
	
	    //���ſͻ��˵�xml����
        public static  List<News>  parserXml(InputStream  in) throws Exception{
        	
        	List<News>   newsLists = null;
        	News  news  = null;
        	// [1] ��ȡxml �Ľ�����
        	XmlPullParser  parser = Xml.newPullParser();
        	// [2]���ý�����Ҫ����������
        	parser.setInput(in,"utf-8");
        	//[3] ��ȡ������ʱ������
        	int type = parser.getEventType();
        	//[4] ��ͣ�����½���
        	while(type!= XmlPullParser.END_DOCUMENT){
        		 //[5] �����ж�һ�½������ǿ�ʼ�ڵ㣬���ǽ����ڵ�
        		switch (type) {
        		case  XmlPullParser.START_TAG :    //������ʼ�ڵ�
        			// [6] �����ж�һ�½��������ĸ���ʼ��ǩ
        			if("channel".equals(parser.getName())){
        				//����һ��List����
        				newsLists  =  new ArrayList<News>();
        			} else if ( "item".equals(parser.getName())){
        				//����һ��News����
        				news  = new  News();
        				// ˳��xml�ļ��������������ö�������
        			} else if ("title".equals(parser.getName())){
        				news.setTitle(parser.nextText());
        			}  else if ("description".equals(parser.getName())){
        				news.setDescription(parser.nextText());
        			}  else if ("image".equals(parser.getName())){
        				news.setImage(parser.nextText());
        			} else if ("type".equals(parser.getName())){
        				news.setType(parser.nextText());
        			} else if ("comment".equals(parser.getName())){
        				news.setComment(parser.nextText());
        			}
        			break;
        		case  XmlPullParser.END_TAG :    //���������ڵ�
        			if ("item".equals(parser.getName())){
        				//��JavaBean��ӵ�����
        				newsLists.add(news);
        			}
        			break;
        		}
        		 type = parser.next();
        	}
			return newsLists;
        }
}
