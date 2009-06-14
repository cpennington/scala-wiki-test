package com.liftwiki.snippet

import _root_.scala.xml._
import _root_.net.liftweb.util.Helpers._
import _root_.net.liftweb.http._
import S._
import _root_.net.liftweb.util._
import Helpers._
import _root_.scala.xml._


class Page {
  def render(contents:NodeSeq) : NodeSeq = {
    val path = S.param("wiki_path").openOr("index")
    
    bind("page", contents, "title" -> "test title", "body" -> path)
  }
}

