Inherit bento and bentogdx

in GdxDefinition add:

    <inherits name="BentoCore" />
    <inherits name="BentoGdx" />

modify build.gradle gwt plugin config, so that it sees the generated classes:

    src = files(file("src/"), file("../core/build/generated/source/apt/main")) // Needs to be in front of "modules" below.

