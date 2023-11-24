module com.example.busquedadeltesoro {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.busquedadeltesoro to javafx.fxml;
    exports com.example.busquedadeltesoro;
    exports Screens;
    opens Screens to javafx.fxml;
    exports Graphs;
    opens Graphs to javafx.fxml;
}