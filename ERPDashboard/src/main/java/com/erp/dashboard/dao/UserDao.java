package com.erp.dashboard.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.erp.dashboard.model.User;
import com.erp.dashboard.utils.ERPUtils;



@Transactional
@Repository
public class UserDao implements IUserDao {
	@PersistenceContext	
	private EntityManager entityManager;	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		String hql = "FROM User";
		return (List<User>) entityManager.createQuery(hql).getResultList();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public User checkUserLogin(User user) {
		String hql = "FROM User as u where u.userName = :user and u.userPassword = :pass ";
		
		List<User> users = entityManager.createQuery(hql)
				.setParameter("user", user.getUserName())
        		.setParameter("pass", user.getUserPassword()).getResultList();
		
		if(ERPUtils.collectionIsEmpty(users)) {
			return null;
		}
		return users.get(0);
	}
}
