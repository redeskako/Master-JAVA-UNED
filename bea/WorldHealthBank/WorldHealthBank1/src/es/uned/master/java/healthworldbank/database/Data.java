package es.uned.master.java.healthworldbank.database;

/**
 * Anotaciones para campos de la tabla DATA 
 * 
 * @author grupo alef (Juan Sánchez)
 * @date 5/4/2012
 *
 */
@DBTable(name="DATA", primaryKey={"indicator_code", "country_code", "year"})
@DBIndex(name="INDEX_YEAR", index = {"YEAR"})
public class Data {
	@SQLString(value = 20, constraints = @Constraints(allowNull = false)) String indicator_code;
	@SQLString(value = 3, constraints = @Constraints(allowNull = false)) String country_code;
	@SQLInteger(constraints = @Constraints(allowNull = false)) int year;
	@SQLDouble(constraints = @Constraints(allowNull = false)) Float percentage;
}
