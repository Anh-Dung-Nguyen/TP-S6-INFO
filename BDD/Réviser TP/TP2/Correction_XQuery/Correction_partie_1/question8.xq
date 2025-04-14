<bibl>
{for $b1 in doc("refbib.xml")//livre,
     $b2 in doc("refbib.xml")//livre
 let $aut1:=for $a in $b1/auteur
            order by $a/nom, $a/prenom
            return $a
 let $aut2:=for $a in $b2/auteur
            order by $a/nom, $a/prenom
            return $a
 where $b1<<$b2 and not($b1/titre=$b2/titre) and deep-equal($aut1,$aut2)
 return
   <paire-de-livres>
     {$b1/titre}
     {$b2/titre}
   </paire-de-livres>
}
</bibl>