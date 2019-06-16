package planet.Strive.Start;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;

public class Openning extends ApplicationAdapter{
	Texture banner;
	Texture start_but;
	SpriteBatch draw;
	FreeTypeFontGenerator generator;
	FreeTypeFontParameter parameter;
	BitmapFont text_drawer;
	BitmapFont text_button;
	Rectangle black_rec;
	ShapeRenderer shapeRenderer;
	private int state = 0;
	private int mouse_state = 0;
	
	private float y = 0f;
	private float fade_time = 0;
	private int size_font = 56;
	private float size_rec = 0;
	Interpolation ease = new Interpolation.Pow(2);
	Interpolation bounce = new Interpolation.BounceOut(3);
	
	//Button
	Stage stage;
	TextButton button;
	TextButtonStyle textButtonStyle;
	
	
	/*
	public float easeInOut(float t, float b, float c, float d) {
		return (float) (-c/2 * (Math.cos(Math.PI*t/d) - 1) + b);
	};
	*/
	
	@Override
	public void create() {
		draw = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		
		//Font create
		generator = new FreeTypeFontGenerator(Gdx.files.internal("font/Intro.otf"));
		parameter = new FreeTypeFontParameter();
		parameter.size = size_font;
		text_drawer = generator.generateFont(parameter);
		
		//Create rectangle
		black_rec = new Rectangle(200, 540, 200, 200);
		
		//Crate BG
		banner = new Texture(Gdx.files.internal("wallpaper.png"));
		start_but = new Texture(Gdx.files.internal("start.png"));
		
		//Create Button
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		text_button = generator.generateFont(parameter);
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = text_button;
        button = new TextButton("START", textButtonStyle);
        stage.addActor(button);
        
        button.addListener(new ClickListener(){
        	@Override
        	public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        		mouse_state = 1;
        	}
        	@Override
        	public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        		mouse_state = 0;
        	}
        });
        
        button.setPosition(1400, 80);
        
        //dispose generator
        //generator.dispose();
		
	}
	
	@Override
	public void render() {			
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//Gdx.app.log("LOG", "Delta Time: " + Gdx.graphics.getDeltaTime());
		//Draw background
		draw.begin();
		draw.setColor(1,1,1,1);
		draw.draw(banner, 0, 0);
		draw.end();
		if (state == 0)
		{
			//Draw start button
			draw.begin();
			draw.setColor(1f, 1f, 1f, 1 - ease.apply(Math.abs(1000-TimeUtils.millis()%2000)/1000f)); //1-easeInOut(frame, 0, 1, 60)
			draw.draw(start_but, 100 * ease.apply(Math.abs(1000-TimeUtils.millis()%2000)/1000f) -100, y); //easeInOut(frame, 0, x, 60)-100
			draw.end();
			//100 * ease.apply(Math.abs(1000-TimeUtils.millis()%2000)/1000f) -100
			//Gdx.app.log("", "Delta Time: " + frame);			
			if(Gdx.input.isButtonPressed(Keys.ANY_KEY))
			{
				state = 1;
			}
		}
		else if (state == 1)
		{
			//Drawing button state
			/*
			shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.setColor(Color.BLACK);
			shapeRenderer.rect(0, 478, 450*ease.apply(size_rec), 72);
			shapeRenderer.rect(0, 328, 450*ease.apply(size_rec), 72);
			shapeRenderer.end();

			draw.begin();
			text_drawer.setColor(1, 1, 1, fade_time);
			text_drawer.draw(draw, "START", 200, 540);
			text_drawer.draw(draw, "EXIT", 200+55, 390);
			draw.end();
			*/
			
			button.setSize(size_font, size_font);
			stage.act();
			stage.draw();
			
			
			
			if (fade_time < 1.0 && size_rec >= 1) {fade_time += 0.025;}
			if (size_rec < 1.0) {size_rec += 0.025;}
			
			if (mouse_state == 0 && size_font <= 56) {size_font += 1;}
			if (mouse_state == 1 && size_font >= 40) {size_font -= 1;}
		}
		
		

		//x++;
		//y++;
		
		
	}
}
