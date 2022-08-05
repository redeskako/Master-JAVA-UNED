package es.uned.master.java.healthworldbank.database;

/**
 * Anotaciones para campos de la tabla COUNTRY
 * 
 * @author grupo alef (Juan Sánchez)
 * @date 5/4/2012
 *
 */
@DBTable(name="COUNTRY")
@DBIndex(name="INDEX_COUNTRY_NAME", index = {"COUNTRY_NAME"})
public class Country {
	@SQLString(value = 3, constraints = @Constraints(primaryKey = true)) String country_code;
	@SQLString(value = 50, constraints = @Constraints(allowNull = false)) String country_name;
}
