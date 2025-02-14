package hotelmanagementsystem.infrastructure.persistence.dao.implementation;

public class DataAccessException extends RuntimeException
{
	public DataAccessException(String message, Throwable cause )
	{
		super( message, cause );
	}
}
