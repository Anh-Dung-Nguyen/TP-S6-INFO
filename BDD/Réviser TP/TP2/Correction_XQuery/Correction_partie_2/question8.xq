<resul>
{for $o in distinct-values(doc("encheres.xml")//noobj)
 let $e:=doc("encheres.xml")//ench_tuple[noobj=$o]
 let $moy:=avg($e/ench)
 where count($e)>=3
 order by $moy descending
 return
   <objet_apprecie>
     <num_obj>{$o}</num_obj>
     <moy_ench>{$moy}</moy_ench>
   </objet_apprecie>
}
</resul>