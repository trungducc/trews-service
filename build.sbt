name := """trews"""
organization := "com.duc.trews"

version := "1.0-SNAPSHOT"

scalaVersion := "2.13.0"

libraryDependencies ++= Seq(
  javaJdbc,
  javaJpa,
  guice,
  "org.postgresql" % "postgresql" % "9.4.1212" exclude("org.slf4j", "slf4j-simple"),
  "org.eclipse.persistence" % "org.eclipse.persistence.jpa" % "2.7.4",
  "org.mindrot" % "jbcrypt" % "0.3m",
)

lazy val root = (project in file(".")).enablePlugins(PlayJava)