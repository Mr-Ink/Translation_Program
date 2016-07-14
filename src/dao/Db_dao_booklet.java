package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import module.Booklet;
import connect.Db_util;

public class Db_dao_booklet {
	private static Connection con;
	
	public Db_dao_booklet() throws Exception{
		con = new Db_util().getcon();
	}
	
	/*
	 * 新建单词本
	 */
	public boolean add_booklet(Booklet booklet) {
		String sql = "insert into booklet(user_id,type) "
				+ "set(?,?)";
		try {
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1, booklet.getUser_id());
			pre.setString(2, booklet.getType());
			pre.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/*
	 * 删除单词本
	 */
	public boolean delete_booklet(Booklet booklet) {
		String sql = "delete from booklet "
				+ "where user_id=? and type=?";
		try {
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1, booklet.getUser_id());
			pre.setString(2, booklet.getType());
			pre.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * 显示单词本中所有的单词
	 */
	public Map<String, String> open_booklet(Booklet booklet) throws Exception {
		Map<String, String> result = new HashMap<>();
		String sql = "select * from full_dictionary where word in("
				+ "select Word from personal_dictionary "
				+ "where User_id=? and Type=?)";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, booklet.getUser_id());
		pre.setString(2, booklet.getType());
		ResultSet re = pre.executeQuery();
		while (re.next()) {
			result.put(re.getString("word"), re.getString("chinese"));
		}
		return result;
	}

	/*
	 * 添加单词到单词本中
	 */
	public boolean add_word(Booklet booklet,String word){
		String sql = "insert into personal_dictionary(User_id,Type,Word) "
				+ "values(?,?,?)";
		try {
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1, booklet.getUser_id());
			pre.setString(2, booklet.getType());
			pre.setString(3, word);
			pre.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/*
	 * 删除单词本中的单词
	 */
	public boolean delete_word(Booklet booklet,String word) {
		String sql = "delete from personal_dictionary "
				+ "where User_id=? and Type=? and Word=?";
		try {
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1, booklet.getUser_id());
			pre.setString(2, booklet.getType());
			pre.setString(3, word);
			pre.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/*
	 * 清空单词本中的所有单词
	 */
	public boolean empty_word(Booklet booklet) {
		String sql = "delete from personal_dictionary "
				+ "where User_id=? and Type=?";
		try {
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1, booklet.getUser_id());
			pre.setString(2, booklet.getType());
			pre.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public static void close_con() throws Exception {
		con.close();
	}
}
