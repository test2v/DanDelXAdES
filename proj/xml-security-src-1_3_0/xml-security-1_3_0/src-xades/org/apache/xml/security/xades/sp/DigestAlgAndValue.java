package org.apache.xml.security.xades.sp;




import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.utils.SignatureElementProxy;
import org.apache.xml.security.signature.XMLSignatureException;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.apache.xml.security.xades.Constants;

/**<pre>&lt;xsd:complexType name="DigestAlgAndValueType">
  	&lt;xsd:sequence>
    	&lt;xsd:element name="DigestMethod" type="ds:DigestMethodType"/>
    	&lt;xsd:element name="DigestValue" type="ds:DigestValueType"/>
  	&lt;/xsd:sequence></pre>
 * 
 * @author Psycho_core (nqkoi_ot_bg@yahoo.com)
 * @author mwnnj (krassen.deltchev@rub.de)
 **/

public class DigestAlgAndValue extends SignatureElementProxy implements SignerRoleContent{

	/** DigestAlgAndValue.DigestMethod element */
	   private DigestMethod _digestmethod = null;	
	
	
	 /**
	    * Constructor DigestAlgAndValue
	    *
	    * @param doc
	    * 
	    */
	   public DigestAlgAndValue(Document doc ) {

	      super(doc);
	      XMLUtils.addReturnToElement(this._constructionElement);
	   }
	  
	   
	   

	/**
	    * Constructor DigestAlgAndValue
	    *
	    * @param element
	    * @param BaseURI
	    * @throws XMLSecurityException
	    */
	   public DigestAlgAndValue(Element element, String BaseURI)
	           throws XMLSecurityException {
	      
		   super(element, BaseURI);

	      //			<element name="DigestMethod" minOccurs="0"/>
	      Element digestmethod = XMLUtils.selectDsNode(this._constructionElement.getFirstChild(),
	                                  Constants._TAG_DIGESTMETHOD,0);

	      // check to see if it is there
	      if (digestmethod == null) {
	         Object exArgs[] = { Constants._TAG_DIGESTMETHOD,
	                             Constants._TAG_DIGESTALGANDVALUE };

	         throw new XMLSignatureException("xml.WrongContent", exArgs);
	      }
	      
	      // create a ObjectIdentifier object from that element
	      this._digestmethod = new DigestMethod(digestmethod, BaseURI);
	      
//			<element name="DigestValue" minOccurs="0"/>
	      Element digestvalue = XMLUtils.selectDsNode(this._constructionElement.getFirstChild(),
	                                  Constants._TAG_DIGESTVALUE,0);

	      // check to see if it is there
	      if (digestvalue == null) {
	         Object exArgs[] = { Constants._TAG_DIGESTVALUE,
	                             Constants._TAG_DIGESTALGANDVALUE };

	         throw new XMLSignatureException("xml.WrongContent", exArgs);
	      }
	   }
	   
	   
	   
	   /**
	    * Method setDigestMethod
	    *
	    * @param digestmethod
	    */
	   public void setDigestMethod (DigestMethod digestmethod) {
		   
		   if (digestmethod == null) {
				throw new IllegalArgumentException("HashDataInfo Element is required!");
		   } else if ((this._state == MODE_SIGN)&& (digestmethod != null)) {
		         this._constructionElement.appendChild(digestmethod.getElement());
		         XMLUtils.addReturnToElement(this._constructionElement);
		      }
	      }
	   
	   
	   /**
	    * Method setDigestValue
	    *
	    * @param DigestValue
	    */
	   public void setDigestValue (String DigestValue) {

		   if (DigestValue == null) {
				throw new IllegalArgumentException("HashDataInfo Element is required!");
		   } else if ((this._state == MODE_SIGN)&& (DigestValue != null)) {
	    	  this.addStringElement(DigestValue,Constants._TAG_DIGESTVALUE);
	         XMLUtils.addReturnToElement(this._constructionElement);
	      }
	   }
	   
	   
	   /**
	    * Method getDigestMethod
	    * @return the DigestMethodElement
	    */
	 
	   public DigestMethod getDigestMethod() {
	      return this._digestmethod;
	   }
		  
	   
	   
	   /**
	    * Method getDigestValue
	    *
	    *
	    * @return the DigestValue 
	    */
	   public String getDigestValue() {

	      String DigestValue =
	         this.getTextFromChildElement(Constants._TAG_DIGESTVALUE,Constants.LocalSpecNS);
	      if (DigestValue != null) {
	    	  return new String(DigestValue);
	      }
	      return null;	      
	   }
	   
	   
	   /**
	    * Method getBaseLocalName
	    * @inheritDoc
	    *
	    */
	   public String getBaseLocalName() {
	      return Constants._TAG_DIGESTALGANDVALUE;
	   }

	  
	   /** @inheritDoc */
	   public String getBaseNamespace() {
	      return "http://uri.etsi.org/01903/v1.1.1#";
	   }
	}
