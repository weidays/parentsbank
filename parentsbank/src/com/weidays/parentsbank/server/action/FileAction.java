package com.weidays.parentsbank.server.action;
/*package com.hilife.server.action;
 
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;
 

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
 

import org.nbc.storage.common.util.ImageScale;
import org.nbc.storage.file.model.FileInfo;
import org.nbc.storage.file.service.FileInfoServie;
import org.nbc.storage.user.model.User;
 品牌【【

import com.hilife.server.common.BaseAction;
import com.sitech.core.orm.PropertyFilter;
import com.sitech.core.utils.Page;
 
public class FileAction extends BaseAction {
 
    private static final long serialVersionUID = 1L;
 
    private Page page;
 
    private String file_name;
 
    private File methodFile;
 
    *//** 下载需要的 *//*
    private InputStream inputStream;
 
    private String downloadFileName;
 
    @Resource
    public FileInfoServie fileInfoServie;
 
    public String userFileList() {
        return "success";
    }
 
 
    *//**
     *
     * 下载文件.
     *
     * @return
     *//*
    @Action(value = "downloadFile", results = { @Result(name = "success", location = "/right", type = "redirectAction") })
    public String downloadFile(){
 
        // 获取用户信息
        HttpSession session = this.getRequest().getSession();
        User user = (User) session.getAttribute("user");
 
        //查出要删除的文件信息
        String fileId = getRequest().getParameter("fileId");
        List lists = fileInfoServie.findBy("fileId", Long.parseLong(fileId), PropertyFilter.MatchType.EQ);
        FileInfo fileInfo = new FileInfo();
        if(lists != null && lists.size() > 0){
            fileInfo = lists.get(0);
        }
 
        //路径
        String path = "/root/file-tmp/"+fileInfo.getFileName();
 
        // 从hdfs取得文件
        Process process;
        try {
            process = Runtime.getRuntime().exec("java -jar /root/hdfs-0.0.1-SNAPSHOT.jar read "+fileInfo.getFilePath()+" /root/file-tmp/"+fileInfo.getFileName());
            InputStreamReader ir = new InputStreamReader(process.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            String line;
            while ((line = input.readLine()) != null)
                System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
 
         try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
 
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            this.getResponse().reset();
            // 设置response的Header
            this.getResponse().addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            this.getResponse().addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(this.getResponse().getOutputStream());
            this.getResponse().setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
 
        return null;
    }
    *//**
     *
     * 删除文件.
     *
     * @return
     *//*
    @Action(value = "deleteFile", results = { @Result(name = "success", location = "/right", type = "redirectAction") })
    public String deleteFile(){
        // 获取用户信息
        HttpSession session = this.getRequest().getSession();
        User user = (User) session.getAttribute("user");
 
        //查出要删除的文件信息
        String fileId = getRequest().getParameter("fileId");
        List lists = fileInfoServie.findBy("fileId", Long.parseLong(fileId), PropertyFilter.MatchType.EQ);
        FileInfo fileInfo = new FileInfo();
        if(lists != null && lists.size() > 0){
            fileInfo = lists.get(0);
        }
 
        // 将文件从hadoop集群删除
        Process process;
        try {
            process = Runtime.getRuntime().exec("java -jar /root/hdfs-0.0.1-SNAPSHOT.jar delete "+fileInfo.getFilePath());
            InputStreamReader ir = new InputStreamReader(process.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            String line;
            while ((line = input.readLine()) != null)
                System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        //从数据库删除
        fileInfoServie.deleteById(fileInfo.getId());
 
        return "success";
    }
 
    @Action(value = "goUploadPage", results = { @Result(name = "success", location = "/WEB-INF/npage/upload-right.jsp") })
    public String goUploadPage() {
 
        return "success";
    }
 
    @Action(value = "goPicPage", results = { @Result(name = "success", location = "/WEB-INF/npage/pic-right.jsp") })
    public String goPicPage() {
        HttpSession session = this.getRequest().getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            page = fileInfoServie.queryPicFileList(0, 20, user.getUserId());
        }
        return "success";
    }
 
    *//**
     *
     * 上传文件或图片.
     *
     * @return
     * @throws IOException
     *//*
    @Action(value = "uploadFile", results = { @Result(name = "success", location = "/right", type = "redirectAction") })
    public String uploadFile() throws IOException {
        // 获取用户信息
        HttpSession session = this.getRequest().getSession();
        User user = (User) session.getAttribute("user");
 
        boolean isPic = false;
        if (file_name.indexOf(".jpg") > 0 || file_name.indexOf(".gif") > 0) {
            isPic = true;
        }
 
        // 接收文件
        String path = "";
        if (null != file_name && !file_name.equals("")) {
            path = fileInfoServie.saveFile(methodFile, file_name);
        }
        String fileName = "";
        String[] strArr = path.split("/");
        if (strArr != null && strArr.length > 0) {
            fileName = strArr[strArr.length - 1];
        }
 
        // 将文件上传到hadoop集群
        Process process;
        try {
            process = Runtime.getRuntime().exec(
                    "java -jar /root/hdfs-0.0.1-SNAPSHOT.jar upload " + path + " hdfs://hadoopm:9000/user/root/upload/"
                            + user.getUserName() + "/" + fileName);
            InputStreamReader ir = new InputStreamReader(process.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
 
            String line;
            while ((line = input.readLine()) != null)
                System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileInfo fileInfo = new FileInfo();
        fileInfo.setCreateTime(new Date());
        fileInfo.setFileName(this.file_name);
        fileInfo.setFilePath("hdfs://hadoopm:9000/user/root/upload/" + user.getUserName() + "/" + fileName);
        File fileTemp = new File(path);
        fileInfo.setFileSize((fileTemp.length() / 1024) > 1 ? (fileTemp.length() / 1024) : 1);
 
        //判断是否为图片
        if (isPic) {
            fileInfo.setFileType(1);// 0 ：普通文件 1：图片
            String tempPath = "/root/" + user.getUserName() + "/"+ System.currentTimeMillis() + "/" + this.file_name;
            ImageScale.resizeFix(new File(path),new File(tempPath), 250, 250);
            fileInfo.setImg(tempPath);
        } else {
            fileInfo.setFileType(0);
        }
        fileInfo.setUserId(user.getUserId());
        fileInfo.setFpos(null);
        fileInfo.setFileId(System.currentTimeMillis());
        fileInfoServie.save(fileInfo);
 
        return "success";
    }
 
    *//**
     *
     * 获取缩略图.
     *
     * @return
     * @throws IOException
     *//*
    @Action(value = "getsImg")
    public String getsImg() throws IOException {
        String fileId = getRequest().getParameter("fileId");
        List lists = fileInfoServie.findBy("fileId", Long.parseLong(fileId), PropertyFilter.MatchType.EQ);
        FileInfo fileInfo = new FileInfo();
        if(lists != null && lists.size() > 0){
            fileInfo = lists.get(0);
        }
        FileInputStream is = new FileInputStream(fileInfo.getImg());
        int i = is.available(); // 得到文件大小
        byte data[] = new byte[i];
        is.read(data); // 读数据
        is.close();
        this.getResponse().setContentType("image/*"); // 设置返回的文件类型
        OutputStream toClient = this.getResponse().getOutputStream(); // 得到向客户端输出二进制数据的对象
        toClient.write(data); // 输出数据
        toClient.close();
 
        return null;
    }
 
    *//**
     *
     * 获取大图.
     *
     * @return
     * @throws IOException
     *//*
    @Action(value = "getbImg")
    public String getbImg() throws IOException{
        // 获取用户信息
        HttpSession session = this.getRequest().getSession();
        User user = (User) session.getAttribute("user");
 
        //查出要删除的文件信息
        String fileId = getRequest().getParameter("fileId");
        List lists = fileInfoServie.findBy("fileId", Long.parseLong(fileId), PropertyFilter.MatchType.EQ);
        FileInfo fileInfo = new FileInfo();
        if(lists != null && lists.size() > 0){
            fileInfo = lists.get(0);
        }
 
        //路径
        String path = "/root/file-tmp/bpic/"+fileInfo.getFileName();
 
        // 从hdfs取得文件
        Process process;
        try {
            process = Runtime.getRuntime().exec("java -jar /root/hdfs-0.0.1-SNAPSHOT.jar read "+fileInfo.getFilePath()+" /root/file-tmp/bpic/"+fileInfo.getFileName());
            InputStreamReader ir = new InputStreamReader(process.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            String line;
            while ((line = input.readLine()) != null)
                System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        File f = new File(path);
        if (!f.exists()) {
            this.getResponse().sendError(404, "File not found!");
            return null;
        }
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
        byte[] buf = new byte[1024];
        int len = 0;
 
        this.getResponse().reset(); // 非常重要
        // 在线打开方式
        URL u = new URL("file:///" + path);
        this.getResponse().setContentType(u.openConnection().getContentType());
        this.getResponse().setHeader("Content-Disposition", "inline; filename=" + f.getName());
        // 文件名应该编码成UTF-8
        OutputStream out = this.getResponse().getOutputStream();
        while ((len = br.read(buf)) > 0)
            out.write(buf, 0, len);
        br.close();
        out.close();
 
        return null;
    }
 
  

 
    public String getFile_name() {
        return file_name;
    }
 
    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }
 
    public File getMethodFile() {
        return methodFile;
    }
 
    public void setMethodFile(File methodFile) {
        this.methodFile = methodFile;
    }
 
    public String getDownloadFileName() {
        return downloadFileName;
    }
 
    public void setDownloadFileName(String downloadFileName) {
        this.downloadFileName = downloadFileName;
    }
 
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
 
    @Action(value = "filelist", results = { @Result(name = "success", location = "/WEB-INF/npage/filelist.jsp") })
    public String filelist() {
//      HttpSession session = this.getRequest().getSession();
//      User user = (User) session.getAttribute("user");
//      if (user != null) {
            page = fileInfoServie.queryUserFileList(0, 20, 1L);
//      }
        return "success";
    }
 
    @Action(value = "goUploadPageCC", results = { @Result(name = "success", location = "/WEB-INF/npage/goUploadPageCC.jsp") })
    public String goUploadPageCC() {
 
        return "success";
    }
 
    @Action(value = "uploadFileCC", results = { @Result(name = "success", location = "/filelist", type = "redirectAction") })
    public String uploadFileCC() throws IOException {
        // 获取用户信息
        HttpSession session = this.getRequest().getSession();
        User user = (User) session.getAttribute("user");
 
        boolean isPic = false;
        if (file_name.indexOf(".jpg") > 0 || file_name.indexOf(".gif") > 0) {
            isPic = true;
        }
 
        // 接收文件
        String path = "";
        if (null != file_name && !file_name.equals("")) {
            path = fileInfoServie.saveFile(methodFile, file_name);
        }
        String fileName = "";
        String[] strArr = path.split("/");
        if (strArr != null && strArr.length > 0) {
            fileName = strArr[strArr.length - 1];
        }
 
        // 将文件上传到hadoop集群
        Process process;
        try {
            process = Runtime.getRuntime().exec(
                    "java -jar /root/hdfs-0.0.1-SNAPSHOT.jar upload " + path + " hdfs://hadoopm:9000/user/root/upload/"
                            + "lisn" + "/" + fileName);
            InputStreamReader ir = new InputStreamReader(process.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
 
            String line;
            while ((line = input.readLine()) != null)
                System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileInfo fileInfo = new FileInfo();
        fileInfo.setCreateTime(new Date());
        fileInfo.setFileName(this.file_name);
        fileInfo.setFilePath("hdfs://hadoopm:9000/user/root/upload/" +"lisn" + "/" + fileName);
        File fileTemp = new File(path);
        fileInfo.setFileSize((fileTemp.length() / 1024) > 1 ? (fileTemp.length() / 1024) : 1);
 
        //判断是否为图片
        if (isPic) {
            fileInfo.setFileType(1);// 0 ：普通文件 1：图片
            String tempPath = "/root/" + "lisn" + "/"+ System.currentTimeMillis() + "/" + this.file_name;
            ImageScale.resizeFix(new File(path),new File(tempPath), 250, 250);
            fileInfo.setImg(tempPath);
        } else {
            fileInfo.setFileType(0);
        }
        fileInfo.setUserId(1L);
        fileInfo.setFpos(null);
        fileInfo.setFileId(System.currentTimeMillis());
        fileInfoServie.save(fileInfo);
 
        return "success";
    }
 
}*/