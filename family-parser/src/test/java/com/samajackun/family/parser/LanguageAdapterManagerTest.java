package com.samajackun.family.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.junit.Test;

import com.samajackun.family.parser.locales.SpanishLanguageAdapter;

public class LanguageAdapterManagerTest
{
	@Test
	public void getFirstValidLocaleForFirst()
	{
		List<Locale> allowedLocales=Arrays.asList(new Locale[] {
			new Locale("es"),
			new Locale("zh"),
		});
		try
		{
			Locale locale=LanguageAdapterManager.getInstance().getFirstValidLocale(allowedLocales);
			assertSame(allowedLocales.get(0), locale);
		}
		catch (UnsupportedLocaleException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void getFirstValidLocaleForSecond()
	{
		List<Locale> allowedLocales=Arrays.asList(new Locale[] {
			new Locale("zh"),
			new Locale("es"),
		});
		try
		{
			Locale locale=LanguageAdapterManager.getInstance().getFirstValidLocale(allowedLocales);
			assertSame(allowedLocales.get(1), locale);
		}
		catch (UnsupportedLocaleException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void getFirstValidLocaleForNone()
	{
		List<Locale> allowedLocales=Arrays.asList(new Locale[] {
			new Locale("zh"),
			new Locale("jp"),
		});
		try
		{
			LanguageAdapterManager.getInstance().getFirstValidLocale(allowedLocales);
			fail("Expected UnsupportedLocaleException");
		}
		catch (UnsupportedLocaleException e)
		{
			assertEquals(allowedLocales, e.getLocales());
		}
	}

	@Test
	public void getAdapterFromRequiredLocale()
	{
		Locale requiredLocale=new Locale("es");
		try
		{
			LanguageAdapter languageAdapter=LanguageAdapterManager.getInstance().getAdapter(requiredLocale);
			assertSame(SpanishLanguageAdapter.getInstance(), languageAdapter);
		}
		catch (UnsupportedLocaleException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void getAdapterFromInvalidLocale()
	{
		Locale requiredLocale=new Locale("zh");
		try
		{
			LanguageAdapterManager.getInstance().getAdapter(requiredLocale);
			fail("Expected UnsupportedLocaleException");
		}
		catch (UnsupportedLocaleException e)
		{
			assertTrue(e.getLocales().contains(requiredLocale));
		}
	}
}
