package edu.uchicago.cs.java.finalproject.game.model;

import edu.uchicago.cs.java.finalproject.controller.Game;
import edu.uchicago.cs.java.finalproject.sounds.Sound;

import java.awt.*;
import java.util.Random;

public class UFO extends Asteroid{

    //level of UFOs
    private int nLevel;
    private final int R = 37;
    private int nSpin;
    private int nLife;
    private Color UFOColor;

    public UFO(int nLevel){
        setnLife(6);
        setDim(Game.DIM);
        setColor(Color.pink);
        setCenter(new Point(Game.R.nextInt(Game.DIM.width),
                Game.R.nextInt(Game.DIM.height)));
        //the spin will be either plus or minus 0-9
        int nSpin = Game.R.nextInt(10);
        if(nSpin %2 ==0)
            nSpin = -nSpin;
        setSpin(nSpin);

        //random delta-x
        int nDX = 1 + Game.R.nextInt(5);
        if(nDX %2 ==0)
            nDX = -nDX;
        setDeltaX(nDX);

        //random delta-y
        int nDY = 1 + Game.R.nextInt(5);
        if(nDY %2 ==0)
            nDY = -nDY;
        setDeltaY(nDY);
        if(nLevel == 0){
            setColor(Color.pink);
        }
        else{
            setColor(Color.GREEN);
        }
        setRadius(R);
    }
@Override
    public int getSize(){

        int nReturn = 0;

        switch (getRadius()) {
            case 100:
                nReturn= 1;
                CommandCenter.setScore(5);
                break;
//            case 50:
//                nReturn= 2;
//                break;
            case 25:
                nReturn= 2;
                break;
        }

        return nReturn;

    }

    public void setSpin(int nSpin) {
        this.nSpin = nSpin;
    }

    public void expire(){
        if (getExpire() == 0)
            CommandCenter.movDebris.remove(this);
        else
            setExpire(getExpire() - 1);
    }

    //overridden
    public void move(){
        super.move();

        //an asteroid spins, so you need to adjust the orientation at each move()
        setOrientation(getOrientation() + getSpin());

    }

    public int getSpin() {
        return this.nSpin;
    }
//
//    public void setColor(){
//        UFOColor = Color.getColor();
//    }

    public void getColor(String thisColor){
        UFOColor = Color.getColor(thisColor);
    }

    @Override
    public void draw(Graphics g) {
        UFOColor = new Color(new Random().nextInt(256),new Random().nextInt(256), new Random().nextInt(256));

        g.setColor(UFOColor);

        g.drawOval(getCenter().x + (getRadius()-18),
                getCenter().y - getRadius()*2+2, getRadius() * 1,
                getRadius() * 2);
        g.fillOval(getCenter().x + (getRadius() - 18),
                getCenter().y - getRadius() * 2 + 2, getRadius() * 1,
                getRadius() * 2);
        g.drawOval(getCenter().x - getRadius(),
                getCenter().y - getRadius(), getRadius() * 4,
                getRadius() * 1);
        g.fillOval(getCenter().x - getRadius(),
                getCenter().y - getRadius(), getRadius() * 4,
                getRadius() * 1);
//        g.setColor(UFOColor);
        
    }

    public int getnLife() {
        return nLife;
    }

    public void setnLife(int nLife) {
        this.nLife = nLife;
    }

}

