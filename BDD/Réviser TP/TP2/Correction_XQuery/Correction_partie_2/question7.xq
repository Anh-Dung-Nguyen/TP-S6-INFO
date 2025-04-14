<encherisseurs_fous>
{for $p in doc("personnes.xml")//perso_tuple
 where every $art in doc("objets.xml")//obj_tuple satisfies
  some $e in doc("encheres.xml")//ench_tuple satisfies
   ($art/noobj=$e/noobj and $p/idperso=$e/idperso)
return $p/nom
}
</encherisseurs_fous>