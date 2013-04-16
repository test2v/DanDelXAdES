package org.apache.xml.security.xades.sp;


import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.ObjectContainer;
import org.apache.xml.security.signature.XMLSignatureException;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.apache.xml.security.xades.*;

/**
 * <pre>
&lt;xsd:complexType name="SignaturePolicyIdType">
		&lt;xsd:sequence>
			&lt;xsd:element name="SigPolicyId" type="ObjectIdentifierType"/>
			&lt;xsd:element ref="ds:Transforms" minOccurs="0"/>
			&lt;xsd:element name="SigPolicyHash" type="DigestAlgAndValueType"/>
    		&lt;xsd:element name="SigPolicyQualifiers" type="SigPolicyQualifiersListType" minOccurs="0"/>
  		&lt;/xsd:sequence>
	&lt;/xsd:complexType></pre>
 * 
 * @author Psycho_core (nqkoi_ot_bg@yahoo.com)
 * @author mwnnj (krassen.deltchev@rub.de)
 **/
public class SignaturePolicyIdType extends ObjectContainer implements  SignedSignaturePropertiesContent{
	
	
	/**
	    * Constructor SignaturePolicyIdType
	    *
	    * @param doc
	    */
	   public SignaturePolicyIdType(Document doc) {

	      super(doc);

	      XMLUtils.addReturnToElement(this._constructionElement);
	     	      
	   }
	   
	   
	/**
	    * Constructor SignaturePolicyIdType
	    *
	    * @param element
	    * @param BaseURI
	    * @throws XMLSecurityException
	    */
	   public SignaturePolicyIdType(Element element, String BaseURI)
	           throws XMLSecurityException {
	      super(element, BaseURI);
	      
	   }
	   
	  
	  /**
	    * Method setSigPolicyId
	    *
	    * @param SigPolicyId
	    */
	   
	   public  void setSigPolicyId (ObjectIdentifier SigPolicyId){
		   if (SigPolicyId == null) {
				throw new IllegalArgumentException("SigPolicyId Element is required!");
			}  else if ((this._state == MODE_SIGN)&& (SigPolicyId != null)) {
		         this._constructionElement.appendChild(SigPolicyId.getElement());
		         XMLUtils.addReturnToElement(this._constructionElement);
		      }
		   
	   } 
	   
	   /**
	    * Method setSigPolicyHash
	    *
	    * @param SigPolicyHash
	    */   
	   
	   public  void setSigPolicyHash (DigestAlgAndValue SigPolicyHash ){
		   if (SigPolicyHash == null) {
				throw new IllegalArgumentException("SigPolicyHash Element is required!");
			} else if ((this._state == MODE_SIGN)&& (SigPolicyHash != null)) {
		         this._constructionElement.appendChild(SigPolicyHash.getElement());
		         XMLUtils.addReturnToElement(this._constructionElement);
		      }		   
	   }
	   
	   
	   /**
	    * Method setTransforms1
	    *
	    * @param transforms1
	    */
	   
	   public  void setTransforms1 (Transforms1 transforms1 ){
		   if ((this._state == MODE_SIGN)&& (transforms1 != null)) {
		         this._constructionElement.appendChild(transforms1.getElement());
		         XMLUtils.addReturnToElement(this._constructionElement);
		      }
		}
	   
	   /**
	    * Method setSigPolicyQualifiers
	    *
	    * @param SigPolicyQualifiers
	    */
	   
	   
	   public  void setSigPolicyQualifiers (SigPolicyQualifiersListType SigPolicyQualifiers ){
		   if ((this._state == MODE_SIGN)&& (SigPolicyQualifiers != null)) {
		         this._constructionElement.appendChild(SigPolicyQualifiers.getElement());
		         XMLUtils.addReturnToElement(this._constructionElement);
		      }
		 }
	   
	   /**
	    * Method getSigPolicyId
	    *
	    *
	    * @throws XMLSecurityException
	    * @return the SigPolicyIdElement
	    */
	   public ObjectIdentifier getSigPolicyId() throws XMLSecurityException {

	      try {
	       Element SigPolicyId =
	             XMLUtils.selectDsNode(this._constructionElement,                                                
	                                                Constants._TAG_SIGPOLICYID, 0);

	         if (SigPolicyId != null) {
	            return new ObjectIdentifier(SigPolicyId, this._baseURI);
	         }

	         return null;
	      } catch (XMLSignatureException ex) {
	         throw new XMLSecurityException("empty", ex);
	      }
	   }
	   
	   /**
	    * Method getSigPolicyId
	    *
	    *
	    * @throws XMLSecurityException
	    * @return the SigPolicyHashElement
	    */
	   public DigestAlgAndValue getSigPolicyHash() throws XMLSecurityException {

	      try {
	       Element SigPolicyHash =
	             XMLUtils.selectDsNode(this._constructionElement,                                                
	                                                Constants._TAG_SIGPOLICYHASH, 0);

	         if (SigPolicyHash != null) {
	            return new DigestAlgAndValue(SigPolicyHash, this._baseURI);
	         }

	         return null;
	      } catch (XMLSignatureException ex) {
	         throw new XMLSecurityException("empty", ex);
	      }
	   }
	   
	   /**
	    * Method getTransforms1
	    *
	    *
	    * @throws XMLSecurityException
	    * @return the TransformslElement
	    */
	   public Transforms1 getTransforms1() throws XMLSecurityException {

	      try {
	       Element transforms1 =
	             XMLUtils.selectDsNode(this._constructionElement,                                                
	                                                Constants._TAG_TRANSFORMS, 0);

	         if (transforms1 != null) {
	            return new Transforms1(transforms1, this._baseURI);
	         }

	         return null;
	      } catch (XMLSignatureException ex) {
	         throw new XMLSecurityException("empty", ex);
	      }
	   }
	   
	   /**
	    * Method getSigPolicyQualifiers
	    *
	    *
	    * @throws XMLSecurityException
	    * @return the SigPolicyQualifiersElement
	    */
	   public SigPolicyQualifiersListType getSigPolicyQualifiers() throws XMLSecurityException {

	      try {
	       Element SigPolicyQualifiers =
	             XMLUtils.selectDsNode(this._constructionElement,                                                
	                                                Constants._TAG_TRANSFORMS, 0);

	         if (SigPolicyQualifiers != null) {
	            return new SigPolicyQualifiersListType(SigPolicyQualifiers, this._baseURI);
	         }

	         return null;
	      } catch (XMLSignatureException ex) {
	         throw new XMLSecurityException("empty", ex);
	      }
	   }
	      
	
	

	   /** @inheritDoc */
	   public String getBaseLocalName() {
	      return Constants._TAG_SIGNATUREPOLICYID;
	   }
	   /** @inheritDoc */
	   public String getBaseNamespace() {
	      return "http://uri.etsi.org/01903/v1.1.1#";
	   }
	}
