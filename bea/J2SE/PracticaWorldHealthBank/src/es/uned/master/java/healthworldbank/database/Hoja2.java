package es.uned.master.java.healthworldbank.database;

/**
 * Anotaciones para campos de la tabla HOJA2
 * 
 * @author grupo alef (Juan Sánchez)
 * @date 5/4/2012
 *
 */
@DBTable(name="Hoja2")
public class Hoja2 {
	@SQLString(value = 20, constraints = @Constraints(allowNull = false)) String indicator_code;
	@SQLString(value = 150, constraints = @Constraints(allowNull = false)) String indicator_name;
	@SQLString(value = 600, constraints = @Constraints(allowNull = false)) String source_note;
	@SQLString(value = 900, constraints = @Constraints(allowNull = false)) String source_organization;
	
}

