package io.github.zy31415.boozoo.database;

import io.github.zy31415.boozoo.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.tools.Server;

import java.nio.file.Paths;
import java.sql.SQLException;

public class DatabaseServer {

    private DatabaseServer() {
    }

    private static final String BaseDir = Paths.get(Configuration.BOOZOOROOT, "db").toString();

    private static final Logger logger = LogManager.getLogger();

    private static final Server server = createTcpServer();
    private static int port = -1;

    private static Server createTcpServer() {
        try {
            Server server = Server.createTcpServer(
                    "-baseDir", BaseDir);
            logger.debug("Server status: {}", server.getStatus());
            return server;
        } catch (SQLException ex) {
            throw new RuntimeException("Cannot create h2 tcp server.", ex);
        }
    }

    public static void start() throws SQLException {
        server.start();
        logger.debug("Server status: {}", server.getStatus());
        port = server.getPort();
    }

    public static void stop() {
        if (null != server) {
            server.stop();
            logger.debug("Server status: {}", server.getStatus());
        } else {
            logger.warn("Database server has not been started.");
        }
    }

    public static int getPort() {
        if (null != server) {
            return port;
        } else {
            logger.warn("Database server has not been started.");
            return -1;
        }
    }
}
