CREATE TABLE users (
    id INT NOT NULL AUTO_INCREMENT,
    nume VARCHAR(250) NOT NULL,
    email VARCHAR(250) NOT NULL,
    parola VARCHAR(250) NOT NULL,
    admin BOOLEAN NOT NULL DEFAULT FALSE,
    date_created DATE NOT NULL DEFAULT CURRENT_TIMESTAMP , PRIMARY KEY (id)
);

CREATE TABLE elev (
    id_elev INT NOT NULL AUTO_INCREMENT,
    nume VARCHAR(50) NOT NULL,
    prenume VARCHAR(50) NOT NULL,
    data_nasterii DATE NOT NULL,
    id_clasa INT NOT NULL,
    PRIMARY KEY (id_elev)
);

CREATE TABLE nota (
    id_nota INT NOT NULL AUTO_INCREMENT,
    id_elev INT NOT NULL,
    id_materie INT NOT NULL,
    semestru INT NOT NULL,
    valoare INT NOT NULL,
    data_notare DATE NOT NULL,
    PRIMARY KEY (id_nota)
);

CREATE TABLE corigenta (
    id_corigenta INT NOT NULL AUTO_INCREMENT,
    id_elev INT NOT NULL,
    id_materie INT NOT NULL,
    semestru INT NOT NULL,
    data_corigenta DATE NOT NULL,
    PRIMARY KEY (id_corigenta)
);

CREATE TABLE absenta (
    id_absenta INT NOT NULL AUTO_INCREMENT,
    id_elev INT NOT NULL,
    id_materie INT NOT NULL,
    semestru INT NOT NULL,
    data_absenta DATE NOT NULL,
    PRIMARY KEY (id_absenta)
);

CREATE TABLE bursa (
    id_bursa INT NOT NULL AUTO_INCREMENT,
    id_elev INT NOT NULL,
    id_tipbursa INT NOT NULL,
    semestru INT NOT NULL,
    PRIMARY KEY (id_bursa)
);

CREATE TABLE tipbursa (
    id_tipbursa INT NOT NULL AUTO_INCREMENT,
    denumire VARCHAR(50) NOT NULL,
    suma INT NOT NULL,
    PRIMARY KEY (id_tipbursa)
);

CREATE TABLE materie (
    id_materie INT NOT NULL AUTO_INCREMENT,
    nume_materie VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_materie)
);

CREATE TABLE clasa (
    id_clasa INT NOT NULL AUTO_INCREMENT,
    nume_clasa VARCHAR(5) NOT NULL,
    specializare VARCHAR(50) NOT NULL,
    extra VARCHAR(50) NULL DEFAULT NULL,
    id_diriginte INT NOT NULL,
    PRIMARY KEY (id_clasa)
);

CREATE TABLE profesor (
    id_profesor INT NOT NULL AUTO_INCREMENT,
    nume VARCHAR(50) NOT NULL,
    prenume VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_profesor)
);

CREATE TABLE profesormaterie (
    id_inregistrare INT NOT NULL AUTO_INCREMENT,
    id_profesor INT NOT NULL,
    id_materie INT NOT NULL,
    PRIMARY KEY (id_inregistrare)
);





