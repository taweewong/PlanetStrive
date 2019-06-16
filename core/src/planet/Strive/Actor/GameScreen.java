package planet.Strive.Actor;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.EnemyStar;
import com.mygdx.game.EnemyStar2;
import com.mygdx.game.EnemyStar3;
import com.mygdx.game.RangerStar;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


/**
 * Created by oat_t on 11/11/2016.
 */
public class GameScreen implements Screen, InputProcessor {

    private Stage stage;
    private SpaceRanger clash;
    private EnemyRanger enemy;
    private BattleShip battleship;
    private SpaceRanger selected;
    private EnemyBattleShip enemy_battleship;
    private RangerSatellite rangerSatellite;
    private Rig rig;
    private FontResource fontResource;
    private Actor chk_selected;
    private int units_count = 0;
    private ShapeRenderer rec_sl;
    private ShapeRenderer endGame;
    private boolean dragging = false;
    private int tempX;
    private int tempY;
    private int mX;
    private int mY;
    private int screenHeight;
    private Rectangle rec;
    private float distance;
    private Texture bg;
    private SpriteBatch batch;
    private SpriteBatch batchResult;
    private Random rand;
    private MainGame game;

    private int buildState = 0;
    private Texture satelliteBuilding;
    private Circle buildingChecker;

    private int rigNumber = 0;

    private int spawnTime = 0;
    private int spawnEnemyRanger = 30000;
    private int spawnRateEnemyRanger = 10000;

    private int b_spawnTime = 0;
    private int spawnEnemyBattleShip = 120000;
    private int spawnRateEnemyBattleShip = 15000;

    private BitmapFont showLevel;
    private BitmapFont bitmapFont;
    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;

    private float instant = 0;
    private float instant2 = 0;

    public static int level = 1;
    private Actor temp;

    //Air Unit ======================================================================
    public static OrthographicCamera camera;
    private TextureRegion backgroundTexture;
    private Vector3 worldCoor = new Vector3(0,0,0);
    float rotate_x;
    float rotate_y;
    float distance_airunit=100, alpha=0;

    private Explorer explorer;
    private EnemyStar enemyStar;
    private EnemyStar2 enemyStar2;
    private EnemyStar3 enemyStar3;
    private RangerStar rangerStar;
    private Stage stage_airunit;
    private Stage stage_airunit_ui;
    InputMultiplexer multiplexer;

    private int panning;

    //===============================================================================

    float total = 0;
    float b_total = 0;

    private ArrayList<SpaceRanger> ranger_units;
    private ArrayList<EnemyRanger> enemy_units;
    private ArrayList<Bullet> bullets;
    private ArrayList<BulletEnemy> bullets_e;
    private ArrayList<BulletBattleShip> bullets_b;
    private ArrayList<Point> points;
    private ArrayList<Explorer> explorers;
    private ArrayList<BattleShip> battleship_units;
    private ArrayList<EnemyBattleShip> enemyBattleShips_unit;
    private ArrayList<BulletEnemyBattleShip> bullets_ebs;
    private ArrayList<RangerSatellite> rangerSatellite_units;
    private ArrayList<Rig> rigs;

    //Button===============================================================================
    private TextureAtlas buttonAtlas;
    private Skin skin;
    private Table table;
    private TextButton.TextButtonStyle buttonStyle;
    private TextButton setting;

    //===============================
    private TextureAtlas buttonAtlas_Expo;
    private Skin skin_Expo;
    private Table table_Expo;
    private TextButton.TextButtonStyle buttonStyle_Expo;
    private TextButton setting_Expo;;
    //======================================================
    private TextureAtlas buttonAtlas_Mts;
    private Skin skin_Mts;
    private Table table_Mts;
    private TextButton.TextButtonStyle buttonStyle_Mts;
    private TextButton setting_Mts;

    //================================================
    private TextureAtlas buttonAtlas_orb;
    private Skin skin_orb;
    private Table table_orb;
    private TextButton.TextButtonStyle buttonStyle_orb;
    private TextButton setting_orb;
    //==================================================
    private TextureAtlas buttonAtlas_rig;
    private Skin skin_rig;
    private Table table_rig;
    private TextButton.TextButtonStyle buttonStyle_rig;
    private TextButton setting_rig;
    //================================================

