package org.apache.xml.security.xades.sp;

import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.ObjectContainer;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.apache.xml.security.xades.*;

/** 
 *
 * @author Psycho_core (nqkoi_ot_bg@yahoo.com)
 * @author mwnnj (krassen.deltchev@rub.de)
 **/

public class AllSignedDataObjects extends ObjectContainer implements  CommitmentTypeIndicationContent{
	
	/**
	    * Constructor AllSignedDataObjects
	    * @param doc
	    */
		
	public AllSignedDataObjects(Document doc) {
		super(doc);	
		XMLUtils.addReturnToElement(this._constructionElement);
	}
	
	/**
	    * Constructor AllSignedDataObjects
	    *
	    * @param element
	    * @param BaseURI
	    * @throws XMLSecurityException
	    */
	   public AllSignedDataObjects(Element element, String BaseURI)
	           throws XMLSecurityException {
	      super(element, BaseURI);
	      
	   }
	   
	   

	   /** @inheritDoc */
	   public String getBaseLocalName() {
	      return Constants._TAG_ALLSIGNEDDATAOBJECTS;
	   }
	   /** @inheritDoc */
	   public String getBaseNamespace() {
	      return "http://uri.etsi.org/01903/v1.1.1#";
	   }
	}
