package at.gca.game.easygame;

import org.newdawn.slick.*;
import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Player extends SpielObjekt{
    private Rectangle shape;
    private Input input;
    private int direction = 0;

    private float speed =1f;
    private float acceleration = 5f;
    private Animation animation;
    private boolean jump=false;

    public Player(int x, int y, Image image, Input input) throws SlickException {
        super(x, y, image);
        this.input= input;
        shape = new Rectangle(x,y, image.getWidth(), image.getHeight());
        animation = new Animation();

        PackedSpriteSheet pss = new PackedSpriteSheet("assets/animation/texture.txr");
        animation.addFrame(pss.getSprite("01.gif"),100);
        animation.addFrame(pss.getSprite("02.gif"),100);
        for (int i=5;i<=13;i++){
            if (i<=9)
             animation.addFrame(pss.getSprite("0"+i+".gif"),100);
            else
                animation.addFrame(pss.getSprite(i+".gif"),100);
        }


    }

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    @Override
    public void draw(Graphics g) {
        if (jump) {
            this.getImage().drawCentered(this.getX(),this.getY());
        } else {
            animation.draw(this.getX(),this.getY()-50);
        }




    }

    @Override
    public void update(int delta) {
        if (jump) {
            this.setY(this.getY()+7);
        }

        if (this.getY() >  700) jump = false;
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
        if (input.isKeyDown(Input.KEY_SPACE)) {
            if (jump == false) {
                jump = true;
                this.setY(this.getY()-150);
            }
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
