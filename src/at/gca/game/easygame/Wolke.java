package at.gca.game.easygame;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import java.util.Random;

public class Wolke extends SpielObjekt{



        private float acceleration = 0.0005f;
        private float speed = 2f;
        private Rectangle shape;

        public Wolke(int x, int y, Image image) {
            super(x, y, image);
            shape = new Rectangle(x,y,image.getWidth(), image.getHeight());
            setRandomPosition();
        }

        @Override
        public Shape getShape() {
            return shape;
        }

        @Override
        public void draw(Graphics g) {
            this.getImage().drawCentered(this.getX(),this.getY());

        }

        @Override
        public void update(int delta) {
            this.speed = (delta * this.acceleration + this.speed);
            if(this.getX()<(0-this.getWidth())){
                this.setRandomPosition();
                this.speed = 2;
            }
            this.setX(this.getX()-(int) this.speed);

            this.shape.setCenterX(this.getX());
            this.shape.setCenterY(this.getY());
        }
        public void setRandomPosition(){
            Random r = new Random();
            int ry =0;
            int rx =0;
            rx = r.nextInt(1100+this.getWidth()+1-0)+1024;
            ry= r.nextInt(680+1+this.getHeight())+300;
            this.setY(ry);
            this.setX(rx);
            this.speed= 2f;

        }
    }



