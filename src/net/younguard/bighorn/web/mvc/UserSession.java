package net.younguard.bighorn.web.mvc;

public class UserSession
{
	public String id;
	public String nickname;

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String username)
	{
		this.nickname = username;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

}
