package planet.Strive.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.utils.Timer;
//import com.sun.org.apache.xpath.internal.operations.String;
import com.mygdx.game.Airunit;

public class SpaceRanger extends Actor{
	protected Texture texture = new Texture("blue ranger.png");
	private Sprite clash = new Sprite(texture);
    private ShapeRenderer circle = new ShapeRenderer();
    private Rectangle rect;
    private Circle cir;
    private Circle cir_atk;
    private EnemyRanger target;
    private ShapeRenderer hpbar;
    protected boolean destroy = false;
    protected float max_hp = 200;
    protected float hp = max_hp;
    private double shoot_time = 0;
    private int atk_distance = 150;
    private boolean selected = false;
    private int status; //0 = idle, 1 = move, 2 = idle attack, 3 = attack.
    private int speed = 200;
    private int cost = 250;
	public SpaceRanger() {
		setTouchable(Touchable.enabled);
        hpbar = new ShapeRenderer();
		clash.setSize(texture.getWidth(), texture.getHeight());
		clash.setOriginCenter();
        this.setOrigin(clash.getWidth()/2, clash.getHeight()/2);
        this.setSize(clash.getWidth(), clash.getHeight());
		setBounds(getX(), getY(), clash.getWidth(), clash.getHeight());
        rect = new Rectangle();
        cir = new Circle();
        cir_atk = new Circle();
        status = 0;

	}

    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isSelected() {
        return selected;
    }

    public void selected() {
        selected = true;
    }

    public void deSelected() {
        selected = false;
    }

    public Rectangle getRect() {
        return rect;
    }

    public int getAtk_distance() { return atk_distance; }
    public void setAtk_distance(int atk_distance) { this.atk_distance = atk_distance; }

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Circle getCir() {
        return this.cir;
    }
    public void setCir(Circle cir) {
        this.cir = cir;
    }

    public Circle getCir_atk() {
        return cir_atk;
    }
    public void setCir_atk(Circle cir_atk) {
        this.cir_atk = cir_atk;
    }

    public EnemyRanger getTarget() {
        return target;
    }
    public void setTarget(EnemyRanger target) {
        this.target = target;
    }

    public double getShoot_time() {
        return shoot_time;
    }
    public void setShoot_time(long shoot_time) {
        this.shoot_time = shoot_time;
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

    public boolean isDestroy() {
        return destroy;
    }
    public void setDestroy(boolean destroy) {
        this.destroy = destroy;
    }

    @Override
	protected void positionChanged() {
        super.positionChanged();
		clash.setPosition(getX(), getY());
	}
	
	
	
	@Override
	protected void rotationChanged() {
        super.rotationChanged();
		clash.setRotation(getRotation());
	}



	@Override
	public void act(float delta) {
        super.act(delta);
        if (hp <= 0) {
            hp = 0;
            destroy = true;
        }
		clash.setPosition(getX(), getY());
        clash.setRotation(getRotation());
        rect.setSize(clash.getWidth(), clash.getHeight());
        rect.setPosition(clash.getX(), clash.getY());
        cir.setRadius(70*clash.getWidth()/100);
        cir.setPosition(clash.getX() + (clash.getWidth()/2), clash.getY() + (clash.getHeight()/2));
        cir_atk.setRadius(atk_distance+10);
        cir_atk.setPosition(clash.getX() + (clash.getWidth()/2), clash.getY() + (clash.getWidth()/2));
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.setProjectionMatrix(GameScreen.camera.combined);
        if (selected == true) {
            batch.end();
            circle.setProjectionMatrix(GameScreen.camera.combined);
            circle.begin(ShapeRenderer.ShapeType.Line);
            circle.setColor(Color.GREEN);
            circle.circle(clash.getX() + clash.getWidth()/2, clash.getY() + clash.getHeight()/2, this.getWidth()/2+10);
            circle.end();
            batch.begin();

        }

        batch.end();
        hpbar.setProjectionMatrix(GameScreen.camera.combined);
        hpbar.begin(ShapeRenderer.ShapeType.Filled);
        hpbar.setColor(Color.GREEN);
        hpbar.rect(clash.getX(), clash.getY()-5, (clash.getWidth()*(hp/max_hp)*100)/100, 5);
        hpbar.end();
        batch.begin();

        clash.draw(batch);

	}


}
