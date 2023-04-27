package tddmicroexercises.telemetrysystem.controls;
public interface DiagnosticControls {
    String getDiagnosticInfo();
    void setDiagnosticInfo(String diagnosticInfo);
    void checkTransmission() throws Exception;
}
