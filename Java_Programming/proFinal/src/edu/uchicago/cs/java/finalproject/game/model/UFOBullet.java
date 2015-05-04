package edu.uchicago.cs.java.finalproject.game.model;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Hao on 11/29/2014.
 */

public class UFOBullet extends Sprite {

        private final double FIRST_FIRE_POWER = 15.0;




        public UFOBullet(UFO ufo){

            super();


            //defined the points on a cartesean grid
            ArrayList<Point> pntCs = new ArrayList<Point>();


            pntCs.add(new Point(0,5)); //top point
            pntCs.add(new Point(2,-1));
            pntCs.add(new Point(0,-2));
            pntCs.add(new Point(-2,-1));


            assignPolarPoints(pntCs);


            //a bullet expires after 10 frames
            setExpire(50);
            setRadius(25);
            setColor(Color.RED);


            //everything is relative to the falcon ship that fired the bullet
            setDeltaX( ufo.getDeltaX() +
                    Math.cos( Math.toRadians( ufo.getOrientation() ) ) * FIRST_FIRE_POWER);
            setDeltaY( ufo.getDeltaY() +
                    Math.sin( Math.toRadians( ufo.getOrientation() ) ) * FIRST_FIRE_POWER);
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
