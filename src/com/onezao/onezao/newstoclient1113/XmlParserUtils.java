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
     	// [1] 获取xml 的解析器
     	XmlPullParser  parser = Xml.newPullParser();
     	// [2]设置解析器要解析的内容
     	parser.setInput(in,"utf-8");
     	//[3] 获取解析的事件类型
     	int eventType = parser.getEventType();
     	//[4] 不停的向下解析
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
    		 
    		 //必须要在这里获取下一个解析，不然就会是死循环了。
    		 eventType = parser.next();
    	 }
    	 for (Yeeyan yeeyanRss : list) {
			System.out.println(yeeyanRss);
		}
     }
	
	    //新闻客户端的xml解析
        public static  List<News>  parserXml(InputStream  in) throws Exception{
        	
        	List<News>   newsLists = null;
        	News  news  = null;
        	// [1] 获取xml 的解析器
        	XmlPullParser  parser = Xml.newPullParser();
        	// [2]设置解析器要解析的内容
        	parser.setInput(in,"utf-8");
        	//[3] 获取解析的时间类型
        	int type = parser.getEventType();
        	//[4] 不停的向下解析
        	while(type!= XmlPullParser.END_DOCUMENT){
        		 //[5] 具体判断一下解析的是开始节点，还是结束节点
        		switch (type) {
        		case  XmlPullParser.START_TAG :    //解析开始节点
        			// [6] 具体判断一下解析的是哪个开始标签
        			if("channel".equals(parser.getName())){
        				//创建一个List集合
        				newsLists  =  new ArrayList<News>();
        			} else if ( "item".equals(parser.getName())){
        				//创建一个News对象
        				news  = new  News();
        				// 顺着xml文件解析，依次设置对象内容
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
        		case  XmlPullParser.END_TAG :    //解析结束节点
        			if ("item".equals(parser.getName())){
        				//把JavaBean添加到集合
        				newsLists.add(news);
        			}
        			break;
        		}
        		 type = parser.next();
        	}
			return newsLists;
        }
}
