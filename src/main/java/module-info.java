module main.Main {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.demo2 to javafx.fxml;
    exports main;
}