package team3.service;

import org.mindrot.jbcrypt.BCrypt;

import team3.dao.UserDao;
import team3.entity.Users;

public class UserServiceImpl implements UserService {
	
	private UserDao userDao = new UserDao();

	@Override
	public void registerUser(Users users) {
		Users u = userDao.getUsersByUid(users.getUid());
		if (u != null)
			return;
		
		// 패스워드 암호화
		String hashedPwd = BCrypt.hashpw(users.getPwd(), BCrypt.gensalt());
		users.setPwd(hashedPwd);
		userDao.insertUsers(users);
	}

	@Override
	public Users getUserByUid(String uid) {
		Users users = userDao.getUsersByUid(uid);
		return users;
	}

	@Override
	public int getUserCount() {
		return userDao.getUsersCount();
	}

	@Override
	public void updateUser(Users users) {
		userDao.updateUsers(users);
	}

	@Override
	public void deleteUser(String uid) {
		userDao.deleteUsers(uid);
		
	}

	@Override
	public int login(String uid, String pwd) {
		Users user = userDao.getUsersByUid(uid);
		
		if (user == null)
			return USER_NOT_EXIST;
		
		if (!BCrypt.checkpw(pwd, user.getPwd()))
			return WRONG_PASSWORD;
		
		return CORRECT_LOGIN;
	}

}
