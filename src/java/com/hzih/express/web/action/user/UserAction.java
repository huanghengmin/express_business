package com.hzih.express.web.action.user;

import com.hzih.express.dao.UserDao;
import com.hzih.express.dao.UserOperLogDao;
import com.hzih.express.domain.User;
import com.hzih.express.service.LogService;
import com.hzih.express.utils.ResultObj;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;

/**
 *
 */
public class UserAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private LogService logService;
	private UserDao userDao;
	private User user;
	private UserOperLogDao userOperLogDao;
	private int start;
	private int limit;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

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

	public UserOperLogDao getUserOperLogDao() {
		return userOperLogDao;
	}

	public void setUserOperLogDao(UserOperLogDao userOperLogDao) {
		this.userOperLogDao = userOperLogDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/*检查*/
	public String check() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String phone = request.getParameter("phone");
		boolean flag = userDao.check(phone);
		if(flag == true){
			String msg = "用户检测手机号已注册";
			out.print("{success:false,msg:\""+msg+"\"}");
			userOperLogDao.newLog("用户检测手机号已注册", phone);
		}else {
			String msg = "用户检测手机号未注册";
			out.print("{success:true,msg:\""+msg+"\"}");
			userOperLogDao.newLog("用户检测手机号未注册", phone);
		}
		//刷新流
		out.flush();
		//关闭流
		out.close();
		return null;
	}

	public String checkIdCard() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String idCard = request.getParameter("idCard");
		boolean flag = userDao.checkIdCard(idCard);
		if(flag == true){
			String msg = "用户身份证号码已被使用";
			out.print("{success:false,msg:\""+msg+"\"}");
			userOperLogDao.newLog("用户身份证号码已被使用", idCard);
		}else {
			String msg = "用户身份证号码已被使用";
			out.print("{success:true,msg:\""+msg+"\"}");
			userOperLogDao.newLog("用户身份证号码已被使用", idCard);
		}
		//刷新流
		out.flush();
		//关闭流
		out.close();
		return null;
	}

	/*注册*/
	public String register() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		boolean flag = userDao.register(phone,password);
		if(flag == true){
			out.print("{success:true}");
			userOperLogDao.newLog("用户注册成功", phone);
		}else {
			out.print("{success:false}");
			userOperLogDao.newLog("用户注册失败", phone);
		}
		//刷新流
		out.flush();
		//关闭流
		out.close();
		return null;
	}

	/*修改密码*/
	public String modifyPassword() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String phone = request.getParameter("phone");
		String oldPwd = request.getParameter("oldPwd");
		String password = request.getParameter("password");
		if(oldPwd!=null) {
			ResultObj obj = userDao.modifyPassword(phone,oldPwd, password);
			if (obj.isFlag()) {
				out.print("{success:true,msg:\""+obj.getMsg()+"\"}");
				userOperLogDao.newLog(obj.getMsg(), phone);
			} else {
				out.print("{success:false,msg:\""+obj.getMsg()+"\"}");
				userOperLogDao.newLog(obj.getMsg(), phone);
			}
		}else {
			boolean flag = userDao.modifyPassword(phone, password);
			if (flag == true) {
				out.print("{success:true}");
				userOperLogDao.newLog("用户修改密码成功", phone);
			} else {
				out.print("{success:false}");
				userOperLogDao.newLog("用户修改密码失败", phone);
			}
		}
		//刷新流
		out.flush();
		//关闭流
		out.close();
		return null;
	}

	/*登录*/
	public String login() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		User user = userDao.login(phone, password);
		if(user != null){
			if(user.getStatus()==1){
				out.print("{success:true}");
				userOperLogDao.newLog("用户登陆成功", phone);
			}else {
				out.print("{success:false}");
				userOperLogDao.newLog("用户已被禁用", phone);
			}
		} else {
			out.print("{success:false}");
			userOperLogDao.newLog("用户登陆失败", phone);
		}
		//刷新流
		out.flush();
		//关闭流
		out.close();

		return null;
	}

	public String findUser()throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String phone = request.getParameter("phone");
		User user = userDao.find(phone);
		if(user != null){
			StringBuilder builder = new StringBuilder();
			builder.append("{");
			builder.append("success:"+true).append(",");
			builder.append("idCard:"+"\""+user.getIdCard()+"\"").append(",");
			builder.append("express_name:" + "\"" + user.getExpress_name() + "\"").append(",");
			builder.append("express_company:"+"\""+user.getCompanyPoint().getCompany().getName()+"\"").append(",");
			builder.append("express_number:"+"\""+user.getExpress_number()+"\"").append(",");
			builder.append("phone:"+"\""+user.getPhone()+"\"").append(",");
			builder.append("register_time:"+"\""+user.getRegister_time()+"\"").append(",");
			builder.append("modify_time:"+"\""+user.getModify_time() + "\"").append(",");
			builder.append("status:"+"\""+user.getStatus() + "\"");
			builder.append("}");
			out.print(builder.toString());
		}else{
			StringBuilder builder = new StringBuilder();
			builder.append("{");
			builder.append("success:"+false).append(",");
			builder.append("}");
			out.print(builder.toString());
		}
		//刷新流
		out.flush();
		//关闭流
		out.close();

		return null;
	}

	public String modifyUser()throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String phone = request.getParameter("phone");
		String idCard = request.getParameter("idCard");
		String express_name = request.getParameter("express_name");
		String express_number = request.getParameter("express_number");
//		String id = request.getParameter("id");
		User user = userDao.find(phone);
		if(user != null){
			user.setIdCard(idCard);
			user.setExpress_name(express_name);
			user.setExpress_number(express_number);
//			CompanyPoint companyPoint = companyPointDao.findById(Long.parseLong(id));
//			user.setCompanyPoint(companyPoint);
			user.setModify_time(new Date());
			boolean modify = userDao.modify(user);
			if(modify){
				StringBuilder builder = new StringBuilder();
				builder.append("{");
				builder.append("success:"+true);
				builder.append("}");
				out.print(builder.toString());
			}else {
				StringBuilder builder = new StringBuilder();
				builder.append("{");
				builder.append("success:"+false);
				builder.append("}");
				out.print(builder.toString());
			}
		}else{
			StringBuilder builder = new StringBuilder();
			builder.append("{");
			builder.append("success:"+false);
			builder.append("}");
			out.print(builder.toString());
		}
		//刷新流
		out.flush();
		//关闭流
		out.close();

		return null;
	}
}
