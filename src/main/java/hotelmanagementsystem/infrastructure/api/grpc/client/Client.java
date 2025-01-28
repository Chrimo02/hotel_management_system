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



}
