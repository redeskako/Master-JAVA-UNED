package es.uned.master.java.healthworldbank.database;

/**
 * Anotaciones para campos de la tabla HEALTH_INDICATOR
 * 
 * @author grupo alef (Juan Sánchez)
 * @date 5/4/2012
 *
 */
@DBTable(name="HEALTH_INDICATOR")
@DBIndex(name="INDEX_INDICATOR_NAME", index = {"INDICATOR_NAME"})
public class HealthIndicator {
	@SQLString(value = 20, constraints = @Constraints(primaryKey = true)) String indicator_code;
	@SQLString(value = 150, constraints = @Constraints(allowNull = false)) String indicator_name;
	@SQLString(600) String source_note;
	@SQLString(900) String source_organization;
}
