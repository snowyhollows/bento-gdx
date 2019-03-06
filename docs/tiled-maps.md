# Tiled maps

TiledMapRenderer built by TiledMapRendererFactory depends on:
 - TiledMap TiledMap built by TiledMapFactory,
 - SpriteBatch, built by SpriteBatchFactory
 
TiledMapFactory depends on:
 - String registered as "tiledmap.filename", which points to an internal file with the map

Since camera and renderer do not keep relation to themselves, we will need to call renderer's setView method in our logic.

We can inject both the camera and the renderer into our class and use them to display the map:

    private final OrthographicCamera camera;
    private final TiledMapRenderer tiledRenderer;

    @WithFactory
    public FruitTwist(
            @ByFactory(TiledMapRendererFactory.class) TiledMapRenderer batch,
            @ByFactory(OrthographicCameraFactory.class) OrthographicCamera camera) {
        this.tiledRenderer = batch;
        this.camera = camera;
        tiledRenderer.setView(camera);
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tiledRenderer.render();
    }


In order for that to work, we need to register the "tiledmap.filename" property in the bootstrap:

        Bento bento = Bento.createRoot();
        bento.register("tiledmap.filename", "map.tmx");
	    fruitTwist = bento.get(FruitTwistFactory.IT);


We can also use the FitViewport to control how much of the map we want to display.

FitViewport built with FitViewportFactory depends on:
 - OrthographicCamera built with OrthographicCameraFactory
 - float "fitviewport.width"
 - float "fitviewport.height"

This means that after we put the following settings into bootstrap:

and in bootstrap:

        Bento bento = Bento.createRoot();
        bento.register("tiledmap.filename", "map.tmx");
        bento.register("fitviewport.width", 320);
        bento.register("fitviewport.height", 240);
	    fruitTwist = bento.get(FruitTwistFactory.IT);

We can display the map by using:

    public class FruitTwist extends ApplicationAdapter {

    private final OrthographicCamera camera;
    private final FitViewport fitViewport;
    private final TiledMapRenderer tiledRenderer;

    @WithFactory
    public FruitTwist(
            @ByFactory(TiledMapRendererFactory.class) TiledMapRenderer batch,
            @ByFactory(OrthographicCameraFactory.class) OrthographicCamera camera,
            @ByFactory(FitViewportFactory.class) FitViewport fitViewport) {
        this.tiledRenderer = batch;
        this.camera = camera;
        this.fitViewport = fitViewport;
    }

    @Override
    public void resize(int width, int height) {
        fitViewport.update(width, height, true);
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tiledRenderer.setView(camera);
        tiledRenderer.render();
    }

    }

The crux of the Tiled integration is the ability to
create Ashley entities from the tilemap metadata.

The entities are created from tiled "objects", which come in variety of types:
 - tile objects,
 - rectangle shapes,
 
This is achieved using EngineFromTilemapPopulationService. The default factory depends on:
 - Engine, built by EngineFactory,
 - TiledMap, built by TiledmapFactory

it walks all the objects in the map and if it meets the "components" key in the metadata ("properties") of the object,
it:
 - creates an entity and adds it to the engine,
 - creates a child bento context and populates it with the object metadata, such as x, y, width, height, name etc.
 - for each component specified in a comma-separated string, it tries to get this component instance from the
   bento, using its name.

This means that we can prepare some objects on the map and try to display them:

components: position,debugDisplay

In bootstrap:

	@Override
	public void create () {
        Bento bento = Bento.createRoot();
        bento.register("tiledmap.filename", "map.tmx");
        bento.register("fitviewport.width", 320);
        bento.register("fitviewport.height", 176);
        bento.register("position", PositionFactory.IT);
        bento.register("debug", DebugDisplayFactory.IT);
	    fruitTwist = bento.get(FruitTwistFactory.IT);
	}

In game:

    @WithFactory
    public FruitTwist(
            @ByFactory(TiledMapRendererFactory.class) TiledMapRenderer batch,
            @ByFactory(OrthographicCameraFactory.class) OrthographicCamera camera,
            @ByFactory(FitViewportFactory.class) FitViewport fitViewport,
            EngineFromTilemapPopulationService engineFromTilemapPopulationService,
            @ByFactory(EngineFactory.class) Engine engine,
            DebugDisplaySystem debugDisplaySystem) {
        this.tiledRenderer = batch;
        this.camera = camera;
        this.fitViewport = fitViewport;
        this.engine = engine;
        engineFromTilemapPopulationService.populate();
        engine.addSystem(debugDisplaySystem);
    }


and then:

    @Override
    public void render () {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        engine.update(Gdx.graphics.getDeltaTime());
        tiledRenderer.setView(camera);
        tiledRenderer.render();
    }

DebugDisplay component built by DebugDisplayFactory depends on:
 - float "debugdisplay.size" - size of the shape displayed
 - string "debugdisplay.color" - color of the shape displayed
 - enum (or string) "debugdisplay.shape" - type of shape displayed: SQUARE or CIRCLE (you can use a provided enum to register it)

Note that since context of the object inherit from the main context, we can just move some of the config up to the bootstrap:
        bento.register("debugdisplay.size", 14);
        bento.register("debugdisplay.color", "#ff0000");
        bento.register("debugdisplay.shape", DebugDisplay.Shape.SQUARE);
        bento.register("position", PositionFactory.IT);
        bento.register("placeholder", PlaceholderFactory.IT);
        bento.register("debug", DebugDisplayFactory.IT);


and now it's enough to inject the debugdisplaysystem and add it to the engine:

    @WithFactory
    public FruitTwist(
            @ByFactory(TiledMapRendererFactory.class) TiledMapRenderer batch,
            @ByFactory(OrthographicCameraFactory.class) OrthographicCamera camera,
            @ByFactory(FitViewportFactory.class) FitViewport fitViewport,
            EngineFromTilemapPopulationService engineFromTilemapPopulationService,
            @ByFactory(EngineFactory.class) Engine engine,
            DebugDisplaySystem debugDisplaySystem) {
        this.tiledRenderer = batch;
        this.camera = camera;
        this.fitViewport = fitViewport;
        this.engine = engine;
        engineFromTilemapPopulationService.populate();
        engine.addSystem(debugDisplaySystem);
    }

    @Override
    public void resize(int width, int height) {
        fitViewport.update(width, height, true);
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tiledRenderer.setView(camera);
        tiledRenderer.render();
        engine.update(Gdx.graphics.getDeltaTime());
    }


DebugDisplaySystem built by DebugDisplaySystemFactory depends on:
 - OrthographicCamera built by OrthographicCameraFactory
 
This means that without spaghetti of wiring we can be sure that any 
changes in the fit viewport will propagate both to debug display and the tiled renderer.

