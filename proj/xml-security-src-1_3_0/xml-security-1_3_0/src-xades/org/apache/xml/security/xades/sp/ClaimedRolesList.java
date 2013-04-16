package org.apache.xml.security.xades.sp;



import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.ObjectContainer;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.apache.xml.security.xades.Constants;

/**<pre>&lt;xsd:complexType name="ClaimedRolesListType">
  	&lt;xsd:sequence>
    		&lt;xsd:element name="ClaimedRole" type="AnyType" maxOccurs="unbounded"/>
  	&lt;/xsd:sequence>
&lt;/xsd:complexType></pre>
 * 
 * @author Psycho_core (nqkoi_ot_bg@yahoo.com)
 * @author mwnnj (krassen.deltchev@rub.de)
 **/

public class ClaimedRolesList extends ObjectContainer implements  SignerRoleContent{
	
	/**
	    * Constructor ClaimedRolesList
	    * @param doc
	    */
	public ClaimedRolesList(Document doc){
		super(doc);
		XMLUtils.addReturnToElement(this._constructionElement);
	}
	
	/**
	    * Constructor ClaimedRolesList
	    *
	    * @param element
	    * @param BaseURI
	    * @throws XMLSecurityException
	    */
	   public ClaimedRolesList(Element element, String BaseURI)
	           throws XMLSecurityException {
	      super(element, BaseURI);
	   }
	   
	   /**
	    * Constructor ClaimedRolesList
	    *
	    * @param doc
	    * @param ClaimedRole
	    * 
	    */
	   public ClaimedRolesList(Document doc,String ClaimedRole ) {

	      super(doc);

	  
	      if (ClaimedRole != null) {
	    	  this.addStringElement(ClaimedRole, Constants._TAG_CLAIMEDROLE);
	          XMLUtils.addReturnToElement(this._constructionElement);
	       }
	   }
	   /**
	    * Method setClaimedRole
	    *
	    * @param ClaimedRole
	    */
	   public void setClaimedRole (String ClaimedRole) {

	      if ((this._state == MODE_SIGN)&& (ClaimedRole != null)) {
	    	  
	    	  this.addStringElement(ClaimedRole, Constants._TAG_CLAIMEDROLE);
	    	  XMLUtils.addReturnToElement(this._constructionElement);
	      }
	   }
	   
	   
	   
	   
	   /**
	    * Method getClaimedRole
	    *
	    * 
	    * @return ClaimedRole String
	    */
	   public String getClaimedRole() {

		      String ClaimedRole =
		         this.getTextFromChildElement(Constants._TAG_CLAIMEDROLE,Constants.SignatureSpecNS);
		      if (ClaimedRole != null) {
		    	  return new String(ClaimedRole);
		      }
		      return null;	      
		   }
	   
	   
	   
	   

	   /** @inheritDoc */
	   public String getBaseLocalName() {
	      return Constants._TAG_CLAIMEDROLESLIST;
	   }
	   /** @inheritDoc */
	   public String getBaseNamespace() {
	      return "http://uri.etsi.org/01903/v1.1.1#";
	   }
	}
