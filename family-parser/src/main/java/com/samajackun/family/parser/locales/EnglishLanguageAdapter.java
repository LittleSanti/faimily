package com.samajackun.family.parser.locales;

import java.util.Map;

import com.samajackun.family.parser.MapLanguageAdapter;
import com.samajackun.family.parser.Token.Type;

public class EnglishLanguageAdapter extends MapLanguageAdapter
{
	private static final EnglishLanguageAdapter INSTANCE=new EnglishLanguageAdapter();

	public static EnglishLanguageAdapter getInstance()
	{
		return INSTANCE;
	}

	private EnglishLanguageAdapter()
	{
	}

	@Override
	protected void fillMap(Map<String, Type> map)
	{
		map.put("of", Type.OF);
		map.put("the", Type.ARTICLE);
		map.put("my", Type.MY);
		map.put("father", Type.FATHER);
		map.put("dad", Type.FATHER);
		map.put("daddy", Type.FATHER);
		map.put("mother", Type.MOTHER);
		map.put("mom", Type.MOTHER);
		map.put("mum", Type.MOTHER);
		map.put("mummy", Type.MOTHER);
		map.put("husband", Type.MATE);
		map.put("wife", Type.MATE);
		map.put("son", Type.CHILD);
		map.put("daughter", Type.CHILD);
		map.put("brother", Type.SIBLING);
		map.put("sister", Type.SIBLING);
		map.put("grandson", Type.GRANDCHILD);
		map.put("granddaughter", Type.GRANDCHILD);
		map.put("grandchild", Type.GRANDCHILD);
		map.put("i", Type.SELF);
		map.put("me", Type.SELF);
	}
}
