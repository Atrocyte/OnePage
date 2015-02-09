<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">
	<xsl:output method="html" doctype-system="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-public="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
		omit-xml-declaration="yes" encoding="UTF-8" indent="yes" />
	<xsl:template match="/">

		<html>
			<head>
				<meta http-equiv="Content-Type" content="text/html; charset=ascii" />
				<title>1Page Ordina</title>
				<link rel="stylesheet" type="text/css" href="css/fluid_grid.css" />
				<link rel="stylesheet" type="text/css" href="style.css" />

			</head>

			<body>
				<div id="main" class="chapter container_16">
					<div class="grid_4">
						<div id="foto" class="alpha omega">&#160;</div>


						<div id="PROFIEL" class="content">
							<h1>
								<xsl:value-of select="Employee/Personalia/Name" />
							</h1>
							<h3>
								<xsl:value-of select="Employee/Personalia/Function" />
							</h3>
							<p>
								<xsl:value-of select="Employee/Personalia/Description" />
							</p>
							<p>
								<xsl:text>Locatie: &#x9;</xsl:text>
								<xsl:value-of select="Employee/Personalia/Location" />
							</p>
							<p>
								<xsl:text>LinkedIn: &#x9;</xsl:text>
								<xsl:value-of select="Employee/Personalia/LinkedIn" />
							</p>
							<p>
								<xsl:text>Hoogste opleiding: &#x9;</xsl:text>
								<xsl:value-of select="Employee/Personalia/HighestEducation" />
							</p>
							<p>
								<xsl:text>Geboortedatum: &#x9;</xsl:text>
								<xsl:value-of select="Employee/Personalia/DateOfBirth" />
							</p>
							<p>
								<xsl:text>Hobbies: &#x9;</xsl:text>
								<xsl:value-of select="Employee/Personalia/Hobbies" />
							</p>
							<p>
								<xsl:text>Tags: &#x9;</xsl:text>
								<xsl:value-of select="Employee/Personalia/Tag" />
							</p>


						</div>
					</div>
					<div class="grid_5">
						<div id="OPLEIDINGEN_CURSUSSEN" class="content">
							<h1>OPLEIDINGEN / CURSUSSEN</h1>
							<xsl:for-each select="Employee/Education/*">
								<p>
									<xsl:value-of select="Certificate" />
									<xsl:text>&#xA;</xsl:text>
									<xsl:value-of select="Institute" />
									<xsl:text>&#x9;</xsl:text>
									<xsl:value-of select="Year" />
									<xsl:text>&#xA;</xsl:text>
								</p>
							</xsl:for-each>



						</div>
						<div id="VAARDIGHEDEN_KENNIS" class="content">
							<h1>VAARDIGHEDEN &amp; KENNIS</h1>
							<xsl:for-each select="Employee/Skills/*">
								<p>
									<xsl:value-of select="Description" />
									<xsl:text>&#x9;</xsl:text>
									<xsl:value-of select="Percentage" />
								</p>
							</xsl:for-each>
						</div>
					</div>
					<div class="grid_7 omega">
						<div id="RELEVANTE_WERKERVARING" class="content">
							<h1>RELEVANTE WERKERVARING</h1>
							<div class="title">
								<h3>
									<xsl:value-of
										select="Employee/PrimaryWorkingExperience/FunctionEmployer" />
								</h3>
								<h3>
									<xsl:value-of select="Employee/PrimaryWorkingExperience/Duration" />
								</h3>
							</div>
							<div class="clearfix">&#160;</div>
							<div>
								<h3 class="alpha grid_3 subtitle">situatie</h3>
								<p class="prefix_3">
									<xsl:value-of select="Employee/PrimaryWorkingExperience/Situation" />
								</p>
							</div>
							<div>
								<h3 class="alpha grid_3 subtitle">taken</h3>
								<p class="prefix_3">
									<xsl:value-of select="Employee/PrimaryWorkingExperience/Tasks" />
								</p>
							</div>
							<div>
								<h3 class="alpha grid_3 subtitle">resultaat</h3>
								<p class="last prefix_3">
									<xsl:value-of select="Employee/PrimaryWorkingExperience/Results" />
								</p>
							</div>

						</div>

						<div id="OVERIGE_WERKERVARING" class="content">
							<h1>OVERIGE WERKERVARING</h1>
							<div>
								<div class="title">
									<h3>
										<xsl:value-of
											select="Employee/OtherWorkingExperience/WorkingExperience1/FunctionEmployer" />
									</h3>
									<h3>
										<xsl:value-of
											select="Employee/OtherWorkingExperience/WorkingExperience1/Duration" />
									</h3>
								</div>
								<div class="clearfix">&#160;</div>
								<p>
									<xsl:value-of
										select="Employee/OtherWorkingExperience/WorkingExperience1/Description" />
								</p>
							</div>
							<div>
								<div class="title">
									<h3>
										<xsl:value-of
											select="Employee/OtherWorkingExperience/WorkingExperience2/FunctionEmployer" />
									</h3>
									<h3>
										<xsl:value-of
											select="Employee/OtherWorkingExperience/WorkingExperience2/Duration" />
									</h3>
								</div>
								<div class="clearfix">&#160;</div>
								<p>
									<xsl:value-of
										select="Employee/OtherWorkingExperience/WorkingExperience2/Description" />
								</p>
							</div>
							<div>
								<div class="title">
									<h3>
										<xsl:value-of
											select="Employee/OtherWorkingExperience/WorkingExperience3/FunctionEmployer" />
									</h3>
									<h3>
										<xsl:value-of
											select="Employee/OtherWorkingExperience/WorkingExperience3/Duration" />
									</h3>
								</div>
								<div class="clearfix">&#160;</div>
								<p class="last">
									<xsl:value-of
										select="Employee/OtherWorkingExperience/WorkingExperience3/Description" />
								</p>
							</div>
						</div>
					</div>
				</div>
			</body>

		</html>
	</xsl:template>
</xsl:stylesheet>