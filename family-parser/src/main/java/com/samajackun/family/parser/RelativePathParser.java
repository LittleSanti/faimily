package com.samajackun.family.parser;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Locale;

import com.samajackun.family.core.Family;
import com.samajackun.family.core.Relative;

/*
 * gramática:
 * parentesco:=(<sujeto><preposición>)*<sujeto_inmediato> | yo
 * sujeto:=[<articulo>] <pariente>
 * preposición:= de|del
 * sujeto_inmediato:=mi <pariente>
 * articulo: el|la
 * pariente: padre|madre|marido|mujer|hijo|hija|hermano|hermana|nieto
 */
public final class RelativePathParser
{
	private static final RelativePathParser INSTANCE=new RelativePathParser();

	public static RelativePathParser getInstance()
	{
		return INSTANCE;
	}

	private RelativePathParser()
	{
	}

	private enum State {
		EXPECTING_SUBJECT, EXPECTING_RELATIVE, READ_SUBJECT, EXPECTING_LAST_RELATIVE, ENDED
	};

	public Relative parse(String src, List<Locale> allowedLocales, Family family)
		throws RelativePathParseException
	{
		Deque<Token> tokens=new ArrayDeque<>();
		Token token;
		Locale locale=LanguageAdapterManager.getInstance().getFirstValidLocale(allowedLocales);
		LanguageAdapter languageAdapter=LanguageAdapterManager.getInstance().getAdapter(locale);
		Tokenizer tokenizer=new Tokenizer(new WordTokenizer(src), languageAdapter);
		State state=State.EXPECTING_SUBJECT;
		try
		{
			while ((token=tokenizer.nextToken()) != null)
			{
				switch (state)
				{
					case EXPECTING_SUBJECT:
						state=expectingSubject(token, tokens);
						break;
					case EXPECTING_RELATIVE:
						state=expectingRelative(token, tokens);
						break;
					case READ_SUBJECT:
						state=readSubject(token, tokens);
						break;
					case EXPECTING_LAST_RELATIVE:
						expectingRelative(token, tokens);
						state=State.ENDED;
						break;
					case ENDED:
						throw new PathEndedException(token);
				}
			}
			return RelativeBuilder.getInstance().build(tokens, family);
		}
		catch (UnknownTermException e)
		{
			e.setLocale(locale);
			throw e;
		}
	}

	private State expectingSubject(Token token, Deque<Token> tokens)
		throws UnexpectedTokenException
	{
		switch (token.getType())
		{
			case ARTICLE:
				return State.EXPECTING_RELATIVE;
			case CHILD:
			case GRANDCHILD:
			case FATHER:
			case MOTHER:
			case MATE:
			case SIBLING:
				tokens.push(token);
				return State.READ_SUBJECT;
			case SELF:
				tokens.push(token);
				return State.ENDED;
			case MY:
				return State.EXPECTING_LAST_RELATIVE;
			default:
				throw new UnexpectedTokenException(token);
		}
	}

	private State expectingRelative(Token token, Deque<Token> tokens)
		throws UnexpectedTokenException
	{
		switch (token.getType())
		{
			case MY:
				return State.EXPECTING_LAST_RELATIVE;
			case CHILD:
			case GRANDCHILD:
			case FATHER:
			case MOTHER:
			case MATE:
			case SIBLING:
				tokens.push(token);
				return State.READ_SUBJECT;
			default:
				throw new UnexpectedTokenException(token);
		}
	}

	private State readSubject(Token token, Deque<Token> tokens)
		throws UnexpectedTokenException
	{
		switch (token.getType())
		{
			case OF:
				return State.EXPECTING_SUBJECT;
			default:
				throw new UnexpectedTokenException(token);
		}
	}
}
