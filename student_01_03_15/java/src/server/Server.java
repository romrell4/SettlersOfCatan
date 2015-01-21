package server;

import java.io.IOException;
import java.net.InetSocketAddress;

import server.handlers.LoginHandler;

import com.sun.net.httpserver.HttpServer;

public class Server implements IServer 
{
	private HttpServer server;
	private static int portNumber;
	private String host = "localhost";
	
	public static void main(String[] args)
	{
		if(args.length == 0)
		{
			portNumber = 8898;
		}
		else
		{
			portNumber = Integer.parseInt(args[0]);
		}
		new Server().run();
	}
	
	protected void run()
	{
		try 
		{
			server = HttpServer.create(new InetSocketAddress(portNumber),10);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		server.setExecutor(null);
		
		server.createContext("/user/login",new LoginHandler());
		
		server.start();
	}

	@Override
	public int getPortNumber() 
	{
		return portNumber;
	}

	@Override
	public String getHost()
	{
		return host;
	}
}