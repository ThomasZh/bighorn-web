package net.younguard.bighorn.web.mvc;

import java.io.Serializable;

public class UserSession
		implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1313053220773319248L;
	public String id;
	public String nickname;
	public String avatarUrl;

	public String getAvatarUrl()
	{
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl)
	{
		this.avatarUrl = avatarUrl;
	}

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
