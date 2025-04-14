let $tous:=doc("objets.xml")//obj_tuple[contains(description,"velo") or contains(description,"Velo") or contains(description,"Tricycle")]
let $ench:=doc("encheres.xml")//ench_tuple[noobj=$tous/noobj]
return
   <enchere_min>{min($ench/ench)}</enchere_min>