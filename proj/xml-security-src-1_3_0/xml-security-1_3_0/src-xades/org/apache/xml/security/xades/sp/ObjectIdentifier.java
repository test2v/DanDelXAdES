package org.apache.xml.security.xades.sp;


import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.XMLSignatureException;
import org.apache.xml.security.signature.ObjectContainer;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.apache.xml.security.xades.Constants;
import org.apache.xml.security.xades.sp.DocumentationReferences;

/**
 * <pre>&lt;xsd:complexType name="ObjectIdentifierType">
  &lt;xsd:sequence>
    &lt;xsd:element name="Identifier" type="xsd:anyURI"/>
    &lt;xsd:element name="Description" type="xsd:string" minOccurs="0"/>
    &lt;xsd:element name="DocumentationReferences" type="DocumentationReferencesType" minOccurs="0"/>
  &lt;/xsd:sequence>
&lt;/xsd:complexType></pre>
 *  
 * @author Psycho_core (nqkoi_ot_bg@yahoo.com)
 * @author mwnnj (krassen.deltchev@rub.de)
 **/

public class ObjectIdentifier extends ObjectContainer implements  DataObjectFormatContent{
	
	/**
	    * Constructor ObjectIdentifier
	    *
	    * @param doc
	    */
	   public ObjectIdentifier(Document doc) {

	      super(doc);

	      XMLUtils.addReturnToElement(this._constructionElement);
	     	      
	   }
	   
	/**
	    * Constructor ObjectIdentifier
	    *
	    * @param element
	    * @param BaseURI
	    * @throws XMLSecurityException
	    */
	   public ObjectIdentifier(Element element, String BaseURI)
	           throws XMLSecurityException {
	      super(element, BaseURI);

	      //			<element name="Identifier" />
	      Element Identifier = XMLUtils.selectDsNode(this._constructionElement.getFirstChild(),
	                                  Constants._TAG_IDENTIFIER,0);

	      
	      
//			<element name="Description" minOccurs="0"/>
	      Element Description = XMLUtils.selectDsNode(this._constructionElement.getFirstChild(),
	                                  Constants._TAG_DESCRIPTION,0);

	      // check to see if it is there
	      if (Description == null) {
	         Object exArgs[] = { Constants._TAG_DESCRIPTION,
	                             Constants._TAG_OBJECTIDENTIFIER };

	         throw new XMLSignatureException("xml.WrongContent", exArgs);
	      }
	      
//			<element name="DocumentationReferences" minOccurs="0"/>
	      Element DocumentationReferences = XMLUtils.selectDsNode(this._constructionElement.getFirstChild(),
	                                  Constants._TAG_DOCUMENTATIONREFERENCES,0);

	      // check to see if it is there
	      if (DocumentationReferences == null) {
	         Object exArgs[] = { Constants._TAG_DOCUMENTATIONREFERENCES,
	                             Constants._TAG_OBJECTIDENTIFIER };

	         throw new XMLSignatureException("xml.WrongContent", exArgs);
	      }

	   }
	   
	   /**
	    * Method setIdentifier
	    *
	    * @param Identifier String
	    */
	   public void setIdentifier (String Identifier) {
		   if (Identifier == null) {
				throw new IllegalArgumentException("Target Attibute is required!");
			}
		   else if (this._state == MODE_SIGN) {
	    	  this.addStringElement(Identifier,Constants._TAG_IDENTIFIER);
	         XMLUtils.addReturnToElement(this._constructionElement);
	      }
	   }
	   
	   /**
	    * Method setDescription
	    *
	    * @param Description String
	    */
	   public void setDescription (String Description) {

	      if ((this._state == MODE_SIGN)&& (Description != null)) {
	    	  this.addStringElement(Description, Constants._TAG_DESCRIPTION);
	         XMLUtils.addReturnToElement(this._constructionElement);
	      }
	   }
	   
	   /**
	    * Method setDocumentationReferences
	    *
	    * @param DocumentationReferences String
	    */
	   public void setDocumentationReferences (String DocumentationReferences) {

	      if ((this._state == MODE_SIGN)&& (DocumentationReferences != null)) {
	    	  this.addStringElement(DocumentationReferences, Constants._TAG_DOCUMENTATIONREFERENCES);
	         XMLUtils.addReturnToElement(this._constructionElement);
	      }
	   }
	   /**
	    * Method getDescription
	    *
	    *
	    * @return the Description Value
	    */
	   public String getDescription() {

	      String Description =
	         this.getTextFromChildElement(Constants._TAG_DESCRIPTION,Constants.SignatureSpecNS);
	      if (Description != null) {
	    	  return new String(Description);
	      }
	      return null;	      
	   }

	   /**
	    * Method getIdentifier
	    *
	    *
	    * @return the Identifier Value
	    */
	   public String getIdentifier() {

	      String Identifier =
	         this.getTextFromChildElement(Constants._TAG_IDENTIFIER,Constants.SignatureSpecNS);
	      if (Identifier != null) {
	    	  return new String(Identifier);
	      }
	      return null;	      
	   }
	   
	   /**
	    * Method getDocumentationReferences
	    *
	    *
	    * @throws XMLSecurityException
	    * @return the DocumentationReferencesElement
	    */
	   public DocumentationReferences getDocumentationReferences() throws XMLSecurityException {

	      try {
	       Element documentationReferencesElem =
	             XMLUtils.selectDsNode(this._constructionElement,                                                
	                                                Constants._TAG_DOCUMENTATIONREFERENCES, 0);

	         if (documentationReferencesElem != null) {
	            return new DocumentationReferences(documentationReferencesElem, this._baseURI);
	         }

	         return null;
	      } catch (XMLSignatureException ex) {
	         throw new XMLSecurityException("empty", ex);
	      }
	   }
	   	   
	   
	   
	   /** @inheritDoc */
	   public String getBaseLocalName() {
	      return Constants._TAG_OBJECTIDENTIFIER;
	   }
	   /** @inheritDoc */
	   public String getBaseNamespace() {
	      return "http://uri.etsi.org/01903/v1.1.1#";
	}
}