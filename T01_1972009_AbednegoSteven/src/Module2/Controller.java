    package Module2;
    /**
     * @author Abednego Steven - 1972009
     */
    
    import javafx.event.ActionEvent;
    import javafx.scene.Scene;
    import javafx.scene.control.Alert;
    import javafx.scene.control.TextField;
    import javafx.scene.layout.TilePane;
    import javafx.stage.Stage;
    
    import java.text.DecimalFormat;
    import java.text.DecimalFormatSymbols;
    import java.util.Locale;
    
    public class Controller {
        public TextField length;
        public TextField width;
        public TextField radius;
        public TextField height;
    
    
        public void square(ActionEvent actionEvent) {
            length.setText("");
            width.setText("");
            radius.setText("");
            height.setText("");
            length.setDisable(false);
            width.setDisable(false);
            radius.setDisable(true);
            height.setDisable(true);
        }
    
        public void ball(ActionEvent actionEvent) {
            length.setText("");
            width.setText("");
            radius.setText("");
            height.setText("");
            length.setDisable(true);
            width.setDisable(true);
            radius.setDisable(false);
            height.setDisable(true);
        }
    
        public void tube(ActionEvent actionEvent) {
            length.setText("");
            width.setText("");
            radius.setText("");
            height.setText("");
            length.setDisable(true);
            width.setDisable(true);
            radius.setDisable(false);
            height.setDisable(false);
        }
    
        public void submit(ActionEvent actionEvent) {
            DecimalFormat dfGerman = new DecimalFormat("#,###.##",
                    new DecimalFormatSymbols(Locale.GERMAN));
            Stage newWindow = new Stage();
            Alert alertType=new Alert(Alert.AlertType.INFORMATION);
            if (radius.isDisabled() && height.isDisabled()){
    
                alertType.setContentText("Keliling "+(Integer.parseInt(length.getText())+Integer.parseInt(width.getText())+
                                Integer.parseInt(length.getText())+Integer.parseInt(width.getText()))+
                        " Luas: "+Integer.parseInt(length.getText())*Integer.parseInt(width.getText()));
            } else if(length.isDisabled() && width.isDisabled() && height.isDisabled()) {
                int radiusInt = Integer.parseInt(radius.getText());
                double luasPermukan = 4 *(Math.PI*(radiusInt *radiusInt ));
                double volume =  (4*(Math.PI*(radiusInt *radiusInt *radiusInt) / 3 ));
                alertType.setContentText("Luas Permukaan: "+dfGerman.format(luasPermukan)    +
                        " Volume: "+ dfGerman.format(volume));
            }else{
                int radiusInt = Integer.parseInt(radius.getText());
                int heightInt = Integer.parseInt(height.getText());
                double luasPermukan = 2 * Math.PI * radiusInt * (radiusInt+heightInt) ;
                double volume = Math.PI * (radiusInt*radiusInt) * heightInt  ;
                alertType.setContentText("Luas Permukaan: "+dfGerman.format(luasPermukan)+
                        " Volume: "+ dfGerman.format(volume)) ;
            }
    
    
            alertType.show();
    
    
    
    
        }
    }
