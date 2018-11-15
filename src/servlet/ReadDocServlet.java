package servlet;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class ReadDocServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");//解决编码问题
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");//输出的内容要放在body中
        out.println("<body>");
        out.println(readWord("C:\\Users\\16320\\Desktop\\课程\\java企业级开发\\Demo2\\web\\WEB-INF\\test.doc"));
        out.println("</body>");
        out.println("</html>");
        System.out.println(readWord("C:\\Users\\16320\\Desktop\\课程\\java企业级开发\\Demo2\\web\\WEB-INF\\test.doc"));

    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    public static String readWord(String filePath){
        String text = "";
        File file = new File(filePath);
        //2003
        if(file.getName().endsWith(".doc")){
            try {
                FileInputStream stream = new FileInputStream(file);
                WordExtractor word = new WordExtractor(stream);
                text = word.getText();
                //去掉word文档中的多个换行
                text = text.replaceAll("(\\r\\n){2,}", "\r\n");
                text = text.replaceAll("(\\n){2,}", "\n");
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(file.getName().endsWith(".docx")){       //2007
            try {
                OPCPackage oPCPackage = POIXMLDocument.openPackage(filePath);
                XWPFDocument xwpf = new XWPFDocument(oPCPackage);
                POIXMLTextExtractor ex = new XWPFWordExtractor(xwpf);
                text = ex.getText();
                //去掉word文档中的多个换行
                text = text.replaceAll("(\\r\\n){2,}", "\r\n");
                text = text.replaceAll("(\\n){2,}", "\n");
                System.out.println("ok");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return text;
    }


}