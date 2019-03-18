insert into Regissør(RegissørID, Navn)
values (1, "Peyton Reed");
insert into Regissør(RegissørID, Navn)
values (2, "Tom Shaydac");
insert into Film(FilmID, Title, Produksjonsår, RegissørID)
values (1, "Yes Man", 2008, 1);
insert into Skuespiller(SkuespillerID, Navn, Fødselsår)
values (1, "Jim Carrey", 1962);
insert into SkuespillerIFilm (FilmID, SkuespillerID, Rolle)
values (1, 1, "Carl");

update Skuespiller
set Navn = "James Eugene Carrey"
where SkuespillerID = 1; 

delete from Regissør
where Navn = "Tom Shaydac";
