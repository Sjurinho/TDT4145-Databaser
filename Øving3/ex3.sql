-- Create a new database called 'Film_ex3a'
-- Connect to the 'master' database to run this snippet
USE master
GO
-- Create the new database if it does not exist already
IF NOT EXISTS (
    SELECT name
        FROM sys.databases
        WHERE name = N'Film_ex3a'
)
CREATE DATABASE Film_ex3a
GO

----------------------------------------------------------

CREATE TABLE Film (
	FilmID INTEGER NOT NULL,
    Title VARCHAR(20),
    Produksjonsår INTEGER,
    RegissørID INTEGER,
    CONSTRAINT Film_PK PRIMARY KEY (FilmID),
    CONSTRAINT Film_FK FOREIGN KEY (RegissørID) REFERENCES Regissør(RegissørID)
);
CREATE TABLE Regissør (
    RegissørID INTEGER NOT NULL,
    Navn VARCHAR(30),
    CONSTRAINT Regissør_PK PRIMARY KEY (RegissørID)
);

CREATE TABLE Skuespiller (
    SkuespillerID INTEGER NOT NULL,
    Navn VARCHAR(30),
    Fødselsår INTEGER,
    CONSTRAINT Skuespiller_PK PRIMARY KEY (SkuespillerID)
);

CREATE TABLE Sjanger(
    SjangerID INTEGER NOT NULL,
    Navn VARCHAR(30),
    Beskrivelse VARCHAR(30),
    CONSTRAINT Sjanger_PK PRIMARY KEY (SjangerID)
);

CREATE TABLE SkuespillerIFilm (
    FilmID INTEGER NOT NULL,
    SkuespillerID INTEGER NOT NULL,
    Rolle VARCHAR(30),
    CONSTRAINT SkuespillerIFilm_PK PRIMARY KEY (SkuespillerID, FilmID),
    CONSTRAINT SkuespillerIFilm_FK1 FOREIGN KEY (SkuespillerID) REFERENCES Skuespiller(SkuespillerID),
    CONSTRAINT SkuespillerIFilm_FK2 FOREIGN KEY (FilmID) REFERENCES Film(FilmID)
                                            ON UPDATE CASCADE
                                            ON DELETE CASCADE
);

CREATE TABLE SjangerForFilm(
    FilmID INTEGER NOT NULL,
    SjangerID INTEGER NOT NULL,
    CONSTRAINT SjangerForFilm_PK PRIMARY KEY (FilmID, SjangerID),
    CONSTRAINT SjangerForFilm_FK1 FOREIGN KEY (SjangerID) REFERENCES Sjanger(SjangerID),
    CONSTRAINT SjangerForFilm_FK2 FOREIGN KEY (FilmID) REFERENCES Film(FilmID)
                                                       ON UPDATE CASCADE
                                                       ON DELETE CASCADE 
);

