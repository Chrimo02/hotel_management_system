package hotelmanagementsystem.infrastructure.api.grpc.impl;

import io.grpc.netty.NettyServerBuilder;

import java.io.IOException;

public class Server
{
	private final io.grpc.Server server;

	public Server(final int port )
	{
		this.server = NettyServerBuilder.forPort( port )
									//	.addService( new TaskManagementServiceImpl( ) ) //TODO:CHANGE
										.build( );
	}

	public static void main( final String[] args ) throws Exception
	{
		final Server server = new Server( 8888 );
		server.start( );
		System.out.println( "Server running ..." );
		server.blockUntilShutdown( );
	}

	public void start( ) throws IOException
	{
		server.start( );

		Runtime.getRuntime( ).addShutdownHook( new Thread( )
		{

			@Override
			public void run( )
			{
				System.err.println( "Shutting down gRPC server since JVM is shutting down" );
				Server.this.stop( );
				System.err.println( "Server shut down" );
			}
		} );
	}

	public void stop( )
	{
		if ( server != null )
		{
			server.shutdown( );
		}
	}

	private void blockUntilShutdown( ) throws InterruptedException
	{
		if ( server != null )
		{
			server.awaitTermination( );
		}
	}
}
