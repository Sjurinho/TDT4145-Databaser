# Dokumentasjon - Databaseprosjekt

## Klasser

### RegistrerKontroller
 - Har ansvaret for registrering av tabellene *Apparater*, *Øvelser*, *Treningsøkter* og *Notat*, spesialiseringene *Apparatøvelse* og *FriØvelse*, samt registrering av relasjonstabellene *Treningsøktøvelse* og 
### ConnectController
- Tar seg av registrering og uthenting av info fra *Øvelsesgruppe* og relasjonsdatabasen *Øvelsesgruppetilhørighet*.
### Oktinfo
- Tar seg av spørringen om et antall n siste gjennomførte økter med tilhørende notater.
### Rekord
- Tar seg av spørringen om rekord i en gitt øvelse.
### Resultatlogg
- Tar seg av spørringen for resultatloggen i et gitt tidsintervall
### DBConn
- Abstrahert klasse for å koble seg opp mot databasen
### Meny
- Tar seg av brukergrensesnittet

## Use cases
1. Realisert ved å lagre informasjon om entitene i mysql tabellen via en connection. Også satt inn i relasjonstabeller.
2. Realisert ved en spørring der *Treningsøkter* left outer joines med *Notat* på **ØktID**. Derette sorteres resultatet synkende, og de ti første elementene skrives ut til brukeren. Brukeren kan velge n ved å skrive til konsoll. Utskrift til konsoll i pent format.
3. Ved en spørring join på **ØktID** sammenlignes mellom *Treningsøktøvelse* og *Treningsøkt*, etterfulgt av en join på **Øvelsesnavn** mellom *Treningsøktøvelse* og *Øvelse*, før det gjennomføres en sjekk om brukerens ønskede øvelse samsvarer med **Øvelsesnavn** og om den ligger innen ønsket tidsintervall.
4. Lager *Øvelsesgrupper* gjennom __RegistrerOvelsegruppe__ og gjennomfører en spørring der det *Øvelsegruppetilhørighet* joines med *Øvelse* og *Øvelsegruppe* på **Øvelsenavn** og **Øvelsegruppenavn**
5. Finner personlig rekord ved en spørring der **AntallKilo** hentes ut fra **Apparatøvelse**, for så å sortere i synkende rekkefølge og hente ut det første elementet.