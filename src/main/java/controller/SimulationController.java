package controller;

import models.Client;
import view.SimulationView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class SimulationController{

    private Simulare simulare;
    private SimulationView view;

    public SimulationController(SimulationView view1)
    {
        view=view1;
        view.addStartListener(new StartListener());

    }

    class StartListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            simulare= new Simulare( view,view.getTextFieldSimTime(),view.getTextFieldN(),view.getTextFieldQ(),
                                    view.getTextFieldArrTimeMin(),view.getTextFieldArrTimeMax(),
                                    view.getTextFieldServTimeMin(),view.getTextFieldServTimeMax()
                                   );
            Thread thread= new Thread(simulare);
            thread.start();

        }

    }

}
