package com.hulunbuir.clam.admin.test_demo;

public class SellTicketDemo /*extends Thread*/ implements Runnable {

    private int ticketCount = 100;

    final Person person = new Person();

    @Override
    public void run() {
        synchronized (person){
            while (ticketCount-->0){
                System.out.println(Thread.currentThread().getName()+"<----ticket-sell-->"+ticketCount);
            }
        }
    }

}
