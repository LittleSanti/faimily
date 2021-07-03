package com.samajackun.family.parser.locales;

import java.util.Map;

import com.samajackun.family.parser.MapLanguageAdapter;
import com.samajackun.family.parser.Token.Type;

public class ItalianLanguageAdapter extends MapLanguageAdapter
{
	private static final ItalianLanguageAdapter INSTANCE=new ItalianLanguageAdapter();

	public static ItalianLanguageAdapter getInstance()
	{
		return INSTANCE;
	}

	private ItalianLanguageAdapter()
	{
	}

	@Override
	protected void fillMap(Map<String, Type> map)
	{
		map.put("de", Type.OF);
		map.put("della", Type.OF);
		map.put("dello", Type.OF);
		map.put("la", Type.ARTICLE);
		map.put("il", Type.ARTICLE);
		map.put("mio", Type.MY);
		map.put("mia", Type.MY);
		map.put("padre", Type.FATHER);
		map.put("papa", Type.FATHER);
		map.put("paparino", Type.FATHER);
		map.put("madre", Type.MOTHER);
		map.put("mamma", Type.MOTHER);
		map.put("marito", Type.MATE);
		map.put("moglie", Type.MATE);
		map.put("figlio", Type.CHILD);
		map.put("figliolo", Type.CHILD);
		map.put("figlia", Type.CHILD);
		map.put("figliola", Type.CHILD);
		map.put("fratello", Type.SIBLING);
		map.put("sorella", Type.SIBLING);
		map.put("nipote", Type.GRANDCHILD);
		map.put("io", Type.SELF);
	}
}
