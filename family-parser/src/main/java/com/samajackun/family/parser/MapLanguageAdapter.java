package com.samajackun.family.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.samajackun.family.parser.Token.Type;

public abstract class MapLanguageAdapter implements LanguageAdapter
{
	private class MyLowerCaseHashMap extends HashMap<String, Type>
	{
		private static final long serialVersionUID=8654098031492457623L;

		@Override
		public Type put(String key, Type value)
		{
			return super.put(key.toLowerCase(), value);
		}
	}

	private final Map<String, Token.Type> map=new MyLowerCaseHashMap();

	protected abstract void fillMap(Map<String, Token.Type> map);

	protected MapLanguageAdapter()
	{
		super();
		fillMap(this.map);
	}

	@Override
	public Type getTokenType(String image)
		throws UnknownTermException
	{
		Type type=this.map.get(image.toLowerCase());
		if (type == null)
		{
			throw new UnknownTermException(image);
		}
		return type;
	}

	public Set<String> getVocabulary()
	{
		return this.map.keySet();
	}
}
