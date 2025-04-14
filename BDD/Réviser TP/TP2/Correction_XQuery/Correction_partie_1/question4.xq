<tri>
{for $livre in doc("refbib.xml")/bib/livre
 order by $livre/titre ascending
 return <livre>{($livre)}</livre>
}
</tri>