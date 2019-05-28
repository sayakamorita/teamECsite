package com.internousdev.leo.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.leo.dao.UserInfoDAO;
import com.opensymphony.xwork2.ActionSupport;

public class CreateUserCompleteAction  extends ActionSupport implements SessionAware{

	private Map<String,Object> session;

	public String execute(){
		String result = ERROR;
		String sex = null;

//		初期値としてラジオボタンの「男性」が選択されているため、
//		session key:sexの値が男性ではなく女性の場合は
//		値が更新されたという意味。→この時点で下記int countに格納する1か0かを判断している。
		if(String.valueOf(session.get("sex")).equals("女性")){
			sex = "1";
		}else{
			sex = "0";
		}

		UserInfoDAO userInfoDAO = new UserInfoDAO();
		int count = userInfoDAO.createUser(

//				sessionに格納している各値をkey名で取り出しcreateUserメソッドの仮引数に渡している。
//				仮引数のデータ型がすべてString型なので、valueOfメソッドでString型に変換している。
				String.valueOf(session.get("familyName")),
				String.valueOf(session.get("firstName")),
				String.valueOf(session.get("familyNameKana")),
				String.valueOf(session.get("firstNameKana")),

//				下記変数sexには14行目で判断した値(0か1)がそのまま格納される。
				sex,
				String.valueOf(session.get("email")),
				String.valueOf(session.get("userIdForCreateUser")),
				String.valueOf(session.get("password"))
				);
//		userInfoDAOのcreateUserメソッドでDBにインサートした10個の値がすべてエラーなく登録されたら、
//		count = 1が返される→以下でsuccessとして戻される
		if(count > 0){
			result = SUCCESS;
		}

//		result=successが送られる前に、セッションに登録された情報（ユーザーIDとパスワード以外）を削除しておく。
		session.remove("familyName");
		session.remove("firstName");
		session.remove("familyNameKana");
		session.remove("firstNameKana");
		session.remove("sex");
		session.remove("sexList");
		session.remove("email");

		return result;
	}

	public Map<String,Object> getSession(){
		return session;
	}
	public void setSession(Map<String,Object>session){
		this.session = session;
	}
}
