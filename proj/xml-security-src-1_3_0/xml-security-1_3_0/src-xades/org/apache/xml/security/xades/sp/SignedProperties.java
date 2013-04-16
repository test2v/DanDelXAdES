


package org.apache.xml.security.xades.sp;


import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.XMLSignatureException;
import org.apache.xml.security.signature.ObjectContainer;
import org.apache.xml.security.utils.IdResolver;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.apache.xml.security.xades.*;
import org.apache.xml.security.xades.sp.SignedSignatureProperties;


/**
 * <pre>&lt;xsd:element name="SignedProperties" type="SignedPropertiesType" />
		&lt;xsd:complexType name="SignedPropertiesType">
			&lt;xsd:sequence>
				&lt;xsd:element name="SignedSignatureProperties" type="SignedSignaturePropertiesType"/>
				&lt;xsd:element name="SignedDataObjectProperties" type="SignedDataObjectPropertiesType" minOccurs="0"/>
			&lt;/xsd:sequence>
		&lt;xsd:attribute name="Id" type="xsd:ID" use="optional"/>
		&lt;/xsd:complexType></pre>
 
 * 
 * @author Psycho_core (nqkoi_ot_bg@yahoo.com)
 * @author mwnnj (krassen.deltchev@rub.de)
 **/

public class SignedProperties extends ObjectContainer implements QualifyingPropertiesContent {

		/** ds:SignedProperties.ds:SignedSignatureProperties element */
	   private SignedSignatureProperties _signedSignatureProperties = null;	
	   	/** ds:SignedProperties.ds:SignedDataObjectProperties element */
	   private SignedDataObjectProperties _signedDataObjectProperties = null;	  
	
	/** {@link org.apache.commons.logging} logging facility */
    static org.apache.commons.logging.Log log = 
        org.apache.commons.logging.LogFactory.getLog(SignedProperties.class.getName());
	
	/**
	    * Constructor SignedProperties
	    * @param doc
	    */
	
	public SignedProperties(Document doc) {
		super(doc);
		XMLUtils.addReturnToElement(this._constructionElement);
	}
	
	
	/**
	    * @param doc
	    * @param SignedSignatureProperties
	    * @param SignedDataObjectProperties
	    * @throws XMLSecurityException
	    */
	
	   public SignedProperties(
	           Document doc, Element SignedSignatureProperties, Element SignedDataObjectProperties)
	              throws XMLSecurityException {

	      super(doc);
	      
	      this._signedSignatureProperties = new SignedSignatureProperties(SignedSignatureProperties, null);
	      
	      this._constructionElement.appendChild(SignedSignatureProperties);
	      XMLUtils.addReturnToElement(this._constructionElement);

	      this._signedDataObjectProperties = new SignedDataObjectProperties(SignedDataObjectProperties, null);
	      
	      this._constructionElement.appendChild(SignedDataObjectProperties);
	      XMLUtils.addReturnToElement(this._constructionElement);
	   }
	   /**
	    * Constructor SignedProperties
	    * 	    
	    * @param ssp
	    * @param sdop
	    * @param doc
	    * @throws XMLSecurityException
	    */

	public SignedProperties(Document doc,SignedSignatureProperties ssp,
			SignedDataObjectProperties sdop)throws XMLSecurityException {
		
		super(doc);
		
		if(ssp == null)
	    	  throw new IllegalArgumentException("Wrong Element Value!");
		
		XMLUtils.addReturnToElement(this._constructionElement);

	     
	      this._signedSignatureProperties = new SignedSignatureProperties(this._doc);
	      this._constructionElement.appendChild(this._signedSignatureProperties.getElement());
	      this._signedDataObjectProperties = new SignedDataObjectProperties(this._doc);
	      this._constructionElement.appendChild(this._signedDataObjectProperties.getElement());
	      XMLUtils.addReturnToElement(this._constructionElement);
	   
	      }
	
