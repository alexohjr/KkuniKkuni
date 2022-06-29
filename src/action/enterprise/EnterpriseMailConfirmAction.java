package action.enterprise;

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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class EnterpriseMailConfirmAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int code = 0;
		code = (int)Math.floor((Math.random() * (99999 - 10000 + 1))) + 10000;
		System.out.println("������ȣ ::: " + code);

		//request.setAttribute("code", code);
		
		
		Properties props = System.getProperties();
		
		props.put("mail.smtp.user", "alexohjr@gmail.com"); // �����»��
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
			// ���� �����ð�
			msg.setSentDate(new Date());
			
			InternetAddress from = new InternetAddress("alexohjr@gmail.com");
			
			// �̸��� �߽���
			msg.setFrom(from);
			
			// �̸��� ������
			String email = request.getParameter("eMail");
			InternetAddress to = new InternetAddress(email);
			msg.setRecipient(Message.RecipientType.TO, to);
			
			// �̸��� ����
			msg.setSubject("�ٴϲٴ� �̸��� ������ȣ", "UTF-8");
			
			// �̸��� ����
			//String code = request.getParameter("code_check");
			String mailText = "��(o.o)��			������ȣ ::: [" + code + "]			��(>.<)��";
			msg.setText(mailText, "UTF-8");
			request.setAttribute("code", code);

			
			// �̸��� ���
			msg.setHeader("content-Type", "text/html");
			
			// ���� ������
			javax.mail.Transport.send(msg);
			System.out.println("����!");
			
		} catch (AddressException addr_e) {
			addr_e.printStackTrace();
		} catch (MessagingException msg_e) {
			msg_e.printStackTrace();
		}
		return "/Enterprise/enterpriseMailConfirm.jsp";
	
	}
}

class MyAuthentication extends Authenticator {
	
	PasswordAuthentication pa;
	
	public MyAuthentication() {
		
		String id = "alexohjr@gmail.com"; // ���� ID
		String pw = "Dhckdghks1!"; // ���� ��й�ȣ
		
		// ID�� ��й�ȣ�� �Է�
		pa = new PasswordAuthentication(id, pw);
	}

	public PasswordAuthentication getPasswordAuthentication() {
		return pa;
	}

}