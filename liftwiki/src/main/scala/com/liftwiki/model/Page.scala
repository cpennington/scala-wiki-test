package com.liftwiki.model

import _root_.net.liftweb.mapper._
import _root_.net.liftweb.util._


object Page extends Page with LongKeyedMetaMapper[Page] {
}

class Page extends LongKeyedMapper[Page] with IdPK {
  def getSingleton = Page // what's the "meta" server
  
  object path extends MappedString(this, 200)
  object name extends MappedString(this, 200)
  object body extends MappedText(this)
  object title extends MappedString(this, 200)
}
