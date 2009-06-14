package com.liftwiki.snippet

import _root_.scala.xml._
import _root_.net.liftweb.util.Helpers._
import _root_.net.liftweb.http._
import S._
import _root_.net.liftweb.util._
import Helpers._
import _root_.scala.xml._
import com.liftwiki.model.{Page}
import _root_.net.liftweb.mapper._


class PageDisplay {
  def render(contents:NodeSeq) : NodeSeq = {
    val path = S.param("wiki_path").openOr("index")
    val page = Page.find(By(Page.path, path))
    print(page)
    bind("page", contents, "title" -> "test title", "body" -> path)
  }
}

