package tddmicroexercises.telemetrysystem.controls;

import tddmicroexercises.telemetrysystem.communication.TelemetryMessage;
import tddmicroexercises.telemetrysystem.connection.TelemetryConnection;

public class TelemetryDiagnosticControls implements DiagnosticControls {

    private final String DiagnosticChannelConnectionString = "*111#";

    private final TelemetryMessage telemetryMessage;
    private final TelemetryConnection telemetryConnection;

    private String diagnosticInfo = "";

    public TelemetryDiagnosticControls()
    {

        telemetryMessage= new TelemetryMessage();
        telemetryConnection= new TelemetryConnection();
    }
    @Override
    public String getDiagnosticInfo() {
        return diagnosticInfo;

    }

    @Override
    public void setDiagnosticInfo(String diagnosticInfo) {
        this.diagnosticInfo = diagnosticInfo;
    }

    @Override
    public void checkTransmission() throws Exception {

        diagnosticInfo = "";

        telemetryConnection.disconnect();

        int retryLeft = 3;
        while ( telemetryConnection.getOnlineStatus() == false && retryLeft > 0)
        {
            telemetryConnection.connect(DiagnosticChannelConnectionString);
            retryLeft -= 1;
        }

        if( telemetryConnection.getOnlineStatus() == false)
        {
            throw new Exception("Unable to connect.");
        }

        telemetryMessage.send(telemetryMessage.DIAGNOSTIC_MESSAGE);
        diagnosticInfo = telemetryMessage.receive();

    }
}
