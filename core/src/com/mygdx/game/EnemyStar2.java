package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import planet.Strive.Actor.GameScreen;
import planet.Strive.Actor.SpaceRanger;

/**
 * Created by oat_t on 11/14/2016.
 */
public class EnemyStar2 extends SpaceRanger{
    private Texture texture = new Texture("Meucury.png");
    private Sprite orbit;
    private Sprite starblue = new Sprite(texture);
    private Circle cir;
    private boolean selected = true;
    //private airunit camera;

    private BitmapFont bitmapFont;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;

    private float max_hp = 10000;
    private float hp = max_hp;

    public EnemyStar2() {
        setTouchable(Touchable.enabled);
        starblue.setSize(texture.getWidth(), texture.getHeight());
        //this.setOriginCenter();
        starblue.setRotation(0);
        starblue.setPosition(5362+105, 100+105);
        starblue.setOrigin(starblue.getWidth()/2, starblue.getHeight()/2);
        this.setSize(starblue.getWidth(), starblue.getHeight());
        this.setOrigin(starblue.getWidth()/2, starblue.getHeight()/2);
        this.setPosition(starblue.getX(), starblue.getY());
        setBounds(getX(), getY(), this.getWidth(), this.getHeight());
        cir = new Circle();

        orbit = new Sprite(new Texture("deprion-1.png"));
        orbit.setPosition(5354, 90);

        bitmapFont = new BitmapFont();
        generator = new FreeTypeFontGenerator(Gdx.files.internal("kenvector_future.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 20;
        parameter.color = Color.GREEN;
        parameter.borderWidth = 1;
        parameter.borderColor = Color.BLACK;
        parameter.shadowOffsetX = 3;
        parameter.shadowOffsetY = 3;
        parameter.shadowColor = Color.BLACK;
        bitmapFont = generator.generateFont(parameter);
    }

    public float getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public void subHp(float hp) {
        this.hp -= hp;
    }

    public void selected() {
        selected = true;
    }

    public void deSelected() {
        selected = false;
    }

    public Circle getCir() {
        return cir;
    }

    public void setCir(Circle cir) {
        this.cir = cir;
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        starblue.setPosition(this.getX(), this.getY());
    }



    @Override
    protected void rotationChanged() {
        super.rotationChanged();
        starblue.setRotation(getRotation());
    }

    //((2569*starblue.getX())/1920)-Gdx.graphics.getDeltaTime()*100, starblue.getY()

    @Override
    public void act(float delta) {
        super.act(delta);
        this.setPosition(getX(), getY());
        starblue.setPosition(starblue.getX(), starblue.getY());
        starblue.setRotation(starblue.getRotation()+ Gdx.graphics.getDeltaTime()*5);
        orbit.setRotation(orbit.getRotation()-Gdx.graphics.getDeltaTime()*5);
        cir.setRadius(starblue.getWidth()/2);
        cir.setPosition(starblue.getX() + starblue.getWidth()/2, starblue.getY() + starblue.getHeight()/2);
    }



    @Override
    public void draw(Batch batch, float parentAlpha) {
        //super.draw(batch, parentAlpha);
        batch.setProjectionMatrix(GameScreen.camera.combined);
        starblue.draw(batch);
        orbit.draw(batch);
        if (GameScreen.level >= 3) {
            bitmapFont.draw(batch, "" + (int) hp + " / " + (int) max_hp, this.getX() + 250, this.getY() + 350);
        }
        //batch.draw(texture, this.getX()/* - airunit.camera.position.x -Camera.x*/, this.getY());
    }
}
