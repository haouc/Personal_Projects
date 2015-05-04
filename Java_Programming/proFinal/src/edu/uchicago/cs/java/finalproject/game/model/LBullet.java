package edu.uchicago.cs.java.finalproject.game.model;

import java.awt.Point;
import java.util.ArrayList;


public class LBullet extends Sprite {

    private final double FIRST_FIRE_POWER = 35.0;

    public LBullet(Falcon fal){

        super();

        Point lCenter = new Point(-12, 3);

        //defined the points on a cartesean grid
        ArrayList<Point> pntCs = new ArrayList<Point>();


//
        pntCs.add(new Point(0,3)); //top point
        pntCs.add(new Point(1,-1));
        pntCs.add(new Point(0,-2));
        pntCs.add(new Point(-1,-1));

        assignPolarPoints(pntCs);


        //a bullet expires after 20 frames
        setExpire( 10 );
        setRadius( 5 );


        //everything is relative to the falcon ship that fired the bullet
        setDeltaX( (fal.getDeltaX()) +
                Math.cos( Math.toRadians( fal.getOrientation() ) ) * FIRST_FIRE_POWER);
        setDeltaY( fal.getDeltaY() +
                Math.sin( Math.toRadians( fal.getOrientation() ) ) * FIRST_FIRE_POWER);
        fal.setRight(fal.getCenter());
        setCenter(fal.getRight());


        //set the bullet orientation to the falcon (ship) orientation
        setOrientation(fal.getOrientation());


    }

    //override the expire method - once an object expires, then remove it from the arrayList. 
    public void expire(){
        if (getExpire() == 0)
            CommandCenter.movFriends.remove(this);
        else
            setExpire(getExpire() - 1);
    }

}
