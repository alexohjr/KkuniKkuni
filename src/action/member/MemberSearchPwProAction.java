package action.member;

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

import bean.member.MemberDataBean;
import bean.member.MemberDBBean;


public class MemberSearchPwProAction implements CommandAction {
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int code = 0;
		code = (int)Math.floor((Math.random() * (99999 - 10000 + 1))) + 10000;

		request.setCharacterEncoding("UTF-8"); 
		
		String id = request.getParameter("id");
		String mail = request.getParameter("email");  
		
		MemberDBBean manager = MemberDBBean.getInstance();
		int check = manager.searchPw(id, mail, code); 
		
		request.setAttribute("id", id);
		request.setAttribute("email", mail);
		request.setAttribute("code", code);
		
		if(check == 1) {
			request.setAttribute("check", 1);
			// �ӽú�й�ȣ�� ����
		} else {
			request.setAttribute("check", -1);
			// �ӽú�й�ȣ ���� ����
		}

		request.setAttribute("check", check);
		
		
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
			String email = request.getParameter("email");
			InternetAddress to = new InternetAddress(email);
			msg.setRecipient(Message.RecipientType.TO, to);
			
			// �̸��� ����
			msg.setSubject("�ٴϲٴ� �ӽ� ��й�ȣ", "UTF-8");
			
			// �̸��� ����
			//String code = request.getParameter("code_check");
			String mailText = "��(o.o)��			�ӽú�й�ȣ ::: [" + code + "]			��(>.<)��";
			msg.setText(mailText, "UTF-8");
			request.setAttribute("code", code);

			
			// �̸��� ���
			msg.setHeader("content-Type", "text/html");
			
			// ���� ������
			if(check == 1) {
			javax.mail.Transport.send(msg);
			System.out.println("����!");
			}
			
		} catch (AddressException addr_e) {
			addr_e.printStackTrace();
		} catch (MessagingException msg_e) {
			msg_e.printStackTrace();
		}
		return "/Member/memberSearchPwPro.jsp";
	
	}
}

/*class MyAuthentication extends Authenticator {
	
	PasswordAuthentication pa;
	
	public MyAuthentication2() {
		
		String id = "alexohjr@gmail.com"; // ���� ID
		String pw = "Dhckdghks1!"; // ���� ��й�ȣ
		
		// ID�� ��й�ȣ�� �Է�
		pa = new PasswordAuthentication(id, pw);
	}

	public PasswordAuthentication getPasswordAuthentication() {
		return pa;
	}

}*/