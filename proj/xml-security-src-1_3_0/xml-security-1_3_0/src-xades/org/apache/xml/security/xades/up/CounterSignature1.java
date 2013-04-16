/*
 * <pre>&lt;xsd:element name="UnsignedSignatureProperties" type="
UnsignedSignaturePropertiesType"/>
&lt;xsd:complexType name="UnsignedSignaturePropertiesType">
&lt;xsd:sequence>
&lt;xsd:element name="CounterSignature" type="CounterSignatureType"
minOccurs="0" maxOccurs="unbounded"/>
&lt;xsd:element name="SignatureTimeStamp" type="TimeStampType"
minOccurs="0" maxOccurs="unbounded"/>
&lt;xsd:element name="CompleteCertificateRefs"
type="CompleteCertificateRefsType" minOccurs="0"/>
&lt;xsd:element name="CompleteRevocationRefs"
type="CompleteRevocationRefsType" minOccurs="0"/>
&lt;xsd:choice>
&lt;xsd:element name="SigAndRefsTimeStamp" type="TimeStampType"
minOccurs="0" maxOccurs="unbounded"/>
&lt;xsd:element name="RefsOnlyTimeStamp" type="TimeStampType"
minOccurs="0" maxOccurs="unbounded"/>
&lt;/xsd:choice>
&lt;xsd:element name="CertificateValues" type="CertificateValuesType"
minOccurs="0"/>
&lt;xsd:element name="RevocationValues" type="RevocationValuesType"
minOccurs="0"/>
&lt;xsd:element name="ArchiveTimeStamp" type="TimeStampType"
minOccurs="0" maxOccurs="unbounded"/>
&lt;/xsd:sequence>
&lt;/xsd:complexType></pre>
 */


package org.apache.xml.security.xades.up;






import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.ObjectContainer;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.apache.xml.security.xades.Constants;

/*** @author $Authors: Psycho_core,mwnnj $***/

public class CounterSignature1 extends ObjectContainer implements UnsignedPropertiesContent{
	
	/** CounterSignature.ds:Signature element */
	   private XMLSignature _signature = null;
	
	  
	
		
	/**
	    * Constructor CounterSignature
	    * @param doc
	    * @throws XMLSecurityException
	    */
	
	public CounterSignature1(Document doc) 
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

	
	   public CounterSignature1(Element element, String BaseURI)
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
	   public void setXMLSignature (XMLSignature signature) {
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
