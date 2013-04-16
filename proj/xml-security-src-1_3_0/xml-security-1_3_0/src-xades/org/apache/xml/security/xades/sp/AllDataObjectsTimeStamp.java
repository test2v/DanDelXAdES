package org.apache.xml.security.xades.sp;

import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.XMLSignatureException;
import org.apache.xml.security.signature.ObjectContainer;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.apache.xml.security.xades.*;

/** <pre>&lt;xsd:element name="AllDataObjectsTimeStamp" type="TimeStampType"/></pre>
 *
 * @author Psycho_core (nqkoi_ot_bg@yahoo.com)
 * @author mwnnj (krassen.deltchev@rub.de)
 **/

public class AllDataObjectsTimeStamp extends ObjectContainer implements  SignedDataObjectPropertiesContent{
	
	/**
	    * Constructor AllDataObjectsTimeStamp
	    * @param doc
	    */
		
	public AllDataObjectsTimeStamp(Document doc) {
		super(doc);	
		XMLUtils.addReturnToElement(this._constructionElement);
	}
	
	/**
	    * Constructor AllDataObjectsTimeStamp
	    *
	    * @param element
	    * @param BaseURI
	    * @throws XMLSecurityException
	    */
	   public AllDataObjectsTimeStamp(Element element, String BaseURI)
	           throws XMLSecurityException {
	      super(element, BaseURI);
	      
	   }
	   
	   /**
	    * Constructor AllDataObjectsTimeStamp
	    *
	    * @param doc
	    * @param AllDataObjectsTimeStamp
	    */
	   public AllDataObjectsTimeStamp(Document doc,TimeStampType AllDataObjectsTimeStamp ) {

	      super(doc);

	      if (AllDataObjectsTimeStamp != null) {
	          this._constructionElement.appendChild(AllDataObjectsTimeStamp.getElement());
	          XMLUtils.addReturnToElement(this._constructionElement);
	       }
	   }
	  /**
	    * Method setAllDataObjectsTimeStamp
	    *
	    * @param AllDataObjectsTimeStamp
	    */
	   public void setAllDataObjectsTimeStamp(TimeStampType AllDataObjectsTimeStamp) {

	      if ((this._state == MODE_SIGN)&& (AllDataObjectsTimeStamp != null)) {
	    	  
	    	  this._constructionElement.appendChild(AllDataObjectsTimeStamp.getElement());
	    	  XMLUtils.addReturnToElement(this._constructionElement);
	      }
	   }


	   /**
	    * Method getAllDataObjectsTimeStamp
	    *
	    *
	    * @throws XMLSecurityException
	    * @return the AllDataObjectsTimeStampElement
	    */
	   public AllDataObjectsTimeStamp getAllDataObjectsTimeStamp() throws XMLSecurityException {

	      try {
	       Element AllDataObjectsTimeStampElem =
	             XMLUtils.selectDsNode(this._constructionElement,                                                
	                                                Constants._TAG_ALLDATAOBJECTSTIMESTAMP, 0);

	         if (AllDataObjectsTimeStampElem != null) {
	            return new AllDataObjectsTimeStamp(AllDataObjectsTimeStampElem, this._baseURI);
	         }

	         return null;
	      } catch (XMLSignatureException ex) {
	         throw new XMLSecurityException("empty", ex);
	      }
	   }
	   

	   /** @inheritDoc */
	   public String getBaseLocalName() {
	      return Constants._TAG_ALLDATAOBJECTSTIMESTAMP;
	   }
	   /** @inheritDoc */
	   public String getBaseNamespace() {
	      return "http://uri.etsi.org/01903/v1.1.1#";
	   }
	}
