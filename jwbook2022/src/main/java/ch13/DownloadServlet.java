package ch13;

import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadServlet
 */
//@WebServlet("/filedown")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("file");
        String FileDIR = "c:/Temp/img/";
        String FilePath = FileDIR + fileName;
        
		// 파일을 읽어오기 위한 파이트 버퍼 설정
        byte b[] = new byte[4096];

		// 지정된 경로로 부터 파일 입력 스트림 생성
        FileInputStream fileInputStream = new FileInputStream(FilePath);
        
		// 파일의 MimeType 즉 워드, 엑셀, 이미지 등 파일 형식에 대한 정보
        String mimeType = getServletContext().getMimeType(FilePath);
        
		// MimeType 확인이 안되는 경우 기본값 설정
        if(mimeType == null) mimeType = "application/octet-stream";
        
		// 응답 헤더 설정
        response.setContentType(mimeType);
        // 한글 파일명 인코딩 설정
        String encFileName = new String(fileName.getBytes("utf-8"),"ISO-8859-1");
        
        response.setHeader("Content-Disposition", "attachment; filename= " + encFileName);
        
        ServletOutputStream servletOutStream = response.getOutputStream();
        
		// 파일을 읽어 스트림으로 전송
        int read;
        while((read = fileInputStream.read(b,0,b.length))!= -1){
            servletOutStream.write(b,0,read);            
        }
        
        servletOutStream.flush();
        servletOutStream.close();
        fileInputStream.close();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
