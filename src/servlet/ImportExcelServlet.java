package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import util.JDBCUtil;


@WebServlet("/ImportExcel")
public class ImportExcelServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        java.sql.Connection conn = null;
        java.sql.Statement stmt = null;
        try {
            conn = JDBCUtil.getConn();
            stmt = conn.createStatement();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        try {
            List items = upload.parseRequest(request);
            InputStream is = null;
            Iterator iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                if (!item.isFormField()) {
                    is = item.getInputStream();
                }
            }
            Workbook workbook = Workbook.getWorkbook(is);
            Sheet sheet = workbook.getSheet(0);
            // 行数
            int rows = sheet.getRows();
            // 列数
            int columns = sheet.getColumns();
            System.out.println("行数为: " + rows + "列数为: " + columns);
            PreparedStatement ps = conn
                    .prepareStatement("insert into userprize2(name,ip,prize,date,coment,number,state) values(?,?,?,?,?,?,?)");

            for (int i = 0; i < rows; i++) {
                if (i == 0) {// 第一行是属性，不读取
                    continue;
                }
                Cell ce0 = ((jxl.Sheet) sheet).getCell(0, i);
                Cell ce1 = ((jxl.Sheet) sheet).getCell(1, i);
                Cell ce2 = ((jxl.Sheet) sheet).getCell(2, i);
                Cell ce3 = ((jxl.Sheet) sheet).getCell(3, i);
                Cell ce4 = ((jxl.Sheet) sheet).getCell(4, i);
                Cell ce5 = ((jxl.Sheet) sheet).getCell(5, i);
                Cell ce6 = ((jxl.Sheet) sheet).getCell(6, i);

                String c0 = ce0.getContents();
                String c1 = ce1.getContents();
                String c2 = ce2.getContents();
                String c3 = ce3.getContents();
                String c4 = ce4.getContents();
                String c5 = ce5.getContents();
                int c6 = Integer.parseInt(ce6.getContents());

                ps.setString(1, c0);
                ps.setString(2, c1);
                ps.setString(3, c2);
                ps.setString(4, c3);
                ps.setString(5, c4);
                ps.setString(6, c5);
                ps.setString(7, String.valueOf(c6));
                ps.execute();
            }
            ps.close();
            conn.close();
            stmt.close();
            response.sendRedirect("ImportExcelSuccess.jsp");// 重定向到成功插入学生数据的页面
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

