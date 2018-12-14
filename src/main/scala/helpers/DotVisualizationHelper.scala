package helpers

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}

import guru.nidi.graphviz.engine._
import guru.nidi.graphviz.parse.Parser

object DotVisualizationHelper {


  def toSvgString(dotVisualization: String): String = {
    val engine = new GraphvizV8Engine
    val graph = Parser.read(dotVisualization)
    Graphviz.useEngine(engine)
    val svg = Graphviz.fromGraph(graph).render(Format.SVG).toString
    Graphviz.releaseEngine()
    svg
  }

  def writeSvgFile(dotVisualization: String, path: String): Unit = {
    val svgString = toSvgString(dotVisualization)
    val bytes = svgString.getBytes(StandardCharsets.UTF_8)
    Files.write(Paths.get(path), bytes)
  }
}
