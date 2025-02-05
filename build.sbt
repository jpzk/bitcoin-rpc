lazy val commonSettings = Seq(
  organization := "io.tokenanalyst",
  version := "2.4.0",
  scalaVersion := "2.12.10",
  description := "bitcoin-rpc")

lazy val bitcoinrpc = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    assemblyJarName in assembly := "bitcoin-rpc.jar",
    publishMavenStyle := false,
    publishTo := {
      val nexus = "https://oss.sonatype.org/"
      if (isSnapshot.value)
        Some("snapshots" at nexus + "content/repositories/snapshots")
      else
        Some("releases" at nexus + "service/local/staging/deploy/maven2")
    } 
  ).
  settings(
    libraryDependencies ++= http4s ++ json ++ zmq ++ cats
  )

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra :=
  <url>https://github.com/tokenanalyst/bitcoin-rpc</url>
    <licenses>
      <license>
        <name>Apache License Version 2.0</name>
        <url>http://www.apache.org/licenses/LICENSE-2.0</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
    <developers>
      <developer>
        <id>jpzk</id>
        <name>Jendrik Poloczek</name>
        <url>https://www.madewithtea.com</url>
      </developer>
      <developer>
        <id>CesarPantoja</id>
        <name>Cesar Pantoja</name>
      </developer>
    </developers>

val http4sVersion = "0.21.0-M5"
val circeVersion = "0.12.0-M4"

lazy val http4s = Seq(
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "org.http4s" %% "http4s-blaze-client" % http4sVersion
)

lazy val json = Seq(
  "org.http4s" %% "http4s-circe" % http4sVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-literal" % circeVersion,
  "io.circe" %% "circe-parser" % circeVersion 
)

lazy val zmq = Seq (
  "org.zeromq" % "jeromq" % "0.5.1"
)

lazy val cats = Seq (
  "org.typelevel" %% "cats-effect" % "2.0.0"
)

