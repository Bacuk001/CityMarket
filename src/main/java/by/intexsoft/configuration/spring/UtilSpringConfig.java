package by.intexsoft.configuration.spring;

public class UtilSpringConfig {
	private static UtilSpringConfig singltoneSpringConfig;
	/**
	 * Getting the id from the database.
	 */
	public final String PROPERTY_GENERATOR = "hibernate.id.new_generator_mappings";
	/**
	 * Parameter which for translation of requests to the database.
	 */
	public final String PROPERTY_SHOW_SQL = "hibernate.show_sql";
	/**
	 * The parameter specifies what to do with the database with each new
	 * connection. If the value is "create" then the databases will be deleted and
	 * overwritten, if "update", the tables in the database will be changed, but the
	 * information in the database will be preserved.
	 */
	public final String PROPERTY_DRIVE_DATA_BASE = "hibernate.hbm2ddl.auto";
	/**
	 * A parameter indicating which dialect accepts database queries.
	 */
	public final String PROPERTY_DIALECT = "hibernate.dialect";
	/**
	 * Password to access the database.
	 */
	public final String PASSWORD = "jdbc.password";
	/**
	 * Username to access the database.
	 */
	public final String USER_NAME = "jdbc.username";
	/**
	 * Database in which data tables are stored.
	 */
	public final String DATABASE = "jdbc.url";
	/**
	 * The driver by which it interacts with the database.
	 */
	public final String DRIVER = "jdbc.driverClassName";
	/**
	 * The package in which the database tables are located.
	 */
	public final String MODELS = "by.intexsoft.entity";

	public static UtilSpringConfig singleton() {
		if (singltoneSpringConfig == null)
			return new UtilSpringConfig();
		return singltoneSpringConfig;
	}
}
