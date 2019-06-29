package cn.sg;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;


@WebServlet(name = "UploadServlet")
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
		//张明辉 666aa
        // Check that we have a file upload request
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        // Create a factory for disk-based file items
        FileItemFactory factory = new DiskFileItemFactory();

// Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

// Parse the request
        try {
            List<FileItem> items = upload.parseRequest(request);
            // Process the uploaded items
            Iterator iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();

                if (item.isFormField()) {
                    processFormField(item);
                } else {
                    processUploadedFile(item,request);
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }
    //    文件域
    private void processUploadedFile(FileItem item,HttpServletRequest request) {
        String key = item.getFieldName();
        String name = item.getName();
        String contentType = item.getContentType();
        long size = item.getSize();
//        动态获取相对路径
        String uoloadPath=request.getServletContext().getRealPath("/WEB-INF/upload");
        File file=new File(uoloadPath);
        if (!file.exists()){
            file.mkdirs();
        }
        File file1=new File(uoloadPath+"/"+name);
        try {
            item.write(file1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    普通域
    private void processFormField(FileItem item) {
        String key = item.getFieldName();
        String value = item.getString();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
