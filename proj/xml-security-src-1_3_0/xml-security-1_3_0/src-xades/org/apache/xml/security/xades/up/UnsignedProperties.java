package org.apache.xml.security.xades.up;

import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.XMLSignatureException;
import org.apache.xml.security.signature.ObjectContainer;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.apache.xml.security.xades.*;

/*** @author $Authors: Psycho_core,mwnnj $***/

public class UnsignedProperties extends ObjectContainer implements QualifyingPropertiesContent {

	
	/**
	    * Constructor UnsignedProperties
	    * @param doc
	    */
	
	public UnsignedProperties(Document doc) {
		super(doc);	
	}
	
	/**
	    * @param doc
	    * @param UnsignedSignaturePropertiesElem
	    * @param UnsignedDataObjectPropertiesElem
	    * @throws XMLSecurityException
	    */
	   public UnsignedProperties(
	           Document doc, Element UnsignedSignaturePropertiesElem, Element UnsignedDataObjectPropertiesElem)
	              throws XMLSecurityException {

	      super(doc);

	      this._constructionElement.appendChild(UnsignedSignaturePropertiesElem);
	      XMLUtils.addReturnToElement(this._constructionElement);

	      this._constructionElement.appendChild(UnsignedDataObjectPropertiesElem);
	      XMLUtils.addReturnToElement(this._constructionElement);
	   }
	
	
	
	
	
	   /**
	    * Constructor UnsignedProperties
	    *
	    * @param element
	    * @param BaseURI
	    * @throws XMLSecurityException
	    */
	
	public UnsignedProperties(Element element, String BaseURI) throws XMLSecurityException {
	super(element, BaseURI);
	}
	

	 /**
	    * Method setUnsignedSignatureProperties
	    *
	    * @param ussp
	    */
	   public void setUnsignedSignatureProperties (UnsignedSignatureProperties ussp) {

	      if ((this._state == MODE_SIGN)&& (ussp != null)) {
	         this._constructionElement.appendChild(ussp.getElement());
	         XMLUtils.addReturnToElement(this._constructionElement);
	      }
	   }
	   
	   /**
	    * Method setUnsignedDataObjectProperties
	    *
	    * @param usdop
	    */
	   public void setUnsignedDataObjectProperties (UnsignedDataObjectProperties usdop) {

	      if ((this._state == MODE_SIGN)&& (usdop != null)) {
	         this._constructionElement.appendChild(usdop.getElement());
	         XMLUtils.addReturnToElement(this._constructionElement);
	      }
	   }
	   
	   
	   /**
	    * Method getUnsignedSignatureProperties
	    *
	    *
	    * @throws XMLSecurityException
	    * @return the ussp-s
	    */
	   public UnsignedSignatureProperties getUnsignedSignatureProperties() throws XMLSecurityException {

	      try {
	       Element ussp =
	             XMLUtils.selectDsNode(this._constructionElement,Constants._TAG_UNSIGNEDSIGNATUREPROPERTIES, 0);

	         if (ussp != null) {
	            return new UnsignedSignatureProperties(ussp, this._baseURI);
	         }

	         return null;
	      } catch (XMLSignatureException ex) {
	         throw new XMLSecurityException("empty", ex);
	      }
	   }
	
	   /**
	    * Method getUnsignedDataObjectProperties
	    *
	    *
	    * @throws XMLSecurityException
	    * @return the usdop-s
	    */
	   public UnsignedDataObjectProperties getUnsignedDataObjectProperties() throws XMLSecurityException {

	      try {
	       Element usdop =
	             XMLUtils.selectDsNode(this._constructionElement,Constants._TAG_UNSIGNEDDATAOBJECTPROPERTIES, 0);

	         if (usdop != null) {
	            return new UnsignedDataObjectProperties(usdop, this._baseURI);
	         }

	         return null;
	      } catch (XMLSignatureException ex) {
	         throw new XMLSecurityException("empty", ex);
	      }
	   }
	
	   
	
	   /** @inheritDoc */
	public String getBaseLocalName() {
		return Constants._TAG_UNSIGNEDPROPERTIES;
	}

	/** @inheritDoc */
	   public String getBaseNamespace() {
	      return "http://uri.etsi.org/01903/v1.1.1#";
	   }
}
