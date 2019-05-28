package com.internousdev.leo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.internousdev.leo.dto.UserInfoDTO;
import com.internousdev.leo.util.DBConnector;

public class UserInfoDAO {
	//ユーザー登録機能にて、エラーチェックが終わった後、ユーザー登録をする際に使用(userCreateCompleteActionから呼ばれる)
		public int createUser(String familyName, String firstName, String familyNameKana, String firstNameKana, String sex, String email, String userId, String password){
			DBConnector dbConnector = new DBConnector();
			Connection connection = dbConnector.getConnection();

			int count = 0;

			String sql = "INSERT INTO user_info(user_id, password, family_name, first_name, family_name_kana,"
					+ "first_name_kana, sex, email, status, logined, regist_date, update_date)"
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,now(),now())";

			try{
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1,userId);
				preparedStatement.setString(2,password);
				preparedStatement.setString(3,familyName);
				preparedStatement.setString(4,firstName);
				preparedStatement.setString(5,familyNameKana);
				preparedStatement.setString(6,firstNameKana);
				preparedStatement.setString(7,sex);
				preparedStatement.setString(8,email);
				preparedStatement.setInt(9,0);
				preparedStatement.setInt(10,1);

				count = preparedStatement.executeUpdate();
			}catch(SQLException e){
				e.printStackTrace();
			}
			try{
				connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			return count;
		}

//		ログイン機能にて使用(LoginActionから呼ばれる)
//		このメソッドで認証処理を確認行ったあと、login(String userId, String password)を使用してログインを行う
		public boolean isExistsUserInfo(String userId, String password){
			DBConnector dbConnector = new DBConnector();
			Connection connection = dbConnector.getConnection();

			boolean result = false;
			String sql = "select count(*) as count from user_info where user_id = ? and password = ?";

			try{
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1,userId);
				preparedStatement.setString(2,password);
				ResultSet resultSet = preparedStatement.executeQuery();

				while(resultSet.next()){
					if(resultSet.getInt("count") > 0){
						result = true;
					}
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			return result;
		}

//		ユーザー登録機能のユーザーID存在チェックで使用(createUserConfirmActionから呼ばれる)
		public boolean isExistsUserInfo(String userId){
			DBConnector dbConnector = new DBConnector();
			Connection connection = dbConnector.getConnection();

			boolean result = false;
			String sql = "select count(*) as count from user_info where user_id = ?";

			try{
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1,userId);
				ResultSet resultSet = preparedStatement.executeQuery();

				while(resultSet.next()){
					if(resultSet.getInt("count") > 0){
						result = true;
					}
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			try{
				connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			return result;
		}

//		ログイン機能にて使用(LoginActionから呼ばれる)

		public UserInfoDTO getUserInfo(String userId, String password){
			DBConnector dbConnector = new DBConnector();
			Connection connection = dbConnector.getConnection();
			UserInfoDTO userInfoDTO = new UserInfoDTO();
			String sql = "select * from user_info where user_id = ? and password = ?";

			try{
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1,userId);
				preparedStatement.setString(2,password);

				ResultSet resultSet = preparedStatement.executeQuery();
				while(resultSet.next()){
					userInfoDTO.setId(resultSet.getInt("id"));
					userInfoDTO.setUserId(resultSet.getString("user_id"));
					userInfoDTO.setPassword(resultSet.getString("password"));
					userInfoDTO.setFamilyName(resultSet.getString("family_name"));
					userInfoDTO.setFirstName(resultSet.getString("first_name"));
					userInfoDTO.setFamilyNameKana(resultSet.getString("family_name_kana"));
					userInfoDTO.setFirstNameKana(resultSet.getString("first_name_kana"));
					userInfoDTO.setSex(resultSet.getInt("sex"));
					userInfoDTO.setEmail(resultSet.getString("email"));
					userInfoDTO.setLogined(resultSet.getInt("status"));
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			try{
				connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			return userInfoDTO;
		}

//		マイページ機能にて使用(MyPageActionから呼ばれる)
		public UserInfoDTO getUserInfo(String userId){
			DBConnector dbConnector = new DBConnector();
			Connection connection = dbConnector.getConnection();
			UserInfoDTO userInfoDTO = new UserInfoDTO();
			String sql = "select * from user_info where user_id = ?";

			try{
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1,userId);
				ResultSet resultSet = preparedStatement.executeQuery();

				while(resultSet.next()){
					userInfoDTO.setId(resultSet.getInt("id"));
					userInfoDTO.setUserId(resultSet.getString("user_id"));
					userInfoDTO.setPassword(resultSet.getString("password"));
					userInfoDTO.setFamilyName(resultSet.getString("family_name"));
					userInfoDTO.setFirstName(resultSet.getString("first_name"));
					userInfoDTO.setFamilyNameKana(resultSet.getString("family_name_kana"));
					userInfoDTO.setFirstNameKana(resultSet.getString("first_name_kana"));
					userInfoDTO.setSex(resultSet.getInt("sex"));
					userInfoDTO.setEmail(resultSet.getString("email"));
					userInfoDTO.setLogined(resultSet.getInt("status"));
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			try{
				connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			return userInfoDTO;
		}

//		パスワード再設定機能にて使用(ResetPasswordActionから呼ばれる)
		public int resetPassword(String userId, String password){
			DBConnector dbConnector = new DBConnector();
			Connection connection = dbConnector.getConnection();
			String sql = "update user_info set password = ?, update_date = now() where user_id = ?";
			int result = 0;

			try{
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1,password);
				preparedStatement.setString(2,userId);
				result = preparedStatement.executeUpdate();
			}catch(SQLException e){
				e.printStackTrace();
			}
			try{
				connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			return result;
		}

//		ログイン機能にて使用(LoginActionから呼ばれる)
//		上記のisExistsUserInfo(String userId, String password)を用いてユーザーの存在を確認したあと、このメソッドでログインを行う動きにうつる
		public int login(String userId, String password){
			DBConnector dbConnector = new DBConnector();
			Connection connection = dbConnector.getConnection();
			int result = 0;
			String sql = "update user_info set logined = 1, update_date = now() where user_id = ? and password = ?";

			try{
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1,userId);
				preparedStatement.setString(2,password);
				result = preparedStatement.executeUpdate();
			}catch(SQLException e){
				e.printStackTrace();
			}
			try{
				connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			return result;
		}

//		ログアウト機能にて使用(LogoutActionから呼ばれる)
		public int logout(String userId){
			DBConnector dbConnector = new DBConnector();
			Connection connection = dbConnector.getConnection();
			int result = 0;
			String sql = "update user_info set logined = 0, update_date = now() where user_id = ?";

			try{
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1,userId);
				result = preparedStatement.executeUpdate();
			}catch(SQLException e){
				e.printStackTrace();
			}
			try{
				connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			return result;
		}
}
