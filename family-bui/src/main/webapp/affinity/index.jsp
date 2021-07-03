<%@ page language="java" contentType="text/xml"
	pageEncoding="ISO-8859-1"
	import="java.util.Locale"
	import="java.util.SortedSet"
	import="java.util.TreeSet"
	import="com.samajackun.family.ui.AffinityCalculatorService"
	import="com.samajackun.family.ui.TranslationUtils"
	import="com.samajackun.family.parser.LanguageAdapter"
	import="com.samajackun.family.parser.LanguageAdapterManager"
	import="com.samajackun.family.ui.AffinityCalculatorInput"
	import="com.samajackun.family.ui.AffinityCalculatorOutput"
	import="com.samajackun.family.ui.AffinityCalculatorService"
	import="com.samajackun.family.ui.AffinityCalculatorException"
	import="com.samajackun.family.ui.AffinityCalculatorFailure"
	import="org.apache.commons.lang3.LocaleUtils"
	%><%!
private String getDegree(AffinityCalculatorInput input, AffinityCalculatorInput.Width width)
{
	String outMessage;
	AffinityCalculatorOutput output=new AffinityCalculatorOutput();
	try
	{
		input.setWidth(width);
		AffinityCalculatorService.getInstance().calculateAffinity(input, output);
		outMessage=Integer.toString(output.getDegree());	
	}
	catch (AffinityCalculatorFailure f)
	{
		outMessage=f.getMessage();
	}
	catch (AffinityCalculatorException e)
	{
		outMessage=e.getMessage();
	}
	return outMessage;
}
%><?xml version="1.0" encoding="ISO-8859-1"?>
<!-- family-bui 0.1.1-SNAPSHOT SKN LTD 2021 -->
<!DOCTYPE affinity SYSTEM "affinity.dtd">
<%
Locale defaultLocale=request.getLocale();
String localeString=request.getParameter("locale")==null ? defaultLocale.toString() : request.getParameter("locale");
%><?xml-stylesheet type="text/xsl" href="affinity.xsl?locale=<%=localeString%>"?>
<affinity>
	<locale><%=localeString%></locale>
	<available-locales>
<% 
	for (Locale item:LanguageAdapterManager.getInstance().getAvailableLanguages())
	{
	%>	<available-locale key="<%=item%>" name="<%=TranslationUtils.translateLocale(defaultLocale,item)%>" />
	<%
	}
%>	</available-locales>
<%
String from=request.getParameter("from")==null ? "" : request.getParameter("from");
%>	<from><%=from%></from>
<%
String to=request.getParameter("to")==null ? "" : request.getParameter("to");
%>	<to><%=to%></to>
<%
String operation=request.getParameter("operation");
if ("calculate".equals(operation))
{
	AffinityCalculatorInput input=new AffinityCalculatorInput();
	input.setFrom(request.getParameter("from"));
	input.setTo(request.getParameter("to"));
	input.setLocale(new Locale(request.getParameter("locale")));
	AffinityCalculatorOutput output=new AffinityCalculatorOutput();
	%>
<result>
	<kinship-degree><%=getDegree(input, AffinityCalculatorInput.Width.KINSHIP) %></kinship-degree>
	<affinity-degree><%=getDegree(input, AffinityCalculatorInput.Width.AFFINITY) %></affinity-degree>
</result>
<%
}
else if ("vocabulary".equals(operation))
{ 
	LanguageAdapter languageAdapter=LanguageAdapterManager.getInstance().getAdapter(LocaleUtils.toLocale(localeString));
	SortedSet<String> vocabulary=new TreeSet<>(languageAdapter.getVocabulary());
	%>
	<vocabulary>
	<%
	for (String term:vocabulary)
	{
		%>	<term><%=term%></term>
		<%
	}
	%>
	</vocabulary>
<%
}
%></affinity>