<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                version="1.0">
    <xsl:output method="html" doctype-system="-//W3C//DTD XHTML 1.0 Transitional//EN"
                    doctype-public="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
                    omit-xml-declaration="yes" encoding="UTF-8" indent="yes" />
    <xsl:template match="/">
        <html>
            <head>
                <title>Clean Minimal Ordina 1 Page</title>
                <link rel="stylesheet" type="text/css" href="style.css"/>
            </head>

            <body>
                <div id="main" class="chapter container_16">
                    <div class="col_1 alpha">
                        <div id="foto">
                            <img>
                                <xsl:attribute name="src">
                                    <xsl:value-of select="Employee/Technical/Tech1" />
                                </xsl:attribute>
                            </img>
                        </div>
                        <div id="PROFIEL" class="box">
                            <div class="titel devider">
                                <h1>
                                    <xsl:value-of select="Employee/Personalia/Name" />
                                </h1>
                                <h3>
                                    <xsl:value-of select="Employee/Personalia/Function" />
                                </h3>
                            </div>
                            <div class="content">
                                <p class="devider">
                                    <xsl:value-of select="Employee/Personalia/Description"/>
                                </p>
                            </div>
                            <ul class="icons">
                                <li class="icon devider placeholder">
                                    <xsl:value-of select="Employee/Personalia/Location"/>
                                </li>
                                <li class="icon devider linkedin">
                                    <xsl:value-of select="Employee/Personalia/LinkedIn"/>
                                </li>
                                <li class="icon devider graduate">
                                    <xsl:value-of select="Employee/Personalia/HighestEducation"/>
                                </li>
                                <li class="icon devider cake">
                                    <xsl:value-of select="Employee/Personalia/DateOfBirth"/>
                                </li>
                                <li class="icon devider funny">
                                    <xsl:value-of select="Employee/Personalia/Hobbies"/>
                                </li> 
                                <li class="icon devider tag">
                                    <xsl:value-of select="Employee/Personalia/Tag"/>
                                </li>
                            </ul>
                        </div>
                    </div>

                    <div class="col_2">
                        <div id="OPLEIDINGEN_CURSUSSEN"  class="box">
                            <div class="titel devider">
                                <h1>Opleidingen / Cursussen</h1>
                            </div>
                            <div class="content">
                                <xsl:for-each select="Employee/Education/*">
                                    <div class="opleiding devider">
                                        <h3 class="alpha grid_3 jaar">
                                            <xsl:value-of select="Year" />
                                        </h3>
                                        <h3 class="push_3 opleiding_cursus">
                                            <xsl:value-of select="Certificate" />
                                        </h3>
                                        <p class="prefix_3 instituut">
                                            <xsl:value-of select="Institute" />
                                        </p>
                                    </div>
                                </xsl:for-each>
                            </div>

                        </div>
                        <div id="VAARDIGHEDEN_KENNIS"  class="box">
                            <div class="titel devider">
                                <h1>Vaardigheden &amp; Kennis</h1>
                            </div>
                            <div class="content">
                                <xsl:for-each select="Employee/Skills/*">
                                    <div class="vaardigheid devider">
                                        <h3>
                                            <xsl:value-of select="Description"/>
                                        </h3>
                                        <div class="bar_bg">
                                            <div class="bar" style="width:{Percentage}%;"></div>
                                        </div>
                                        <ul>
                                            <li class="left">junior</li>
                                            <li class="center">medior</li>
                                            <li class="right">senior</li>
                                        </ul>												
                                    </div>
                                </xsl:for-each>

                            </div>

                        </div>
                    </div>


                    <div class="col_3 omega">
                        <div id="RELEVANTE_WERKERVARING" class="box">
                            <h1>RELEVANTE WERKERVARING</h1>
                            <div class="titel">
                                <h3>
                                    <xsl:value-of select="Employee/PrimaryWorkingExperience/FunctionEmployer"/>
                                </h3>
                                <h3>
                                    <xsl:value-of select="Employee/PrimaryWorkingExperience/Duration"/>
                                </h3>
                            </div>
                            <div class="content">
                                <div id="situatie">
                                    <h3 class="alpha grid_3 subtitel">situatie</h3>
                                    <p class="prefix_3">
                                        <xsl:value-of
                                            select="Employee/PrimaryWorkingExperience/Situation"/>
                                    </p>
                                </div>					
                                <div id="taken">
                                    <h3 class="alpha grid_3 subtitel">taken</h3>
                                    <p class="prefix_3">
                                        <xsl:value-of
                                            select="Employee/PrimaryWorkingExperience/Tasks"/>
                                    </p>
                                </div>					
                                <div id="resultaat">
                                    <h3 class="alpha grid_3 subtitel">resultaat</h3>
                                    <p class="prefix_3">
                                        <xsl:value-of
                                            select="Employee/PrimaryWorkingExperience/Results"/>
                                    </p>
                                </div>					
                            </div>
                        </div>

                        <div id="OVERIGE_WERKERVARING" class="box">
                            <h1>OVERIGE WERKERVARING</h1>
                            <div>
                                <div class="titel">
                                    <h3>
                                        <xsl:value-of select="Employee/OtherWorkingExperience/WorkingExperience1/FunctionEmployer"/>
                                    </h3>
                                    <h3>
                                        <xsl:value-of select="Employee/OtherWorkingExperience/WorkingExperience1/Duration"/>
                                    </h3>
                                </div>
                                <div class="content">
                                    <p>
                                        <xsl:value-of select="Employee/OtherWorkingExperience/WorkingExperience1/Description"/>
                                    </p>
                                </div>
                            </div>
                            <div>
                                <div class="titel">
                                    <h3>
                                        <xsl:value-of select="Employee/OtherWorkingExperience/WorkingExperience2/FunctionEmployer"/>
                                    </h3>
                                    <h3>
                                        <xsl:value-of select="Employee/OtherWorkingExperience/WorkingExperience2/Duration"/>
                                    </h3>
                                </div>
                                <div class="content">
                                    <p>
                                        <xsl:value-of select="Employee/OtherWorkingExperience/WorkingExperience2/Description"/>
                                    </p>
                                </div>
                            </div>
                            <div>
                                <div class="titel">
                                    <h3>
                                        <xsl:value-of select="Employee/OtherWorkingExperience/WorkingExperience3/FunctionEmployer"/>
                                    </h3>
                                    <h3>
                                        <xsl:value-of select="Employee/OtherWorkingExperience/WorkingExperience3/Duration"/>
                                    </h3>
                                </div>
                                <div class="content">
                                    <p class="last">
                                        <xsl:value-of select="Employee/OtherWorkingExperience/WorkingExperience3/Description"/>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </body>

        </html>
    </xsl:template>
</xsl:stylesheet>