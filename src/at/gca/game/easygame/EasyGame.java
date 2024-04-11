package at.gca.game.easygame;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import java.util.ArrayList;
import java.util.Random;

public class EasyGame extends BasicGame {

    private Image background;
    private ArrayList<Stein> steinList;
    private ArrayList<Stern> sternList;
    private Player player;

    private Sound sound;
    private  Music music;
    private float lautstaerke = 0f;
    private int hits = 0;
    private int miss = 0;
    private AngelCodeFont font;
    private Font font2; 


    public EasyGame() {
        super("EasyGame");
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer container = new AppGameContainer(new EasyGame());
        container.setDisplayMode(1024, 768, false);
        //container.setClearEachFrame(false);
        container.setMinimumLogicUpdateInterval(25);
        container.setTargetFrameRate(60);
        container.setShowFPS(true);
        container.start();
    }



    @Override
    public void init(GameContainer container) throws SlickException {
        background = new Image("assets/pics/backg.jpg");
        steinList = new ArrayList<>();
        sternList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            steinList.add(new Stein(100, 100, new Image("assets/pics/star_50.png")));
            sternList.add(new Stern(100, 100, new Image("assets/pics/komet_50.png")));

        }
        player = new Player(500, 700, new Image("assets/animation/Runner_New.gif"), container.getInput());
        music = new Music("testdata/kirby.ogg");
        music.loop();
        music.setVolume(1);

        sound = new Sound("testdata/burp.aif");
        font = new AngelCodeFont("testdata/demo2.fnt", "testdata/demo2_00.tga");
    }


        @Override
        public void update (GameContainer container,int delta) throws SlickException {
            Input input = container.getInput();
            // Fenster mit ESC scließen
            player.update(delta);

            if (input.isKeyPressed(Input.KEY_ESCAPE)) {
                container.exit();
            }
            //Lautstärke leiser
            if (input.isKeyPressed(Input.KEY_DOWN)) {
                lautstaerke = lautstaerke - 0.5f;
                if (lautstaerke <= 0) lautstaerke = 0;
                music.setVolume(lautstaerke / 10f);
            }
            //Lautstärke lauter
            if (input.isKeyPressed(Input.KEY_UP)) {
                lautstaerke = lautstaerke + 0.5f;
                if (lautstaerke > 0) lautstaerke = 10;
                music.setVolume(lautstaerke / 10f);
            }

            for (Stein u : steinList) {
                if (player.intersects(u.getShape())) {
                    sound.play();
                    hits++;
                    u.setRandomPosition();
                }
                if (u.getY() > 768) {
                    miss++;
                    u.update(delta);
                    u.setRandomPosition();
                }
                u.update(delta);
            }
            for (Stern u : sternList) {
                if (player.intersects(u.getShape())) {
                    sound.play();
                    hits++;
                    u.setRandomPosition();
                }
                if (u.getY() >  768) {
                    miss++;
                    u.update(delta);
                    u.setRandomPosition();
                }
                u.update(delta);
            }
        }

        @Override
        public void render (GameContainer container, Graphics g) throws SlickException {
            background.draw();
            player.draw(g);
            for (Stein u : steinList) {
                u.draw(g);
            }
            for (Stern u : sternList) {
                u.draw(g);
            }
            font.drawString(10, 5, "Hit" + hits, Color.black);
            font.drawString(10, 35, "Miss" + miss, Color.red);
        }

}
