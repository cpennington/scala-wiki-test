package com.liftwiki.model

import _root_.net.liftweb.mapper._
import _root_.net.liftweb.util._
import _root_.net.liftweb.http._
import _root_.scala.xml._
import S._

object Page extends Page with LongKeyedMetaMapper[Page] {
  override def fieldOrder = List(title, body)
}

class Page extends LongKeyedMapper[Page] with IdPK {
  def getSingleton = Page // what's the "meta" server
  
  object path extends MappedString(this, 200) {
    override def dbDisplay_? = false
  }
  object title extends MappedString(this, 200)
  object body extends MappedText(this) {
    /**
     * Create an input field for the item
     */
    override def _toForm: Box[NodeSeq] = {
      S.fmapFunc({s: List[String] => this.setFromAny(s)}){funcName =>
	Full(<textarea name={funcName}
	     rows={textareaRows.toString}
	     cols={textareaCols.toString} id={fieldId}>{this.toString}</textarea>)}
    }

    def textareaRows  = 8
    
    def textareaCols = 20
  }

}
