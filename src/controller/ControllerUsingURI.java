package controller;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import action.NullAction;

public class ControllerUsingURI extends HttpServlet {
	
	private Map commandMap = new HashMap(); // ��ɾ�� ��ɾ� ó�� Ŭ������ ������ ����
	
	// ��ɾ�� ó��Ŭ������ ���εǾ� �ִ� properties ������ �о Map��ü�� commandMap�� ����
	// ��ɾ�� ó��Ŭ������ ���εǾ� �ִ� properties ������ Command.properties����
	public void init(ServletConfig config) throws ServletException {
		
		String props = config.getInitParameter("configFile2"); // web.xml���� propertyConfig�� �ش��ϴ� init-param �� ���� �о�� 
		Properties pr = new Properties(); // ��ɾ�� ó��Ŭ������ ���������� ������ Properites��ü ����
		FileInputStream f = null;
		
		try {
			String configFilePath = config.getServletContext().getRealPath(props);
			f = new FileInputStream(configFilePath); // Command.properties������ ������ �о��
			pr.load(f); // Command.properties������ ������ Properties��ü�� ����
		} catch(IOException e) {
			throw new ServletException(e);
		} finally {
			if(f != null) try {f.close(); } catch(IOException ex) {}
		}
		Iterator keyIter = pr.keySet().iterator(); // Iterator��ü�� Enumeration ��ü�� Ȯ���Ų ������ ��ü
		while(keyIter.hasNext()) { // ��ü�� �ϳ��� ������ �� ��ü������ Properties��ü�� ����� ��ü�� ����
				String command = (String)keyIter.next();
				String className = pr.getProperty(command);
				try {
					Class commandClass = Class.forName(className); // �ش繮�ڿ��� Ŭ������ �����
					Object commandInstance = commandClass.newInstance(); // �ش�Ŭ������ ��ü�� ����
					commandMap.put(command, commandInstance); // Map��ü�� commandMap�� ����
				} catch(ClassNotFoundException e) {
					throw new ServletException(e);
				} catch(InstantiationException e) {
					throw new ServletException(e);
				} catch(IllegalAccessException e) {
					throw new ServletException(e);
				}
			}
		}

		// get����� ���� �޼���
		public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			requestPro(request, response);
		}
		
		// post����� ���� �޼���
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			requestPro(request, response);
		}
		
		// ������� ��û�� �м��ؼ� �ش��۾��� ó��
		private void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String view = null;
			CommandAction com = null;
			try {
				String command = request.getRequestURI();
				if (command.indexOf(request.getContextPath()) == 0) {
					command = command.substring(request.getContextPath().length());
				}
				com = (CommandAction)commandMap.get(command);
				if(com == null) {
					com = new NullAction();
				}
			view = com.requestPro(request, response);
			} catch(Throwable e) {
				throw new ServletException(e);
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
		
}
