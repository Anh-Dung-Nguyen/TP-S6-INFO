<resultats>
{for $aut in distinct-values(doc("refbib.xml")//auteur)
 return
   <auteur>
     {$aut}
     {for $liv in doc("refbib.xml")/bib/livre
      where $liv/auteur=$aut
      return $liv/titre
     }
   </auteur>
}
</resultats>