<bibli>
{for $l in doc("refbib.xml")//livre
 where count($l/auteur) >0
 return
   <livre>
     {$l/titre}
     {for $a in $l/auteur[position()<=2]
      return $a
     }
     {if (count($l/auteur)>2)
       then <et-al/>
       else ()
     }
   </livre>
}
</bibli>