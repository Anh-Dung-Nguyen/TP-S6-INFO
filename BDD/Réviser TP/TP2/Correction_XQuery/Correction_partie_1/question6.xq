<resultats>
{for $t in distinct-values(doc("refbib.xml")//titre)
 let $p:=doc("refbib.xml")//livre[titre=$t]/prix
 return
   <prixmin titre="{$t}">
     <prix>{min($p)}</prix>
   </prixmin>
}
</resultats>