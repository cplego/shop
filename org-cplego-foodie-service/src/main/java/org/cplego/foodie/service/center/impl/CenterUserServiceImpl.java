package org.cplego.foodie.service.center.impl;

import org.cplego.foodie.mapper.UsersMapper;
import org.cplego.foodie.pojo.Users;
import org.cplego.foodie.service.center.CenterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class CenterUserServiceImpl implements CenterUserService {

	@Autowired
	private UsersMapper usersMapper;

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public Users queryUser(String userId) {
		Users users = new Users();
		users.setId(userId);
		return usersMapper.selectOne(users);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Users updateUser(String userId,Users users) {
		users.setId(userId);
		users.setUpdatedTime(new Date());
		usersMapper.updateByPrimaryKeySelective(users);
		return queryUser(userId);
	}

	@Override
	public Users updateUserFace(String userId, String userFace) {
		Users users  = new Users();
		users.setId(userId);
		users.setFace(userFace);
		users.setUpdatedTime(new Date());
		usersMapper.updateByPrimaryKeySelective(users);
		return queryUser(userId);

	}
}
