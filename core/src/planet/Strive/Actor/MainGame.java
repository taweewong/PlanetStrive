package planet.Strive.Actor;

import com.badlogic.gdx.*;

public class MainGame extends Game{
	private GameScreen gameScreen;

	@Override
	public void create() {
		gameScreen = new GameScreen(this);
		this.setScreen(gameScreen);
	}

	@Override
	public void render() {
		super.render();

	}

	@Override
	public void dispose() {

	}
	
}
