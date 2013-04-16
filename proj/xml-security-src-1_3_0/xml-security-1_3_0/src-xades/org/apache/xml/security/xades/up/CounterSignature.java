package org.apache.xml.security.xades.up;


import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.ObjectContainer;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.apache.xml.security.xades.Constants;

/**<pre>&lt;xsd:element name="CounterSignature" type="CounterSignatureType" minOccurs="0" maxOccurs="unbounded"/></pre>
 * 
 * @author Psycho_core (nqkoi_ot_bg@yahoo.com)
 * @author mwnnj (krassen.deltchev@rub.de)
 **/

public class CounterSignature extends ObjectContainer implements UnsignedPropertiesContent{
	
	/** CounterSignature.ds:Signature element */
	   private XMLSignature _signature = null;
	
	  
	
		
	/**
	    * Constructor CounterSignature
	    * @param doc
	    * @throws XMLSecurityException
	    */
	
	public CounterSignature(Document doc) 
	throws XMLSecurityException {
		super(doc);	       
	         XMLUtils.addReturnToElement(this._constructionElement);
	      }
	
	
	/**
	    * Constructor QualifiyngProperties
	    *
	    * @param element
	    * @param BaseURI
	    * @throws XMLSecurityException
	    */

	
	   public CounterSignature(Element element, String BaseURI)
       throws XMLSecurityException {

		   super(element, BaseURI);

  
		   this._signature =
			   new XMLSignature(this._doc,this._baseURI,this._baseURI);
	   }
		
		
		
		
	/**
	    * Method setXMLSignature
	    *
	    * @param signature
	    */
	   public void  setXMLSignature (XMLSignature signature) {
		   if ((this._state == MODE_SIGN)&& (signature != null)) {
		         this._constructionElement.appendChild(signature.getElement());
		         XMLUtils.addReturnToElement(this._constructionElement);
		      }
	     
	   }
	 
	   
	   
	   
	   
	/**
	    * Method getXMLSignature
	    *
	    *
	    * @throws XMLSecurityException
	    * @return the Signature
	    */
	   public XMLSignature getXMLSignature() {
		      return this._signature;
		   }
	   
	   
	   /** @inheritDoc */
	   public String getBaseLocalName() {
	      return Constants._TAG_COUNTERSIGNATURE;
	   }
	   /** @inheritDoc */
	   public String getBaseNamespace() {
	      return "http://uri.etsi.org/01903/v1.1.1#";
	   }
	
	}
