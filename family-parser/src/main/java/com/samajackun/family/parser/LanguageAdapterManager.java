package com.samajackun.family.parser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import com.samajackun.family.parser.locales.EnglishLanguageAdapter;
import com.samajackun.family.parser.locales.ItalianLanguageAdapter;
import com.samajackun.family.parser.locales.SpanishLanguageAdapter;

public class LanguageAdapterManager
{
	private final Map<Locale, LanguageAdapter> map=createMap();

	private static final LanguageAdapterManager INSTANCE=new LanguageAdapterManager();

	public static LanguageAdapterManager getInstance()
	{
		return INSTANCE;
	}

	private Map<Locale, LanguageAdapter> createMap()
	{
		Map<Locale, LanguageAdapter> map=new HashMap<>();
		map.put(new Locale("es"), SpanishLanguageAdapter.getInstance());
		map.put(new Locale("en"), EnglishLanguageAdapter.getInstance());
		map.put(new Locale("it"), ItalianLanguageAdapter.getInstance());
		return map;
	}

	private LanguageAdapterManager()
	{
	}

	public Locale getFirstValidLocale(List<Locale> allowedLocales)
		throws UnsupportedLocaleException
	{
		Locale preferred=null;
		for (Iterator<Locale> iterator=allowedLocales.iterator(); iterator.hasNext() && preferred == null;)
		{
			preferred=iterator.next();
			if (!this.map.containsKey(preferred))
			{
				preferred=null;
			}
		}
		if (preferred == null)
		{
			throw new UnsupportedLocaleException(allowedLocales);
		}
		return preferred;
	}

	public LanguageAdapter getAdapter(Locale requiredLocale)
		throws UnsupportedLocaleException
	{
		LanguageAdapter languageAdapter=this.map.get(requiredLocale);
		if (languageAdapter == null)
		{
			throw new UnsupportedLocaleException(requiredLocale);
		}
		return languageAdapter;
	}

	public Set<Locale> getAvailableLanguages()
	{
		return this.map.keySet();
	}
}
