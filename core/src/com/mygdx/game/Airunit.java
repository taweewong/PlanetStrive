package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
//import com.sun.javafx.scene.control.TableColumnSortTypeWrapper;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import planet.Strive.Actor.Explorer;

public class Airunit extends ApplicationAdapter implements InputProcessor, ApplicationListener{
	public static OrthographicCamera camera; 
	SpriteBatch batch;
	Texture img, star, star2;
	TextureRegion backgroundTexture;		
	Vector3 mouse_position = new Vector3(0,0,0);
	float pos_x;
	float rotate_x, mX=0;
	float rotate_y, angle = 0;
	float distance_airunit=100, alpha=0;
	int update_position = 0;
	private Sprite sprite;
	private Explorer explorer;
	private EnemyStar starblue;
	private RangerStar starred;
	private int expx = 2200;
	private int expy = 400;
	Stage stage_airunit;
	Stage stage_airunit_ui;
	InputMultiplexer multiplexer;
	


	private int panning;

	@Override
	public void create () {	
		Gdx.input.setInputProcessor(stage_airunit_ui);
		InputMultiplexer multiplexer = new InputMultiplexer();
		batch = new SpriteBatch();
		img = new Texture("orbit.png");
		stage_airunit = new Stage();
		stage_airunit_ui = new Stage();
		star = new Texture("star.png");
		star2 = new Texture("star2.png");
		backgroundTexture = new TextureRegion(new Texture("origin.jpg"), 0, 0, 4000, 1100);
		camera = new OrthographicCamera(1920, 1080);
		camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera.update();Gdx.input.setInputProcessor(this);
        explorer = new Explorer();
        starblue = new EnemyStar();
        starred = new RangerStar();
		multiplexer.addProcessor(this);
		multiplexer.addProcessor(stage_airunit_ui);
		Gdx.input.setInputProcessor(multiplexer);
        panning = 0;
        
        
        //button
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("kenvector_future.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        BitmapFont font = generator.generateFont(parameter);
        
        //action = new Action
        
        TextureAtlas buttonAtlas = new TextureAtlas(Gdx.files.internal("Button/Buttonout/pre_ui.pack"));
        Skin skin = new Skin(buttonAtlas);
        
        Table table = new Table(skin);
        table.setBounds(700, -300, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        
        LabelStyle style = new LabelStyle(font, Color.WHITE);
        Label tinyLabel = new Label("TINY HOUSE", style);
        tinyLabel.setFontScale(4);
        
        TextButtonStyle buttonStyle = new TextButtonStyle();
        buttonStyle.up = skin.getDrawable("button_inac");
        buttonStyle.down = skin.getDrawable("button_ac");
            
        buttonStyle.font = font;
        buttonStyle.fontColor = Color.WHITE;
        TextButton setting = new TextButton("", buttonStyle);
        setting.addListener(new ClickListener(){
        	@Override
        	public void clicked(InputEvent event, float x, float y) {
        		Gdx.app.exit();
        	}
        });
        
        //explorer.setPosition(1600, 500);
        table.add(setting);
        stage_airunit.addActor(starblue);
        stage_airunit.addActor(starred);
        stage_airunit.addActor(explorer);
        stage_airunit_ui.addActor(table);

	}
	
	private void cameraControl() {
		switch(panning){
		case 1:
			if(Gdx.input.getX()<=20 && camera.position.x>=0+(Gdx.graphics.getWidth()/2)){
				camera.translate(-10, 0);
				//Camera.get -= 10;
				break;}
			break;
		case 2:
			if(Gdx.input.getX()>=1900 && camera.position.x<=2569-(Gdx.graphics.getWidth()/2)){
				camera.translate(10, 0);
				//Camera.x += 10;
				break;}
			break;
		}
	}
	
	@Override
	public void render () {
		batch.setProjectionMatrix(camera.combined);
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// 1000,550
	    mouse_position.set(Gdx.input.getX(), Gdx.input.getY(), 0);

		camera.unproject(mouse_position.set(Gdx.input.getX(),Gdx.input.getY(), 0));
		//System.out.println(mouse_position);
		//batch.setProjectionMatrix(camera.combined);
		
		
		
		rotate_x = (float) (Math.cos(alpha*MathUtils.degreesToRadians)*distance_airunit*2.5);
		rotate_y = (float) (Math.sin(alpha*MathUtils.degreesToRadians)*distance_airunit*2.5);
		alpha++;


		batch.begin();
		//batch.draw(backgroundTexture, 0, 0);
		batch.draw(backgroundTexture,0,0,Gdx.graphics.getWidth()*2, Gdx.graphics.getHeight());
		batch.draw(img, 300+rotate_x, 450+rotate_y);
		//batch.draw(star,250,300);

		batch.draw(img, 2300+1300+rotate_x, 450+rotate_y);
		//batch.draw(star2,2200,300);
		//sprite.draw(batch);
		//explorer.setPosition(expx--, expy);
		explorer.setPosition(explorer.getX(), explorer.getY());
		
		
		batch.end();
		stage_airunit.act();
		stage_airunit_ui.act();
		cameraControl();
		stage_airunit.draw();
		stage_airunit_ui.draw();
		
		camera.update();
	}

	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		if(screenX < 50 ){
			panning = 1;
		}
		else if(screenX > 1400 ){
			panning = 2;
		}
		else{
			panning = 0;
		}
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public void setCamera(OrthographicCamera camera) {
		this.camera = camera;
	}
	
	
}
