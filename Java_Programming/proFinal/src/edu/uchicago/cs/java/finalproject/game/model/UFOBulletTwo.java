package edu.uchicago.cs.java.finalproject.game.model;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Hao on 11/29/2014.
 */

public class UFOBulletTwo extends Sprite {

    private final double FIRST_FIRE_POWER = 25.0;




    public UFOBulletTwo(UFO ufo){

        super();


        //defined the points on a cartesean grid
        ArrayList<Point> pntCs = new ArrayList<Point>();


//        pntCs.add(new Point(0, 0)); //top point
//        pntCs.add(new Point(-5, -1));
//        pntCs.add(new Point(0, 3));
//        pntCs.add(new Point(5, -1));

        pntCs.add(new Point(-3, 0)); //top point
        pntCs.add(new Point(-1, -7));
        pntCs.add(new Point(0, 0));
        pntCs.add(new Point(-1, 7));


        assignPolarPoints(pntCs);


        //a bullet expires after 10 frames
        setExpire(35);
        setRadius(15);
        setColor(Color.RED);


        //everything is relative to the falcon ship that fired the bullet
        setDeltaX( ufo.getDeltaX() +
                Math.sin( Math.toRadians( ufo.getOrientation() ) ) * FIRST_FIRE_POWER);
        setDeltaY( ufo.getDeltaY() +
                Math.cos( Math.toRadians( ufo.getOrientation() ) ) * FIRST_FIRE_POWER);
        setCenter( ufo.getCenter() );

        //set the bullet orientation to the falcon (ship) orientation
        setOrientation(ufo.getOrientation());


    }

    //override the expire method - once an object expires, then remove it from the arrayList.
    public void expire(){
        if (getExpire() == 0)
            CommandCenter.movFoes.remove(this);
        else
            setExpire(getExpire() - 1);
    }



}
