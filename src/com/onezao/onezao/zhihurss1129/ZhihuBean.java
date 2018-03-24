package com.onezao.onezao.zhihurss1129;

public class ZhihuBean {
         public String  rss ;
         public String channel;
         public String item;
         public String title;
         public String link;
         public String description;
         public String dcCreator;
         public String pubDate;
         public String guid;
		public String getRss() {
			return rss;
		}
		public void setRss(String rss) {
			this.rss = rss;
		}
		public String getChannel() {
			return channel;
		}
		public void setChannel(String channel) {
			this.channel = channel;
		}
		public String getItem() {
			return item;
		}
		public void setItem(String item) {
			this.item = item;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getLink() {
			return link;
		}
		public void setLink(String link) {
			this.link = link;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getDcCreator() {
			return dcCreator;
		}
		public void setDcCreator(String dcCreator) {
			this.dcCreator = dcCreator;
		}
		public String getPubDate() {
			return pubDate;
		}
		public void setPubDate(String pubDate) {
			this.pubDate = pubDate;
		}
		public String getGuid() {
			return guid;
		}
		public void setGuid(String guid) {
			this.guid = guid;
		}
		@Override
		public String toString() {
			return "ZhihuBean [rss=" + rss + ", channel=" + channel + ", item="
					+ item + ", title=" + title + ", link=" + link
					+ ", description=" + description + ", dcCreator="
					+ dcCreator + ", pubDate=" + pubDate + ", guid=" + guid
					+ "]";
		}
         
         
}
