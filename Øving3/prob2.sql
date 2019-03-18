select * from Film;

select Navn from Skuespiller
where Fødselsår > 1960;

select Navn from Skuespiller
Where Fødselsår >= 1980 and Fødselsår < 1990
order by Navn asc;

select f.Title, sif.Rolle
from Film f, SkuespillerIFilm sif, Skuespiller s
where sif.SkuespillerID = s.SkuespillerID and s.Navn = "Morgan Freeman" and f.FilmID = sif.FilmID;

select f.Title
from Film f, Regissør r, Skuespiller s, SkuespillerIFilm sif
where f.RegissørID = r.RegissørID and r.Navn = s.Navn and sif.FilmID = f.FilmID and s.SkuespillerID = sif.SkuespillerID;

select Count(*) as AntallSkuespillereMedNavnPåC
from Skuespiller
where Navn like "C%";

select s.Navn, Count(s.Navn) as AntallFilmer
from Sjanger s, SjangerForFilm sff, Film f
where s.SjangerID = sff.SjangerID and sff.FilmID = f.FilmID
group by s.Navn;

select s.Navn
from Skuespiller s, SkuespillerIFilm sif, Film f
where sif.FilmID = f.FilmID and sif.SkuespillerID = s.SkuespillerID and f.Title = "Ace Ventura: Pet Detective" 
and not s.Navn in
(select s.Navn
from Skuespiller s, SkuespillerIFilm sif, Film f
where sif.FilmID = f.FilmID and sif.SkuespillerID = s.SkuespillerID and f.Title = "Ace Ventura: When Nature Calls");

SELECT Title, Film.FilmId, AVG(Fødselsår) as Avg
FROM Film JOIN SkuespillerIFilm ON Film.FilmID = SkuespillerIFilm.FilmID JOIN
Skuespiller ON Skuespiller.SkuespillerID = SkuespillerIFilm.SkuespillerID
GROUP BY Title, FilmID
HAVING Avg > (
SELECT AVG(Fødselsår) FROM Skuespiller);
