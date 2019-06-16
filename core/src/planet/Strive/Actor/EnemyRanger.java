package planet.Strive.Actor;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;


public class EnemyRanger extends Actor{
    private Texture texture = new Texture("red ranger.png");
    private Sprite enemy = new Sprite(texture);
    private Circle cir;
    private Circle cir_atk;
    private ShapeRenderer hpbar;
    private SpaceRanger/*SpaceRanger*/ target;
    private int atk_distance = 150;
    private int speed = 50;
    private double shoot_time = 0;
    private int status = 0; // 0 = normal, 1 = attack;
    private float max_hp = 100;
    private float hp = max_hp;
    private boolean selected = true;
    private boolean destroy = false;

    public EnemyRanger() {
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


    public Circle getCir() {
        return cir;
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

    public float getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public void subHp(int hp) {
        this.hp -= hp;
    }

    public boolean isDestroy() {
        return destroy;
    }
    public void setDestroy(boolean destroy) {
        this.destroy = destroy;
    }

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public SpaceRanger getTarget() {
        return target;
    }
    public void setTarget(SpaceRanger target) {
        this.target = target;
    }

    public double getShoot_time() {
        return shoot_time;
    }
    public void setShoot_time(double shoot_time) {
        this.shoot_time = shoot_time;
    }

    public int getAtk_distance() {
        return atk_distance;
    }
    public void setAtk_distance(int atk_distance) {
        this.atk_distance = atk_distance;
    }

    public int getSpeed() {
        return speed;
    }
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
        cir.setRadius(70*enemy.getWidth()/100);
        cir.setPosition(enemy.getX() + (enemy.getWidth()/2), enemy.getY()+ (enemy.getHeight()/2));
        cir_atk.setRadius(this.atk_distance);
        cir_atk.setPosition(enemy.getX() + (enemy.getWidth()/2), enemy.getY()+ (enemy.getHeight()/2));
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
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
