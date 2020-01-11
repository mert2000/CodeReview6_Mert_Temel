package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import  javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import  javafx.scene.layout.VBox;
import  javafx.scene.text.Text;
import javafx.scene.paint.Color;
import  javafx.scene.control.ListView;
import javafx.scene.control.Label;
import  javafx.scene.control.TextField;;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Main extends Application {
    private ListView<schoollist> listView;
    private ObservableList<schoollist> data;
    private TextField nametxt;
    private TextField surename;
    private Text actionstatus;
    private dbClass dbaccess;

    @Override
    public void init()
    {
        try
        {
            dbaccess = new dbClass();
        }
        catch (Exception e)
        {
            displayException(e);
        }
    }

    @Override
    public void stop()
    {
        try
        {
            dbaccess.closeDb();
        }
        catch (Exception e)
        {
            displayException(e);
        }
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setHgap(15);
        gp.setVgap(20);
        gp.setPadding(new Insets(50, 50, 50, 50));
       // Label lb = new Label("TEACHERS");
       // Label lbl = new Label("THIS TEACHER");
        Label lbl1 = new Label("ID:\t");
        Label lbl2 = new Label("Name:\t");
        Label lbl3 = new Label("Surename:\t");
        Label lbl4 = new Label("E-mail:\t");
        Label lbl5 = new Label("");
        Label lbl6 = new Label("");
        Label lbl7 = new Label("");
        Label lbl8 = new Label("");



        //listview listener

        listView = new ListView<>();
        listView.getSelectionModel().selectedIndexProperty().addListener(new ListSelectChangeListener());
        listView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            lbl5.setText(String.valueOf(newValue.getId()));
        });
        listView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            lbl6.setText(String.valueOf(newValue.getName()));
        });
        listView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            lbl7.setText(String.valueOf(newValue.getSurename()));
        });
        listView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            lbl8.setText(String.valueOf(newValue.getEmail()));
        });
        data = getDbData();
        listView.setItems(data);
        gp.add(listView, 1, 1);

        //status
        actionstatus = new Text();
        actionstatus.setFill(Color.FIREBRICK);
        actionstatus.setText("");
        gp.add(actionstatus, 1, 3);

        primaryStage.setTitle("SCHOOL");
        //VBox vba = new VBox(lb);
        //VBox vbb=new VBox(lbl);
        VBox vb = new VBox(lbl1, lbl2, lbl3, lbl4);
        vb.setAlignment(Pos.CENTER_LEFT);
        VBox vb2 = new VBox(lbl5, lbl6, lbl7, lbl8);
        vb2.setAlignment(Pos.CENTER_LEFT);
        HBox hb = new HBox(gp,vb,vb2);
        Scene sc = new Scene(hb, 900, 600);
        primaryStage.setScene(sc);
        primaryStage.show();

        listView.getSelectionModel().selectFirst();
    }
    private   class  ListSelectChangeListener implements  ChangeListener<Number> {

        @Override
        public   void  changed(ObservableValue<? extends Number> ov,
                               Number old_val, Number new_val) {

            if  ((new_val.intValue() < 0 ) || (new_val.intValue() >= data.size())) {

                return ; // invalid data
            }

            // set name and desc fields for the selected todo
            schoollist todo = data.get(new_val.intValue());
            nametxt.setText(todo.getName());
            actionstatus.setText(todo.getName() + " - selected" );
        }
    }

    private  ObservableList<schoollist> getDbData() {

        List<schoollist> list = null ;

        try  {
            list = dbaccess.getAllRows();
        }
        catch  (Exception e) {

            displayException(e);
        }

        ObservableList<schoollist> dbData = FXCollections.observableList(list);
        return  dbData;
    }

    private   void  displayException(Exception e) {

        System.out.println( "###### Exception ######" );
        e.printStackTrace();
        System.exit( 0 );
    }

    public static void main(String[] args) {
        launch(args);
    }
}
