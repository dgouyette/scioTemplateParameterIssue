name := """scioTemplateParameterIssue"""

version := "1.0"

scalaVersion := "2.11.11"

val scioVersion = "0.4.0-alpha1"


// Change this to another test framework if you prefer
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"
libraryDependencies += "com.spotify" %% "scio-core" % scioVersion
//libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.0.0-RC1"


// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.11"

