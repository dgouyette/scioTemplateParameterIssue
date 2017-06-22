package com.example

import java.lang

import com.google.auto.service.AutoService
import com.google.common.collect.ImmutableList
import org.apache.beam.sdk.options.{PipelineOptions, PipelineOptionsRegistrar}

@AutoService(classOf[PipelineOptionsRegistrar])
class WordCountOptionsRegistrat extends PipelineOptionsRegistrar {
  override def getPipelineOptions: lang.Iterable[Class[_ <: PipelineOptions]] =
    ImmutableList.of[Class[_ <: PipelineOptions]](classOf[WordCountOptions])
}
