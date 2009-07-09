package com.liftwiki.model

import _root_.net.liftweb.mapper._
import _root_.net.liftweb.util._
import _root_.net.liftweb.http._
import _root_.scala.xml._
import S._
import com.liftwiki.model._

case class IndexField[A <: Mapper[A], T](field: MappedField[T, A]) extends IndexItem[A] {
  def indexDesc: String = field.dbColumnName
}

object Version extends Version with LongKeyedMetaMapper[Version] {
  override def fieldOrder = List(title, body)
  override def dbIndexes = UniqueIndex(page, revision) :: Nil
}

class Version extends LongKeyedMapper[Version] with IdPK {
  def getSingleton = Version // what's the "meta" server
  
  object revision extends MappedLong(this) {
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
  object page extends MappedLongForeignKey(this, Page) {
    override def dbDisplay_? = false
  }

}
