package planet.Strive.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

public class BulletEnemyBattleShip extends Actor{
    public static final int SPEED = 500;
    private Texture texture = new Texture(Gdx.files.internal("red mother laser.png"));
    private SpaceRanger enemy ;
    private EnemyRanger ranger;
    private Sprite bullet = new Sprite(texture);
    private Circle cir;

    private float rotate;
    private boolean remove = false;

    public BulletEnemyBattleShip(EnemyRanger ranger, SpaceRanger enemy, float rotate) {
        this.enemy = enemy;
        this.ranger = ranger;
        this.rotate = rotate;
        bullet.setRotation(rotate);
        this.setRotation(rotate);
        cir = new Circle();
        setTouchable(Touchable.disabled);
        bullet.setSize(texture.getWidth(), texture.getHeight());
        bullet.setOrigin(bullet.getWidth()/2, bullet.getHeight()/2);
        cir.setRadius(bullet.getWidth());
        this.setOrigin(bullet.getWidth()/2, bullet.getHeight()/2);
        setBounds(getX(), getY(), bullet.getWidth(), bullet.getHeight());


        /*
        float degrees = (float) ((Math.atan2 (enemy.getX()+(enemy.getWidth()/2) - bullet.getX(), -(enemy.getY()+(enemy.getHeight()/2)  - bullet.getY()))*180.0d/Math.PI)+180);
        bullet.setRotation(degrees);
        */

        /*
        MoveToAction mta = new MoveToAction();
        mta.setPosition(enemy.getX()+(enemy.getWidth()/2) , enemy.getY()+(enemy.getHeight()/2) );
        mta.setDuration(0.3f);

        RunnableAction ra = new RunnableAction();
        ra.setRunnable(new Runnable() {
            @Override
            public void run() {
                remove = true;
            }
        });

        SequenceAction sa = new SequenceAction();
        sa.addAction(mta);
        sa.addAction(ra);

        this.addAction(sa);
        */
    }

    public boolean isRemove() {
        return remove;
    }
    public void setRemove(boolean remove) {
        this.remove = remove;
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        bullet.setPosition(bullet.getX(), bullet.getY());

    }

    @Override
    protected void rotationChanged() {
        super.rotationChanged();
        bullet.setRotation(bullet.getRotation());

    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (enemy == null) {
            remove = true;
        }

        else {

            if (cir.overlaps(enemy.getCir())) {
                if (enemy.getHp() - 80 < 0) {
                    enemy.setHp(0);
                }
                else {
                    enemy.subHp(80);
                }
                remove = true;
            }

            MoveToAction mta = new MoveToAction();
            mta.setPosition(enemy.getX()+(enemy.getWidth()/2) , enemy.getY()+(enemy.getHeight()/2) );
            mta.setDuration(0.5f);

            this.addAction(mta);


            bullet.setPosition(this.getX(), this.getY());
            float degrees = (float) ((Math.atan2 (enemy.getX()+(enemy.getWidth()/2) - bullet.getX(), -(enemy.getY()+(enemy.getHeight()/2)  - bullet.getY()))*180.0d/Math.PI)+180);
            bullet.setRotation(degrees);

            cir.setPosition(bullet.getX() + bullet.getWidth()/2, bullet.getY() + bullet.getHeight()/2);
        }


    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        bullet.draw(batch);
    }
}
