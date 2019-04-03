enablePlugins(ScalaJSPlugin)
enablePlugins(ScalaJSBundlerPlugin)

lazy val root = project.
  // Activate the ScalaJSPlugin and ScalaJSBundlelr
  // The former is where the scala -> js magic happens
  // The latter helps us retrieve JS depependencies and bundle them up 
  enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
  .settings(
    libraryDependencies += "com.github.japgolly.scalajs-react" %%% "core" % "1.4.1",
    libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.6",

    scalaJSUseMainModuleInitializer := true,
    mainClass in Compile := Some("Main"),
    
    // We get to view/debug our scala in the browser! 
    emitSourceMaps := true,

    // Bundle up all our JS depedencies into one file via fastOptJS::webpack
    webpackBundlingMode := BundlingMode.LibraryOnly(),

    // Our JS dependencies; you'll need `npm` binary in order for this to work
    npmDependencies in Compile ++= Seq(
      "react" -> "16.7.0",
      "react-dom" -> "16.7.0")

)

