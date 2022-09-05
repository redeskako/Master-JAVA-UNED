<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="CDS">
		<html>
		<head><title>Repertorio de música</title></head>
		<body>
			<ul>
			<xsl:for-each select="CD">
				<li>
					<xsl:value-of select="TITULO"/> - 
					<xsl:value-of select="INTERPRETE"/>
				</li>
			</xsl:for-each>
			</ul>
		</body>
		</html>
	</xsl:template>
</xsl:stylesheet>