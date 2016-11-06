package com.bank.common.model;


import java.util.Vector;

public class MailBean {
	
		private String to;// 收件人
		private String from;// 发件人
		private String host;// SMTP主机
		private String username;// 发件人的用户名
		private String password;// 发件人的密码
    	private String subject;// 邮件主题
    	private String content;// 邮件正文
    	private Vector<String> file;// 多个附件
    	private String filename;// 附件的文件名
    	private String senderAlias;//发送人别名
	    
	    public MailBean() {
		}

		public String getSenderAlias() {
			return senderAlias;
		}

		public void setSenderAlias(String senderAlias) {
			this.senderAlias = senderAlias;
		}

		public String getTo() {
	        return to;
	    }

	    public void setTo(String to) {
	        this.to = to;
	    }

	    public String getFrom() {
	        return from;
	    }

	    public void setFrom(String from) {
	        this.from = from;
	    }

	    public String getHost() {
	        return host;
	    }

	    public void setHost(String host) {
	        this.host = host;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public String getSubject() {
	        return subject;
	    }

	    public void setSubject(String subject) {
	        this.subject = subject;
	    }

	    public String getContent() {
	        return content;
	    }

	    public void setContent(String content) {
	        this.content = content;
	    }

	    public String getFilename() {
	        return filename;
	    }

	    public void setFilename(String filename) {
	        this.filename = filename;
	    }

	    public Vector<String> getFile() {
	        return file;
	    }
	    
	    public void attachFile(String fileName) {
	        if(file == null)
	            file = new Vector<String>();
	        file.addElement(fileName);
	    }

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("MailBean [to=");
			builder.append(to);
			builder.append(", from=");
			builder.append(from);
			builder.append(", host=");
			builder.append(host);
			builder.append(", username=");
			builder.append(username);
			builder.append(", password=");
			builder.append(password);
			builder.append(", subject=");
			builder.append(subject);
			builder.append(", content=");
			builder.append(content);
			builder.append(", file=");
			builder.append(file);
			builder.append(", filename=");
			builder.append(filename);
			builder.append(", senderAlias=");
			builder.append(senderAlias);
			builder.append("]");
			return builder.toString();
		}
	    
}



