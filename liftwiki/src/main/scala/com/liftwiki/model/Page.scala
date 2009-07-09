package com.liftwiki.model

import _root_.net.liftweb.mapper._
import _root_.net.liftweb.util._
import _root_.net.liftweb.http._
import _root_.scala.xml._
import S._

object Page extends Page with LongKeyedMetaMapper[Page] {

}

class Page extends LongKeyedMapper[Page] with IdPK {
  def getSingleton = Page // what's the "meta" server
  
  object path extends MappedString(this, 200) with UniqueIndexed {
    override def dbDisplay_? = false
  }

}
