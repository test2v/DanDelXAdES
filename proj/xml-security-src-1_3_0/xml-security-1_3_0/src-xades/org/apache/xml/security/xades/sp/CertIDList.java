package org.apache.xml.security.xades.sp;



import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.XMLSignatureException;
import org.apache.xml.security.signature.ObjectContainer;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


import org.apache.xml.security.xades.Constants;
import org.apache.xml.security.xades.sp.CertID;


/**
 * <pre>&lt;xsd:complexType name="CertIDListType">
  	&lt;xsd:sequence>
    		&lt;xsd:element name="Cert" type="CertIDType" maxOccurs="unbounded"/>
  	&lt;/xsd:sequence>
&lt;/xsd:complexType><pre/>
 * 
 * @author Psycho_core (nqkoi_ot_bg@yahoo.com)
 * @author mwnnj (krassen.deltchev@rub.de)
 **/



public class CertIDList extends ObjectContainer implements  SignerRoleContent{
	
	/**
	 	*  Constructor SignerRole
		*  @param doc
		*/
	
	public CertIDList(Document doc){
		super(doc);
		XMLUtils.addReturnToElement(this._constructionElement);
	}
		

	/**
	    * Constructor CertIDList
	    *
	    * @param element
	    * @param BaseURI
	    * @throws XMLSecurityException
	    */
	   public CertIDList(Element element, String BaseURI)
	           throws XMLSecurityException {
	      super(element, BaseURI);
	   }
	   
	   /**
	    * Constructor CertIDList
	    *
	    * @param doc
	    * @param Cert CertID
	    * 
	    */
	   public CertIDList(Document doc,CertID Cert ) {

	      super(doc);

	  
	      if (Cert != null) {
	          this._constructionElement.appendChild(Cert.getElement());
	          XMLUtils.addReturnToElement(this._constructionElement);
	       }
	       }
	   
	   /**
	    * Method setCertID
	    *
	    * @param Cert
	    */
	   public void setCert (CertID Cert) {

	      if ((this._state == MODE_SIGN)&& (Cert != null)) {
	         this._constructionElement.appendChild(Cert.getElement());
	         XMLUtils.addReturnToElement(this._constructionElement);
	      }
	   }
	   
	   
	   
	   /**
	    * Method getCert
	    *
	    *
	    * @throws XMLSecurityException
	    * @return the CertElement
	    */
	   public CertID getCert() throws XMLSecurityException {

		   try {
			   Element Cert =
				   XMLUtils.selectDsNode(this._constructionElement,                                                
                                             Constants._TAG_CERT, 0);

			   if (Cert != null) {
				   return new CertID(Cert, this._baseURI);
      }

      return null;
   } catch (XMLSignatureException ex) {
      throw new XMLSecurityException("empty", ex);
   }
}
	   
	   
	   

	   /** @inheritDoc */
	   public String getBaseLocalName() {
	      return Constants._TAG_CERTIDLIST;
	   }
	   
	   /** @inheritDoc */
	   public String getBaseNamespace() {
	      return "http://uri.etsi.org/01903/v1.1.1#";
	   }
	}
