module com.raymedis.ellipse {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.raymedis.ellipse to javafx.fxml;
    exports com.raymedis.ellipse;
    exports com.raymedis.ellipse.AnnotationsWrapper;
    opens com.raymedis.ellipse.AnnotationsWrapper to javafx.fxml;
}