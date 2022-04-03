module javafx.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    opens javafx.javafx to javafx.fxml;
    exports javafx.javafx;
    exports javafx.javafx.gui;
    opens javafx.javafx.gui to javafx.fxml;

    opens javafx.javafx.entities;
    opens javafx.javafx.model.services;
}