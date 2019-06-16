package planet.Strive.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class FontResource extends Actor {
    private BitmapFont bitmapFont;
    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
    private Texture mineral;
    private Texture rig;

    private int x;
    private int y;
    private float resource = 5000;
    private float rate;

    FontResource(int x, int y) {
        bitmapFont = new BitmapFont();
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("kenvector_future.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        mineral = new Texture("mineral bar.png");
        rig = new Texture("rig bar.png");

        fontParameter.size = 35;
        fontParameter.color = Color.BLACK;
        bitmapFont = fontGenerator.generateFont(fontParameter);

        rate = 0;

        this.x = x;
        this.y = y;

    }

    public float getResource() {
        return resource;
    }
    public void setResource(float resource) {
        this.resource = resource;
    }
    public void subResource(int resource) {
        this.resource -= resource;
    }

    public float getRate() {
        return rate;
    }
    public void setRate(float rate) {
        this.rate = rate;
    }
    public void plusRate(float rate) {
        this.rate += rate;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (resource < 50000)
        resource += Gdx.graphics.getDeltaTime()*(rate*25);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.draw(mineral, x, y);
        batch.draw(rig, x+350, y);
        bitmapFont.draw(batch, ""+ (int) resource, x+130, y+65);
        bitmapFont.draw(batch, ""+ (int) rate, x+500, y+65);

    }


}
