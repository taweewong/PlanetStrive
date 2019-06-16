package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import planet.Strive.Actor.GameScreen;
import planet.Strive.Actor.MainGame;
import planet.Strive.Actor.SpaceRanger;

public class RangerStar extends SpaceRanger {
	private Texture texture = new Texture("planet blue.png");
    private Sprite starblue = new Sprite(texture);
    private Sprite orbit;
    private Circle cir;

    private BitmapFont bitmapFont;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;

    private float max_hp = 10000;
    private float hp = max_hp;
    private boolean selected = true;
    //private airunit camera;

    public RangerStar() {
        setTouchable(Touchable.enabled);
        starblue.setSize(texture.getWidth(), texture.getHeight());
        //this.setOriginCenter();
        starblue.setPosition(14420, 100);
        starblue.setOrigin(starblue.getWidth()/2, starblue.getHeight()/2);
        this.setSize(starblue.getWidth(), starblue.getHeight());
        this.setOrigin(starblue.getWidth()/2, starblue.getHeight()/2);
        this.setPosition(14420, 100);
        setBounds(getX(), getY(), this.getWidth(), this.getHeight());
        cir = new Circle();

        orbit = new Sprite(new Texture("Tosluna.png"));
        orbit.setPosition(14412, 90);

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
        starblue.setPosition(this.getX(), this.getY());
        starblue.setRotation(starblue.getRotation()+Gdx.graphics.getDeltaTime()*5);
        orbit.setRotation(orbit.getRotation()-Gdx.graphics.getDeltaTime()*5);
        cir.setRadius(78*(starblue.getWidth()/2)/100);
        cir.setPosition(starblue.getX() + starblue.getWidth()/2, starblue.getY() + starblue.getHeight()/2);
    }

    

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //super.draw(batch, parentAlpha);
    	batch.setProjectionMatrix(GameScreen.camera.combined);
        starblue.draw(batch);
        orbit.draw(batch);
        bitmapFont.draw(batch, ""+(int) hp + " / " + (int) max_hp , this.getX()+330, this.getY()+450);
        //batch.draw(texture, this.getX()/* - airunit.camera.position.x -Camera.x*/, this.getY());
    }

}
