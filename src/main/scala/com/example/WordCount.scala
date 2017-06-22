package com.example

import com.spotify.scio.ContextAndArgs
import org.apache.beam.sdk.Pipeline
import org.apache.beam.sdk.options.PipelineOptionsFactory

/*
SBT
runMain
  scio-examples/runMain com.examples.WordCount
  --project=[PROJECT] --runner=DataflowRunner --zone=[ZONE]
  --input=gs://dataflow-samples/shakespeare/kinglear.txt
  --output=gs://[BUCKET]/[PATH]/wordcount
*/

object WordCount {
  def main(cmdlineArgs: Array[String]): Unit = {

    val options = PipelineOptionsFactory.fromArgs(cmdlineArgs: _*).withValidation().as[WordCountOptions](classOf[WordCountOptions])
    PipelineOptionsFactory.register(classOf[WordCountOptions])

    val (sc, args) = ContextAndArgs(cmdlineArgs)
    val pipeline: Pipeline = sc.pipeline

    if (options.getInput.isAccessible) {

      sc.parallelize(List(options.getInput.get()))
        .map(_.toUpperCase)
      val result = sc.close().waitUntilFinish()
      println(result.allCounters)
    } else {

      pipeline.run()
      sc.close()
    }


  }
}