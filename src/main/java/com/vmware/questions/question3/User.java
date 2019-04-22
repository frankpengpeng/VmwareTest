package com.vmware.questions.question3;

import com.vmware.questions.question3.drawer.IDrawingMechanism;

public class User {
    private IDrawingMechanism drawingMechanism;

    public void draw(){
        drawingMechanism.draw();
    }

    public void setDrawingMechanism(IDrawingMechanism drawingMechanism){
        this.drawingMechanism = drawingMechanism;
    }
}
