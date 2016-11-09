package com.bank.common.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.log4j.Logger;

import com.bank.common.AppConstants;
import com.bank.common.model.MailBean;




public class EmailUtil{

    private static Logger logger = Logger.getLogger(EmailUtil.class);
    private synchronized void init(MailBean mailBean, String message) {
        mailBean.setHost((String) PropertiesUtil.getContextProperty(AppConstants.EMAIL_HOST));// 设置SMTP主机(163)，若用126，则设为：smtp.126.com
        mailBean.setUsername((String) PropertiesUtil.getContextProperty(AppConstants.EMAIL_SENDER));// 设置发件人邮箱的用户名
        mailBean.setPassword((String) PropertiesUtil.getContextProperty(AppConstants.EMAIL_PASSWORD));// 设置发件人邮箱的密码，需将*号改成正确的密码
        mailBean.setFrom((String) PropertiesUtil.getContextProperty(AppConstants.EMAIL_SENDER));// 设置发件人的邮箱
        mailBean.setTo((String) PropertiesUtil.getContextProperty(AppConstants.EMAIL_RECEIVER));// 设置收件人的邮箱
        mailBean.setSubject((String) PropertiesUtil.getContextProperty(AppConstants.EMAIL_SUBJECT));// 设置邮件的主题
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyy年MM月dd日"); //格式化当前系统日期
        String dateTime = dateFm.format(new java.util.Date());
        StringBuffer content = new StringBuffer();
        content.append("<table width='400' border='0'>");
        content.append("<tr><td>bias system have some exception: </td></tr>");
        content.append("<tr><td>&nbsp;</td></tr>");
        content.append("<tr><td>&nbsp;</td></tr>");
        content.append("<tr><td>The Exception Detail Information is : </td></tr>");
        content.append("<tr><td>&nbsp;</td></tr>");
        content.append("<tr><td>" + message + "</td></tr>");
        content.append("<tr><td>&nbsp;</td></tr>");
        content.append("<tr><td>please notice deal with these exception!</td></tr>");
        content.append("<tr><td>&nbsp;</td></tr>");
        content.append("<tr><td>&nbsp;</td></tr>");
        content.append("<tr><td>this all</td></tr>");
        content.append("<tr><td>bias system</td></tr>");
        content.append("<tr><td>" + dateTime + "</td></tr>");
        content.append("</table>");
        mailBean.setContent(content.toString());// 设置邮件的正文
        //mailBean.attachFile((String) PropertiesUtil.getContextProperty(AppConstants.EMAIL_ATTACHMENT));// 往邮件中添加附件
    }
    private static String toChinese(String text){
        try{
            text = MimeUtility.encodeText(new String(text.getBytes(), AppConstants.ENCODING_UTF), AppConstants.ENCODING_UTF, AppConstants.EMAIL_ENCODE);
        } catch (Exception e) {
            logger.error("email附件名转换为中文异常!");
            e.printStackTrace();
        }
        return text;
    }

    /*
     * java mail发送邮件
     * 注释：apricot版本弃用该方法，采用sendMailExt方法
     * @param mailBean
     * @return
     * @throws Exception
     */
    public synchronized boolean sendMail(String message) {
        MailBean mailBean = new MailBean();
        init(mailBean,message);
        String host = mailBean.getHost();
        final String username = mailBean.getUsername();
        final String password = mailBean.getPassword();
        String from = mailBean.getFrom();
        String to = mailBean.getTo();
        String subject = mailBean.getSubject();
        String content = mailBean.getContent();
        String fileName = mailBean.getFilename();
        String senderAlias = mailBean.getSenderAlias();
        Vector<String> file = mailBean.getFile();
        Properties props = System.getProperties();
        props.put(AppConstants.MAIL_SMTP_HOST, host);// 设置SMTP的主机
        props.put(AppConstants.MAIL_SMTP_AUTH, AppConstants.MAIL_AUTH_RESULT);// 需要经过验证
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from, senderAlias, AppConstants.ENCODING_UTF));
            InternetAddress[] address ={new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(toChinese(subject));
            Multipart mp = new MimeMultipart();
            BodyPart html = new MimeBodyPart();
            html.setContent(content, "text/html; charset=utf-8");
            mp.addBodyPart(html);
            // 往邮件中添加附件
            if(file!=null){
                Enumeration<String> efile = file.elements();
                while (efile.hasMoreElements()){
                    MimeBodyPart mailBeanpFile = new MimeBodyPart();
                    fileName = efile.nextElement().toString();
                    FileDataSource fds = new FileDataSource(fileName);
                    mailBeanpFile.setDataHandler(new DataHandler(fds));
                    mailBeanpFile.setFileName(toChinese(fds.getName()));
                    mp.addBodyPart(mailBeanpFile);
                }
            }
            msg.setContent(mp);
            msg.setSentDate(new Date());
            Transport.send(msg);

        } catch (MessagingException me){
            me.printStackTrace();
            return false;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return true;
    }

    /*
     * java mail 发送邮件
     * apricot版本，新增
     * @param mailBean
     * @return
     * @throws Exception
     */
    public synchronized boolean sendMailExt(String messageContent) {
        MailBean mailBean = new MailBean();
        init(mailBean, messageContent);
        String host = mailBean.getHost();
        final String username = mailBean.getUsername();
        final String password = mailBean.getPassword();
        String from = mailBean.getFrom();
        String to = mailBean.getTo();
        String subject = mailBean.getSubject();
        String content = mailBean.getContent();
        String fileName = mailBean.getFilename();
        String senderAlias = mailBean.getSenderAlias();
        Vector<String> file = mailBean.getFile();
        Properties props = System.getProperties();
        props.put(AppConstants.MAIL_HOST, host);// 设置主机
        props.setProperty(AppConstants.MAIL_TRANSPORT_PROTOCOL, AppConstants.MAIL_SMTP_VALUE);
        props.put(AppConstants.MAIL_SMTP_AUTH, AppConstants.MAIL_AUTH_RESULT);// 需要经过验证

        try{
            //1、创建session
            Session session = Session.getInstance(props);
            //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
            session.setDebug(true);
            //2、通过session得到transport对象
            Transport ts = session.getTransport();
            //3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
            ts.connect(host, username, password);
            //创建邮件对象
            MimeMessage message = new MimeMessage(session);
            //创建容器描述数据关系
            Multipart mp = new MimeMultipart();
            //创建邮件附件
            BodyPart html = new MimeBodyPart();
            //指明邮件的发件人
            message.setFrom(new InternetAddress(from, senderAlias, AppConstants.ENCODING_UTF));
            //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //邮件的标题
            message.setSubject(toChinese(subject));
            // 设置HTML内容
            html.setContent(content, "text/html; charset=utf-8");
            mp.addBodyPart(html);
            /*  往邮件中添加附件    */
            if (file != null) {
                Enumeration<String> efile = file.elements();
                while (efile.hasMoreElements()) {
                    MimeBodyPart mailBeanpFile = new MimeBodyPart();
                    fileName = efile.nextElement().toString();
                    FileDataSource fds = new FileDataSource(fileName);
                    mailBeanpFile.setDataHandler(new DataHandler(fds));
                    mailBeanpFile.setFileName(toChinese(fds.getName()));
                    mp.addBodyPart(mailBeanpFile);
                }
            }
            message.setContent(mp);
            message.setSentDate(new Date());
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();
        } catch (MessagingException me) {
            logger.error("发送email失败!");
            me.printStackTrace();
            return false;
        } catch (UnsupportedEncodingException e) {
            logger.error("发送email失败!");
            e.printStackTrace();
        }
        return true;
    }

}
