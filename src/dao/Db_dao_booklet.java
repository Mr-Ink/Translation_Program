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
	 * �½����ʱ�
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
	 * ɾ�����ʱ�
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
	 * ��ʾ���ʱ������еĵ���
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
	 * ��ӵ��ʵ����ʱ���
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
	 * ɾ�����ʱ��еĵ���
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
	 * ��յ��ʱ��е����е���
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
