package planet.Strive.Actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class EnemyBattleShip extends EnemyRanger{
    private Texture texture = new Texture("red mother.png");
    private Sprite enemy = new Sprite(texture);
    private Circle cir;
    private Circle cir_atk;
    private ShapeRenderer hpbar;
    private SpaceRanger/*SpaceRanger*/ target;
    private int atk_distance = 300;
    private int speed = 50;
    private double shoot_time = 0;
    private int status = 0; // 0 = normal, 1 = attack;
    private float max_hp = 1000;
    private float hp = max_hp;
    private boolean selected = true;
    private boolean destroy = false;

    public EnemyBattleShip() {
        setTouchable(Touchable.enabled);
        enemy.setSize(texture.getWidth(), texture.getHeight());
        enemy.setOriginCenter();
        enemy.setRotation(270);
        this.setOrigin(enemy.getWidth()/2, enemy.getHeight()/2);
        setBounds(getX(), getY(), enemy.getWidth(), enemy.getHeight());
        cir = new Circle();
        cir_atk = new Circle();
        hpbar = new ShapeRenderer();
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
        return cir_atk;
    }
    @Override
    public void setCir_atk(Circle cir_atk) {
        this.cir_atk = cir_atk;
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
    public void subHp(int hp) {
        this.hp -= hp;
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
    public int getStatus() {
        return status;
    }
    @Override
    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public SpaceRanger getTarget() {
        return target;
    }
    @Override
    public void setTarget(SpaceRanger target) {
        this.target = target;
    }

    @Override
    public double getShoot_time() {
        return shoot_time;
    }
    @Override
    public void setShoot_time(double shoot_time) {
        this.shoot_time = shoot_time;
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
    public int getSpeed() {
        return speed;
    }
    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        enemy.setPosition(getX(), getY());
    }



    @Override
    protected void rotationChanged() {
        super.rotationChanged();
        enemy.setRotation(getRotation());
    }



    @Override
    public void act(float delta) {
        super.act(delta);
        if (hp <= 0) {
            hp = 0;
            this.destroy = true;
        }
        this.setPosition(getX() /*+ Gdx.graphics.getDeltaTime()*100*/, getY());
        this.setRotation(getRotation());
        this.cir.setRadius(70*enemy.getWidth()/100);
        this.cir.setPosition(this.getX() + (this.getWidth()/2), this.getY() + (this.getHeight()/2));
        this.cir_atk.setRadius(this.atk_distance);
        this.cir_atk.setPosition(this.getX() + (this.getWidth()/2), this.getY()+ (this.getHeight()/2));
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        //super.draw(batch, parentAlpha);
        batch.setProjectionMatrix(GameScreen.camera.combined);
        batch.end();
        hpbar.setProjectionMatrix(GameScreen.camera.combined);
        hpbar.begin(ShapeRenderer.ShapeType.Filled);
        hpbar.setColor(Color.GREEN);
        hpbar.rect(enemy.getX(), enemy.getY()-5, (enemy.getWidth()*(hp/max_hp)*100)/100, 5);
        hpbar.end();
        batch.begin();
        enemy.draw(batch);
    }
}
