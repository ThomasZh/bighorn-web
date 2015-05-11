package net.younguard.bighorn.web.domain;

import net.younguard.bighorn.GlobalArgs;

public class GameInviteInfo
{
	private String id;
	private short timeRule;
	private short playerColor;
	private String playerName;
	private String playerAvatarUrl;
	private short isMe = GlobalArgs.FALSE;

	public short getIsMe()
	{
		return isMe;
	}

	public void setIsMe(short isMe)
	{
		this.isMe = isMe;
	}

	public short getTimeRule()
	{
		return timeRule;
	}

	public void setTimeRule(short timeRule)
	{
		this.timeRule = timeRule;
	}

	public short getPlayerColor()
	{
		return playerColor;
	}

	public void setPlayerColor(short playerColor)
	{
		this.playerColor = playerColor;
	}

	public String getPlayerName()
	{
		return playerName;
	}

	public void setPlayerName(String playerName)
	{
		this.playerName = playerName;
	}

	public String getPlayerAvatarUrl()
	{
		return playerAvatarUrl;
	}

	public void setPlayerAvatarUrl(String playerAvatarUrl)
	{
		this.playerAvatarUrl = playerAvatarUrl;
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
