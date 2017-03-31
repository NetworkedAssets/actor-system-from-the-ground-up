name := "actor-system-from-the-ground-up"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.12.1"

val akka = "com.typesafe.akka" %% "akka-actor" % "2.4.16"
val junit = "junit" % "junit" % "4.12"
val assertj =  "org.assertj" % "assertj-core" % "3.6.2"
val gag = "com.google.gag" % "gag" % "1.0.1"


lazy val root = (project in file(".")).settings(
  name := "actor-system-from-the-ground-up",
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.12.1",
  libraryDependencies ++= Seq(akka, junit, assertj, gag)
)