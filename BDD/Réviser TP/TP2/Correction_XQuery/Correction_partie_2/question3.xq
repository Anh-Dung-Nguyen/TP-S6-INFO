<resultats>
{for $article in doc("objets.xml")//obj_tuple
   where contains($article/description, "Velo") or contains($article/description, "velo")
   order by $article/noobj
   return
     <article>
      {$article/noobj}
      {$article/description}
     </article>
}
</resultats>