package com.samajackun.family.ui;

import java.util.Locale;
import java.util.ResourceBundle;

public final class TranslationUtils
{
	private TranslationUtils()
	{
	}

	public static String translateLocale(Locale locale, Locale key)
	{
		ResourceBundle bundle=ResourceBundle.getBundle("com.samajackun.family.ui.bundles.AffinityCalculator", locale);
		return bundle.getString("locale."+key.toString());
	}
	public static String translateText(Locale locale, String key)
	{
		ResourceBundle bundle=ResourceBundle.getBundle("com.samajackun.family.ui.bundles.AffinityCalculator", locale);
		return bundle.getString(key);
	}

	public static String translateException(Locale locale, Exception exception)
	{
		ResourceBundle bundle=ResourceBundle.getBundle("com.samajackun.family.ui.bundles.AffinityCalculator", locale);
		return bundle.getString(exception.getClass().getName());
	}
}
