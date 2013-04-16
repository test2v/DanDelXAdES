


package org.apache.xml.security.xades.sp;



import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.ObjectContainer;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.apache.xml.security.xades.*;

/**<pre>&lt;xsd:complexType name="SigPolicyQualifiersListType">
	&lt;xsd:sequence>
		&lt;xsd:element name="SigPolicyQualifier" type="AnyType" maxOccurs="unbounded"/>
	&lt;/xsd:sequence>
&lt;/xsd:complexType></pre>
* 
* @author Psycho_core (nqkoi_ot_bg@yahoo.com)
* @author mwnnj (krassen.deltchev@rub.de)
**/

public class SigPolicyQualifiersListType extends ObjectContainer implements  SignedSignaturePropertiesContent{
	
	
	/**
	    * Constructor SigPolicyQualifiersListType
	    *
	    * @param doc
	    */
	   public SigPolicyQualifiersListType(Document doc) {

	      super(doc);

	      XMLUtils.addReturnToElement(this._constructionElement);
	     	      
	   }
	   
	   
	/**
	    * Constructor SigPolicyQualifiersListType
	    *
	    * @param element
	    * @param BaseURI
	    * @throws XMLSecurityException
	    */
	   public SigPolicyQualifiersListType(Element element, String BaseURI)
	           throws XMLSecurityException {
	      super(element, BaseURI);
	      
	   }
	   
	   /**
	    * Method setSigPolicyQualifier
	    *
	    * @param SigPolicyQualifier
	    */	   
	   public  void setSigPolicyQualifier (String SigPolicyQualifier){
		   if ((this._state == MODE_SIGN)&& (SigPolicyQualifier != null)) {

		    	  this.addStringElement(SigPolicyQualifier, Constants._TAG_SIGPOLICYQUALIFIER);
		    	  XMLUtils.addReturnToElement(this._constructionElement);
		      }
		   
	   }
	   
	   /**
	    * Method getSigPolicyQualifier
	    *
	    *
	    * @return the SigPolicyQualifier Value
	    */
	   public String getSigPolicyQualifier() {

	      String SigPolicyQualifier =
	         this.getTextFromChildElement(Constants._TAG_SIGPOLICYQUALIFIER,Constants.LocalSpecNS);
	      if (SigPolicyQualifier != null) {
	    	  return new String(SigPolicyQualifier);
	      }
	      return null;	      
	   }	   

	   
	
	

	   /** @inheritDoc */
	   public String getBaseLocalName() {
	      return Constants._TAG_SIGPOLICYQUALIFIERS;
	   }
	   /** @inheritDoc */
	   public String getBaseNamespace() {
	      return "http://uri.etsi.org/01903/v1.1.1#";
	   }
	}
