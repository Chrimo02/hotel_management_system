package hotelmanagementsystem.infrastructure.api.grpc.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

public class Client
{
	private final ManagedChannel channel;

	//private final TaskManagementServiceGrpc.TaskManagementServiceBlockingStub blockingStub; //TODO:Change

	public Client(final String host, final int port )
	{
		channel = ManagedChannelBuilder.forAddress( host, port ).usePlaintext( ).build( );

		//blockingStub = TaskManagementServiceGrpc.newBlockingStub( channel ); //TODO:Change
	}

	public static void main( final String[] args ) throws InterruptedException
	{
		final Client client = new Client( "localhost", 8888 );
		try
		{
			//TODO:Call methods
			/*client.createUser("Horst");
			client.getUser(1);*/
		}
		finally
		{
			client.shutdown( );
		}
	}

	public void shutdown( ) throws InterruptedException
	{
		channel.shutdown( ).awaitTermination( 5, TimeUnit.SECONDS );
	}

	//TODO:Implement Methods

	/*public void createUser(String name){
		CreateUser user = CreateUser.newBuilder().setName(name).build();
		try
		{
			final UserId response = blockingStub.createUser( user );
			System.out.println( "Created user at Id = " + response.getUserId() );
		}
		catch ( final StatusRuntimeException e )
		{
			return;
		}
	}

	public void getUser(long id){
		UserId userId = UserId.newBuilder().setUserId(id).build();
		try
		{
			final User response = blockingStub.getUser( userId );
			System.out.println( "User at Id = " + response.getId() + " is " + response.getName());
			System.out.println( response.getName() + " has following tasks " + response.getTasksList());
		}
		catch ( final StatusRuntimeException e )
		{
			if (e.getStatus().getCode() == Status.Code.NOT_FOUND) {
				System.out.println("Error: " + e.getStatus().getDescription());
			} else {
				System.err.println("An unexpected error occurred: " + e.getMessage());
			}
		}
	}*/

}
