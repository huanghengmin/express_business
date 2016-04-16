package com.hzih.express.web.servlet;

import com.hzih.express.entity.Version;
import com.hzih.express.utils.FileUtil;
import com.hzih.express.utils.StringContext;
import com.hzih.express.utils.VersionUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-4-3
 * Time: 下午5:43
 * To change this template use File | Settings | File Templates.
 */
public class DownLoadClient extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Logger logger = Logger.getLogger(DownLoadClient.class);

    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String msg = null;
        String path = null;
        try{
            String Agent = request.getHeader("User-Agent");
            StringTokenizer st = new StringTokenizer(Agent,";");
            st.nextToken();
            String userBrowser = st.nextToken();
            Version av = null;
            String android_version = StringContext.systemPath + "/client/android" + "/version.xml";
            File android_info = new File(android_version);
            if (android_info.exists()) {
                av = VersionUtils.readInfo(android_info);
                String name = av.getName();
                path = StringContext.systemPath + "/client/android" + "/" + name;
            }
            File source = new File(path);
            String name = source.getName();
            FileUtil.downType(response, name, userBrowser);
            response = FileUtil.copy(source, response);
            msg = "下载Android 快递 客户端成功"+request.getRemoteAddr();
            logger.info(msg+",时间:"+new Date());
        } catch (Exception e){
            logger.error("下载Android 快递 客户端失败"+request.getRemoteAddr(), e);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
