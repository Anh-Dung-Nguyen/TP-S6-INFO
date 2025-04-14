<vente>
{for $article in doc("objets.xml")/objets/obj_tuple,
     $utilisateur in doc("personnes.xml")/personnes/perso_tuple
 where some $enchere in doc("encheres.xml")/encheres/ench_tuple
            satisfies ($article/noobj = $enchere/noobj and
                       $article/prix_de_reserve < $enchere/ench)
       and $utilisateur/idperso = $article/propose_par
 return <article>
          {$article/description}
          {$utilisateur/nom}
          {$article/date_fin}
        </article>
}
</vente>