package org.apache.xml.security.xades.sp;


import org.apache.xml.security.signature.XMLSignatureException;
import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.ObjectContainer;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;



import org.apache.xml.security.xades.Constants;

/**
 * <pre>&lt;xsd:complexType name="CertifiedRolesListType">
  	&lt;xsd:sequence>
    		&lt;xsd:element name="CertifiedRole" type="EncapsulatedPKIDataType" maxOccurs="unbounded"/>
  	&lt;/xsd:sequence>
&lt;/xsd:complexType></pre>
 * 
 * @author Psycho_core (nqkoi_ot_bg@yahoo.com)
 * @author mwnnj (krassen.deltchev@rub.de)
 **/

public class CertifiedRolesList extends ObjectContainer implements  SignerRoleContent{
	
	/**
	    * Constructor CertifiedRolesList
	    * @param doc
	    */
	public CertifiedRolesList(Document doc){
		super(doc);
		XMLUtils.addReturnToElement(this._constructionElement);
	}
		

	/**
	    * Constructor CertifiedRolesList
	    *
	    * @param element
	    * @param BaseURI
	    * @throws XMLSecurityException
	    */
	   public CertifiedRolesList(Element element, String BaseURI)
	           throws XMLSecurityException {
	      super(element, BaseURI);
	   }
	   
	   /**
	    * Constructor CertifiedRolesList
	    *
	    * @param doc
	    * @param CertifiedRole EncapsulatedPKIData
	    * 
	    */
	   public CertifiedRolesList(Document doc,EncapsulatedPKIDataType CertifiedRole ) {

	      super(doc);

	  
	      if (CertifiedRole != null) {
	          this._constructionElement.appendChild(CertifiedRole.getElement());
	          XMLUtils.addReturnToElement(this._constructionElement);
	      		}
	       }
	   
	   
	   
	   
	   
	   /**
	    * Method getCertifiedRole
	    *
	    *
	    * @throws XMLSecurityException
	    * @return the CertifiedRoleElement
	    */
	   public EncapsulatedPKIDataType getCertifiedRole() throws XMLSecurityException {

		   try {
			   Element CertifiedRole =
				   XMLUtils.selectDsNode(this._constructionElement,                                                
                                             Constants._TAG_CERTIFIEDROLE, 0);

			   if (CertifiedRole != null) {
				   return new EncapsulatedPKIDataType(CertifiedRole, this._baseURI);
      }

      return null;
   } catch (XMLSignatureException ex) {
      throw new XMLSecurityException("empty", ex);
   }
}
	   
	   
	   

	   /** @inheritDoc */
	   public String getBaseLocalName() {
	      return Constants._TAG_CERTIFIEDROLESLIST;
	   }
	   
	   /** @inheritDoc */
	   public String getBaseNamespace() {
	      return "http://uri.etsi.org/01903/v1.1.1#";
	   }
	}
