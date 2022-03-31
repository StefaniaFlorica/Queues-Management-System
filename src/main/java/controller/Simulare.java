package controller;

import models.Client;
import models.LoggerClass;
import models.QueueT;
import view.SimulationView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Simulare extends Thread {
    private LoggerClass loggerSimulare= new LoggerClass();
    private SimulationView view;
    private int simulationTime;
    private AtomicInteger currentTime;
    private int nrClients;
    private int nrQueues;
    private int arrTimeMin;
    private int arrTimeMax;
    private int servTimeMin;
    private int servTimeMax;
    private double averageWaitingTime;
    private int peakTime;
    private List<QueueT> queues;
    private List<Client> waitingClients;
    private String qStatus;
    public Simulare(SimulationView simulationView,int simT,int n, int q,int arrTimeMin,int arrTimeMax,int servTimeMin, int servTimeMax)
    {
        try {
            LoggerClass.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.view=simulationView;
        this.simulationTime=simT;
        this.qStatus="";
        currentTime=new AtomicInteger(0);
        this.nrClients=n;
        this.nrQueues=q;
        this.arrTimeMin=arrTimeMin;
        this.arrTimeMax=arrTimeMax;
        this.servTimeMin=servTimeMin;
        this.servTimeMax=servTimeMax;
        this.queues= new ArrayList<QueueT>();
        this.waitingClients= new ArrayList<Client>();
        this.peakTime=Integer.MIN_VALUE;

        loggerSimulare.appendToLogFile("Number of clients: "+nrClients+"\nNumber of queues: "+nrQueues+"\nSimulation time: "+simulationTime);
        loggerSimulare.appendToLogFile("Arrival Time: ["+arrTimeMin+"; "+arrTimeMax+"]");
        loggerSimulare.appendToLogFile("Service Time: ["+servTimeMin+"; "+servTimeMax+"]");
        for(int i=0;i<nrClients;i++)
        {

            Client client= randomClient(i+1);

            loggerSimulare.appendToLogFile("client "+client+"was generated");
            System.out.println(client);
            waitingClients.add(client);
        }

        for(int i=0;i<nrQueues;i++)
        {
            QueueT queueT= new QueueT(loggerSimulare);
            queues.add(queueT);
            new Thread(queueT,"q"+i).start();
        }

    }
    public int getRandomNumber(int min, int max) {
        return (int)Math. floor(Math. random()*(max-min+1)+min);
    }
    public Client randomClient(int id)
    {
        return new Client(id,getRandomNumber(arrTimeMin,arrTimeMax),getRandomNumber(servTimeMin,servTimeMax));
    }

    public List<QueueT> getQueues() {
        return queues;
    }

    public void setQueues(List<QueueT> queues) {
        this.queues = queues;
    }

    public void dispatchClient(Client client) //adaug clientul cu arrivalTime == currentTime din SimluationController in coada corespunzatoare
    //si il sterg din lista de waiting clients
    {
        int indexBestQueue = getBestQueue();

        loggerSimulare.appendToLogFile("-->"+client+" will enter Q"+(indexBestQueue+1)+" with WT="+queues.get(indexBestQueue).getWaitingTime());
        view.setTextArea("-->"+client+" will enter Q"+(indexBestQueue+1)+" with WT="+queues.get(indexBestQueue).getWaitingTime());

        queues.get(indexBestQueue).addClient(client);
        waitingClients.remove(client);
    }
    public int getBestQueue()
    {
        int indexMin=-1;
        int minWaitingTime= Integer.MAX_VALUE;
        for(int i=0;i<queues.size();i++)
        {
            if(queues.get(i).getWaitingTime()<minWaitingTime)
            {
                minWaitingTime=queues.get(i).getWaitingTime();
                indexMin=i;
            }
        }
        averageWaitingTime+=minWaitingTime;
        return indexMin;
    }
    public boolean queuesAreEmpty()
    {
        for(QueueT q:queues)
        {
            if(q.getQueue().size()!=0)
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String queueStatus="";
        int i=1;
        for(QueueT q:queues)
        {
            queueStatus+="Q"+i+" "+q.toString()+" \n";
            i++;
        }
        return queueStatus;
    }
    public String getWaitingClientsToString()
    {
        String generatedClients="";
        for(Client client:waitingClients)
        {
            generatedClients+=client+"\n";
        }
        return generatedClients;
    }

    public int computeNrClients()
    {
        int nrCl=0;
        for(QueueT q:queues)
        {
            nrCl+=q.getQueue().size();
        }
        return nrCl;
    }
    @Override
    public void run() {
        loggerSimulare.appendToLogFile("Clients to be assigned to queues:\n"+getWaitingClientsToString());
        view.setTextArea("Clients to be assigned to queues:\n"+getWaitingClientsToString());
        int peakTime=Integer.MIN_VALUE;
        int peakValue=0;
        while((currentTime.get()<=simulationTime || !queuesAreEmpty()) && (waitingClients.size()!=0 || !queuesAreEmpty()))
        {
            loggerSimulare.appendToLogFile("\t\tTIME:"+currentTime+"/"+simulationTime);
            view.setTextArea("\t\tTIME:"+currentTime+"/"+simulationTime);
            ListIterator<Client> it=waitingClients.listIterator();
            while(it.hasNext()) {
                if (it.next().getArrivalTime() == currentTime.get()) {
                    Client chosenClient=it.previous();
                    dispatchClient(chosenClient);
                    it=waitingClients.listIterator();
                    loggerSimulare.appendToLogFile("------there are "+waitingClients.size()+" more clients to go------");
                    view.setTextArea("------there are "+waitingClients.size()+" more clients to go------");
                }
            }
            loggerSimulare.appendToLogFile(this.toString());
            view.setTextArea(this.toString());
            currentTime.incrementAndGet();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //PEAK TIME
            int nrOfClientsInQueues=computeNrClients();
            if(nrOfClientsInQueues>peakValue)
            {
                peakValue=nrOfClientsInQueues;
                peakTime=currentTime.get();
            }

        }
        if(waitingClients.size()==0)
        {
            if(queuesAreEmpty())
            {
                loggerSimulare.appendToLogFile("\t\tTIME:"+currentTime+"/"+simulationTime);
                loggerSimulare.appendToLogFile(this.toString());
                loggerSimulare.appendToLogFile("SIMULATION ENDED");
                view.setTextArea("\t\tTIME:"+currentTime+"/"+simulationTime);
                view.setTextArea(this.toString());
                view.setTextArea("SIMULATION ENDED");
                if(currentTime.get()>simulationTime)
                {
                    loggerSimulare.appendToLogFile("--->Service time exceeded simulation time!");
                    view.setTextArea("--->Service time exceeded simulation time!");
                }
                loggerSimulare.appendToLogFile("--->Queues are empty and there are no more clients waiting");
                view.setTextArea("--->Queues are empty and there are no more clients waiting");
            }
        }
        loggerSimulare.appendToLogFile("Average Waiting Time: "+averageWaitingTime/nrClients);
        loggerSimulare.appendToLogFile("Peak Time: "+peakTime);
        view.setTextArea("Average Waiting Time: "+averageWaitingTime/nrClients);
        view.setTextArea("Peak Time: "+peakTime +" with "+peakValue+" clients");
        for(QueueT q:queues)
        {
            q.stopQueue();
        }
    }
}
