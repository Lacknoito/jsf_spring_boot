package com.erp.dashboard.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.erp.dashboard.entity.User;
import com.erp.dashboard.utils.ERPUtils;

@Repository
public class UserDao implements IUserDao {
	private static Logger logger = LogManager.getLogger(UserDao.class);
	
	@PersistenceContext	
	private EntityManager entityManager;	
	
	@Override
	@SuppressWarnings("unchecked")
	public User checkUserLogin(User user) {
		StringBuilder str = new StringBuilder();
		str.append(" select usr.user_name as userName  from (SELECT usr.user_name, ");
		str.append(" 	get_pwd.decrypt((SELECT (SELECT get_pwd.decrypt ");
		str.append(" 		(fnd_web_sec.get_guest_username_pwd, usertable.encrypted_foundation_password) ");
		str.append(" 		FROM DUAL) AS apps_password ");
		str.append("	FROM fnd_user usertable  ");
		str.append(" 	WHERE usertable.user_name = ");
		str.append(" 		(SELECT SUBSTR(fnd_web_sec.get_guest_username_pwd,  ");
		str.append(" 			1, INSTR(fnd_web_sec.get_guest_username_pwd,'/')- 1)FROM DUAL)), ");
		str.append(" 		usr.encrypted_user_password  ) PASSWORD ");
		str.append(" FROM fnd_user usr) usr ");
		str.append(" WHERE lower(usr.user_name) = lower(:userName) ");
		str.append(" and usr.PASSWORD = :password ");
		
		Query query =  entityManager.unwrap(Session.class)
				.createSQLQuery(str.toString())
				.addScalar("userName")
				.setResultTransformer(Transformers.aliasToBean(User.class));

		query.setParameter("userName", user.getUserName());
		query.setParameter("password", user.getUserPassword());
		
		List<User> users =  query.list();
		
		if(ERPUtils.collectionIsEmpty(users)) {
			return null;
		}
		return users.get(0);
	}
}
