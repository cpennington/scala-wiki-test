package com.liftwiki.snippet

import _root_.scala.xml._
import _root_.net.liftweb.util.Helpers._

class Page {
  def render(contents:NodeSeq) : NodeSeq = {
    bind("page", contents, "title" -> "test title", "body" -> "body")
  }
}

