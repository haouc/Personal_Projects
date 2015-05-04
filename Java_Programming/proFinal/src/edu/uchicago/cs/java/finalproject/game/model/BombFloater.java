package edu.uchicago.cs.java.finalproject.game.model;

import edu.uchicago.cs.java.finalproject.controller.Game;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Hao on 12/4/2014.
 */
public class BombFloater extends NewShipFloater {
    public BombFloater() {

        super();

        ArrayList<Point> pntCs = new ArrayList<Point>();
        // top of ship
        pntCs.add(new Point(0, 5));
        pntCs.add(new Point(-2, 4));
        pntCs.add(new Point(-2, 0));
        pntCs.add(new Point(-4,-1));
        pntCs.add(new Point(-4, -3));
        pntCs.add(new Point(0, -3));
        pntCs.add(new Point(4, -3));
        pntCs.add(new Point(4, -1));
        pntCs.add(new Point(2, 0));
        pntCs.add(new Point(2, 4));



        assignPolarPoints(pntCs);

        setExpire(250);
        setRadius(50);
        setColor(Color.RED);


        int nX = Game.R.nextInt(10);
        int nY = Game.R.nextInt(10);
        int nS = Game.R.nextInt(5);

        //set random DeltaX
        if (nX % 2 == 0)
            setDeltaX(nX);
        else
            setDeltaX(-nX);

        //set rnadom DeltaY
        if (nY % 2 == 0)
            setDeltaX(nY);
        else
            setDeltaX(-nY);

        //set random spin
        if (nS % 2 == 0)
            setSpin(nS);
        else
            setSpin(-nS);

        //random point on the screen
        setCenter(new Point(Game.R.nextInt(Game.DIM.width),
                Game.R.nextInt(Game.DIM.height)));

        //random orientation
        setOrientation(Game.R.nextInt(360));

    }
}
