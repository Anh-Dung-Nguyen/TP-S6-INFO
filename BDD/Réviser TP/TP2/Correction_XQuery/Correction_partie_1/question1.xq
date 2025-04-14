for $f in doc("refbib.xml")/bib/livre
 where $f/titre="Data on the Web"
 return
  for $a in $f//auteur
   return ($a/prenom,$a/nom)
