# Øving 5

## Oppgave 1

### a)
Når det bare skal hentes ut hele tabeller, uten rene attributtsøk eller verdiområdesøk så er det lettest å bruke heapfiler, der man søker etter tabellen innenfor blokken der den er lagret

### b)
Når vi ønsker attributtspesifikke søk egner B+-trær bra. Med 4 nivåer vil vi ende med 4 blokkaksesserer for å hente ut dataene i tabellen. Derfor vil det være bedre i dette tilfellet å bruke statisk hashing, siden hashing egner seg bra for direkte aksess. I tillegg vil det kun krever 1.25 blokkaksesserer å slå opp, sammenlignet med B+-treet sine 4.

### c)
Når det i tillegg til direkte aksess på *Eksamennr* skal gjøres en sortering på *Studentnr*, så vil B+-treet der begge attributtene brukes som søkenøkler gi mindre ekstra utregning enn de øvrige alternativene. Man trenger bare å slå opp på *Eksamennr* og plukke ut *Studentnr* i synkende rekkefølge.

### d)
Når man kun skal sette inn verdier så egner heapfiler seg best, siden det ikke er noe krav til sortering i filen. Derfor gir dette minst unødvendig arbeid, man kan bare legge blocken til på slutten av filen.