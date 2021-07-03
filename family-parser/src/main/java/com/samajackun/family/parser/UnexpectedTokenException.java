package com.samajackun.family.parser;

public class UnexpectedTokenException extends RelativePathParseException
{
	private static final long serialVersionUID=6101176198440580034L;

	private final Token token;

	public UnexpectedTokenException(Token token)
	{
		super("Unexpected token " + token);
		this.token=token;
	}

	public Token getToken()
	{
		return this.token;
	}
}
