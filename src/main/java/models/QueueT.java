package models;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class QueueT implements Runnable{
    private boolean ok=true;
    private LoggerClass logger;
    private BlockingQueue<Client> queue;
    private AtomicInteger waitingTime= new AtomicInteger(0);
    public QueueT(LoggerClass loggerSimulare)
    {
        queue=new LinkedBlockingQueue<>();
        logger=loggerSimulare;
    }
    public QueueT()
    {

    }

    public void addClient(Client client) {
        try {
            queue.put(client);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitingTime.getAndAdd(client.getServiceTime());
    }

    public void stopQueue()
    {
        ok=false;
    }
    public BlockingQueue<Client> getQueue() {
        return queue;
    }

    public int getWaitingTime() {
        return waitingTime.get();
    }

    @Override
    public String toString() {
        String clientString="(WT="+waitingTime+"): ";
        for(Client client:queue)
        {
            clientString+=client;
            clientString+=" | ";
        }
        return clientString;
    }

    @Override
    public void run() {

        while(ok) {

            if(queue.size()!=0)
            {
                if (queue.peek().getServiceTime() > 0) {
                    queue.peek().decrementServiceTime();
                    waitingTime.decrementAndGet();
                }
                if(queue.peek().getServiceTime() == 0)
                    try {
                        logger.appendToLogFile("Client # "+queue.peek().getId()+"left the queue");
                        queue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}
