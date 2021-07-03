package com.samajackun.family.ui;

import static com.samajackun.family.ui.TranslationUtils.translateException;
import static com.samajackun.family.ui.TranslationUtils.translateLocale;
import static com.samajackun.family.ui.TranslationUtils.translateText;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.Locale;

import com.samajackun.family.core.AffinityDegreeCalculatorException;
import com.samajackun.family.core.DefaultAffinityDegreeCalculator;
import com.samajackun.family.core.Family;
import com.samajackun.family.core.Relative;
import com.samajackun.family.parser.PathEndedException;
import com.samajackun.family.parser.RelativePathParseException;
import com.samajackun.family.parser.RelativePathParser;
import com.samajackun.family.parser.UnknownTermException;
import com.samajackun.family.parser.UnsupportedLocaleException;

public final class AffinityCalculatorService
{
	private static final AffinityCalculatorService INSTANCE=new AffinityCalculatorService();

	public static AffinityCalculatorService getInstance()
	{
		return INSTANCE;
	}

	private AffinityCalculatorService()
	{
	}

	public void calculateAffinity(AffinityCalculatorInput input, AffinityCalculatorOutput output)
		throws AffinityCalculatorFailure,
		AffinityCalculatorException
	{
		Family family=new Family();
		try
		{
			Relative fromRelative=RelativePathParser.getInstance().parse(input.getFrom(), Collections.singletonList(input.getLocale()), family);
			Relative toRelative=RelativePathParser.getInstance().parse(input.getTo(), Collections.singletonList(input.getLocale()), family);
			int degree=input.getWidth() == AffinityCalculatorInput.Width.AFFINITY
				? DefaultAffinityDegreeCalculator.getInstance().affinityDegreeTo(fromRelative, toRelative)
				: DefaultAffinityDegreeCalculator.getInstance().kinshipDegreeTo(fromRelative, toRelative);
			if (degree == Integer.MAX_VALUE)
			{
				throw new AffinityCalculatorException(translateText(input.getLocale(), "unrelated"));
			}
			output.setDegree(degree);
		}
		catch (UnknownTermException e)
		{
			String text=MessageFormat.format(translateException(input.getLocale(), e), e.getTerm(), translateLocale(input.getLocale(),e.getLocale()));
			throw new AffinityCalculatorFailure(text);
		}
		catch (UnsupportedLocaleException e)
		{
			String localesStr="";
			for (Locale locale:e.getLocales())
			{
				if (!localesStr.isEmpty()) localesStr+=",";
				localesStr+=translateLocale(input.getLocale(),locale);
			}
			String text=MessageFormat.format(translateException(input.getLocale(), e), e.getLocales().toString());
			throw new AffinityCalculatorFailure(text);
		}
		catch (PathEndedException e)
		{
			String text=MessageFormat.format(translateException(input.getLocale(), e), e.getToken().getImage());
			throw new AffinityCalculatorFailure(text);
		}		
		catch (RelativePathParseException e)
		{
			throw new AffinityCalculatorFailure(translateException(input.getLocale(), e));
		}
		catch (AffinityDegreeCalculatorException e)
		{
			throw new AffinityCalculatorFailure(translateException(input.getLocale(), e));
		}
	}
}
