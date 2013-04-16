package org.apache.xml.security.xades.up;

import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.ObjectContainer;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.apache.xml.security.xades.Constants;

/**
 * <pre>&lt;xsd:element name="UnsignedDataObjectProperties" type="UnsignedDataObjectPropertiesType" />
	&lt;xsd:complexType name="UnsignedDataObjectPropertiesType">
  		&lt;xsd:sequence>
    		&lt;xsd:element name="UnsignedDataObjectProperty" type="AnyType" minOccurs="0" maxOccurs="unbounded"/>
  		&lt;/xsd:sequence>
&lt;/xsd:complexType>
 * 
 * @author Psycho_core (nqkoi_ot_bg@yahoo.com)
 * @author mwnnj (krassen.deltchev@rub.de)
 **/

public class UnsignedDataObjectProperties extends ObjectContainer {

	
	/**
	    * Constructor UnsignedDataObjectProperties
	    * @param doc
	    * @throws XMLSecurityException
	    */
	   public UnsignedDataObjectProperties(
	           Document doc)
	              throws XMLSecurityException {

	      super(doc);	     
	      XMLUtils.addReturnToElement(this._constructionElement);
	   }
	
	
	
	/**
	    * Constructor UnsignedUnsignedDataObjectProperties
	    *
	    * @param element
	    * @param BaseURI
	    * @throws XMLSecurityException
	    */
	
	public UnsignedDataObjectProperties(Element element, String BaseURI) throws XMLSecurityException {
		super(element, BaseURI);
	}
	

	 
	
	   
	
	   /** @inheritDoc */
	public String getBaseLocalName() {
		return Constants._TAG_UNSIGNEDDATAOBJECTPROPERTIES;
	}
	 /** @inheritDoc */
	   public String getBaseNamespace() {
	      return "http://uri.etsi.org/01903/v1.1.1#";
	   }


}
