<resultat>
{for $p in doc("personnes.xml")//perso_tuple,
     $o in doc("objets.xml")//obj_tuple
 where $p/confiance>"C"
      and $o/prix_de_reserve>1000
      and $o/propose_par=$p/idperso
 return
   <cas_particulier>
     {$p/nom}
     {$p/confiance}
     {$o/description}
     {$o/prix_de_reserve}
   </cas_particulier>
}
</resultat>
