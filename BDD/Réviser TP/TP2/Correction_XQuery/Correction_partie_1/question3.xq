<biblio>
{for $b in doc("refbib.xml")/bib/livre
 where $b/edition="Addison-Wesley" and $b/@annee>1992
 return
  <livre annee="{$b/@annee}">
   {$b/titre}
  </livre>
}
</biblio>