package com.hulunbuir.clam.admin.test_demo;

public class SellTicketDemo /*extends Thread*/ implements Runnable {

    private int ticketCount = 100;
    Object obj = new Object();
    @Override
    public void run() {
        while (true){
            synchronized (obj){
                if(ticketCount>0){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                    System.out.println(Thread.currentThread().getName()+"<----ticket-sell-->"+ticketCount--);
                }
            }
        }
    }

}
