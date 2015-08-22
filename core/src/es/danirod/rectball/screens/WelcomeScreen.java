package es.danirod.rectball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import es.danirod.rectball.AssetLoader;
import es.danirod.rectball.RectballGame;
import es.danirod.rectball.actors.Button;

public class WelcomeScreen extends AbstractScreen {

    public WelcomeScreen(RectballGame game) {
        super(game);
    }

    private Stage stage;

    private Table table;

    @Override
    public void show() {
        Texture tex = AssetLoader.get().get("ui/yellowpatch.png", Texture.class);
        NinePatch ninePatch = new NinePatch(tex, 6, 6, 6, 6);
        NinePatchDrawable drb = new NinePatchDrawable(ninePatch);

        FileHandle font = Gdx.files.internal("fonts/Play-Regular.ttf");
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(font);
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 72;
        BitmapFont buttonFont = generator.generateFont(parameter);
        generator.dispose();

        TextButtonStyle tbs = new TextButtonStyle(drb, drb, drb, buttonFont);

        Viewport v = new FitViewport(480, 640);
        stage = new Stage(v);
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        stage.addActor(table);
        table.setFillParent(true);



        TextButton playButton = new TextButton("PLAY", tbs);
        playButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(1);
            }
        });
        table.add(playButton).width(400).height(150).row();

        TextButton settingsButton = new TextButton("SETTINGS", tbs);
        settingsButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(4);
            }
        });
        table.add(settingsButton).padTop(50).width(400).height(150).row();
    }

    @Override
    public int getID() {
        return 3;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.8f, 0.8f, 0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }
}
