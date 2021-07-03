package com.samajackun.family.ui;

import java.util.Locale;

public class AffinityCalculatorInput
{
	private String from;

	private String to;

	public enum Width {
		AFFINITY, KINSHIP
	};

	private Locale locale;

	private Width width=Width.KINSHIP;

	public String getFrom()
	{
		return this.from;
	}

	public void setFrom(String from)
	{
		this.from=from;
	}

	public String getTo()
	{
		return this.to;
	}

	public void setTo(String to)
	{
		this.to=to;
	}

	public Width getWidth()
	{
		return this.width;
	}

	public void setWidth(Width width)
	{
		this.width=width;
	}

	public Locale getLocale()
	{
		return this.locale;
	}

	public void setLocale(Locale locale)
	{
		this.locale=locale;
	}
}
