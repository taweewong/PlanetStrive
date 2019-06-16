package planet.Strive.Flying;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Flying extends ApplicationAdapter{
	private SpriteBatch batch;
	private Texture clash;
	private Sprite aircraft;
	private float cursorX;
	private float cursorY;
	private float degrees;
	private int num = 0;
	private Vector2 vec;
	
	float pathX;
    float pathY;
    float distance;
    float directionX;
    float directionY;

	
	@Override
	public void create() {
		Gdx.input.setInputProcessor(new InputAdapter(){
			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				vec = new Vector2(screenX, Math.abs(screenY-1080));
				cursorX = vec.x;
				cursorY = vec.y;				
				//vec.sub(aircraft.getX(), aircraft.getY());
				//vec.nor();
				
				if(vec.x != aircraft.getX() || vec.y != aircraft.getY()){
		            pathX = vec.x - aircraft.getX();
		            pathY = vec.y - aircraft.getY();

		            distance = (float) Math.sqrt(pathX * pathX + pathY * pathY);
		            directionX = pathX / distance;
		            directionY = pathY / distance;

		            //aircraft.setX(aircraft.getX() + directionX * 1);
		            //aircraft.setY(aircraft.getY() + directionY * 1);
		        }
				
				
				
				//System.out.println(vec);				
				//System.out.println(cursorX + " " + cursorY);  
				return true;
			}
		});
		
		batch = new SpriteBatch();
		clash = new Texture(Gdx.files.internal("clash.png"));
		//System.out.println(clash.getWidth());
		aircraft = new Sprite(clash, 400, 400);
		aircraft.setSize(100, 100);
		aircraft.setOrigin(50, 50);
		//System.out.printf("%f %f", aircraft.getOriginX(), aircraft.getOriginX());
		
		
	}
	
	public void update() {
		if((cursorX <= (int) aircraft.getX()-5 || cursorX >= (int) aircraft.getX()+5)||(cursorY <= (int) aircraft.getY()-5 || cursorY >= (int) aircraft.getY()+5)) {
			aircraft.setX(aircraft.getX() + directionX * 5);
	        aircraft.setY(aircraft.getY() + directionY * 5);
	        
	        degrees = (float) ((Math.atan2 (cursorX - aircraft.getX(), -(cursorY - aircraft.getY()))*180.0d/Math.PI)+180);
	        
	        
	        System.out.println(vec);				
			System.out.println(aircraft.getX() + " " + aircraft.getY());
		}
		
	}
	
	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		aircraft.setRotation(num);
		update();
		aircraft.setRotation(degrees);
		/*
		cursorX = Gdx.input.getX();
		cursorY = Gdx.input.getY();
		aircraft.setX(cursorX-50);
		aircraft.setY(Math.abs(cursorY-1080)-50);
        */
		batch.begin();
		aircraft.draw(batch);
		batch.end();
		
	}
	
	@Override
	public void dispose() {
		
	}
}
