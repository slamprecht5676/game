package at.gca.game.easygame;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Player extends SpielObjekt{
    private Rectangle shape;
    private Input input;
    private int direction = 0;

    private float speed =1f;
    private float acceleration = 5f;

    public Player(int x, int y, Image image, Input input) {
        super(x, y, image);
        this.input= input;
        shape = new Rectangle(x,y, image.getWidth(), image.getHeight());
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
        int olddirection = this.direction;
        this.speed += acceleration;
        if(input.isKeyDown(Input.KEY_A)){
            this.setY(this.getX()-(int) this.speed);
            //check ob position <0
            if (this.getY()<(this.getWidth()/2))
                this.setY(this.getWidth()/2);
            direction =-1;
        }
        if (input.isKeyDown(Input.KEY_D)){
            this.setY(this.getX()+(int)this.speed);
            //check ob position > als 1024
            if (this.getY()>(1024-(this.getWidth()/2)))
                this.setY(1024-(this.getWidth()/2));
            direction = 1;
        }
        if (this.direction == olddirection){
            speed += acceleration;
        } else {
            speed = 12f;
        }

        this.shape.setCenterX(this.getX());
        this.shape.setCenterY(this.getY());
    }

    public boolean intersects (Shape shape) {
        if (shape != null){
            return this.getShape().intersects(shape);
        }
        return false;
    }
}
