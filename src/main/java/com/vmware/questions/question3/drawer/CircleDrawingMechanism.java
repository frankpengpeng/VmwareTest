package com.vmware.questions.question3.drawer;

import com.vmware.questions.question3.drawer.AbstractDrawingMechanism;

public class CircleDrawingMechanism extends AbstractDrawingMechanism implements IDrawingMechanism{
    public void doDraw() {
        System.out.println("Drawing circle, happy happy");
    }
}
