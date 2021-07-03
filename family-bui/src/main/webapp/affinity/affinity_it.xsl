<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:output method="html" version="5" encoding="ISO-8859-1"/>

<xsl:template match="/affinity">
<html>
<head>
<title>Calcolo del grado di consanguineità/affinità</title>
<link rel="stylesheet" type="text/css" href="affinity.css"/>
<style type="text/css">
</style>
</head>
<body>
<div class="body">
	<form>
		<h1>Calcolo del grado di consanguineità/affinità</h1>
		<p>
			<label for="locale">idioma:</label>
			<select name="locale">
			<xsl:apply-templates select="available-locales/available-locale"><xsl:with-param name="selected-locale" select="locale"></xsl:with-param></xsl:apply-templates>
			</select>
		</p>
		<p>
			<label for="from">da:</label>
			<input type="text" name="from" size="100" value="{from}"/>
		</p>
		<p>
			<label for="to">a:</label>
			<input type="text" name="to" size="100" value="{to}"/>
		</p>
		<p>
			<button type="submit" name="operation" value="calculate">calculare gradi</button>
			<button type="submit" name="operation" value="vocabulary">visualizzare il vocabolario dei termini supportati</button>
		</p>
	</form>
	<xsl:apply-templates select="result"/>
	<xsl:apply-templates select="vocabulary"/>
</div>
</body>
</html>
</xsl:template>

<xsl:template match="available-locale">
	<xsl:param name="selected-locale"></xsl:param>
	<option value="{@key}"><xsl:if test="@key=$selected-locale"><xsl:attribute name="selected">selected</xsl:attribute></xsl:if><xsl:value-of select="@name"></xsl:value-of></option>
</xsl:template>

<xsl:template match="result">
	<ul class="result">
		<li>grado di consanguineità:<xsl:value-of select="kinship-degree"/></li>
		<li>grado di affinità:<xsl:value-of select="affinity-degree"/></li>
	</ul>
</xsl:template>

<xsl:template match="vocabulary">
	<div class="vocabulary">
		<label>vocabulario:</label>
		<span>
			<xsl:apply-templates select="term"></xsl:apply-templates>
		</span>
	</div>
</xsl:template>
 
<xsl:template match="term"><xsl:value-of select="." /><xsl:text>, </xsl:text> 
</xsl:template>

</xsl:stylesheet>
