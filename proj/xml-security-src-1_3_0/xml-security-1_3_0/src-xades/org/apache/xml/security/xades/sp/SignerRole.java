

package org.apache.xml.security.xades.sp;



import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.XMLSignatureException;
import org.apache.xml.security.signature.ObjectContainer;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


import org.apache.xml.security.xades.*;

/**<pre>&lt;xsd:element name="SignerRole" type="SignerRoleType"/>
		&lt;xsd:complexType name="SignerRoleType">
  			&lt;xsd:sequence>
    			&lt;xsd:element name="ClaimedRoles" type="ClaimedRolesListType" minOccurs="0"/>
    			&lt;xsd:element name="CertifiedRoles" type="CertifiedRolesListType" minOccurs="0"/>
  			&lt;/xsd:sequence>
		&lt;/xsd:complexType></pre>

 * 
 * @author Psycho_core (nqkoi_ot_bg@yahoo.com)
 * @author mwnnj (krassen.deltchev@rub.de)
 **/

public class SignerRole extends ObjectContainer implements  SignedSignaturePropertiesContent{
	
	
	/**
	    * Constructor SignerRole
	    * @param doc
	    */
	public SignerRole(Document doc) {
		super(doc);	
		XMLUtils.addReturnToElement(this._constructionElement);
	}
	
	/**
	    * Constructor SignerRole
	    *
	    * @param element
	    * @param BaseURI
	    * @throws XMLSecurityException
	    */
	   public SignerRole(Element element, String BaseURI)
	           throws XMLSecurityException {
	      super(element, BaseURI);
	   }
	   
	   /**
	    * Constructor SignerRole
	    *
	    * @param doc
	    * @param ClaimedRoles   ClaimedRolesList
	    * @param CertifiedRoles CertifiedRolesList
	    */
	   public SignerRole(Document doc, ClaimedRolesList ClaimedRoles, CertifiedRolesList CertifiedRoles) {

	      super(doc);

	      if (ClaimedRoles != null) {
	          this._constructionElement.appendChild(ClaimedRoles.getElement());
	          XMLUtils.addReturnToElement(this._constructionElement);
	       }
	      
	      if (CertifiedRoles != null) {
	          this._constructionElement.appendChild(CertifiedRoles.getElement());
	          XMLUtils.addReturnToElement(this._constructionElement);
	       }
	   }
	   
	   
	   /**
	    * Method setClaimedRoles
	    *
	    * @param ClaimedRoles
	    */
	   public void setClaimedRoles (ClaimedRolesList ClaimedRoles) {

	      if ((this._state == MODE_SIGN)&& (ClaimedRoles != null)) {
	         this._constructionElement.appendChild(ClaimedRoles.getElement());
	         XMLUtils.addReturnToElement(this._constructionElement);
	      }
	   }
	   
	   /**
	    * Method setCertifiedRoles
	    *
	    * @param CertifiedRoles
	    */
	   public void setCertifiedRoles (CertifiedRolesList CertifiedRoles) {

	      if ((this._state == MODE_SIGN)&& (CertifiedRoles != null)) {
	         this._constructionElement.appendChild(CertifiedRoles.getElement());
	         XMLUtils.addReturnToElement(this._constructionElement);
	      }
	   }
	   
	   /**
	    * Method getClaimedRolesList
	    *
	    *
	    * @throws XMLSecurityException
	    * @return the ClaimedRolesListElement
	    */
	   public ClaimedRolesList getClaimedRolesList() throws XMLSecurityException {

	      try {
	       Element ClaimedRolesElem =
	             XMLUtils.selectDsNode(this._constructionElement,                                                
	                                                Constants._TAG_CLAIMEDROLES, 0);

	         if (ClaimedRolesElem != null) {
	            return new ClaimedRolesList(ClaimedRolesElem, this._baseURI);
	         }

	         return null;
	      } catch (XMLSignatureException ex) {
	         throw new XMLSecurityException("empty", ex);
	      }
	   }
	   
	   /**
	    * Method getCertifiedRolesList
	    *
	    *
	    * @throws XMLSecurityException
	    * @return the CertifiedRolesListElement
	    */
	   public CertifiedRolesList getCertifiedRolesList() throws XMLSecurityException {

	      try {
	       Element CertifiedRolesList =
	             XMLUtils.selectDsNode(this._constructionElement,                                                
	                                                Constants._TAG_CERTIFIEDROLES, 0);

	         if (CertifiedRolesList != null) {
	            return new CertifiedRolesList(CertifiedRolesList, this._baseURI);
	         }

	         return null;
	      } catch (XMLSignatureException ex) {
	         throw new XMLSecurityException("empty", ex);
	      }
	   }

	   /** @inheritDoc */
	   public String getBaseLocalName() {
	      return Constants._TAG_SIGNERROLE;
	   }
	   /** @inheritDoc */
	   public String getBaseNamespace() {
	      return "http://uri.etsi.org/01903/v1.1.1#";
	   }
	}
