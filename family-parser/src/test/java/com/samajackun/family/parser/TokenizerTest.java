package com.samajackun.family.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.samajackun.family.parser.Token.Type;
import com.samajackun.family.parser.locales.SpanishLanguageAdapter;

public class TokenizerTest
{
	private Tokenizer createTokenizer(String src)
	{
		WordTokenizer wordTokenizer=new WordTokenizer(src);
		LanguageAdapter languageAdapter=SpanishLanguageAdapter.getInstance();
		Tokenizer tokenizer=new Tokenizer(wordTokenizer, languageAdapter);
		return tokenizer;
	}

	@Test
	public void empty()
	{
		String src="";
		Tokenizer tokenizer=createTokenizer(src);
		try
		{
			Token token=tokenizer.nextToken();
			assertNull(token);
		}
		catch (UnknownTermException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	private void assertTokenEquals(Type expectedType, String expectedImage, Token token)
	{
		assertEquals(expectedType, token.getType());
		assertEquals(expectedImage, token.getImage());
	}

	@Test
	public void father()
	{
		String src="padre";
		Tokenizer tokenizer=createTokenizer(src);
		try
		{
			Token token;
			token=tokenizer.nextToken();
			assertTokenEquals(Token.Type.FATHER, "padre", token);
			token=tokenizer.nextToken();
			assertNull(token);
		}
		catch (UnknownTermException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void theFather()
	{
		String src="el padre";
		Tokenizer tokenizer=createTokenizer(src);
		try
		{
			Token token;
			token=tokenizer.nextToken();
			assertTokenEquals(Token.Type.ARTICLE, "el", token);
			token=tokenizer.nextToken();
			assertTokenEquals(Token.Type.FATHER, "padre", token);
		}
		catch (UnknownTermException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void ofTheFather()
	{
		String src="del padre";
		Tokenizer tokenizer=createTokenizer(src);
		try
		{
			Token token;
			token=tokenizer.nextToken();
			assertTokenEquals(Token.Type.OF, "del", token);
			token=tokenizer.nextToken();
			assertTokenEquals(Token.Type.FATHER, "padre", token);
		}
		catch (UnknownTermException e)
		{
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void unknown()
	{
		String src="primo";
		Tokenizer tokenizer=createTokenizer(src);
		try
		{
			tokenizer.nextToken();
			fail("Expected UnknownTermException");
		}
		catch (UnknownTermException e)
		{
			assertEquals("primo", e.getTerm());
		}
	}
}
