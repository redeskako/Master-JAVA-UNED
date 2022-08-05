CREATE TABLE Anotacion(
code integer primary key,
source_note varchar(256) not null);
CREATE TABLE AnotacionOrganizacion(
code_anotacion integer,
code_organizacion integer
,constraint pk_anotaorganiza primary key(code_anotacion,code_organizacion),
constraint fk_anotaorganiza_anota foreign key(code_anotacion) references Anotacion(code),
constraint fk_anotaorganiza_organiza foreign key(code_organizacion) references Organizacion(code));
CREATE TABLE [Estadistica] (
[country_code] varchar(3)  NULL,
[indicator_code] varchar(100)  NULL,
[valor] FLOAT  NOT NULL,
[year] integer  NULL,
PRIMARY KEY ([country_code],[indicator_code],[year])
);
CREATE TABLE Indicador(
indicator_code varchar(100) primary key,
indicator_name varchar(256) not null);
CREATE TABLE IndicadorAnotacion(
indicator_code varchar(100),
note_code integer,
constraint pk_indicadoranotacion primary key(indicator_code,note_code),
constraint fk_indicadoranotacion_indicador foreign key(indicator_code) references Indicador(indicator_code),
constraint fk_indicadoranotacion_anotacion foreign key(note_code) references Anotacion(code));
CREATE TABLE IndicadorPais(
country_code varchar(3),
indicator_code varchar(100),
constraint pk_indicadorPais primary key(country_code,indicator_code),
constraint fk_indicadorPais_Pais foreign key(country_code) references Pais(country_code),
constraint fk_indicadorPais_Indicador foreign key(indicator_code) references Indicador(indicator_code)
);
CREATE TABLE Organizacion(
code integer primary key,
source_organization varchar(256) not null);
CREATE TABLE Pais(
country_code varchar(3) primary key,
country_name varchar(150) not null);
CREATE INDEX [IDX_ANOTACIONORGANIZACION_PK] ON [AnotacionOrganizacion](
[code_anotacion]  ASC,
[code_organizacion]  ASC
);
CREATE INDEX [IDX_ANOTACION_CODE] ON [Anotacion](
[code]  ASC
);
CREATE INDEX [IDX_ESTADISTICA_COUNTRY_CODE] ON [Estadistica](
[country_code]  ASC
);
CREATE INDEX [IDX_ESTADISTICA_INDICATOR_CODE] ON [Estadistica](
[indicator_code]  ASC
);
CREATE INDEX [IDX_ESTADISTICA_PK] ON [Estadistica](
[country_code]  ASC,
[indicator_code]  ASC,
[year]  ASC
);
CREATE INDEX [IDX_ESTADISTICA_YEAR] ON [Estadistica](
[year]  ASC
);
CREATE INDEX [IDX_INDICADORANOTACION_PK] ON [IndicadorAnotacion](
[indicator_code]  ASC,
[note_code]  ASC
);
CREATE INDEX [IDX_INDICADORPAIS_PK] ON [IndicadorPais](
[country_code]  ASC,
[indicator_code]  ASC
);
CREATE INDEX [IDX_INDICADORPAIS_Pais] ON [IndicadorPais](
[country_code]  ASC
);
CREATE INDEX [IDX_INDICADORPAIS_indicador] ON [IndicadorPais](
[indicator_code]  ASC
);
CREATE INDEX [IDX_INDICADOR_INDICATOR_CODE] ON [Indicador](
[indicator_code]  ASC
);
CREATE INDEX [IDX_ORGANIZACION_CODE] ON [Organizacion](
[code]  ASC
);
CREATE INDEX [IDX_PAIS_COUNTRY_CODE] ON [Pais](
[country_code]  ASC
);
