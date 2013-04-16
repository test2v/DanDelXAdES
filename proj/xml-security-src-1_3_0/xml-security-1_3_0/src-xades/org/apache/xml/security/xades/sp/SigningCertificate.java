

package org.apache.xml.security.xades.sp;


import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.XMLSignatureException;
import org.apache.xml.security.signature.ObjectContainer;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.apache.xml.security.xades.sp.CertIDList;
import org.apache.xml.security.xades.*;


/**<pre>&lt;xsd:element name="SigningCertificate" type="CertIDListType"/>
&lt;xsd:complexType name="CertIDListType">
		&lt;xsd:sequence>
			&lt;xsd:element name="Cert" type="CertIDType" maxOccurs="unbounded"/>
		&lt;/xsd:sequence>
&lt;/xsd:complexType></pre>

* 
* @author Psycho_core (nqkoi_ot_bg@yahoo.com)
* @author mwnnj (krassen.deltchev@rub.de)
**/

public class SigningCertificate extends ObjectContainer implements SignedSignaturePropertiesContent{
	
	/** {@link org.apache.commons.logging} logging facility */
	  static org.apache.commons.logging.Log log = 
	        org.apache.commons.logging.LogFactory.getLog(SigningCertificate.class.getName());

	  /**
	    * Constructor SigningCertificate
	    *
	    * @param doc
	    */
	   public SigningCertificate(Document doc) {

	      super(doc);

	      XMLUtils.addReturnToElement(this._constructionElement);
	     	      
	   }
	   
	   /**
	    * Constructor SigningCertificate
	    *
	    * @param element
	    * @param BaseURI
	    * @throws XMLSecurityException
	    */
	   public SigningCertificate(Element element, String BaseURI) throws XMLSecurityException {

	      super(element, BaseURI);

	   }

	   
	   
	   
	   
	/**
    * Constructor SigningCertificate
    *
    * @param  SigningCertificate  CertIdList(type)
    * @throws XMLSecurityException
    */
	 
	   public SigningCertificate(Document doc, CertIDList SigningCertificate) {

		      super(doc);

		      if (SigningCertificate != null) {
		          this._constructionElement.appendChild(SigningCertificate.getElement());
		          XMLUtils.addReturnToElement(this._constructionElement);
		       }
		      
		     
		   }
	   
	   /**
	    * Method setSigningCertificate
	    *
	    * @param SigningCertificate
	    */
	   public void setSigningCertificate (CertIDList SigningCertificate) {

	      if ((this._state == MODE_SIGN)&& (SigningCertificate != null)) {
	         this._constructionElement.appendChild(SigningCertificate.getElement());
	         XMLUtils.addReturnToElement(this._constructionElement);
	      }
	   }
		   /**
		    * Method getSigningCertificate
		    *
		    *
		    * @throws XMLSecurityException
		    * @return the SigningCertificateElement
		    */
		   public SigningCertificate getSigningCertificate() throws XMLSecurityException {

		      try {
		       Element SigningCertificateElement =
		             XMLUtils.selectDsNode(this._constructionElement,                                                
		                                                Constants._TAG_SIGNINGCERTIFICATE, 0);

		         if (SigningCertificateElement != null) {
		            return new SigningCertificate(SigningCertificateElement, this._baseURI);
		         }

		         return null;
		      } catch (XMLSignatureException ex) {
		         throw new XMLSecurityException("empty", ex);
		      }
		   }
		   
		  
   

	   /** @inheritDoc */
	   public String getBaseLocalName() {
		   return Constants._TAG_SIGNINGCERTIFICATE;
	   }
	   /** @inheritDoc */
	   public String getBaseNamespace() {
	      return "http://uri.etsi.org/01903/v1.1.1#";
	   }
	
}

