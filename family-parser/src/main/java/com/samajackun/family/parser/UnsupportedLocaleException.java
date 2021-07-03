package com.samajackun.family.parser;

import java.util.Collection;
import java.util.Collections;
import java.util.Locale;

public class UnsupportedLocaleException extends RelativePathParseException
{
	private static final long serialVersionUID=-8286426448626232885L;

	private final Collection<Locale> locales;

	public UnsupportedLocaleException(Collection<Locale> locales)
	{
		super("Unsupported locales " + locales);
		this.locales=locales;
	}

	public UnsupportedLocaleException(Locale locale)
	{
		super("Unsupported locale " + locale);
		this.locales=Collections.singleton(locale);
	}

	public Collection<Locale> getLocales()
	{
		return this.locales;
	}
}
