package org.apache.xml.security.xades.sp;






import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.ObjectContainer;
import org.apache.xml.security.signature.XMLSignatureException;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.apache.xml.security.xades.Constants;
import org.apache.xml.security.xades.IssuerSerial;

/** 
 * 	<pre>&lt;xsd:complexType name="CertIDType">
  	&lt;xsd:sequence>
   		&lt;xsd:element name="CertDigest" type="DigestAlgAndValueType"/>
  		&lt;xsd:element name="IssuerSerial" type="ds:X509IssuerSerialType"/>
  	&lt;/xsd:sequence>
&lt;/xsd:complexType></pre>
 * 
 * @author Psycho_core (nqkoi_ot_bg@yahoo.com)
 * @author mwnnj (krassen.deltchev@rub.de)
 **/

public class CertID extends ObjectContainer implements  SigningCertificateContent{
	
	/**
	    * Constructor CertID
	    * @param doc
	    */
	public CertID(Document doc){
		super(doc);
		XMLUtils.addReturnToElement(this._constructionElement);
	}
		

	/**
	    * Constructor CertID
	    *
	    * @param element
	    * @param BaseURI
	    * @throws XMLSecurityException
	    */
	   public CertID(Element element, String BaseURI)
	           throws XMLSecurityException {
	      super(element, BaseURI);
	   }
	   
	   /**
	    * Constructor CertID
	    *
	    * @param doc
	    * @param CertDigest DigestAlgAndValue 
	    * @param issuerserial XMLX509IssuerSerial 
	    * 
	    */
	   public CertID(Document doc,DigestAlgAndValue CertDigest,
			    IssuerSerial issuerserial) {

	      super(doc);

		   if ((CertDigest == null)||(issuerserial == null)) {
				throw new IllegalArgumentException("ObjectReference Attibute is required!");
			}
		   else if (CertDigest != null) {
	          this._constructionElement.appendChild(CertDigest.getElement());
	          XMLUtils.addReturnToElement(this._constructionElement);
	       }
	      if (issuerserial != null) {
	          this._constructionElement.appendChild(issuerserial.getElement());
	          XMLUtils.addReturnToElement(this._constructionElement);
	       }
	       }
	   
	   /**
	    * Method setCertDigest 
	    *
	    * @param CertDigest
	    */
	   public void setCertDigest (DigestAlgAndValue CertDigest) {
		   if (CertDigest == null) {
				throw new IllegalArgumentException("CertDigest Element is required!");
			}  
		   else if ((this._state == MODE_SIGN)&& (CertDigest != null)) {
	         this._constructionElement.appendChild(CertDigest.getElement());
	         XMLUtils.addReturnToElement(this._constructionElement);
	      }
	      
	   }
	   
	   /**
	    * Method setIssuerSerial
	    *
	    * @param issuerserial
	    */
	   public void setIssuerSerial (IssuerSerial issuerserial) {
		   if (issuerserial == null) {
				throw new IllegalArgumentException("IssuerSerial Element is required!");
			}  
		   else if ((this._state == MODE_SIGN)&& (issuerserial != null)) {
	         this._constructionElement.appendChild(issuerserial.getElement());
	         XMLUtils.addReturnToElement(this._constructionElement);
	      }
	      
	   }
	   
	   
	   /**
	    * Method getCertDigest
	    *
	    *
	    * @throws XMLSecurityException
	    * @return the CertDigestElement
	    */
	   public DigestAlgAndValue getCertDigest() throws XMLSecurityException {

	      try {
	       Element CertDigest =
	             XMLUtils.selectDsNode(this._constructionElement,                                                
	                                                Constants._TAG_CERTDIGEST, 0);

	         if (CertDigest != null) {
	            return new DigestAlgAndValue(CertDigest, this._baseURI);
	         }

	         return null;
	      } catch (XMLSignatureException ex) {
	         throw new XMLSecurityException("empty", ex);
	      }
	   }
	   
	   /**
	    * Method getIssuerSerial
	    *
	    *
	    * @throws XMLSecurityException
	    * @return the IssuerSerialElement
	    */
	   public IssuerSerial getIssuerSerial() throws XMLSecurityException {

	      try {
	       Element issuerserial =
	             XMLUtils.selectDsNode(this._constructionElement,                                                
	                                                Constants._TAG_ISSUERSERIAL, 0);

	         if (issuerserial != null) {
	            return new IssuerSerial(issuerserial, this._baseURI);
	         }

	         return null;
	      } catch (XMLSignatureException ex) {
	         throw new XMLSecurityException("empty", ex);
	      }
	   }	   
	   

	   /** @inheritDoc */
	   public String getBaseLocalName() {
	      return Constants._TAG_CERTID;
	   }
	   
	   /** @inheritDoc */
	   public String getBaseNamespace() {
	      return "http://uri.etsi.org/01903/v1.1.1#";
	   }
	}
