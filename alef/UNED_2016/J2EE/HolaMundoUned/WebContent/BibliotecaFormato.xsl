<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		
	<html>
	<head>
		<title>XML_XSL</title>
		
		<link href="css/principal.css" rel="stylesheet" type="text/css" />
	</head>

	<body>
	<div id="faja">
		<header>
			<div id="cabecera_arriba">
			</div>
			<div id="cabecera_abajo">
				<div id="enlace1" class="enlace">
					<a href="#">INICIO</a>
				</div>
				<div id="enlace2" class="enlace">
					<a href="#">GALERÍA</a>
				</div>
				<div id="enlace3" class="enlace">
					<a href="#">VÍDEOS</a>
				</div>
				<div id="enlace4" class="enlace">
					<a href="#">FORO</a>
				</div>
				<div id="enlace5" class="enlace">
					<a href="#">HISTORIA</a>
				</div>
			</div>
		</header>
		<div id="cuerpo">
			<nav>
			</nav>
			<div id="principal">
				<h2>Mi Biblioteca</h2>
				<center>
				<table>
					<tr>
					<th>Títulos</th>
					<th>Autor</th>
					</tr>
					<xsl:for-each select="libros/libro">
						<tr>
						<td>
							<xsl:value-of select="titulo"/>
						</td>
						<td>
							<xsl:value-of select="autor"/>
						</td>
						</tr>
					</xsl:for-each>
				</table>
				</center>
			</div>
		</div>
		<footer>
		</footer>
	</div>
	</body>
	</html>

	</xsl:template>
</xsl:stylesheet>