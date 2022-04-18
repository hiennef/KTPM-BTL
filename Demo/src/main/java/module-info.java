module com.mycompany.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens com.mycompany.demo to javafx.fxml;
    exports com.mycompany.demo;
    exports com.mycompany.pojo;
}
