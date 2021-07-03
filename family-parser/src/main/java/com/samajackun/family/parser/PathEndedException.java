package com.samajackun.family.parser;

public class PathEndedException extends RelativePathParseException
{
	private static final long serialVersionUID=8519214609930402882L;

	private final Token token;

	public PathEndedException(Token token)
	{
		super("Token " + token + " was found after a path ended");
		this.token=token;
	}

	public Token getToken()
	{
		return this.token;
	}

}
