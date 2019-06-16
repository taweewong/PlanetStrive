package planet.Strive.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import planet.Strive.Actor.MainGame;


public class Explorer extends SpaceRanger{

	private Texture texture_ex = new Texture("blue explore.png");
    private Sprite explorer = new Sprite(texture_ex);
    private Circle cir;
    private ShapeRenderer hpbar;
    private boolean destroy = false;
    private float max_hp = 250;
    private float hp = max_hp;
    private int speed = 50;
    private boolean selected = true;
    private int cost = 25000;
    //private airunit camera;

    public Explorer() {
        setTouchable(Touchable.enabled);
        explorer.setSize(texture_ex.getWidth(), texture_ex.getHeight());
        //this.setOriginCenter();
        explorer.setRotation(90);
        explorer.setPosition(3200, 500);
        explorer.setOrigin(explorer.getWidth()/2, explorer.getHeight()/2);
        setBounds(getX(), getY(), this.getWidth(), this.getHeight());
        hpbar = new ShapeRenderer();
        cir = new Circle();
        cir.setRadius(50*explorer.getWidth()/100);
    }

    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void selected() {
        selected = true;
    }
    @Override
    public void deSelected() {
        selected = false;
    }

    @Override
    public Circle getCir() {
        return this.cir;
    }
    @Override
    public void setCir(Circle cir) {
        this.cir = cir;
    }

    @Override
    public boolean isDestroy() {
        return this.destroy;
    }
    @Override
    public void setDestroy(boolean destroy) {
        this.destroy = destroy;
    }

    @Override
    public void subHp(float hp) {
        this.hp -= hp;
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        explorer.setPosition(this.getX(), this.getY());
    }



    @Override
    protected void rotationChanged() {
        super.rotationChanged();
        explorer.setRotation(getRotation());
    }

    //((2569*explorer.getX())/1920)-Gdx.graphics.getDeltaTime()*100, explorer.getY()

    @Override
    public void act(float delta) {
        super.act(delta);
        if (hp <= 0) {
            destroy = true;
        }
        this.setPosition(explorer.getX(), explorer.getY());
        explorer.setPosition(explorer.getX()-Gdx.graphics.getDeltaTime()*speed, explorer.getY());
        explorer.setRotation(explorer.getRotation());
        this.cir.setPosition(explorer.getX() + (explorer.getWidth()/2), explorer.getY() + (explorer.getHeight()/2));
    }

    

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //super.draw(batch, parentAlpha);
    	batch.setProjectionMatrix(GameScreen.camera.combined);
        batch.end();
        hpbar.setProjectionMatrix(GameScreen.camera.combined);
        hpbar.begin(ShapeRenderer.ShapeType.Filled);
        hpbar.setColor(Color.GREEN);
        hpbar.rect(explorer.getX(), explorer.getY()-5, (explorer.getWidth()*(hp/max_hp)*100)/100, 5);
        hpbar.end();
        batch.begin();
        explorer.draw(batch);
        //batch.draw(texture, this.getX()/* - airunit.camera.position.x -Camera.x*/, this.getY());
    }

    @Override
    public boolean remove() {
        texture_ex.dispose();
        return super.remove();
    }
}