    public GameScreen(MainGame gam) {
        super();
        game = gam;
        fontResource = new FontResource(50, 950);
        temp = new Actor();
        stage = new Stage();
        clash = new SpaceRanger();
        enemy = new EnemyRanger();
        battleship = new BattleShip();
        enemy_battleship = new EnemyBattleShip();
        rangerSatellite = new RangerSatellite();
        ranger_units = new ArrayList<SpaceRanger>();
        enemy_units = new ArrayList<EnemyRanger>();
        battleship_units = new ArrayList<BattleShip>();
        rig = new Rig();
        points = new ArrayList<Point>();
        bullets = new ArrayList<Bullet>();
        bullets_e = new ArrayList<BulletEnemy>();
        bullets_b = new ArrayList<BulletBattleShip>();
        explorers = new ArrayList<Explorer>();
        bullets_ebs = new ArrayList<BulletEnemyBattleShip>();
        enemyBattleShips_unit = new ArrayList<EnemyBattleShip>();
        rangerSatellite_units = new ArrayList<RangerSatellite>();
        rigs = new ArrayList<Rig>();


        satelliteBuilding = new Texture("orbit blue.png");
        buildingChecker = new Circle();
        buildingChecker.setRadius(satelliteBuilding.getWidth()/2);

        rec_sl = new ShapeRenderer();
        endGame = new ShapeRenderer();
        screenHeight = Gdx.graphics.getHeight();
        rec = new Rectangle();
        bg = new Texture("bg resize.png");
        batch = new SpriteBatch();
        batchResult = new SpriteBatch();
        rand = new Random();

        showLevel = new BitmapFont();
        bitmapFont = new BitmapFont();
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("kenvector_future.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        fontParameter.size = 100;
        fontParameter.color = Color.WHITE;
        fontParameter.shadowColor = Color.BLACK;
        fontParameter.shadowOffsetX = 10;
        fontParameter.shadowOffsetY = 10;

        bitmapFont = fontGenerator.generateFont(fontParameter);

        fontParameter.size = 40;
        fontParameter.color = Color.WHITE;
        fontParameter.shadowColor = Color.BLACK;
        fontParameter.shadowOffsetX = 10;
        fontParameter.shadowOffsetY = 10;

        showLevel = fontGenerator.generateFont(fontParameter);




        InputMultiplexer im = new InputMultiplexer();
        //MainInput main_in = new MainInput();

        //Gdx.input.setInputProcessor(im);

        //Create AirUnit============================================================
        Gdx.input.setInputProcessor(stage_airunit_ui);
        //InputMultiplexer multiplexer = new InputMultiplexer();
        batch = new SpriteBatch();
        stage_airunit = new Stage();
        stage_airunit_ui = new Stage();
        backgroundTexture = new TextureRegion(new Texture("bg big size.png"), 0, 0, 15360, 1200);
        camera = new OrthographicCamera(1920, 1080);
        camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera.position.set(15340-Gdx.graphics.getWidth()/2, camera.position.y, 0);
        camera.update();
        explorer = new Explorer();
        enemyStar = new EnemyStar();
        enemyStar2 = new EnemyStar2();
        enemyStar3 = new EnemyStar3();
        rangerStar = new RangerStar();

        im.addProcessor(this);
        im.addProcessor(stage);
        im.addProcessor(stage_airunit_ui);
        //Gdx.input.setInputProcessor(this);
        Gdx.input.setInputProcessor(im);
        panning = 0;

        //button /////////////////////////////////////////////////////////////////////////////////////////////////
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("kenvector_future.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        BitmapFont font = generator.generateFont(parameter);

        //action = new Action

        buttonAtlas = new TextureAtlas(Gdx.files.internal("Ship/ranger button/button.pack"));
        skin = new Skin(buttonAtlas);
        table = new Table(skin);
        table.setBounds(900, 400, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle style = new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle(font, com.badlogic.gdx.graphics.Color.WHITE);
        com.badlogic.gdx.scenes.scene2d.ui.Label tinyLabel = new com.badlogic.gdx.scenes.scene2d.ui.Label("" + clash.getCost(), style);
        tinyLabel.setFontScale(1.5f);
        buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.up = skin.getDrawable("green ranger");
        buttonStyle.down = skin.getDrawable("gray ranger");
        buttonStyle.font = font;
        buttonStyle.fontColor = com.badlogic.gdx.graphics.Color.WHITE;
        setting = new TextButton("", buttonStyle);
        setting.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {

                SpaceRanger clash = new SpaceRanger();

                if (fontResource.getResource() >= clash.getCost()) {
                    if (level == 1) {
                        clash.setPosition(rangerStar.getX()+rangerStar.getWidth()/2, 500);
                    }
                    else if (level == 2) {
                        clash.setPosition(enemyStar3.getX()+enemyStar3.getWidth()/2, 500);
                    }
                    else if (level == 3) {
                        clash.setPosition(enemyStar2.getX()+enemyStar2.getWidth()/2, 500);
                    }
                    else if (level == 4) {
                        clash.setPosition(enemyStar.getX()+enemyStar.getWidth()/2, 500);
                    }
                    stage.addActor(clash);
                    stage.setKeyboardFocus(clash);
                    ranger_units.add(clash); //Add unit to array.

                    fontResource.subResource(clash.getCost());
                }

            }

        });


        //A    air Expo = new Action==============================================================================
        buttonAtlas_Expo = new TextureAtlas(Gdx.files.internal("Ship/explore button/Expobutton.pack"));
        skin_Expo = new Skin(buttonAtlas_Expo);
        table_Expo = new Table(skin_Expo);
        table_Expo.setBounds(900, 200, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle style_Expo = new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle(font, com.badlogic.gdx.graphics.Color.WHITE);
        com.badlogic.gdx.scenes.scene2d.ui.Label tinyLabel_Expo = new com.badlogic.gdx.scenes.scene2d.ui.Label("" + explorer.getCost(), style_Expo);
        tinyLabel_Expo.setFontScale(1.5f);



        buttonStyle_Expo = new TextButton.TextButtonStyle();
        buttonStyle_Expo.up = skin_Expo.getDrawable("green explore");
        buttonStyle_Expo.down = skin_Expo.getDrawable("gray explore");
        buttonStyle_Expo.font = font;
        buttonStyle_Expo.fontColor = com.badlogic.gdx.graphics.Color.WHITE;
        setting_Expo = new TextButton("", buttonStyle_Expo);
        setting_Expo.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {

                Explorer explorer = new Explorer();

                if (fontResource.getResource() >= explorer.getCost() && explorers.isEmpty()) {
                    if (level == 1) {
                        explorer.setPosition(rangerStar.getX()+rangerStar.getWidth()/2, 500);
                    }
                    else if (level == 2) {
                        explorer.setPosition(enemyStar3.getX()+enemyStar3.getWidth()/2, 500);
                    }
                    else if (level == 3) {
                        explorer.setPosition(enemyStar2.getX()+enemyStar2.getWidth()/2, 500);
                    }
                    else if (level == 4) {
                        explorer.setPosition(enemyStar.getX()+enemyStar.getWidth()/2, 500);
                    }
                    stage.addActor(explorer);
                    stage.setKeyboardFocus(explorer);
                    explorers.add(explorer); //Add unit to array.

                    fontResource.subResource(explorer.getCost());
                }

            }
        });


        //Air Expo = new Action==============================================================================
        buttonAtlas_Mts = new TextureAtlas(Gdx.files.internal("Ship/mother button/button.pack"));
        skin_Mts = new Skin(buttonAtlas_Mts);
        table_Mts  = new Table(skin_Mts) ;
        table_Mts .setBounds(900, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle style_Mts = new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle(font, com.badlogic.gdx.graphics.Color.WHITE);
        com.badlogic.gdx.scenes.scene2d.ui.Label tinyLabel_Mts = new com.badlogic.gdx.scenes.scene2d.ui.Label("" + battleship.getCost(), style_Mts);
        tinyLabel_Mts.setFontScale(1.5f);


        buttonStyle_Mts  = new TextButton.TextButtonStyle();
        buttonStyle_Mts.up = skin_Mts.getDrawable("green mother");
        buttonStyle_Mts.down = skin_Mts.getDrawable("gray mother");
        buttonStyle_Mts.font = font;
        buttonStyle_Mts.fontColor = com.badlogic.gdx.graphics.Color.WHITE;
        setting_Mts = new TextButton("", buttonStyle_Mts);
        setting_Mts.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                BattleShip battleShip = new BattleShip();

                if (fontResource.getResource() >= battleShip.getCost()) {
                    if (level == 1) {
                        battleShip.setPosition(rangerStar.getX()+rangerStar.getWidth()/2, 500);
                    }
                    else if (level == 2) {
                        battleShip.setPosition(enemyStar3.getX()+enemyStar3.getWidth()/2, 500);
                    }
                    else if (level == 3) {
                        battleShip.setPosition(enemyStar2.getX()+enemyStar2.getWidth()/2, 500);
                    }
                    else if (level == 4) {
                        battleShip.setPosition(enemyStar.getX()+enemyStar.getWidth()/2, 500);
                    }
                    stage.addActor(battleShip);
                    stage.setKeyboardFocus(battleShip);
                    battleship_units.add(battleShip); //Add unit to array.

                    fontResource.subResource(battleShip.getCost());
                }

            }
        });



        //Air orbit = new Action==============================================================================
        buttonAtlas_orb = new TextureAtlas(Gdx.files.internal("Ship/orbit button/button.pack"));
        skin_orb = new Skin(buttonAtlas_orb);
        table_orb  = new Table(skin_orb) ;
        table_orb .setBounds(900, -200, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle style_orb = new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle(font, com.badlogic.gdx.graphics.Color.WHITE);
        com.badlogic.gdx.scenes.scene2d.ui.Label tinyLabel_orb = new com.badlogic.gdx.scenes.scene2d.ui.Label("" + rangerSatellite.getCost(), style_orb);
        tinyLabel_orb.setFontScale(1.5f);


        buttonStyle_orb  = new TextButton.TextButtonStyle();
        buttonStyle_orb.up = skin_orb.getDrawable("green orbit");
        buttonStyle_orb.down = skin_orb.getDrawable("gray orbit");
        buttonStyle_orb.font = font;
        buttonStyle_orb.fontColor = com.badlogic.gdx.graphics.Color.WHITE;
        setting_orb = new TextButton("", buttonStyle_orb);
        setting_orb.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (fontResource.getResource() >= rangerSatellite.getCost()) {
                    buildState = 1;
                }



            }
        });


        //Air rig = new Action==============================================================================
        buttonAtlas_rig = new TextureAtlas(Gdx.files.internal("Ship/rig button/button.pack"));
        skin_rig = new Skin(buttonAtlas_rig);
        table_rig  = new Table(skin_rig) ;
        table_rig .setBounds(900, -400, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle style_rig = new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle(font, com.badlogic.gdx.graphics.Color.WHITE);
        com.badlogic.gdx.scenes.scene2d.ui.Label tinyLabel_rig = new com.badlogic.gdx.scenes.scene2d.ui.Label("" + rig.getCost(), style_rig);
        tinyLabel_rig.setFontScale(1.5f);


        buttonStyle_rig  = new TextButton.TextButtonStyle();
        buttonStyle_rig.up = skin_rig.getDrawable("green rig");
        buttonStyle_rig.down = skin_rig.getDrawable("gray rig");
        buttonStyle_rig.font = font;
        buttonStyle_rig.fontColor = com.badlogic.gdx.graphics.Color.WHITE;
        setting_rig = new TextButton("", buttonStyle_rig);
        setting_rig.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                Rig rig = new Rig();

                if (fontResource.getResource() >= rig.getCost()) {
                    rig.setPosition(3200, 500);
                    stage.addActor(rig);
                    stage.setKeyboardFocus(rig);
                    rigs.add(rig); //Add unit to array.

                    fontResource.subResource(rig.getCost());

                    fontResource.plusRate(1);
                }

            }
        });


        //explorer.setPosition(1600, 500);
        table.add(setting);
        table.row();
        table.add(tinyLabel);
        table_Expo.add(setting_Expo);
        table_Expo.row();
        table_Expo.add(tinyLabel_Expo);
        table_Mts.add(setting_Mts);
        table_Mts.row();
        table_Mts.add(tinyLabel_Mts);
        table_orb.add(setting_orb);
        table_orb.row();
        table_orb.add(tinyLabel_orb);
        table_rig.add(setting_rig);
        table_rig.row();
        table_rig.add(tinyLabel_rig);
        stage.addActor(enemyStar);
        stage.addActor(enemyStar2);
        stage.addActor(enemyStar3);
        stage.addActor(rangerStar);
        stage_airunit_ui.addActor(table);
        stage_airunit_ui.addActor(table_Expo);
        stage_airunit_ui.addActor(table_Mts);
        stage_airunit_ui.addActor(table_orb);
        stage_airunit_ui.addActor(table_rig);
        stage_airunit_ui.addActor(fontResource);

        stage_airunit_ui.addActor(table);
        //==========================================================================

    }

    private void cameraControl() {
        switch(panning){
            case 1:
                if(camera.position.x>=30+(Gdx.graphics.getWidth()/2)){
                    camera.translate(-30, 0);
                    break;}
                break;
            case 2:
                if(camera.position.x<=15330-(Gdx.graphics.getWidth()/2)){
                    camera.translate(30, 0);
                    break;}
                break;
        }

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Button Set====================================================================================================
        if (fontResource.getResource() < clash.getCost()) {
            buttonStyle.up = skin.getDrawable("red ranger");
        }
        else {
            buttonStyle.up = skin.getDrawable("green ranger");
        }

        if (fontResource.getResource() < explorer.getCost()) {
            buttonStyle_Expo.up = skin_Expo.getDrawable("red explore");
        }
        else {
            buttonStyle_Expo.up = skin_Expo.getDrawable("green explore");
        }

        if (fontResource.getResource() < battleship.getCost()) {
            buttonStyle_Mts.up = skin_Mts.getDrawable("red mother");
        }
        else {
            buttonStyle_Mts.up = skin_Mts.getDrawable("green mother");
        }

        if (fontResource.getResource() < rangerSatellite.getCost()) {
            buttonStyle_orb.up = skin_orb.getDrawable("red orbit");
        }
        else {
            buttonStyle_orb.up = skin_orb.getDrawable("green orbit");
        }
        if (fontResource.getResource() < rig.getCost()) {
            buttonStyle_rig.up = skin_rig.getDrawable("red rig");
        }
        else {
            buttonStyle_rig.up = skin_rig.getDrawable("green rig");
        }
        //Button end.===================================================================================================


        rec_sl.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(backgroundTexture,0,0,backgroundTexture.getRegionWidth(), backgroundTexture.getRegionHeight());
        batch.end();


        worldCoor.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        //world_position.set(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 0);

        camera.unproject(worldCoor);

        mX = (int) worldCoor.x;
        mY = (int) worldCoor.y;


        if (level == 1) {
            spawnRateEnemyRanger = 10000;
            //spawnRateEnemyBattleShip
        }
        else if (level == 2) {
            spawnRateEnemyRanger = 5000;
            spawnRateEnemyBattleShip = 10000;
        }
        else if (level == 3) {
            spawnRateEnemyRanger = 2000;
            spawnRateEnemyBattleShip = 4000;
        }

        spawnTime += Gdx.graphics.getDeltaTime()*1000;
        if (spawnTime >= spawnRateEnemyRanger) {
            EnemyRanger e = new EnemyRanger();
            e.setPosition(-100, 100+rand.nextInt(700));
            enemy_units.add(e);
            stage.addActor(e);
            spawnTime = 0;

        }



        b_spawnTime += Gdx.graphics.getDeltaTime()*1000;
        if (b_spawnTime >= spawnRateEnemyBattleShip) {
            EnemyBattleShip e = new EnemyBattleShip();
            e.setPosition(-100, 100+rand.nextInt(700));
            enemyBattleShips_unit.add(e);
            stage.addActor(e);
            b_spawnTime = 0;

        }

        /*
        b_total += Gdx.graphics.getDeltaTime()*1000;

        if (b_total > 4000) {
            EnemyBattleShip eb = new EnemyBattleShip();
            eb.setPosition(-100, 100+rand.nextInt(700));
            enemyBattleShips_unit.add(eb);
            stage.addActor(eb);
            b_total = 0;
        }
        */

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        if (buildState == 1 || buildState == 2) {
            buildingChecker.setPosition(worldCoor.x , worldCoor.y );

            /*batch.end();
            rec_sl.begin(ShapeRenderer.ShapeType.Filled);
            rec_sl.setColor(Color.GREEN);
            rec_sl.circle(buildingChecker.x, buildingChecker.y, buildingChecker.radius);
            rec_sl.end();
            batch.begin();*/

            if (level == 1) {
                temp = rangerStar;
            }
            else if (level == 2) {
                temp = enemyStar3;
            }
            else if (level == 3) {
                temp = enemyStar2;
            }
            else if (level == 4) {
                temp = enemyStar;
            }

            if (worldCoor.x < temp.getX() - 2000 || buildingChecker.overlaps(rangerStar.getCir()) ||
                    buildingChecker.overlaps(enemyStar.getCir()) || buildingChecker.overlaps(enemyStar2.getCir()) ||
                    buildingChecker.overlaps(enemyStar3.getCir())) {
                buildState = 2;
            }
            else {
                buildState = 1;
            }
            if (buildState == 1) {
                for (RangerSatellite rs : rangerSatellite_units) {
                    if (rs.getCir().overlaps(buildingChecker)) {
                        buildState = 2;
                        break;
                    }
                    else {
                        buildState = 1;
                    }
                }
            }


            if (buildState == 1) {
                batch.setColor(0.3f, 1, 0.3f, 0.5f);
            }
            else if (buildState == 2) {
                batch.setColor(1f, 0.3f, 0.3f, 0.5f);
            }

            batch.begin();
            batch.draw(satelliteBuilding, worldCoor.x - satelliteBuilding.getWidth()/2, worldCoor.y - satelliteBuilding.getHeight()/2);
            batch.end();
            batch.setColor(1, 1, 1, 1);

            rec_sl.begin(ShapeRenderer.ShapeType.Line);
            rec_sl.setColor(Color.GREEN);
            rec_sl.line(temp.getX() - 2000 - rangerSatellite.getWidth(), 0, temp.getX() - 2000 - rangerSatellite.getWidth(), 1080);
            rec_sl.circle(rangerStar.getCir().x, rangerStar.getCir().y, rangerStar.getCir().radius);
            rec_sl.circle(enemyStar3.getCir().x, enemyStar3.getCir().y, enemyStar3.getCir().radius);
            rec_sl.circle(enemyStar2.getCir().x, enemyStar2.getCir().y, enemyStar2.getCir().radius);
            rec_sl.circle(enemyStar.getCir().x, enemyStar.getCir().y, enemyStar.getCir().radius);
            rec_sl.end();
        }

        //check status units
        for (SpaceRanger i : ranger_units) {
            if (!i.hasActions() && i.getStatus() == 1) {
                i.setStatus(0);
            }
        }

        for (BattleShip i : battleship_units) {
            if (!i.hasActions() && i.getStatus() == 1) {
                i.setStatus(0);
            }
        }

        //multiple selection
        if (dragging) {
            //System.out.println(tempX + " " + tempY + " " + mX + " " + mY);
            for (SpaceRanger i : ranger_units) {
                //Multi selected.
                if ((i.getX() + i.getWidth()/2 < Math.max(tempX, mX) && i.getX()  + i.getWidth()/2 > Math.min(tempX, mX)) && ((i.getY() + i.getHeight()/2) < Math.max(tempY, /*screenHeight - */mY) && i.getY() > Math.min(tempY, /*screenHeight - */mY))) {
                    i.selected();
                }
				/*else {
					i.deSelected();
				}*/

            }
            for (BattleShip i : battleship_units) {
                //Multi selected.
                if ((i.getX() + i.getWidth()/2 < Math.max(tempX, mX) && i.getX()  + i.getWidth()/2 > Math.min(tempX, mX)) && ((i.getY() + i.getHeight()/2) < Math.max(tempY, /*screenHeight - */mY) && i.getY() > Math.min(tempY, /*screenHeight - */mY))) {
                    i.selected();
                }
				/*else {
					i.deSelected();
				}*/

            }

            rec_sl.begin(ShapeRenderer.ShapeType.Line);
            rec_sl.setColor(com.badlogic.gdx.graphics.Color.GREEN);
            rec_sl.rect(tempX, tempY, mX - tempX, mY - tempY);
            rec_sl.end();



        }


        //Loop for Space Rangers.=======================================================================================
        for (SpaceRanger i : ranger_units) {
            //Check enemy.
            for (EnemyRanger e : enemy_units) {
                if (i.getCir_atk().overlaps(e.getCir())) {

                }
            }
            //System.out.println(i.getActions());
            //Idle and prepare to attack.
            if (i.getStatus() == 0) {
                i.setTarget(null);
                for (EnemyRanger e : enemy_units) {
                    if (i.getCir_atk().overlaps(e.getCir())) {
                        i.setTarget(e);
                        i.setStatus(2);
                        break;
                    }
                }

                for (EnemyBattleShip e : enemyBattleShips_unit) {
                    if (i.getCir_atk().overlaps(e.getCir())) {
                        i.setTarget(e);
                        i.setStatus(2);
                        break;
                    }
                }

            }

            if (i.getStatus() == 2) {

                if (i.getTarget().isDestroy() || i.getTarget() == null) {
                    i.setStatus(0);
                    //i.setTarget(null);
                }
                if (i.getCir_atk().overlaps(i.getTarget().getCir())) {
                    RotateToAction rta = new RotateToAction();
                    float degrees = (float) ((Math.atan2 (i.getTarget().getX() - i.getX(), -(i.getTarget().getY() - i.getY()))*180.0d/Math.PI)+180);
                    rta.setRotation(degrees);
                    rta.setDuration(0.2f);
                    i.addAction(rta);

                    if(i.getShoot_time() < TimeUtils.millis()){
                        i.setShoot_time(TimeUtils.millis()+500);
                        Bullet bullet = new Bullet(i, i.getTarget(), i.getRotation());
                        bullet.setPosition(i.getX() + i.getWidth()/2, i.getY() + i.getHeight()/2);
                        stage.addActor(bullet);
                        bullets.add(bullet);
                    }
                }
                else {
                    i.setTarget(null);
                    i.setStatus(0);
                }

            }

            //Fly follow enemy.
            if (i.getStatus() == 3) {

                if (i.getTarget().isDestroy() || i.getTarget() == null) {
                    i.setStatus(0);
                    i.clearActions();
                    //i.setTarget(null);
                }
                else if (i.getCir_atk().overlaps(i.getTarget().getCir())) {
                    if(i.getShoot_time() < TimeUtils.millis()){
                        i.setShoot_time(TimeUtils.millis()+500);
                        Bullet bullet = new Bullet(i, i.getTarget(), i.getRotation());
                        bullet.setPosition(i.getX() + i.getWidth()/2, i.getY() + i.getHeight()/2);
                        stage.addActor(bullet);
                        bullets.add(bullet);
                    }
                }

                if (i.getStatus() == 0) {
                    i.setTarget(null);
                }
                if (i.getTarget() != null) {
                    distance = (float) Math.sqrt(Math.pow(((i.getTarget().getX() + i.getTarget().getWidth()/2) - (i.getX()+ i.getWidth()/2)), 2) + Math.pow(((i.getTarget().getY() + i.getTarget().getHeight()/2) - (i.getY() + i.getWidth()/2)), 2));
                    float t = (i.getAtk_distance() + i.getTarget().getWidth()/2) / distance;

                    MoveToAction mta = new MoveToAction();
                    RotateToAction rta = new RotateToAction();
                    SequenceAction sa = new SequenceAction();
                    mta.setPosition(((1-t)*i.getTarget().getX()+(t*i.getX())), ((1-t)*i.getTarget().getY())+t*i.getY());
                    mta.setDuration((distance-i.getAtk_distance()) / i.getSpeed());
                    //mta.setInterpolation(new Interpolation.PowOut(3));

                    float degrees = (float) ((Math.atan2 (i.getTarget().getX() - i.getX(), -(i.getTarget().getY() - i.getY()))*180.0d/Math.PI)+180);
                    rta.setRotation(degrees);
                    rta.setDuration(0.2f);

                    i.addAction(rta);
                    //i.addAction(mta);
                    if (Math.sqrt(Math.pow((i.getTarget().getX() - i.getX()), 2) + Math.pow((i.getTarget().getY() - i.getY()), 2)) > i.getAtk_distance()) {
                        i.addAction(mta);
                    }


                    //i.addAction(sa);
                }

            }
        }
        //End Space Rangers loop.=======================================================================================

        //Loop for BattleShip.=========================================================================================
        for (BattleShip i : battleship_units) {
            //Check enemy.
            for (EnemyRanger e : enemy_units) {
                if (i.getCir_atk().overlaps(e.getCir())) {

                }
            }

            //System.out.println(i.getActions());
            //Idle and prepare to attack.
            if (i.getStatus() == 0) {
                i.setTarget(null);
                //System.out.println("main" + i.getCir_atk()); //wrong position
                for (EnemyRanger e : enemy_units) {
                    if (i.getCir_atk().overlaps(e.getCir())) {
                        //System.out.println("Hits");
                        i.setTarget(e);
                        i.setStatus(2);
                        break;
                    }


                }

                for (EnemyBattleShip e : enemyBattleShips_unit) {
                    if (i.getCir_atk().overlaps(e.getCir())) {
                        i.setTarget(e);
                        i.setStatus(2);
                        break;
                    }
                }

            }

            if (i.getStatus() == 2) {

                if (i.getTarget().isDestroy() || i.getTarget() == null) {
                    i.setStatus(0);
                    //i.setTarget(null);
                }



                if (i.getCir_atk().overlaps(i.getTarget().getCir())) {
                    //System.out.println("Hit!");
                    RotateToAction rta = new RotateToAction();
                    float degrees = (float) ((Math.atan2 (i.getTarget().getX() - i.getX(), -(i.getTarget().getY() - i.getY()))*180.0d/Math.PI)+180);
                    rta.setRotation(degrees);
                    rta.setDuration(0.2f);
                    i.addAction(rta);

                    if(i.getShoot_time() < TimeUtils.millis()){
                        i.setShoot_time(TimeUtils.millis()+2000);
                        BulletBattleShip bullet = new BulletBattleShip(i, i.getTarget(), i.getRotation(), 80);
                        bullet.setPosition(i.getX() + i.getWidth()/2, i.getY() + i.getHeight()/2);
                        stage.addActor(bullet);
                        bullets_b.add(bullet);
                    }
                }
                else {
                    i.setTarget(null);
                    i.setStatus(0);
                }

            }

            //Fly follow enemy.
            if (i.getStatus() == 3) {

                if (i.getTarget().isDestroy() || i.getTarget() == null) {
                    i.setStatus(0);
                    i.clearActions();
                    //i.setTarget(null);
                }
                else if (i.getCir_atk().overlaps(i.getTarget().getCir())) {
                    if(i.getShoot_time() < TimeUtils.millis()){
                        i.setShoot_time(TimeUtils.millis()+2000);
                        BulletBattleShip bullet = new BulletBattleShip(i, i.getTarget(), i.getRotation(), 80);
                        bullet.setPosition(i.getX() + i.getWidth()/2, i.getY() + i.getHeight()/2);
                        stage.addActor(bullet);
                        bullets_b.add(bullet);
                    }
                }

                if (i.getStatus() == 0) {
                    i.setTarget(null);
                }
                if (i.getTarget() != null) {
                    distance = (float) Math.sqrt(Math.pow(((i.getTarget().getX() + i.getTarget().getWidth()/2) - (i.getX()+ i.getWidth()/2)), 2) + Math.pow(((i.getTarget().getY() + i.getTarget().getHeight()/2) - (i.getY() + i.getWidth()/2)), 2));
                    float t = (i.getAtk_distance() + i.getTarget().getWidth()/2) / distance;

                    MoveToAction mta = new MoveToAction();
                    RotateToAction rta = new RotateToAction();
                    SequenceAction sa = new SequenceAction();
                    mta.setPosition(((1-t)*i.getTarget().getX()+(t*i.getX())), ((1-t)*i.getTarget().getY())+t*i.getY());
                    mta.setDuration((distance-i.getAtk_distance()) / i.getSpeed());
                    //mta.setInterpolation(new Interpolation.PowOut(3));

                    float degrees = (float) ((Math.atan2 (i.getTarget().getX() - i.getX(), -(i.getTarget().getY() - i.getY()))*180.0d/Math.PI)+180);
                    rta.setRotation(degrees);
                    rta.setDuration(0.2f);

                    i.addAction(rta);
                    //i.addAction(mta);
                    if (Math.sqrt(Math.pow((i.getTarget().getX() - i.getX()), 2) + Math.pow((i.getTarget().getY() - i.getY()), 2)) > i.getAtk_distance()) {
                        i.addAction(mta);
                    }


                    //i.addAction(sa);
                }

            }
        }
        //End BattleShip loop.==========================================================================================

        //Loop for Satellite.===========================================================================================
        for (RangerSatellite i : rangerSatellite_units) {

            //Idle and prepare to attack.
            if (i.getStatus() == 0) {
                i.setTarget(null);
                //System.out.println("main" + i.getCir_atk()); //wrong position
                for (EnemyRanger e : enemy_units) {
                    if (i.getCir_atk().overlaps(e.getCir())) {
                        //System.out.println("Hits");
                        i.setTarget(e);
                        i.setStatus(2);
                        break;
                    }


                }

                for (EnemyBattleShip e : enemyBattleShips_unit) {
                    if (i.getCir_atk().overlaps(e.getCir())) {
                        i.setTarget(e);
                        i.setStatus(2);
                        break;
                    }
                }

            }

            if (i.getStatus() == 2) {

                if (i.getTarget().isDestroy() || i.getTarget() == null) {
                    i.setStatus(0);
                    //i.setTarget(null);
                }



                if (i.getCir_atk().overlaps(i.getTarget().getCir())) {
                    //System.out.println("Hit!");
                    RotateToAction rta = new RotateToAction();
                    float degrees = (float) ((Math.atan2 (i.getTarget().getX() - i.getX(), -(i.getTarget().getY() - i.getY()))*180.0d/Math.PI)+180);
                    rta.setRotation(degrees);
                    rta.setDuration(0.2f);
                    i.addAction(rta);

                    if(i.getShoot_time() < TimeUtils.millis()){
                        i.setShoot_time(TimeUtils.millis()+1500);
                        BulletBattleShip bullet = new BulletBattleShip(i, i.getTarget(), i.getRotation(), 50);
                        bullet.setPosition(i.getX() + i.getWidth()/2, i.getY() + i.getHeight()/2);
                        stage.addActor(bullet);
                        bullets_b.add(bullet);
                    }
                }
                else {
                    i.setTarget(null);
                    i.setStatus(0);
                }

            }
        }
        //End Satellite loop.===========================================================================================

        //Loop for Enemy Rangers.=======================================================================================
        for (EnemyRanger j : enemy_units) {
            for (SpaceRanger s : ranger_units) {
                if (j.getCir_atk().overlaps(s.getCir())) {
                    j.setStatus(1);
                    j.setTarget(s);
                }
            }

            for (Explorer e : explorers) {
                if (j.getCir_atk().overlaps(e.getCir())) {
                    j.setStatus(1);
                    j.setTarget(e);
                }
            }

            for (BattleShip b : battleship_units) {
                if (j.getCir_atk().overlaps(b.getCir())) {
                    j.setStatus(1);
                    j.setTarget(b);
                }
            }

            for (RangerSatellite rs : rangerSatellite_units) {
                if (j.getCir_atk().overlaps(rs.getCir())) {
                    j.setStatus(1);
                    j.setTarget(rs);
                }
            }

            if (j.getCir_atk().overlaps(rangerStar.getCir())/* && level >= 1*/) {
                j.setStatus(1);
                j.setTarget(rangerStar);
            }
            if (j.getCir_atk().overlaps(enemyStar3.getCir())/* && level >= 2*/) {
                if (level < 2) {
                    j.setStatus(0);
                    j.setTarget(null);
                }
                else {
                    j.setStatus(1);
                    j.setTarget(enemyStar3);
                }

            }
            if (j.getCir_atk().overlaps(enemyStar2.getCir())/* && level >= 3*/) {
                if (level < 3) {
                    j.setStatus(0);
                    j.setTarget(null);
                } else {
                    j.setStatus(1);
                    j.setTarget(enemyStar2);
                }
            }

            if (j.getStatus() == 0) {
                distance = (float) Math.sqrt(Math.pow((15300 - j.getX()), 2) + Math.pow((j.getY() - j.getY()), 2));
                RotateToAction rta = new RotateToAction();
                MoveToAction mta = new MoveToAction();
                mta.setPosition(15300, j.getY());
                mta.setDuration(distance / j.getSpeed());
                rta.setRotation(270);
                rta.setDuration(0.2f);
                j.addAction(mta);
                j.addAction(rta);
            }

            if (j.getStatus() == 1) {
                if (j.getTarget().isDestroy() || j.getTarget() == null) {
                    j.setStatus(0);
                }
                if (j.getCir_atk().overlaps(j.getTarget().getCir())) {
                    j.clearActions();
                    RotateToAction rta = new RotateToAction();
                    float degrees = (float) ((Math.atan2 (j.getTarget().getX()+(j.getTarget().getWidth()/2) - j.getX(), -(j.getTarget().getY()+(j.getTarget().getHeight()/2) - j.getY()))*180.0d/Math.PI)-180);
                    rta.setRotation(degrees);
                    rta.setDuration(0.2f);
                    j.addAction(rta);

                    if(j.getShoot_time() < TimeUtils.millis()){
                        j.setShoot_time(TimeUtils.millis()+500);
                        BulletEnemy bullet = new BulletEnemy(j, j.getTarget(), j.getRotation());
                        bullet.setPosition(j.getX() + j.getWidth()/2, j.getY() + j.getHeight()/2);
                        stage.addActor(bullet);
                        bullets_e.add(bullet);
                    }
                }
                else {
                    j.setTarget(null);
                    j.setStatus(0);
                }
            }


        }

        //End Enemy Ranger loop.========================================================================================

        //Loop for Enemy Battle Ship.===================================================================================
        for (EnemyBattleShip j : enemyBattleShips_unit) {
            for (SpaceRanger s : ranger_units) {
                if (j.getCir_atk().overlaps(s.getCir())) {
                    j.setStatus(1);
                    j.setTarget(s);
                }
            }

            for (Explorer e : explorers) {
                if (j.getCir_atk().overlaps(e.getCir())) {
                    j.setStatus(1);
                    j.setTarget(e);
                }
            }

            for (BattleShip b : battleship_units) {
                if (j.getCir_atk().overlaps(b.getCir())) {
                    j.setStatus(1);
                    j.setTarget(b);
                }
            }

            for (RangerSatellite rs : rangerSatellite_units) {
                if (j.getCir_atk().overlaps(rs.getCir())) {
                    j.setStatus(1);
                    j.setTarget(rs);
                }
            }

            if (j.getCir_atk().overlaps(rangerStar.getCir())/* && level >= 1*/) {
                j.setStatus(1);
                j.setTarget(rangerStar);
            }
            if (j.getCir_atk().overlaps(enemyStar3.getCir())/* && level >= 2*/) {
                if (level < 2) {
                    j.setStatus(0);
                    j.setTarget(null);
                }
                else {
                    j.setStatus(1);
                    j.setTarget(enemyStar3);
                }

            }
            if (j.getCir_atk().overlaps(enemyStar2.getCir())/* && level >= 3*/) {
                if (level < 3) {
                    j.setStatus(0);
                    j.setTarget(null);
                }
                else {
                    j.setStatus(1);
                    j.setTarget(enemyStar2);
                }

            }

            if (j.getStatus() == 0) {
                distance = (float) Math.sqrt(Math.pow((15300 - j.getX()), 2) + Math.pow((j.getY() - j.getY()), 2));
                RotateToAction rta = new RotateToAction();
                MoveToAction mta = new MoveToAction();
                mta.setPosition(15300, j.getY());
                mta.setDuration(distance / j.getSpeed());
                rta.setRotation(270);
                rta.setDuration(0.2f);
                j.addAction(mta);
                j.addAction(rta);
            }

            if (j.getStatus() == 1) {
                if (j.getTarget().isDestroy() || j.getTarget() == null) {
                    j.setStatus(0);
                }
                if (j.getCir_atk().overlaps(j.getTarget().getCir())) {
                    j.clearActions();
                    RotateToAction rta = new RotateToAction();
                    float degrees = (float) ((Math.atan2 (j.getTarget().getX()+j.getTarget().getWidth()/2 - j.getX(), -(j.getTarget().getY()+j.getTarget().getHeight()/2 - j.getY()))*180.0d/Math.PI)+180);
                    rta.setRotation(degrees);
                    rta.setDuration(0.2f);
                    j.addAction(rta);

                    if(j.getShoot_time() < TimeUtils.millis()){
                        j.setShoot_time(TimeUtils.millis()+2200);
                        BulletEnemyBattleShip bullet_ebs = new BulletEnemyBattleShip(j, j.getTarget(), j.getRotation());
                        bullet_ebs.setPosition(j.getX() + j.getWidth()/2, j.getY() + j.getHeight()/2);
                        stage.addActor(bullet_ebs);
                        bullets_ebs.add(bullet_ebs);
                    }
                }
                else {
                    j.setTarget(null);
                    j.setStatus(0);
                }
            }


        }

        //End Enemy Battle Ship loop.===================================================================================

        //Remove Zone.==================================================================================================
        Iterator<Bullet> iter = bullets.iterator();
        while(iter.hasNext()){
            Bullet temp = iter.next();
            if(temp.isRemove()){
                temp.remove();
                iter.remove();
            }
        }

        Iterator<EnemyRanger> iter_enemy = enemy_units.iterator();
        while(iter_enemy.hasNext()){
            EnemyRanger temp = iter_enemy.next();
            if(temp.isDestroy()){
                temp.remove();
                iter_enemy.remove();
            }
        }

        Iterator<BulletEnemy> iter_b = bullets_e.iterator();
        while(iter_b.hasNext()){
            BulletEnemy temp = iter_b.next();
            if(temp.isRemove()){
                temp.remove();
                iter_b.remove();
            }
        }

        Iterator<BulletBattleShip> iter_bat = bullets_b.iterator();
        while(iter_bat.hasNext()){
            BulletBattleShip temp = iter_bat.next();
            if(temp.isRemove()){
                temp.remove();
                iter_bat.remove();
            }
        }

        Iterator<BulletEnemyBattleShip> iter_bebs = bullets_ebs.iterator();
        while(iter_bebs.hasNext()){
            BulletEnemyBattleShip temp = iter_bebs.next();
            if(temp.isRemove()){
                temp.remove();
                iter_bebs.remove();
            }
        }

        Iterator<SpaceRanger> iter_s = ranger_units.iterator();
        while(iter_s.hasNext()){
            SpaceRanger temp = iter_s.next();
            if(temp.isDestroy()){
                temp.remove();
                iter_s.remove();
            }
        }

        Iterator<Explorer> iter_e = explorers.iterator();
        while(iter_e.hasNext()){
            Explorer temp = iter_e.next();
            if(temp.isDestroy()){
                temp.remove();
                iter_e.remove();
            }
        }

        Iterator<BattleShip> iter_bs = battleship_units.iterator();
        while(iter_bs.hasNext()){
            BattleShip temp = iter_bs.next();
            if(temp.isDestroy()){
                temp.remove();
                iter_bs.remove();
            }
        }

        Iterator<RangerSatellite> iter_rs = rangerSatellite_units.iterator();
        while(iter_rs.hasNext()){
            RangerSatellite temp = iter_rs.next();
            if(temp.isDestroy()){
                temp.remove();
                iter_rs.remove();
            }
        }

        Iterator<EnemyBattleShip> iter_ebs = enemyBattleShips_unit.iterator();
        while(iter_ebs.hasNext()){
            EnemyBattleShip temp = iter_ebs.next();
            if(temp.isDestroy()){
                temp.remove();
                iter_ebs.remove();
            }
        }



        //Render AirUnit===============================================================
        batch.setProjectionMatrix(camera.combined);




        //camera.unproject(world_position);


        rotate_x = (float) (Math.cos(alpha* MathUtils.degreesToRadians)*distance_airunit*2.5);
        rotate_y = (float) (Math.sin(alpha*MathUtils.degreesToRadians)*distance_airunit*2.5);
        alpha++;

        explorer.setPosition(explorer.getX(), explorer.getY());


        stage_airunit.act();
        stage_airunit_ui.act();
        cameraControl();
        stage_airunit.draw();
        stage_airunit_ui.draw();

        //Draw level
        batchResult.begin();
        showLevel.draw(batchResult, "Planet: " + level + " / 4", 950, 1020);
        batchResult.end();

        //Condition zone
        for (Explorer i : explorers) {
            if (i.getCir().overlaps(enemyStar3.getCir()) && level == 1) {
                level = 2;
                i.setDestroy(true);
            }
            else if (i.getCir().overlaps(enemyStar2.getCir()) && (level == 1 || level == 2)) {
                level = 3;
                i.setDestroy(true);
            }
            else if (i.getCir().overlaps(enemyStar.getCir()) && (level == 1 || level == 2 || level == 3)) {
                level = 4;
                i.setDestroy(true);
            }
        }

        if (rangerStar.getHp() <= 0) {
            if (instant < 1) {
                instant += Gdx.graphics.getDeltaTime()/2;
            }
            if (instant2 < 1 && instant >= 1) {
                instant2 += Gdx.graphics.getDeltaTime();
            }

            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            endGame.begin(ShapeRenderer.ShapeType.Filled);
            endGame.setColor(0, 0, 0, instant);
            endGame.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            endGame.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);

            batchResult.begin();
            batchResult.setColor(1, 1, 1, 0.5f);
            bitmapFont.setColor(1, 1, 1, instant);
            bitmapFont.draw(batchResult, "YOU LOSE", Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
            batchResult.end();


        }

        if (enemyStar2.getHp() <= 0) {
            level = 2;
            enemyStar2.setHp(10000);
        }
        if (enemyStar3.getHp() <= 0) {
            level = 1;
            enemyStar3.setHp(10000);
        }

        if (level == 4) {
            if (instant < 1) {
                instant += Gdx.graphics.getDeltaTime()/2;
            }
            if (instant2 < 1 && instant >= 1) {
                instant2 += Gdx.graphics.getDeltaTime();
            }

            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            endGame.begin(ShapeRenderer.ShapeType.Filled);
            endGame.setColor(0, 0, 0, instant);
            endGame.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            endGame.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);

            batchResult.begin();
            batchResult.setColor(1, 1, 1, 0.5f);
            bitmapFont.setColor(1, 1, 1, instant);
            bitmapFont.draw(batchResult, "YOU WIN", Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
            batchResult.end();


        }
        camera.update();
        //=============================================================================

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();

    }


    public SpaceRanger getClash() {
        return clash;
    }

    public void setClashAction(Action action) {
        clash.addAction(action);
    }



    //INPUT PROCESSOR -----------------------------------------------------------------------------
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.ENTER){
            units_count++;
            SpaceRanger clash = new SpaceRanger();
            clash.setPosition(2800, 500);
            clash.setName("clash_" + units_count);
            stage.addActor(clash);
            stage.setKeyboardFocus(clash);
            ranger_units.add(clash); //Add unit to array.
        }

        if (keycode == Input.Keys.Q) {
            BattleShip battleship = new BattleShip();
            battleship.setPosition(2800, 500);
            stage.addActor(battleship);
            stage.setKeyboardFocus(battleship);
            battleship_units.add(battleship); //Add unit to array.
        }

        if (keycode == Input.Keys.W) {
            RangerSatellite rangerSatellite = new RangerSatellite(500, 500);
            stage.addActor(rangerSatellite);
            stage.setKeyboardFocus(rangerSatellite);
            rangerSatellite_units.add(rangerSatellite); //Add unit to array.
        }

        if (keycode == Input.Keys.E) {
            rigNumber += 1;
            Rig rig = new Rig();

            if (fontResource.getResource() >= rig.getCost()) {
                rig.setPosition(3200, 500);
                stage.addActor(rig);
                stage.setKeyboardFocus(rig);
                rigs.add(rig); //Add unit to array.

                fontResource.subResource(rig.getCost());

                fontResource.plusRate(1);
            }
        }

        if (keycode == Input.Keys.P) {
            Explorer explorer = new Explorer();

            explorer.setHp(100000000);
            explorer.setSpeed(10000);
            if (explorers.isEmpty()) {
                if (level == 1) {
                    explorer.setPosition(rangerStar.getX()+rangerStar.getWidth()/2, 500);
                }
                else if (level == 2) {
                    explorer.setPosition(enemyStar3.getX()+enemyStar3.getWidth()/2, 500);
                }
                else if (level == 3) {
                    explorer.setPosition(enemyStar2.getX()+enemyStar2.getWidth()/2, 500);
                }
                else if (level == 4) {
                    explorer.setPosition(enemyStar.getX()+enemyStar.getWidth()/2, 500);
                }

                System.out.println(explorer.getHp());
                System.out.println(explorer.getSpeed());
                stage.addActor(explorer);
                stage.setKeyboardFocus(explorer);
                explorers.add(explorer); //Add unit to array.

                //fontResource.subResource(explorer.getCost());
            }
        }

        if(keycode == Input.Keys.B){
            game.setScreen(new TempScreen(game));
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            tempX = (int) worldCoor.x;
            tempY = (int) worldCoor.y;

            if (buildState == 1) {
                RangerSatellite satellite = new RangerSatellite(tempX - satelliteBuilding.getWidth()/2, tempY - satelliteBuilding.getHeight()/2);

                if (fontResource.getResource() >= satellite.getCost()) {
                    stage.addActor(satellite);
                    stage.setKeyboardFocus(satellite);
                    rangerSatellite_units.add(satellite); //Add unit to array.

                    fontResource.subResource(satellite.getCost());
                }

                buildState = 0;
            }


            if (selected == null) {
                for(SpaceRanger i : ranger_units) {
                    i.deSelected();
                }
                chk_selected = stage.hit(worldCoor.x, worldCoor.y, false);
                if (chk_selected != null && chk_selected.getClass() == SpaceRanger.class) {
                    selected = (SpaceRanger) chk_selected;
                    selected.selected();
                }

                for(BattleShip i : battleship_units) {
                    i.deSelected();
                }
                chk_selected = stage.hit(worldCoor.x, worldCoor.y, false);
                if (chk_selected != null && chk_selected.getClass() == BattleShip.class) {
                    selected = (BattleShip) chk_selected;
                    selected.selected();
                }
            }
            else {
                for(SpaceRanger i : ranger_units) {
                    i.deSelected();
                }
                //selected.deSelected();
                chk_selected = stage.hit(worldCoor.x, worldCoor.y, false);
                if (chk_selected != null) {
                    if (chk_selected.getClass() == SpaceRanger.class)
                        selected = (SpaceRanger) chk_selected;
                    selected.selected();
                } else {
                    selected = null;
                }

                for(BattleShip i : battleship_units) {
                    i.deSelected();
                }
                //selected.deSelected();
                chk_selected = stage.hit(worldCoor.x, worldCoor.y, false);
                if (chk_selected != null) {
                    if (chk_selected.getClass() == BattleShip.class)
                        selected = (BattleShip) chk_selected;
                    selected.selected();
                } else {
                    selected = null;
                }
            }

            if(mouseMoved(screenX, screenY)) {
                dragging = true;
            }
        }

        if (button == Input.Buttons.RIGHT) {
            if (buildState == 1 || buildState == 2) {
                buildState = 0;
            }
            points.clear();
            int loop_for_direction = 0;
            for(SpaceRanger i : ranger_units){

                if(i.isSelected()) {

                    chk_selected = stage.hit(worldCoor.x, worldCoor.y, false);

                    if (chk_selected != null && chk_selected.getClass() == EnemyRanger.class) {
                        //System.out.println("HIT");
                        //Go to enemy.
                        i.clearActions();
                        i.setTarget((EnemyRanger) chk_selected);
                        chk_selected = null;
                        //Find distance for moving to enemy with margin.
                        i.setStatus(3);
                    }

                    else if (chk_selected != null && chk_selected.getClass() == EnemyBattleShip.class) {
                        //System.out.println("HIT");
                        //Go to enemy.
                        i.clearActions();
                        i.setTarget((EnemyRanger) chk_selected);
                        chk_selected = null;
                        //Find distance for moving to enemy with margin.
                        i.setStatus(3);
                    }

                    else {
                        //GO to point.
                        i.clearActions();
                        MoveToAction mta = new MoveToAction();
                        RotateToAction rta = new RotateToAction();
                        SequenceAction sa = new SequenceAction();
                        float next_x = worldCoor.x - i.getWidth()/2;
                        float next_y = screenHeight - worldCoor.y + i.getHeight()/2;

                        for (Point p : points) {
                            if (p.getX() == (int) next_x && p.getY() == (int) next_y) {
                                while(true){
                                    int random_direction = loop_for_direction % 8;
                                    float tempX = next_x;
                                    float tempY = next_y;
                                    switch (random_direction) {
                                        case 0: next_x += i.getWidth();
                                            next_y += i.getHeight();
                                            break;
                                        case 1: next_y -= i.getHeight();
                                            break;
                                        case 2: next_x += i.getWidth();
                                            next_y -= i.getHeight();
                                            break;
                                        case 3: next_y += i.getHeight();
                                            break;
                                        case 4: next_x -= i.getWidth();
                                            next_y += i.getHeight();
                                            break;
                                        case 5: next_x -= i.getWidth();
                                            break;
                                        case 6: next_x -= i.getWidth();
                                            next_y -= i.getHeight();
                                            break;
                                        case 7: next_x += i.getWidth();
                                            break;
                                    }
                                    if (next_y > Gdx.graphics.getHeight() || next_y < 0  || next_x > backgroundTexture.getRegionWidth() || next_x < 0){
                                        loop_for_direction++;
                                        next_x = tempX;
                                        next_y = tempY;
                                    }
                                    else {
                                        break;
                                    }
                                }


                            }
                        }

                        loop_for_direction++;
                        mta.setPosition(next_x, screenHeight-next_y);
                        mta.setDuration((float) (Math.sqrt(Math.pow((worldCoor.x - i.getX()), 2) + Math.pow((worldCoor.y - i.getY()), 2))) / (i.getSpeed()+i.getSpeed()/2));
                        mta.setInterpolation(Interpolation.sineOut);

                        //System.out.println("setPosition x:"+(int)next_x+"y:"+(screenHeight-next_y));

                        points.add(new Point((int) next_x, (int) next_y));

                        float degrees = (float) ((Math.atan2 (worldCoor.x - (i.getX()+i.getWidth()/2), -(worldCoor.y - (i.getY()+i.getHeight()/2)))*180.0d/Math.PI)+180);;
                        rta.setRotation(degrees);
                        rta.setDuration(0.2f);

                        sa.addAction(rta);
                        sa.addAction(mta);

                        i.addAction(sa);
                        i.setStatus(1);
                    }

                }

            }

            //Battle Ship Click Right
            loop_for_direction = 0;
            for(BattleShip i : battleship_units){

                if(i.isSelected()) {

                    chk_selected = stage.hit(worldCoor.x, worldCoor.y, false);

                    if (chk_selected != null && chk_selected.getClass() == EnemyRanger.class) {
                        //System.out.println("HIT");
                        //Go to enemy.
                        i.clearActions();
                        i.setTarget((EnemyRanger) chk_selected);
                        chk_selected = null;
                        //Find distance for moving to enemy with margin.
                        i.setStatus(3);
                    }

                    else if (chk_selected != null && chk_selected.getClass() == EnemyBattleShip.class) {
                        //System.out.println("HIT");
                        //Go to enemy.
                        i.clearActions();
                        i.setTarget((EnemyRanger) chk_selected);
                        chk_selected = null;
                        //Find distance for moving to enemy with margin.
                        i.setStatus(3);
                    }

                    else {
                        //GO to point.
                        i.clearActions();
                        MoveToAction mta = new MoveToAction();
                        RotateToAction rta = new RotateToAction();
                        SequenceAction sa = new SequenceAction();
                        float next_x = worldCoor.x - i.getWidth()/2;
                        float next_y = screenHeight - worldCoor.y + i.getHeight()/2;

                        for (Point p : points) {
                            if (p.getX() == (int) next_x && p.getY() == (int) next_y) {
                                while(true){
                                    int random_direction = loop_for_direction % 8;
                                    float tempX = next_x;
                                    float tempY = next_y;
                                    switch (random_direction) {
                                        case 0: next_x += i.getWidth();
                                            next_y += i.getHeight();
                                            break;
                                        case 1: next_y -= i.getHeight();
                                            break;
                                        case 2: next_x += i.getWidth();
                                            next_y -= i.getHeight();
                                            break;
                                        case 3: next_y += i.getHeight();
                                            break;
                                        case 4: next_x -= i.getWidth();
                                            next_y += i.getHeight();
                                            break;
                                        case 5: next_x -= i.getWidth();
                                            break;
                                        case 6: next_x -= i.getWidth();
                                            next_y -= i.getHeight();
                                            break;
                                        case 7: next_x += i.getWidth();
                                            break;
                                    }
                                    if (next_y > Gdx.graphics.getHeight() || next_y < 0  || next_x > backgroundTexture.getRegionWidth() || next_x < 0){
                                        loop_for_direction++;
                                        next_x = tempX;
                                        next_y = tempY;
                                    }
                                    else {
                                        break;
                                    }
                                }


                            }
                        }

                        loop_for_direction++;
                        mta.setPosition(next_x, screenHeight-next_y);
                        mta.setDuration((float) (Math.sqrt(Math.pow((worldCoor.x - i.getX()), 2) + Math.pow((worldCoor.y - i.getY()), 2))) / (i.getSpeed()+i.getSpeed()/2));
                        mta.setInterpolation(Interpolation.sineOut);

                        //System.out.println("setPosition x:"+(int)next_x+"y:"+(screenHeight-next_y));

                        points.add(new Point((int) next_x, (int) next_y));

                        float degrees = (float) ((Math.atan2 (worldCoor.x - (i.getX()+i.getWidth()/2), -(worldCoor.y - (i.getY()+i.getHeight()/2)))*180.0d/Math.PI)+180);
                        rta.setRotation(degrees);
                        rta.setDuration(0.5f);

                        sa.addAction(rta);
                        sa.addAction(mta);

                        i.addAction(sa);
                        i.setStatus(1);
                    }

                }

            }
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        dragging = false;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        if(screenX < 20){
            panning = 1;
        }
        else if(screenX > 1900){
            panning = 2;
        }
        else{
            panning = 0;
        }
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        // TODO Auto-generated method stub
        return false;
    }

}
