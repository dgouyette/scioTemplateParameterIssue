package com.example

import com.spotify.scio.ContextAndArgs
import org.apache.beam.sdk.io.TextIO

object WordCount {
  def main(cmdlineArgs: Array[String]): Unit = {
    val (sc, args) = ContextAndArgs(cmdlineArgs)
    sc.customInput("input", TextIO.read().from(sc.optionsAs[WordCountOptions].getInput))
      .map(_.toUpperCase)
      .saveAsTextFile(args("output"))
    sc.close()
  }
}