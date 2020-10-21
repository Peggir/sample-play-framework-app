import com.typesafe.config._

val conf = ConfigFactory.parseFile(new File("conf/application.conf")).resolve()

name := "sample-play-framework-app"
version := conf.getString("application.version")
      
lazy val `sample-play-framework-app` = (project in file(".")).enablePlugins(PlayJava, SbtWeb)

pipelineStages := Seq(uglify, digest, gzip)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

scalaVersion := "2.13.1"

libraryDependencies ++= Seq(
  "com.peggir" % "SimpleDbUtil" % "1.2.0",
  "com.typesafe.play" %% "play" % "2.8.2",
  "org.postgresql" % "postgresql" % "42.1.4",
  "junit" % "junit" % "4.13" % Test,
  javaJdbc,
  javaWs,
  guice,
  evolutions,
  ehcache
)

LessKeys.compress in Assets := true
LessKeys.strictMath in Assets := true

includeFilter in(Assets, LessKeys.less) := "*.less"

unmanagedResourceDirectories in Test += baseDirectory.value / "target/web/public/test"

resourceDirectories in Compile += baseDirectory.value / "resources"