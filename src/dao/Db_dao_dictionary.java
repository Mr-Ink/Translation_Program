package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import module.Dictionary;
import connect.Db_util;

public class Db_dao_dictionary {
	private static Connection con;
	
	public Db_dao_dictionary() throws Exception{
		con = new Db_util().getcon();
	}
	/*
	 * 增添新单词
	 */
	public boolean add_word(Dictionary dictionary,String eng,String chi){
		String sql = "insert into full_dictionary (word,chinese,user_id)"
				+ "values(?,?,?)";
		try {
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1, eng);
			pre.setString(2, chi);
			pre.setString(3, dictionary.getId());
			pre.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	/*
	 * 删除单词
	 */
	public boolean delete(Dictionary dictionary,String word){
		String sql = "delete from full_dictionary where word=? and user_id=?";
		try {
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1, word);
			pre.setString(2, dictionary.getId());
			pre.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/*
	 * 更新单词
	 */
	public boolean update(Dictionary dictionary,String word,String chi){
		String sql = "update full_dictionary set chinese=?"
								+ "where word=? and user_id=?";
		try {
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1, chi);
			pre.setString(2, word);
			pre.setString(3, dictionary.getId());
			pre.execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/*
	 * 查询单词(模糊和精确)
	 */
	public Map<String, String> find(Dictionary dictionary,String word) throws Exception {
		Map<String, String> result = new HashMap<>();
		String sql = "select * from full_dictionary "
				+ "where user_id=? and word like "+"'"+word+"%'";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, dictionary.getId());
		ResultSet re = pre.executeQuery();
		while (re.next()) {
			result.put(re.getString("word"),re.getString("chinese"));
		}
		return result;
	}
	/*
	 * 查看词库的所有单词
	 */
	public Map<String, String> find_all(Dictionary dictionary) throws Exception {
		Map<String, String> result = new HashMap<>();
		String sql = "select * from full_dictionary where user_id=?";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, dictionary.getId());
		ResultSet re = pre.executeQuery();
		while (re.next()) {
			result.put(re.getString("word"), re.getString("chinese"));
		}
		return result;
	}
	/*
	 * 简单句子翻译（未涉及语法）
	 */
	public String translate(Dictionary dictionary,String eng) throws Exception {
		String sql = "select chinese from full_dictionary "
				+ "where user_id=? and word =?";
		PreparedStatement pre = con.prepareStatement(sql);
		pre.setString(1, dictionary.getId());
		String[] sql_eng = eng.split(" ");
		String chi = new String();
		for (String string : sql_eng) {
			pre.setString(2, string);
			ResultSet re = pre.executeQuery();
			while (re.next()) {
				chi+=re.getString("chinese");
			}
		}
		return chi;
	}
	public static void close_con() throws Exception {
		con.close();
	}
}
