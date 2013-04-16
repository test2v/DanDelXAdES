package org.apache.xml.security.xades.sp;

import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.ObjectContainer;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.apache.xml.security.xades.*;

/**<pre>&lt;xsd:complexType name="TimeStampType">
			&lt;xsd:sequence>
  				&lt;xsd:element name="HashDataInfo" type="HashDataInfoType" maxOccurs="unbounded"/>
  				&lt;xsd:choice>
    				&lt;xsd:element name="EncapsulatedTimeStamp" type="EncapsulatedPKIDataType"/>
    				&lt;xsd:element name="XMLTimeStamp" type="AnyType"/>
  				&lt;/xsd:choice>
			&lt;/xsd:sequence>
		&lt;/xsd:complexType><pre>
 * 
 * @author Psycho_core (nqkoi_ot_bg@yahoo.com)
 * @author mwnnj (krassen.deltchev@rub.de)
 **/

public class TimeStampType extends ObjectContainer {
	
	
	/** TimeStampType.HashDataInfo element */
	private HashDataInfo _hashdatainfo = null;
	/** TimeStampType.EncapsulatedPKIDataType element */
	private EncapsulatedPKIDataType _EncapsulatedTimeStamp = null;
	
	
	
	/**
	    * Constructor TimeStampType
	    * @param doc
	    */
	public TimeStampType(Document doc){
		super(doc);
		XMLUtils.addReturnToElement(this._constructionElement);
	}
	
	/**
	    * Constructor TimeStampType
	    *
	    * @param element
	    * @param BaseURI
	    * @throws XMLSecurityException
	    */
	   public TimeStampType(Element element, String BaseURI)
	           throws XMLSecurityException {
	      super(element, BaseURI);
	   }
	   
	   /**
	    * Constructor TimeStampType
	    *
	    * @param doc
	    * @param hashdatainfo
	    * @param encapsulatedtimestamp
	    * @param XMLTimeStamp
	    */
	   public TimeStampType(Document doc,HashDataInfoType hashdatainfo,EncapsulatedPKIDataType encapsulatedtimestamp,String XMLTimeStamp ) {

	      super(doc);
	      
	      
	      	 this._constructionElement.appendChild(hashdatainfo.getElement());
	         XMLUtils.addReturnToElement(this._constructionElement);
	         
	         if ((this._state == MODE_SIGN)&& (encapsulatedtimestamp != null)|| (XMLTimeStamp != null)){
	         this._constructionElement.appendChild(encapsulatedtimestamp.getElement());
	         XMLUtils.addReturnToElement(this._constructionElement);

	         this.addStringElement(XMLTimeStamp,Constants._TAG_XMLTIMESTAMP);
	         XMLUtils.addReturnToElement(this._constructionElement);
	         }
	      
	     
	   } 
	   
	   /**
	    * Method setHashDataInfo
	    *
	    * @param hashdatainfo
	    */
	   public void setHashDataInfo (HashDataInfoType hashdatainfo) {

		   if (hashdatainfo == null) {
				throw new IllegalArgumentException("HashDataInfo Element is required!");
		   }
		   
	   else  if ((this._state == MODE_SIGN)&& (hashdatainfo != null)) {
	    	  
	    	  this._constructionElement.appendChild(hashdatainfo.getElement());
	    	  XMLUtils.addReturnToElement(this._constructionElement);
	      }
	   }
	  
	   /**
	    * Method setEncapsulatedTimeStamp
	    *
	    * @param encapsulatedtimestamp
	    */
	   public void setEncapsulatedTimeStamp(EncapsulatedPKIDataType encapsulatedtimestamp) {
		     
		   if ((this._state == MODE_SIGN)&& (encapsulatedtimestamp != null)) {
	    	  
	    	  this._constructionElement.appendChild(encapsulatedtimestamp.getElement());
	    	  XMLUtils.addReturnToElement(this._constructionElement);
	      }
	   }

	   /**
	    * Method setXMLTimeStamp
	    *
	    * @param XMLTimeStamp
	    */
	   public void setXMLTimeStamp (String XMLTimeStamp) {
		   
		   if ((this._state == MODE_SIGN)&& (XMLTimeStamp != null)) {
	    	  
	    	  this.addStringElement(XMLTimeStamp, Constants._TAG_XMLTIMESTAMP);
	    	  XMLUtils.addReturnToElement(this._constructionElement);
	      }
	   }
	  
	   /**
	    * Method getHashDataInfo
	    * @return the HashDataInfo
	    */
	 
	   public HashDataInfo getHashDataInfo() {
	      return this._hashdatainfo;
	   }
	   
	   /**
	    * Method getEncapsulatedTimeStamp
	    * @return the EncapsulatedTimeStamp
	    */
	 
	   public EncapsulatedPKIDataType getEncapsulatedTimeStamp() {
	      return this._EncapsulatedTimeStamp;
	   }
	  

/**
	    * Method getXMLTimeStamp
	    *
	    *
	    * @return the XMLTimeStamp Value
	    */
	   public String getXMLTimeStamp() {

	      String XMLTimeStamp =
	         this.getTextFromChildElement(Constants._TAG_XMLTIMESTAMP,Constants.LocalSpecNS);
	      if (XMLTimeStamp != null) {
	    	  return new String(XMLTimeStamp);
	      }
	      return null;	      
	   }
	  


	   /** @inheritDoc */
	   public String getBaseLocalName() {
	      return Constants._TAG_TIMESTAMPTYPE;
	   }
	   /** @inheritDoc */
	   public String getBaseNamespace() {
	      return "http://uri.etsi.org/01903/v1.1.1#";
	   }
	}
