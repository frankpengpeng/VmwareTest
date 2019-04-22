package com.vmware.questions.question3.drawer;

public abstract class AbstractDrawingMechanism {

    protected void preProcessing(){
        System.out.println("Do something before drawing");
    }

    protected void afterProcessing(){
        System.out.println("Do something after drawing");
    }

    public void draw(){
        preProcessing();
        doDraw();
        afterProcessing();
    }

    public abstract void doDraw();
}
