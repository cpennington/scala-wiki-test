package com.liftwiki.snippet

import _root_.scala.xml._
import _root_.net.liftweb.util.Helpers._
import _root_.net.liftweb.http._
import S._
import _root_.net.liftweb.util._
import Helpers._
import _root_.scala.xml._
import com.liftwiki.model._
import _root_.net.liftweb.mapper._


class PageDisplay {
  def render(contents:NodeSeq) : NodeSeq = {
    val path = S.param("wiki_path").openOr("index")
    val page = Page.find(By(Page.path, path))
    page match {
      case Full(existingPage) => {
	val editForm = existingPage.toForm(Full("Save"), {
	  _.path(path).save
	})
	bind("page", chooseTemplate("choose", "display", contents),
	     "title" -> existingPage.title,
	     "body" -> SHtml.swappable(<div>{existingPage.body}</div>,
				       <div>{bind("page", chooseTemplate("choose", "create", contents),
					    "form" -> editForm)}</div>
				     )
	   )
      }
      case Empty => {
	val form = Page.toForm(Full("Create Page"), {
	  _.path(path).save
	})
	
	bind("page", chooseTemplate("choose", "create", contents),
	     "form" -> form)
      }
    }
  }
}
