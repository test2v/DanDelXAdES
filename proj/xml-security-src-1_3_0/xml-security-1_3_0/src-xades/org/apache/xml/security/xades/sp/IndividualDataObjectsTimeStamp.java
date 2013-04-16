package org.apache.xml.security.xades.sp;

import org.apache.xml.security.exceptions.XMLSecurityException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.apache.xml.security.signature.ObjectContainer;
import org.apache.xml.security.signature.XMLSignatureException;
import org.apache.xml.security.utils.XMLUtils;
import org.apache.xml.security.xades.*;

/**
 * <pre>&lt;xsd:element name="IndividualDataObjectsTimeStamp" type="TimeStampType"/></pre>
 *  
 * @author Psycho_core (nqkoi_ot_bg@yahoo.com)
 * @author mwnnj (krassen.deltchev@rub.de)
 **/



public class IndividualDataObjectsTimeStamp extends ObjectContainer implements  SignedDataObjectPropertiesContent{
	
	public IndividualDataObjectsTimeStamp(Document doc) {
		super(doc);
		XMLUtils.addReturnToElement(this._constructionElement);
	}
	
	/**
	    * Constructor IndividualDataObjectsTimeStamp
	    *
	    * @param element
	    * @param BaseURI
	    * @throws XMLSecurityException
	    */
	   public IndividualDataObjectsTimeStamp(Element element, String BaseURI)
	           throws XMLSecurityException {
	      super(element, BaseURI);
	   }
	   
	   /**
	    * Constructor IndividualDataObjectsTimeStamp
	    *
	    * @param doc
	    * @param individualdataobjectstimestamp
	    */
	   public IndividualDataObjectsTimeStamp(Document doc,TimeStampType individualdataobjectstimestamp ) {

	      super(doc);

	      if (individualdataobjectstimestamp != null) {
	          this._constructionElement.appendChild(individualdataobjectstimestamp.getElement());
	          XMLUtils.addReturnToElement(this._constructionElement);
	       }
	   }
	   /**
	    * Method setIndividualDataObjectsTimeStamp
	    *
	    * @param individualdataobjectstimestamp
	    */
	   public void setIndividualDataObjectsTimeStamp(TimeStampType individualdataobjectstimestamp) {

	      if ((this._state == MODE_SIGN)&& (individualdataobjectstimestamp != null)) {
	    	  
	    	  this._constructionElement.appendChild(individualdataobjectstimestamp.getElement());
	    	  XMLUtils.addReturnToElement(this._constructionElement);
	      }
	   }
	   /**
	    * Method getTimeStampType
	    *
	    *
	    * @throws XMLSecurityException
	    * @return the individualdataobjectstimestamp
	    */
	   public IndividualDataObjectsTimeStamp getIndividualDataObjects() throws XMLSecurityException {

	      try {
	       Element individualdataobjectstimestamp =
	             XMLUtils.selectDsNode(this._constructionElement,                                                
	                                                Constants._TAG_INDIVIDUALDATAOBJECTSTIMESTAMP, 0);

	         if (individualdataobjectstimestamp != null) {
	            return new IndividualDataObjectsTimeStamp(individualdataobjectstimestamp, this._baseURI);
	         }

	         return null;
	      } catch (XMLSignatureException ex) {
	         throw new XMLSecurityException("empty", ex);
	      }
	   }
	   
	   

	   /** @inheritDoc */
	   public String getBaseLocalName() {
	      return Constants._TAG_INDIVIDUALDATAOBJECTSTIMESTAMP;
	   }
	   /** @inheritDoc */
	   public String getBaseNamespace() {
	      return "http://uri.etsi.org/01903/v1.1.1#";
	   }
	}
