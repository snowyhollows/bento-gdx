# Basic setup

## generate libgdx project and add bento to build.gradle

_build.gradle_ must contain:

buildscript classpath dependency for:
  
		classpath "net.ltgt.gradle:gradle-apt-plugin:0.19"

Just to make things simpler to manage, add versions to _allproject ext_:

        bentoVersion = '1.2'
        bentoGeneratorVersion = '1.1-SNAPSHOT'
        bentoGdxVersion = '1.2-SNAPSHOT'

desktop / android / html dependencies:

        compile "net.snowyhollows.bento:bento2-core:$bentoVersion"
        compile "net.snowyhollows.bento:bento2-gdx:$bentoGdxVersion"

core - requires additional plugin and more dependencies:


    apply plugin: "net.ltgt.apt-idea"

	dependencies {
		
		.....

        compile "net.snowyhollows.bento:bento2-core:$bentoVersion"
        compile "net.snowyhollows.bento:bento2-gdx:$bentoGdxVersion"
        annotationProcessor "net.snowyhollows.bento:bento2-generator:$bentoGeneratorVersion"
    }


## Add simple code, see if it works

This is the default blank gdx app (assuming we called the initial class _Bootstrap_):

	public class Bootstrap extends ApplicationAdapter {
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
	
We create a version that will have all the dependencies injected (no _new_ whatsoever):

	public class FirstScene extends ApplicationAdapter {
	
	    private final SpriteBatch batch;
	    private final Texture img;
	
	    @WithFactory
	    public FirstScene(
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

 - create a `Bento` instance
 - register any required configuration (in Bento "configuration values" and actual objects are not separated, any value is just another dependency).
 - ask the container to create the initial scene
 - delegate the lifecycle methods to the acquired scene

This is the only place in our code where we need to touch the `Bento` object - any scene dependencies will have their own dependencies injected in a transitive manner.

	public class Bootstrap extends ApplicationAdapter {
	    private ApplicationListener scene;
	
		@Override
		public void create () {
	        Bento bento = Bento.createRoot();
	        bento.register("texture.filename", "badlogic.jpg");
		    scene = bento.get(FirstSceneFactory.IT);
		}
	
	    @Override
	    public void render() {
	        scene.render();
	    }
	
	    @Override
	    public void dispose() {
	        scene.dispose();
	    }
	}
	