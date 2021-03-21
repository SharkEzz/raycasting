module raycasting.main {
    requires java.base;
    requires javafx.controls;

    opens app;
    opens app.Raycasting;
    opens app.Raycasting.Utils;
}