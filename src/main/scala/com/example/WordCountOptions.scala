package com.example

import org.apache.beam.sdk.options.{PipelineOptions, Validation, ValueProvider}

trait WordCountOptions  extends PipelineOptions {

  def getInput: ValueProvider[String]
  def setInput(value: ValueProvider[String]): Unit

}
