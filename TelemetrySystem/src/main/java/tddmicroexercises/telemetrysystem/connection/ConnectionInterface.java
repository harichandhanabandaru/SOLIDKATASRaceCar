package tddmicroexercises.telemetrysystem.connection;
public interface ConnectionInterface {

    void connect(String telemetryServerConnectionString);
    void disconnect();
    boolean getOnlineStatus();
}
