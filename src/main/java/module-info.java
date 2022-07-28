module com.visualsmic.vshuellassocket {
    requires javafx.controls;
    requires javafx.fxml;    
    requires java.base;
    requires java.net.http;
    requires com.google.gson;
    requires org.json;
    requires socket.io.client;
    requires engine.io.client;
    requires dpotapi;
    requires java.desktop;
    requires java.logging;

    opens com.visualsmic.vshuellassocket to javafx.fxml;
    exports com.visualsmic.vshuellassocket;
}
