<resultats>
{for $article in doc("objets.xml")//obj_tuple
 let $e:=doc("encheres.xml")//ench_tuple[noobj=$article/noobj]
   where contains($article/description, "Velo") or contains($article/description, "velo")
   order by $article/noobj
   return
     <article>
      {$article/noobj}
      {$article/description}
      <nb_enchere>{count($e/ench)}</nb_enchere>
      <enchere_max>{max($e/ench)}</enchere_max>
     </article>
}
</resultats>