


package org.apache.xml.security.xades.sp;



import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.XMLSignatureException;
import org.apache.xml.security.signature.ObjectContainer;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.apache.xml.security.xades.*;

/**
 * <pre>&lt;xsd:element name="SignaturePolicyIdentifier" type="SignaturePolicyIdentifierType"/>
	&lt;xsd:complexType name="SignaturePolicyIdentifierType">
		&lt;xsd:choice>
			&lt;xsd:element name="SignaturePolicyId" type="SignaturePolicyIdType"/>
			&lt;xsd:element name="SignaturePolicyImplied"/>
		&lt;/xsd:choice>
	&lt;/xsd:complexType>
	&lt;xsd:complexType name="SignaturePolicyIdType">
		&lt;xsd:sequence>
			&lt;xsd:element name="SigPolicyId" type="ObjectIdentifierType"/>
			&lt;xsd:element ref="ds:Transforms" minOccurs="0"/>
			&lt;xsd:element name="SigPolicyHash" type="DigestAlgAndValueType"/>
    		&lt;xsd:element name="SigPolicyQualifiers" type="SigPolicyQualifiersListType" minOccurs="0"/>
  		&lt;/xsd:sequence>
	&lt;/xsd:complexType>

	&lt;xsd:complexType name="SigPolicyQualifiersListType">
  		&lt;xsd:sequence>
    		&lt;xsd:element name="SigPolicyQualifier" type="AnyType" maxOccurs="unbounded"/>
  		&lt;/xsd:sequence>
	&lt;/xsd:complexType></pre>
 * 
 * @author Psycho_core (nqkoi_ot_bg@yahoo.com)
 * @author mwnnj (krassen.deltchev@rub.de)
 **/

public class SignaturePolicyIdentifier extends ObjectContainer implements  SignedSignaturePropertiesContent{
	
	
	/**
	    * Constructor SignaturePolicyIdentifier
	    *
	    * @param doc
	    */
	   public SignaturePolicyIdentifier(Document doc) {

	      super(doc);

	      XMLUtils.addReturnToElement(this._constructionElement);
	     	      
	   }
	   
	   
	/**
	    * Constructor SignaturePolicyIdentifier
	    *
	    * @param element
	    * @param BaseURI
	    * @throws XMLSecurityException
	    */
	   public SignaturePolicyIdentifier(Element element, String BaseURI)
	           throws XMLSecurityException {
	      super(element, BaseURI);
	      
	   }
	   
	   /**
	    * Method setSignaturePolicyId
	    *
	    * @param SignaturePolicyId
	    */
	   
	   
	   public  void setSignaturePolicyId (SignaturePolicyIdType SignaturePolicyId){
		   if (SignaturePolicyId == null) {
				throw new IllegalArgumentException("SignaturePolicyId Element is required!");
			} else if ((this._state == MODE_SIGN)&& (SignaturePolicyId != null)) {
		         this._constructionElement.appendChild(SignaturePolicyId.getElement());
		         XMLUtils.addReturnToElement(this._constructionElement);
		      }
		   
	   }
	   
	   /**
	    * Method getSignaturePolicyId
	    *
	    * @throws XMLSecurityException
	    * @return the SignaturePolicyIdentifier Element
	    */
	   public SignaturePolicyIdType getSignaturePolicyId() throws XMLSecurityException {

	      try {
	       Element SignaturePolicyId =
	             XMLUtils.selectDsNode(this._constructionElement,                                                
	                                                Constants._TAG_SIGNATUREPOLICYIDENTIFIER, 0);

	         if (SignaturePolicyId != null) {
	            return new SignaturePolicyIdType(SignaturePolicyId, this._baseURI);
	         }

	         return null;
	      } catch (XMLSignatureException ex) {
	         throw new XMLSecurityException("empty", ex);
	      }
	   }
	   
	   
	   

	   
	
	

	   /** @inheritDoc */
	   public String getBaseLocalName() {
	      return Constants._TAG_SIGNATUREPOLICYIDENTIFIER;
	   }
	   /** @inheritDoc */
	   public String getBaseNamespace() {
	      return "http://uri.etsi.org/01903/v1.1.1#";
	   }
	}
