package planet.Strive.Actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class RangerSatellite extends SpaceRanger{
    private Texture texture = new Texture("orbit blue.png");
    private Sprite satellite;
    private Circle cir;
    private Circle cir_atk_b;
    private ShapeRenderer circle = new ShapeRenderer();
    private ShapeRenderer hpbar = new ShapeRenderer();
    private EnemyRanger target;

    private int speed = 75;
    private boolean selected = false;
    private float max_hp = 1500;
    private float hp = max_hp;
    private int atk_distance = 310;
    private boolean destroy = false;
    private double shoot_time = 0;
    private int status = 0; //0 = idle, 1 = move, 2 = idle attack, 3 = attack.
    private int cost = 3000;

    private int x;
    private int y;

    public RangerSatellite() {}

    public RangerSatellite(int x, int y) {
        setTouchable(Touchable.enabled);
        satellite = new Sprite(texture);
        satellite.setSize(texture.getWidth(), texture.getHeight());
        satellite.setOriginCenter();
        this.setSize(satellite.getWidth(), satellite.getHeight());
        this.setOrigin(getWidth()/2, getHeight()/2);
        setBounds(getX(), getY(), satellite.getWidth(), satellite.getHeight());
        cir = new Circle();
        cir_atk_b = new Circle();
        this.setPosition(x, y);
        satellite.setPosition(x, y);
    }

    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
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
    public Circle getCir_atk() {
        return this.cir_atk_b;
    }

    @Override
    public void setCir_atk(Circle cir_atk) {
        this.cir_atk_b = cir_atk;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public void selected() {
        this.selected = true;
    }

    @Override
    public void deSelected() {
        this.selected = false;
    }

    @Override
    public EnemyRanger getTarget() {
        return this.target;
    }

    @Override
    public void setTarget(EnemyRanger target) {
        this.target = target;
    }

    @Override
    public void setShoot_time(long shoot_time) {
        this.shoot_time = shoot_time;
    }

    @Override
    public void subHp(float hp) {
        this.hp -= hp;
    }

    @Override
    public float getHp() {
        return hp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public int getAtk_distance() {
        return atk_distance;
    }

    @Override
    public void setAtk_distance(int atk_distance) {
        this.atk_distance = atk_distance;
    }

    @Override
    public boolean isDestroy() {
        return destroy;
    }

    @Override
    public void setDestroy(boolean destroy) {
        this.destroy = destroy;
    }

    @Override
    public double getShoot_time() {
        return shoot_time;
    }

    public void setShoot_time(double shoot_time) {
        this.shoot_time = shoot_time;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        satellite.setPosition(this.getX(), this.getY());
    }



    @Override
    protected void rotationChanged() {
        super.rotationChanged();
        satellite.setRotation(this.getRotation());
    }



    @Override
    public void act(float delta) {
        super.act(delta);
        if (hp <= 0) {
            hp = 0;
            destroy = true;
        }

        this.setPosition(getX(), getY());
        satellite.setPosition(this.getX(), this.getY());
        satellite.setRotation(this.getRotation());
        this.cir.setRadius(satellite.getWidth()/2);
        this.cir.setPosition(satellite.getX() + (satellite.getWidth()/2), satellite.getY() + (satellite.getHeight()/2));
        this.cir_atk_b.setRadius(atk_distance+10);
        this.cir_atk_b.setPosition(satellite.getX() + (satellite.getWidth()/2), satellite.getY() + (satellite.getWidth()/2));
        //System.out.println(this.cir_atk_b);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //super.draw(batch, parentAlpha);
        batch.setProjectionMatrix(GameScreen.camera.combined);

        batch.end();
        hpbar.setProjectionMatrix(GameScreen.camera.combined);
        hpbar.begin(ShapeRenderer.ShapeType.Filled);
        hpbar.setColor(Color.GREEN);
        hpbar.rect(satellite.getX(), satellite.getY()-5, (satellite.getWidth()*(hp/max_hp)*100)/100, 5);
        hpbar.end();
        batch.begin();

        satellite.draw(batch);

    }
}
