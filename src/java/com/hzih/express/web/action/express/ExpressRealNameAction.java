package com.hzih.express.web.action.express;

import com.hzih.express.dao.ExpressLogDao;
import com.hzih.express.dao.UserDao;
import com.hzih.express.domain.ExpressLog;
import com.hzih.express.domain.User;
import com.hzih.express.service.LogService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 15-11-12.
 */
public class ExpressRealNameAction extends ActionSupport {
    private Logger logger = Logger.getLogger(ExpressRealNameAction.class);
    private LogService logService;
    private ExpressLogDao expressLogDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    private UserDao userDao;
    private int start;
    private int limit;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public ExpressLogDao getExpressLogDao() {
        return expressLogDao;
    }

    public void setExpressLogDao(ExpressLogDao expressLogDao) {
        this.expressLogDao = expressLogDao;
    }

    public LogService getLogService() {
        return logService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");

    public String upload() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        String json = "{success:false,msg:'添加出错'}";
        String msg = null;
        ExpressLog expressLog = new ExpressLog();

        String type = request.getParameter("type");
        expressLog.setType(type);
        if (type.equals("OCR")) {
            String shapeCode = request.getParameter("shapeCode");
            String latitude = request.getParameter("latitude");
            String longitude = request.getParameter("longitude");
            String sendTime = request.getParameter("sendTime");
            String phone = request.getParameter("phone");
            String contact = request.getParameter("contact");
            String state_type = request.getParameter("state_type");

            String idimg = request.getParameter("idimg");
            if (idimg != null) {
                byte[] bytes = Base64.decodeBase64(idimg.getBytes());
                Blob photo = Hibernate.createBlob(bytes);
                expressLog.setBitmap(photo);
            }

            String faceimg = request.getParameter("faceimg");
            if (faceimg != null) {
                byte[] bytes = Base64.decodeBase64(faceimg.getBytes());
                Blob photo = Hibernate.createBlob(bytes);
                expressLog.setFace_img(photo);
            }

            String senderimg = request.getParameter("senderimg");
            if (senderimg != null) {
                byte[] bytes = Base64.decodeBase64(senderimg.getBytes());
                Blob photo = Hibernate.createBlob(bytes);
                expressLog.setSender_img(photo);
            }

            String unpackimg = request.getParameter("unpackimg");
            if (unpackimg != null) {
                byte[] bytes = Base64.decodeBase64(unpackimg.getBytes());
                Blob photo = Hibernate.createBlob(bytes);
                expressLog.setUnpack_img(photo);
            }

            expressLog.setShapeCode(shapeCode);
            expressLog.setLatitude(latitude);
            expressLog.setLongitude(longitude);
            expressLog.setContact(contact);
            expressLog.setState_type(state_type);
            User user = userDao.find(phone);
            if (user != null)
                expressLog.setUser(user);
            if (sendTime != null)
                expressLog.setSendTime(format.parse(sendTime));
        } else if (type.equals("OTG")) {
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String birthday = request.getParameter("birthday");
            String dn = request.getParameter("dn");
            String idCard = request.getParameter("idCard");
            String nation = request.getParameter("nation");
            String sex = request.getParameter("sex");
            String shapeCode = request.getParameter("shapeCode");
            String signDepart = request.getParameter("signDepart");
            String validTime = request.getParameter("validTime");
            String latitude = request.getParameter("latitude");
            String longitude = request.getParameter("longitude");
            String sendTime = request.getParameter("sendTime");
            String phone = request.getParameter("phone");
            String contact = request.getParameter("contact");
            String state_type = request.getParameter("state_type");

            String idimg = request.getParameter("idimg");
            if (idimg != null) {
                byte[] bytes = Base64.decodeBase64(idimg.getBytes());
                Blob photo = Hibernate.createBlob(bytes);
                expressLog.setBitmap(photo);
            }

            String faceimg = request.getParameter("faceimg");
            if (faceimg != null) {
                byte[] bytes = Base64.decodeBase64(faceimg.getBytes());
                Blob photo = Hibernate.createBlob(bytes);
                expressLog.setFace_img(photo);
            }

            String senderimg = request.getParameter("senderimg");
            if (senderimg != null) {
                byte[] bytes = Base64.decodeBase64(senderimg.getBytes());
                Blob photo = Hibernate.createBlob(bytes);
                expressLog.setSender_img(photo);
            }

            String unpackimg = request.getParameter("unpackimg");
            if (unpackimg != null) {
                byte[] bytes = Base64.decodeBase64(unpackimg.getBytes());
                Blob photo = Hibernate.createBlob(bytes);
                expressLog.setUnpack_img(photo);
            }

            expressLog.setName(name);
            expressLog.setAddress(address);
            expressLog.setBirthday(birthday);
            expressLog.setDN(dn);
            expressLog.setIdCard(idCard);
            expressLog.setNation(nation);
            expressLog.setSex(sex);
            expressLog.setShapeCode(shapeCode);
            expressLog.setSignDepart(signDepart);
            expressLog.setValidTime(validTime);
            expressLog.setLatitude(latitude);
            expressLog.setLongitude(longitude);
            expressLog.setContact(contact);
            expressLog.setState_type(state_type);
            User user = userDao.find(phone);
            if (user != null)
                expressLog.setUser(user);
            if (sendTime != null)
                expressLog.setSendTime(format.parse(sendTime));
        }
        try {
            expressLogDao.add(expressLog);
            msg = "添加快递信息成功" + expressLog.getUser().getPhone() + "**********" + expressLog.getShapeCode();
            json = "{success:true,msg:'" + msg + "'}";
            logger.info("添加快递信息成功,操作时间:" + new Date() + ",操作信息:" + msg);
        } catch (Exception e) {
            msg = "添加快递信息失败" + expressLog.getUser().getPhone() + "**********" + expressLog.getShapeCode();
            json = "{success:false,msg:'" + msg + "'}";
            logger.info("添加快递信息失败,操作时间:" + new Date() + ",操作信息:" + msg);
        }
        response.getWriter().write(json);
        return null;
    }

}
