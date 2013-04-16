package org.apache.xml.security.xades.sp;



import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.ObjectContainer;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.apache.xml.security.xades.Constants;

/**
 * <pre>&lt;xsd:complexType name="CommitmentTypeQualifiersListType">
  	&lt;xsd:sequence>
    	&lt;xsd:element name="CommitmentTypeQualifier" type="AnyType" minOccurs="0" maxOccurs="unbounded"/>
  	&lt;/xsd:sequence>
&lt;/xsd:complexType></pre>
 * 
 * @author Psycho_core (nqkoi_ot_bg@yahoo.com)
 * @author mwnnj (krassen.deltchev@rub.de)
 **/

public class CommitmentTypeQualifiersList extends ObjectContainer implements  CommitmentTypeIndicationContent{
	
	/**
	    * Constructor CommitmentTypeQualifiersList
	    * @param doc
	    */
	
	public CommitmentTypeQualifiersList(Document doc) {
		super(doc);	
		XMLUtils.addReturnToElement(this._constructionElement);
	}
	
	/**
	    * Constructor CommitmentTypeQualifiersList
	    *
	    * @param element
	    * @param BaseURI
	    * @throws XMLSecurityException
	    */
	   public CommitmentTypeQualifiersList(Element element, String BaseURI)
	           throws XMLSecurityException {
	      super(element, BaseURI);
	   }
	   
	   /**
	    * Constructor CommitmentTypeQualifiersList
	    *
	    * @param doc
	    * @param CommitmentTypeQualifier String
	    * 
	    */
	   public CommitmentTypeQualifiersList(Document doc,String CommitmentTypeQualifier ) {

	      super(doc);

	  
	      if (CommitmentTypeQualifier != null) {
	    	  this.addText(CommitmentTypeQualifier);
	          XMLUtils.addReturnToElement(this._constructionElement);
	       }
	   }
	   /**
	    * Method setCommitmentTypeQualifier
	    *
	    * @param CommitmentTypeQualifier
	    */
	   public void setCommitmentTypeQualifier (String CommitmentTypeQualifier) {

	      if ((this._state == MODE_SIGN)&& (CommitmentTypeQualifier != null)) {
	    	  
	    	  this.addStringElement(CommitmentTypeQualifier, Constants._TAG_COMMITMENTTYPEQUALIFIER);
	    	  XMLUtils.addReturnToElement(this._constructionElement);
	      }
	   }
	   
	   
	   /**
	    * Method getCommitmentTypeQualifier
	    *
	    * 
	    * @return CommitmentTypeQualifier String
	    */
	   public String getCommitmentTypeQualifier() {

		      String CommitmentTypeQualifier =
		         this.getTextFromChildElement(Constants._TAG_COMMITMENTTYPEQUALIFIER,Constants.SignatureSpecNS);
		      if (CommitmentTypeQualifier != null) {
		    	  return new String(CommitmentTypeQualifier);
		      }
		      return null;	      
		   }
	   
	   
	   
	   

	   /** @inheritDoc */
	   public String getBaseLocalName() {
	      return Constants._TAG_COMMITMENTTYPEQUALIFIERSLIST;
	   }
	   
	   /** @inheritDoc */
	   public String getBaseNamespace() {
	      return "http://uri.etsi.org/01903/v1.1.1#";
	   }
	}
