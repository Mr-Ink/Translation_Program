package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connect.Db_util;
import module.User;

public class Db_dao_user {
	
	private static Connection con;
	
	public Db_dao_user() throws Exception{
		con = new Db_util().getcon();
	}
	
	public User login(User user) throws Exception {
		User resultUser = new User();
		resultUser = user;
		String sql = "select * from user where user_id=? and password=?";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, user.getId());
		pre.setString(2, user.getPassword());
		ResultSet re = pre.executeQuery();
		if(!re.next()){
			con.close();
			return null;
		}
		return resultUser;
	}
	
	public User register(String id,String password,String name)throws Exception{
		String sql = "insert into user (User_id,NickName,Password)"+ "values(?,?,?)";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, id);
		pre.setString(2, name);
		pre.setString(3, password);
		pre.execute();
		User user = new User();
		user.setId(id);
		user.setPassword(password);
		user.setName(name);
		return user;
	}
	
	public static void close_con() throws Exception {
		con.close();
	}
	
}
