package com.samajackun.family.parser;

import java.util.Set;

public interface LanguageAdapter
{
	public Token.Type getTokenType(String image)
		throws UnknownTermException;

	public Set<String> getVocabulary();
}
