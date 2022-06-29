package action.notice;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.CommandAction;
import bean.notice.NoticeDataBean;

public class NoticeDownloadAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("UTF-8");

		int idx = Integer.parseInt(request.getParameter("idx"));

		NoticeDataBean article = new NoticeDataBean();
		// NoticeDataBean article = BoardDao.getInstance().getArticle(idx);

		String nFile = article.getnFile();

		String uploadFileName = request.getRealPath("/files") + "/" + nFile;

		File downFile = new File(uploadFileName);

		if (downFile.exists() && downFile.isFile()) // 다운로드

		{

			try

			{

				long filesize = downFile.length();

				response.setContentType("application/x-msdownload");

				response.setContentLength((int) filesize);

				String strClient = request.getHeader("user-agent");

				if (strClient.indexOf("MSIE 5.5") != -1)

				{

					response.setHeader("Content-Disposition", "filename=" + nFile + ";");

				}

				else

				{


					// 인코딩 변경과 '+'문자 깨짐 방지 2013 06 10 - 한송이님 감사합니다^^

					response.setHeader("Content-Disposition", "attachment; filename=" + nFile + ";");

				}

				response.setHeader("Content-Length", String.valueOf(filesize));

				response.setHeader("Content-Transfer-Encoding", "binary;");

				response.setHeader("Pragma", "no-cache");

				response.setHeader("Cache-Control", "private");

				byte b[] = new byte[1024];

				BufferedInputStream fin = new BufferedInputStream(new FileInputStream(downFile));

				BufferedOutputStream outs = new BufferedOutputStream(response.getOutputStream());

				int read = 0;

				while ((read = fin.read(b)) != -1)

				{

					outs.write(b, 0, read);

				}

				outs.flush();

				outs.close();

				fin.close();

			} catch (Exception e)

			{

				System.out.println("Download Exception : " + e.getMessage());

			}

		} else

		{

			System.out.println("Download Error : downFile Error [" + downFile + "]");

		}

		return null;

	}

}
