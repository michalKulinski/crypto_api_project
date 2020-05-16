package connection;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.component.AbstractLifeCycle;
import org.eclipse.jetty.webapp.*;

public class ServerConnection {
    public static void serverStart() {
        WebAppContext webapp = new WebAppContext();
        webapp.setResourceBase("/crypto_api_project/src/main/webapp");
        webapp.setContextPath("/");
        webapp.setConfigurations(new Configuration[]
                {
                        new AnnotationConfiguration(),
                        new WebInfConfiguration(),
                        new WebXmlConfiguration(),
                        new MetaInfConfiguration(),
                        new FragmentConfiguration(),
                        new EnvConfiguration(),
                        new PlusConfiguration(),
                        new JettyWebXmlConfiguration()
                });
        webapp.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", ".*/classes/.*");

        //        webapp.addServlet(HelloServlet.class, "/api/*");
        Server server = new Server(8080);
        server.setHandler(webapp);

        server.addLifeCycleListener(new AbstractLifeCycle.AbstractLifeCycleListener() {

        });
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
