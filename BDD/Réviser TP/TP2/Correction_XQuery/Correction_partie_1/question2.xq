<auteurs>
{for $l in doc("refbib.xml")/bib/livre
 return
  for $a in $l//auteur
   return <nom>{$a/prenom/text(),$a/nom/text()}</nom>
}
</auteurs>