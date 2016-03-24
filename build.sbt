name := "scala-influxdb-client"

organization := "com.paulgoldbaum"

scalaVersion := "2.11.7"
crossScalaVersions := Seq("2.11.7", "2.10.6")

testOptions in Test += Tests.Argument("-oDF")

useGpg := true
releaseCrossBuild := true

libraryDependencies += "com.ning" % "async-http-client" % "1.9.36"
libraryDependencies += "io.netty" % "netty" % "3.10.5.Final"
libraryDependencies += "io.spray" %%  "spray-json" % "1.3.2"
libraryDependencies += "com.github.tomakehurst" % "wiremock" % "1.57" % "test"
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test"

import ReleaseTransformations._

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  ReleaseStep(action = Command.process("publishSigned", _), enableCrossBuild = true),
  setNextVersion,
  commitNextVersion,
  ReleaseStep(action = Command.process("sonatypeReleaseAll", _), enableCrossBuild = true),
  pushChanges
)
