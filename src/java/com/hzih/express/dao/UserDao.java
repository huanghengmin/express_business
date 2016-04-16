package com.hzih.express.dao;


import cn.collin.commons.dao.BaseDao;
import cn.collin.commons.domain.PageResult;
import com.hzih.express.domain.User;
import com.hzih.express.utils.ResultObj;

import java.util.Date;

/**
 *
 * @author jerry
 * @Date 2012-12-6
 * @Email 1457049206@qq.com
 * 与数据库进行交互的接口
 */
public interface UserDao extends BaseDao {
	/*检查*/
	boolean check(String phone);

	boolean checkIdCard(String idCard);
	/*注册*/
	boolean register(String phone,String password);
	/*登录*/
	User login(String phone, String password);

	boolean modifyPassword(String phone, String password);

	User find(String phone);

	boolean modify(User user);

	ResultObj modifyPassword(String phone, String oldPwd, String password);

	PageResult listByParams(int i, int limit, String idCard, String express_name, String express_number,String company_code, String company_point, String phone);


	PageResult listByParams(int i, int limit, String idCard, String express_name, String express_number, long company_point_id, String phone);

	void remove(User user);

	boolean setStatus(long id, int status);
}
