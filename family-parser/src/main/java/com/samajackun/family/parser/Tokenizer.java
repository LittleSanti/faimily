package com.samajackun.family.parser;

public class Tokenizer
{
	private final WordTokenizer src;

	private final LanguageAdapter languageAdapter;

	public Tokenizer(WordTokenizer src, LanguageAdapter languageAdapter)
	{
		super();
		this.src=src;
		this.languageAdapter=languageAdapter;
	}

	public Token nextToken()
		throws UnknownTermException
	{
		String word=this.src.nextWord();
		Token token;
		if (word == null)
		{
			token=null;
		}
		else
		{
			token=new Token(this.languageAdapter.getTokenType(word), word);
		}
		return token;
	}

}
