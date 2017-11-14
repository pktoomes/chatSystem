package com.company;

import java.net.*;
import java.io.*;
import java.awt.*;



public class ChatClient extends Frame implements Runnable {
    protected DataInputStream i;
    protected DataOutputStream o;
    protected TextArea output;
    protected TextField input;
    protected Thread listener;

    public ChatClient(String title, InputStream i, OutputStream o){
        super (title);
        this.i = new DataInputStream(new BufferedInputStream(i));
        this.o = new DataOutputStream(new BufferedOutputStream(o));
        setLayout(new BorderLayout());
        add("Center", output = new TextArea());
        output.setEditable(false);
        add("South", input = new TextField());
        pack();
        show();
        input.requestFocus();
        listener = new Thread(this);
        listener.start();
    }
    public void run(){
        try{
            while(true){
                String line = i.readUTF();
                output.appendText(line + "/n");
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }finally {
            listener = null;
            input.hide();
            validate();
            try{
                o.close();

            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }
}
