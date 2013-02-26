import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.webapp.WebAppContext;


/**
 * The main entrance of server
 * @author LIU Fangran
 *
 */
public class JettyMain {

	public static void main(String[] args) throws Exception {
		Server jettyServer = new Server();

		SocketConnector conn = new SocketConnector();
		conn.setPort(9111);
		jettyServer.setConnectors(new Connector[] { conn });

		WebAppContext webAppContext = new WebAppContext();
		webAppContext.setContextPath("/myJoke");
		webAppContext.setWar("src/main/webapp");
		jettyServer.setHandler(webAppContext);
		jettyServer.start();

	}

	
	
}
