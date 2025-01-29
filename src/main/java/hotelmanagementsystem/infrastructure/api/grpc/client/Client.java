package hotelmanagementsystem.infrastructure.api.grpc.client;

import hotelmanagementsystem.infrastructure.api.grpc.generated.CreateGuestRequest;
import hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse;
import hotelmanagementsystem.infrastructure.api.grpc.generated.GuestServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

public class Client
{
	private final ManagedChannel channel;
	private final GuestServiceGrpc.GuestServiceBlockingStub blockingStub;
	//private final TaskManagementServiceGrpc.TaskManagementServiceBlockingStub blockingStub; //TODO:Change

	public Client(final String host, final int port )
	{
		channel = ManagedChannelBuilder.forAddress( host, port ).usePlaintext( ).build( );

		//blockingStub = TaskManagementServiceGrpc.newBlockingStub( channel ); //TODO:Change

		blockingStub = GuestServiceGrpc.newBlockingStub(channel);

	}

	public static void main( final String[] args ) throws InterruptedException
	{
		final Client client = new Client( "localhost", 8888 );
		try
		{
			for (int i = 0; i<4; i++){
				System.out.println("test"+i);
			}

			CreateGuestRequest request = CreateGuestRequest.newBuilder().setFirstName("hans").setLastName("peter").setBirthday("2000-02-02").setTitle("walter").setEmail("aksdfjla").setPhoneNumber("akdfjasfja").build();
			GuestResponse response = client.blockingStub.createGuest(request);
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
