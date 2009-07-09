
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
import _root_.com.petebevin.markdown._


class PageDisplay {
  def render(contents:NodeSeq) : NodeSeq = {
    val path = S.param("wiki_path").openOr("index")
    val currentVersion = Page.find(By(Page.path, path)).map(page => {
      (page, Version.find(By(Version.page, page.id), OrderBy(Version.revision, Descending)))
    })

    currentVersion match {
      case Full((page, Full(version))) => {
	var newVersion = version;
	val editForm = version.toForm(Full("Save"), version => {
	  newVersion = Version.create.body(version.body.is).title(version.title.is).page(version.page.is).revision(version.revision.is+1)
	  newVersion.save
	})
	val body = Unparsed(new MarkdownProcessor().markdown(newVersion.body))
	bind("page", chooseTemplate("choose", "display", contents),
	     "title" -> newVersion.title,
	     "body" -> SHtml.swappable(<div>{body}</div>,
				       <div>{bind("page", chooseTemplate("choose", "create", contents),
					    "form" -> editForm)}</div>
				     )
	   )
      }
      case Empty | Full((_, Empty)) => {
	val form = Version.toForm(Full("Create Page"), version => {
	  val page = Page.create.path(path)
	  page.save()
	  val newVersion = version.revision(0).page(page)
	  newVersion.save()
	})
	
	bind("page", chooseTemplate("choose", "create", contents),
	     "form" -> form)
      }
    }
  }
}
