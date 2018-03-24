package com.onezao.onezao.sqlite1118;

public class SqliteBean {
        public String id;
        public String name;
        public String phone;
        public String sex;
        public String age;
        public String email;
        public String time;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public String getAge() {
			return age;
		}
		public void setAge(String age) {
			this.age = age;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		@Override
		public String toString() {
			return "SqliteBean [id=" + id + ", name=" + name + ", phone="
					+ phone + ", sex=" + sex + ", age=" + age + ", email="
					+ email + ", time=" + time + "]";
		}
        
		
}
