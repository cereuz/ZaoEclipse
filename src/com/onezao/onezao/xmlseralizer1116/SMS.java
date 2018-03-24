package com.onezao.onezao.xmlseralizer1116;

public class SMS {
      //发信人
	public String from;
	//短信内容
	public String content;
	//收到短信的时间
	public String time;
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	@Override
	public String toString() {
		return "SMS [from=" + from + ", content=" + content + ", time=" + time
				+ "]\n";
	}
	
}
