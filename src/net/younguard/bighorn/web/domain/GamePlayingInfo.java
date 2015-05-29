package net.younguard.bighorn.web.domain;

import net.younguard.bighorn.GlobalArgs;

public class GamePlayingInfo
{
	private String id;
	private short timeRule;
	private String blackPlayerName;
	private String blackPlayerAvatarUrl;
	private short isBlackThinking = GlobalArgs.FALSE;
	private short blackBadgeNum = 0;
	private String whitePlayerName;
	private String whitePlayerAvatarUrl;
	private short isWhiteThinking = GlobalArgs.FALSE;
	private short whiteBadgeNum = 0;
	private short step;
	private short playingColor;

	public short getIsBlackThinking()
	{
		return isBlackThinking;
	}

	public void setIsBlackThinking(short isBlackThinking)
	{
		this.isBlackThinking = isBlackThinking;
	}

	public short getBlackBadgeNum()
	{
		return blackBadgeNum;
	}

	public void setBlackBadgeNum(short blackBadgeNum)
	{
		this.blackBadgeNum = blackBadgeNum;
	}

	public short getIsWhiteThinking()
	{
		return isWhiteThinking;
	}

	public void setIsWhiteThinking(short isWhiteThinking)
	{
		this.isWhiteThinking = isWhiteThinking;
	}

	public short getWhiteBadgeNum()
	{
		return whiteBadgeNum;
	}

	public void setWhiteBadgeNum(short whiteBadgeNum)
	{
		this.whiteBadgeNum = whiteBadgeNum;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public short getTimeRule()
	{
		return timeRule;
	}

	public void setTimeRule(short timeRule)
	{
		this.timeRule = timeRule;
	}

	public String getBlackPlayerName()
	{
		return blackPlayerName;
	}

	public void setBlackPlayerName(String blackPlayerName)
	{
		this.blackPlayerName = blackPlayerName;
	}

	public String getBlackPlayerAvatarUrl()
	{
		return blackPlayerAvatarUrl;
	}

	public void setBlackPlayerAvatarUrl(String blackPlayerAvatarUrl)
	{
		this.blackPlayerAvatarUrl = blackPlayerAvatarUrl;
	}

	public String getWhitePlayerName()
	{
		return whitePlayerName;
	}

	public void setWhitePlayerName(String whitePlayerName)
	{
		this.whitePlayerName = whitePlayerName;
	}

	public String getWhitePlayerAvatarUrl()
	{
		return whitePlayerAvatarUrl;
	}

	public void setWhitePlayerAvatarUrl(String whitePlayerAvatarUrl)
	{
		this.whitePlayerAvatarUrl = whitePlayerAvatarUrl;
	}

	public short getStep()
	{
		return step;
	}

	public void setStep(short step)
	{
		this.step = step;
	}

	public short getPlayingColor()
	{
		return playingColor;
	}

	public void setPlayingColor(short playingColor)
	{
		this.playingColor = playingColor;
	}

}
