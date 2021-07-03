package com.samajackun.family.parser.locales;

import java.util.Map;

import com.samajackun.family.parser.MapLanguageAdapter;
import com.samajackun.family.parser.Token.Type;

public class SpanishLanguageAdapter extends MapLanguageAdapter
{
	private static final SpanishLanguageAdapter INSTANCE=new SpanishLanguageAdapter();

	public static SpanishLanguageAdapter getInstance()
	{
		return INSTANCE;
	}

	private SpanishLanguageAdapter()
	{
	}

	@Override
	protected void fillMap(Map<String, Type> map)
	{
		map.put("de", Type.OF);
		map.put("del", Type.OF);
		map.put("el", Type.ARTICLE);
		map.put("la", Type.ARTICLE);
		map.put("mi", Type.MY);
		map.put("padre", Type.FATHER);
		map.put("papá", Type.FATHER);
		map.put("madre", Type.MOTHER);
		map.put("mamá", Type.MOTHER);
		map.put("marido", Type.MATE);
		map.put("mujer", Type.MATE);
		map.put("esposo", Type.MATE);
		map.put("esposa", Type.MATE);
		map.put("hijo", Type.CHILD);
		map.put("hija", Type.CHILD);
		map.put("hermano", Type.SIBLING);
		map.put("hermana", Type.SIBLING);
		map.put("nieto", Type.GRANDCHILD);
		map.put("nieta", Type.GRANDCHILD);
		map.put("yo", Type.SELF);
	}
}
