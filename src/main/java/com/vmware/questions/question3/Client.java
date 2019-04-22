package com.vmware.questions.question3;

import com.vmware.questions.question3.drawer.CircleDrawingMechanism;
import com.vmware.questions.question3.drawer.IDrawingMechanism;
import com.vmware.questions.question3.drawer.SquareDrawingMechanism;

public class Client {
    public static void main(String[] args) {
        User user = new User();
        // draw circle
        IDrawingMechanism circleDrawer = new CircleDrawingMechanism();
        user.setDrawingMechanism(circleDrawer);
        user.draw();

        // draw square
        IDrawingMechanism squareDrawer = new SquareDrawingMechanism();
        user.setDrawingMechanism(squareDrawer);
        user.draw();
    }
}