	/**
	    * Constructor SignedProperties
	    *
	    * @param element
	    * @param BaseURI
	    * @throws XMLSecurityException
	    */

	public SignedProperties(Element element, String BaseURI) throws XMLSecurityException {
		super(element, BaseURI);
	
		//		<element name="SignedSignatureProperties" />
	      Element ssp = XMLUtils.selectDsNode(this._constructionElement.getFirstChild(),
	                                  Constants._TAG_SIGNEDSIGNATUREPROPERTIES,0);

	      
	      // create a SignedProperties object from that element
	      this._signedSignatureProperties = new SignedSignatureProperties(ssp, BaseURI);
	      
		//<element name="SignedDataObjectProperties" minOccurs="0"/>
		Element sdop = XMLUtils.selectDsNode(this._constructionElement.getFirstChild(),
                       Constants._TAG_SIGNEDDATAOBJECTPROPERTIES,0);
		
		
		//If it exists use it
		 if (sdop != null) {
	         this._signedDataObjectProperties = new SignedDataObjectProperties(sdop, BaseURI);

		}
		 else if (sdop == null){
			 throw new IllegalArgumentException("Wrong Element Value!");
		 }
	}
	
	/**
	    * Sets the <code>Id</code> Element
	    *
	    * @param Id 
	    */
	public void setId(String Id) {

	      if ((this._state == MODE_SIGN) && (Id != null)) {
	         this._constructionElement.setAttributeNS(null, Constants._ATT_ID, Id);
	         IdResolver.registerElementById(this._constructionElement, Id);
	      }
	   }
	   
	  
	   
	   /**
	    * Method setSignedSignatureProperties
	    *
	    * @param ssp
	    */
	   public void setSignedSignatureProperties (SignedSignatureProperties ssp) {

	      if ((this._state == MODE_SIGN)&& (ssp != null)) {
	         this._constructionElement.appendChild(ssp.getElement());
	         XMLUtils.addReturnToElement(this._constructionElement);
	      }
	   }
	   
	   
	   
	   /**
	    * Method setSignedDataObjectProperties
	    *
	    * @param sdop
	    */
	   public void setSignedDataObjectProperties (SignedDataObjectProperties sdop) {

	      if ((this._state == MODE_SIGN)&& (sdop != null)) {
	         this._constructionElement.appendChild(sdop.getElement());
	         XMLUtils.addReturnToElement(this._constructionElement);
	      }
	   }
	   
	   
	   /**
	    * Returns the <code>Id</code> attribute
	    *
	    * @return the <code>Id</code> attribute
	    */
	   public String getId() {
	      return this._constructionElement.getAttributeNS(null, Constants._ATT_ID);
	   }
	   
	   
	   /**
	    * Method getSignedSignaturePropeties
	    *
	    *
	    * @throws XMLSecurityException
	    * @return the ssp-s
	    */
	   public SignedSignatureProperties getSignedSignaturePropeties() {

		   return this._signedSignatureProperties;
	   }
	
	   /**
	    * Method getSignedDataObjectProperties
	    *
	    *
	    * @throws XMLSecurityException
	    * @return the sdop-s
	    */
	   public SignedDataObjectProperties getSignedDataObjectProperties() throws XMLSecurityException {

	      try {
	       Element sdop =
	             XMLUtils.selectDsNode(this._constructionElement,Constants._TAG_SIGNEDDATAOBJECTPROPERTIES, 0);

	         if (sdop != null) {
	            return new SignedDataObjectProperties(sdop, this._baseURI);
	         }

	         return null;
	      } catch (XMLSignatureException ex) {
	         throw new XMLSecurityException("empty", ex);
	      }
	   }
	
	

	   /** @inheritDoc */
	   public String getBaseLocalName() {
	      return Constants._TAG_SIGNEDPROPERTIES;
	   }
	   /** @inheritDoc */
	   public String getBaseNamespace() {
	      return "http://uri.etsi.org/01903/v1.1.1#";
	   }


}
