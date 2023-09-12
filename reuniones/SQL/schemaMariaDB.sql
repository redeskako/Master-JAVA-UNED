CREATE TABLE reunion (
	id INTEGER(10)) PRIMARY KEY,
	asunto VARCHAR(128) NOT NULL,
	fecha DATETIME NOT NULL,
	UNIQUE (ASUNTO)
);

CREATE TABLE persona (
	id BIGSERIAL PRIMARY KEY,
	nombre VARCHAR(32) NOT NULL,
	apellidos VARCHAR(32) NOT NULL
	UNIQUE (nombre, apellidos)
);

CREATE TABLE asistente (
	reunion_id INTEGER(10),
	persona_id INTEGER(10),
	PRIMARY KEY (reunion_id, persona_id),
	FOREIGN KEY (reunion_id) REFERENCES reunion(id),
	FOREIGN KEY (persona_id) REFERENCES persona(id)
)
