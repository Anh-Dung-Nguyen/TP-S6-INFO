<boutique>
{for $utilisateur in doc("personnes.xml")//perso_tuple
 return <vendeur>
          {$utilisateur/nom}
          {for $article in doc("objets.xml")//obj_tuple
           where $utilisateur/idperso = $article/propose_par
           return $article/description}
        </vendeur>
}
</boutique>