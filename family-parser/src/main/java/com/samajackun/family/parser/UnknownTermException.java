package com.samajackun.family.parser;

import java.util.Locale;

public class UnknownTermException extends RelativePathParseException
{
	private static final long serialVersionUID=-5008341608217435679L;

	private final String term;

	private Locale locale;

	public UnknownTermException(String term)
	{
		super("Unknown term '" + term + "'");
		this.term=term;
	}

	public String getTerm()
	{
		return this.term;
	}

	public Locale getLocale()
	{
		return this.locale;
	}

	@Override
	public String getMessage()
	{
		return super.getMessage() + " in locale " + this.locale;
	}

	public void setLocale(Locale locale)
	{
		this.locale=locale;
	}

}
