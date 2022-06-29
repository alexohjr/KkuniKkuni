package action.member;

import action.CommandAction;

import bean.member.MemberDBBean;

import java.io.IOException;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberMailConfirmAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int code = 0;
		code = (int)Math.floor((Math.random() * (99999 - 10000 + 1))) + 10000;
		
		System.out.println("인증번호::" + code);
		Properties props = System.getProperties();
		props.put("mail.smtp.user", "alexohjr@gmail.com"); // 보내는사람
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		
		Authenticator auth = new MyAuthentication();
		
		Session session = Session.getDefaultInstance(props, auth);
		MimeMessage msg = new MimeMessage(session);
		try {
			// 편지 보낸시간
			msg.setSentDate(new Date());
			InternetAddress from = new InternetAddress("alexohjr@gmail.com");
			
			// 이메일 발신자
			msg.setFrom(from);
			
			// 이메일 수신자
			String email = request.getParameter("email");
			InternetAddress to = new InternetAddress(email);
			msg.setRecipient(Message.RecipientType.TO, to);
			
			// 이메일 제목
			msg.setSubject("꾸니꾸니 이메일 인증번호", "UTF-8");
			
			// 이메일 내용
			String mailText = "ㄴ(o.o)ㄱ			인증번호 ::: [" + code + "]			ㄴ(>.<)ㄱ";
			msg.setText(mailText, "UTF-8");
			request.setAttribute("code", code);
			
			// 이메일 헤더
			msg.setHeader("content-Type", "text/html");
			
			// 메일 보내기
			javax.mail.Transport.send(msg);
			
		} catch (AddressException addr_e) {
			addr_e.printStackTrace();
		} catch (MessagingException msg_e) {
			msg_e.printStackTrace();
		}
		return "/Member/memberMailConfirm.jsp";
	}
}

class MyAuthentication extends Authenticator {
	
	PasswordAuthentication pa;
	public MyAuthentication() {
		
		String id = "alexohjr@gmail.com"; // 구글 ID
		String pw = "Dhckdghks1!"; // 구글 비밀번호
		// ID와 비밀번호를 입력
		pa = new PasswordAuthentication(id, pw);
	}
	public PasswordAuthentication getPasswordAuthentication() {
		return pa;
	}
}
