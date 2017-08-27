<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h1 align="center">Tennis Counter App</h1>
<h4 align="center"> by Ali Touat</h4>
<hr>
<h2 align="center"> qui "GAGNE"  le point?</h2>

<table border='30' align="center">
    <thead>
        <tr>
            <th>Joueur 1</th>
            <th>Joueur 2</th>
        </tr>
    </thead>
    <tbody>
    <FORM action="FrontControleur" method="POST" accept-charset="UTF-8">
        <tr>
            <td align='center'><input type="submit" name="jo1" value="gagne" /></td>
            <td align='center'><input type="submit" name="jo2" value="gagne" /></td>
        </tr>
        <input type="hidden" name="scJo1" value="${scJo1}">
        <input type="hidden" name="scJo2" value="${scJo2}">
        <input type="hidden" name="egAvJo1" value="${egAv1}">
        <input type="hidden" name="egAvJo2" value="${egAv2}">
        <input type="hidden" name="setJo1" value="${setJo1}">
        <input type="hidden" name="setJo2" value="${setJo2}">
        <input type="hidden" name="tbJo1" value="${tbJo1}">
        <input type="hidden" name="tbJo2" value="${tbJo2}">
        <input type="hidden" name="manchJo1" value="${manchJo1}">
        <input type="hidden" name="manchJo2" value="${manchJo2}">



        <tr>
            <td>Score : ${scJo1}</td>
            <td>Score : ${scJo2}</td>
        </tr>
        <tr>
            <td>${egAv1}</td>
            <td>${egAv2}</td>
        </tr>
        <tr>
            <td>Set : ${setJo1}</td>
            <td>Set : ${setJo2}</td>
        </tr>
        <tr>
            <td>Tie-Break : ${tbJo1}</td>
            <td>Tie-Break : ${tbJo2}</td>
        </tr>
        <tr>
            <td>Manche : ${manchJo1}</td>
            <td>Manche : ${manchJo2}</td>
        </tr>
    </FORM> 
</tbody>
</table>
        <p></p>
        <h5 align="center">
            <p>Application développée en Java 8, x-tiers et MVC1. </p>
            <p>Une version MVC2 est envisageable.</p>
            <p>Possibilité de persister les manches & les matches (script SQL fourni à la demande)</p>
            <p>Design CSS fourni à la demande</p>
            <P>Le code a été décommenté. Une version avec commentaires peut être fournie</p>
            <p>Cordialement, Ali Touat</p></h5>

