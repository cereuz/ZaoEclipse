package com.onezao.onezao.xmlseralizer1116;

public class SMS {
      //������
	public String from;
	//��������
	public String content;
	//�յ����ŵ�ʱ��
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
