import controller.SimulationController;
import view.SimulationView;

public class Main {
    public static void main(String[] args) {

        SimulationView view= new SimulationView();
        SimulationController controller=new SimulationController(view);
    }
}
