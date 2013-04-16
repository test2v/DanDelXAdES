
package org.apache.xml.security.xades.up;

import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.ObjectContainer;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.apache.xml.security.xades.Constants;

/**<pre>&lt;xsd:element name="UnsignedSignatureProperties" type="UnsignedSignaturePropertiesType"/>
	&lt;xsd:complexType name="UnsignedSignaturePropertiesType">
		&lt;xsd:sequence>
			&lt;xsd:element name="CounterSignature" type="CounterSignatureType" minOccurs="0" maxOccurs="unbounded"/>
			&lt;xsd:element name="SignatureTimeStamp" type="TimeStampType" minOccurs="0" maxOccurs="unbounded"/>
			&lt;xsd:element name="CompleteCertificateRefs" type="CompleteCertificateRefsType" minOccurs="0"/>
			&lt;xsd:element name="CompleteRevocationRefs" type="CompleteRevocationRefsType" minOccurs="0"/>
		&lt;xsd:choice>
			&lt;xsd:element name="SigAndRefsTimeStamp" type="TimeStampType" minOccurs="0" maxOccurs="unbounded"/>
			&lt;xsd:element name="RefsOnlyTimeStamp" type="TimeStampType" minOccurs="0" maxOccurs="unbounded"/>
		&lt;/xsd:choice>
			&lt;xsd:element name="CertificateValues" type="CertificateValuesType" minOccurs="0"/>
			&lt;xsd:element name="RevocationValues" type="RevocationValuesType" minOccurs="0"/>
            &lt;xsd:element name="ArchiveTimeStamp" type="TimeStampType" minOccurs="0" maxOccurs="unbounded"/>
		&lt;/xsd:sequence>
&lt;/xsd:complexType></pre>
 * 
 * @author Psycho_core (nqkoi_ot_bg@yahoo.com)
 * @author mwnnj (krassen.deltchev@rub.de)
 **/

public class UnsignedSignatureProperties extends ObjectContainer implements UnsignedPropertiesContent{
    
   	/** ds:UnsignedSignatureProperties.ObjectIdentifier element */
	   private CounterSignature _signature = null;	
	
	
	/**
	    * Constructor UnsignedSignatureProperties
	    * @param doc
	    */
	public UnsignedSignatureProperties(Document doc) {
		super(doc);	
		XMLUtils.addReturnToElement(this._constructionElement);
	}
	
   
	
		/**
	    * Constructor UnsignedSignatureProperties
	    * @param element
	    * @param BaseURI
	   	* @throws XMLSecurityException
    	*/

	public UnsignedSignatureProperties(Element element, String BaseURI) throws XMLSecurityException {
		super(element, BaseURI);
		
	}
	
	/**
	    * Constructor UnsignedSignatureProperties
	    * @param doc
	    * @param signature
	    */
	
	public UnsignedSignatureProperties(Document doc,CounterSignature signature) {
		super(doc);

	      if (signature != null) {
		         this._constructionElement.appendChild(signature.getElement());
		         XMLUtils.addReturnToElement(this._constructionElement);
		      }
	      }
	
	 /**
	    * Method setCounterSignature
	    *
	    * @param signature
	    */
	   public void setCounterSignature (CounterSignature  signature) {

	      if ((this._state == MODE_SIGN)&& (signature != null)) {
	         this._constructionElement.appendChild(signature.getElement());
	         XMLUtils.addReturnToElement(this._constructionElement);
	      }
	   }
	   
	   /**
	    * Method getCounterSignature
	    * @return the CounterSignatureElement
	    */
	 
	   public CounterSignature getCounterSignature() {
	      return this._signature;
	   }
	   

	   /** @inheritDoc */
	   public String getBaseLocalName() {
	      return Constants._TAG_UNSIGNEDSIGNATUREPROPERTIES;
	   }
	   
	   /** @inheritDoc */
	   public String getBaseNamespace() {
	      return "http://uri.etsi.org/01903/v1.1.1#";
	   }
	}
