
    create table Treningsokt (
		OktID integer not null,
        Dato date,
        Tidspunkt timestamp,
        Varighet time,
        PersonligForm integer,
        Prestasjon integer,
        primary key(OktID)
    );
    
    create table Notat (
		OktID integer not null,
        NotatID integer not null,
        Treningsformal varchar(500),
        primary key(OktID,NotatID),
        foreign key(OktID) references Treningsokt(OktID)
								on update cascade
                                on delete cascade
    );
    
    create table Apparat(
		ApparatID integer not null,
        Apparatnavn varchar(40),
        Apparatbeskrivelse varchar(500),
        primary key(ApparatID)
    );
    
    create table Ovelse(
		Ovelsesnavn varchar(40) not null,
        primary key(Ovelsesnavn)
    );
    
    create table Apparatovelse(
		Ovelsesnavn varchar(40) not null,
        ApparatID integer not null,
        primary key(Ovelsesnavn),
        foreign key(Ovelsesnavn) references Ovelse(Ovelsesnavn)
								on update cascade
                                on delete cascade,
		foreign key(ApparatID) references Apparat(ApparatID)
								on update cascade
                                on delete no action
    );
    create table Friovelse(
		Ovelsesnavn varchar(40) not null,
        Ovelsesbeskrivelse varchar(500),
        primary key(Ovelsesnavn),
        foreign key(Ovelsesnavn) references Ovelse(Ovelsesnavn)
								on update cascade
                                on delete cascade
    );
    
    create table Ovelsegruppe(
		Ovelsegruppenavn varchar(40) not null,
        primary key(Ovelsegruppenavn)
    );
    
    create table Ovelsegruppetilhorighet(
		Ovelsegruppenavn varchar(40) not null,
        Ovelsesnavn varchar(40) not null,
        primary key(Ovelsegruppenavn,Ovelsesnavn),
        foreign key(Ovelsegruppenavn) references Ovelsegruppe(Ovelsegruppenavn)
								on update cascade
                                on delete cascade,
		foreign key(Ovelsesnavn) references Ovelse(Ovelsesnavn)
								on update cascade
                                on delete cascade
    );
    
    create table Treningsoktovelse(
		OktID integer not null,
        AntallSett integer,
        AntallKilo integer,
        Ovelsesnavn varchar(40) not null,
        primary key(OktID,Ovelsesnavn),
        foreign key(OktID) references Treningsokt(OktID)
								on update cascade
                                on delete cascade,
		foreign key(Ovelsesnavn) references Ovelse(Ovelsesnavn)
								on update cascade
                                on delete cascade
    );
    
    