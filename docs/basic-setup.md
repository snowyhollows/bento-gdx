# Basic setup

build.gradle must contain:

buildscript classpath dependency for:
  
  classpath "net.ltgt.gradle:gradle-apt-plugin:0.19"

Just to make things simpler to manage, add versions to ext:
        bentoVersion = '1.2'
        bentoGeneratorVersion = '1.1-SNAPSHOT'
        bentoGdxVersion = '1.2-SNAPSHOT'

desktop dependencies:

        compile "net.snowyhollows.bento:bento2-core:$bentoVersion"
        compile "net.snowyhollows.bento:bento2-gdx:$bentoGdxVersion"

html dependencies:

        compile "net.snowyhollows.bento:bento2-core:$bentoVersion"
        compile "net.snowyhollows.bento:bento2-gdx:$bentoGdxVersion"

core - additional plugin:

    apply plugin: "net.ltgt.apt-idea"

dependencies:

        compile "net.snowyhollows.bento:bento2-core:$bentoVersion"
        compile "net.snowyhollows.bento:bento2-gdx:$bentoGdxVersion"
        annotationProcessor "net.snowyhollows.bento:bento2-generator:$bentoGeneratorVersion"


## See if it works

This is the default blank gdx app:

public class FruitTwistBootstrap extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}

We create a version that will have all the dependencies injected:

public class FruitTwist extends ApplicationAdapter {

    private final SpriteBatch batch;
    private final Texture img;

    @WithFactory
    public FruitTwist(
            @ByFactory(SpriteBatchFactory.class) SpriteBatch batch,
            @ByFactory(TextureFactory.class) Texture img) {
        this.batch = batch;
        this.img = img;
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();
    }

    @Override
    public void dispose () {
        batch.dispose();
        img.dispose();
    }
}


And we modify the original skeleton to:

 - configure bento instance (this will include setting global configuration)
 - create the initial singleton (that's the FruitTwist game)
 - delegate the lifecycle methods to the singleton

public class FruitTwistBootstrap extends ApplicationAdapter {
    private FruitTwist fruitTwist;

	@Override
	public void create () {
        Bento bento = Bento.createRoot();
	    fruitTwist = bento.get(FruitTwistFactory.IT);
	}

    @Override
    public void render() {
        fruitTwist.render();
    }

    @Override
    public void dispose() {
        fruitTwist.dispose();
    }
}

It will turn out that we need to configure some more stuff.
This is why we do:

        bento.register("texture.filename", "badlogic.jpg");

