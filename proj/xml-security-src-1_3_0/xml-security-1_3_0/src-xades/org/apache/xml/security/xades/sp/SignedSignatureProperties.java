


package org.apache.xml.security.xades.sp;



import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.ObjectContainer;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


import org.apache.xml.security.xades.*;

/**
 * <pre>&lt;xsd:element name="SignedSignatureProperties" type="	SignedSignaturePropertiesType" />
	&lt;xsd:complexType name="SignedSignaturePropertiesType">
		&lt;xsd:sequence>
			&lt;xsd:element name="SigningTime" type="xsd:dateTime"/>
			&lt;xsd:element name="SigningCertificate" type="CertIDListType"/>
			&lt;xsd:element name="SignaturePolicyIdentifer" type="SignaturePolicyIdentifierType"/>
			&lt;xsd:element name="SignatureProductionPlace" type="SignatureProductionPlaceType" minOccurs="0"/>
			&lt;xsd:element name="SignerRole" type="SignerRoleType" minOccurs="0"/>
		&lt;/xsd:sequence>
	&lt;/xsd:complexType></pre>
 * 
 * @author Psycho_core (nqkoi_ot_bg@yahoo.com)
 * @author mwnnj (krassen.deltchev@rub.de)
 **/



public class SignedSignatureProperties extends ObjectContainer implements SignedPropertiesContent{
	
	/** ds:SignedSignatureProperties.SignerRole element */
	   private SignerRole _signerRole = null;
	    /** ds:SignedSignatureProperties.SigningTime element */
	   private SigningTime  _signingTime = null;
	    /** ds:SignedSignatureProperties.SigningCertificate element */
	   private SigningCertificate _signingCertificate = null;	   
	    /** ds:SignedSignatureProperties.SignaturePolicyIdentifier element */
	   private SignaturePolicyIdentifier _signaturePolicyIdentifier = null;
	    /** ds:SignedSignatureProperties.SignatureProductionPlace element */
	   private SignatureProductionPlace _signatureProductionPlace = null;
	
	/**
	    * Constructor SignedSignatureProperties
	    * @param doc
	    */
	public SignedSignatureProperties(Document doc) {
		super(doc);	
		XMLUtils.addReturnToElement(this._constructionElement);
	}
	
		
	
	/**
	    * Constructor SignedSignatureProperties
	    *
	    * @param element
	    * @param BaseURI
	    * @throws XMLSecurityException
	    */
	   public SignedSignatureProperties(Element element, String BaseURI)
	           throws XMLSecurityException {
	      super(element, BaseURI);
	   }

	   /**
	    * Constructor SignedSignatureProperties
	    *
	    * @param doc
	    * @param signingtime SigningTime  
	    * @param signingcertificate SigningCertificate
	    * @param signaturepolicyidentifier SignaturePolicyIdentifier
	    * @param signatureproductionplace SignatureProductionPlace      
	    * @param signerrole SignerRole
	    */
	   public SignedSignatureProperties(Document doc, SigningTime signingtime, SigningCertificate signingcertificate,
			   SignaturePolicyIdentifier signaturepolicyidentifier, SignatureProductionPlace signatureproductionplace,SignerRole signerrole) {

	      super(doc);

	      if(signingtime == null || signingcertificate == null || signaturepolicyidentifier == null)
	    	  throw new IllegalArgumentException("Wrong Element Value!");
	      
         this._constructionElement.appendChild(signingtime.getElement());
	     XMLUtils.addReturnToElement(this._constructionElement);
	     	      
         this._constructionElement.appendChild(signingcertificate.getElement());
         XMLUtils.addReturnToElement(this._constructionElement);
	      
         this._constructionElement.appendChild(signaturepolicyidentifier.getElement());
         XMLUtils.addReturnToElement(this._constructionElement);

         if (signatureproductionplace != null) {
	         this._constructionElement.appendChild(signatureproductionplace.getElement());
	         XMLUtils.addReturnToElement(this._constructionElement);
	      }
         if (signerrole != null) {
		     this._constructionElement.appendChild(signerrole.getElement());
		     XMLUtils.addReturnToElement(this._constructionElement);
		  }
	     
	   }

	   /**
	    * Method getSigningTime
	    *
	    *
	    * @throws XMLSecurityException
	    * @return the signingtime
	    */

	   public SigningTime getSigningTime() {

		      // check to see if we are signing and if we have to create a SigningTime elment
		      if ((this._state == MODE_SIGN) && (this._signingTime == null)) {

		         // create the SigningTime
		         this._signingTime = new SigningTime(this._doc);

		         // get the Element 
		         Element signingTimeElement = this._signingTime.getElement();
		         Element firstObject=null;
		         Node sibling= this._constructionElement.getFirstChild();
		         firstObject = XMLUtils.selectDsNode(sibling,Constants._TAG_SIGNINGCERTIFICATE,0);
			   	     
		            if (firstObject != null) {

		            	// add it before the SigningCertificate Element 
		               this._constructionElement.insertBefore(signingTimeElement,
		                                                      firstObject);
		               this._constructionElement
		                  .insertBefore(this._doc.createTextNode("\n"), firstObject);
		            } else {

		            	// add it as the last element to the SignedSignatureProperties
		               this._constructionElement.appendChild(signingTimeElement);
		               XMLUtils.addReturnToElement(this._constructionElement);
		            }         
		      }

		      return this._signingTime;
		   }
	
	   
	   
	   
	   /**
	    * Method getSigningCertificate
	    * @return the SigningCertificate
	    */
	   
	   public SigningCertificate getSigningCertificate() {

		      // check to see if we are signing and if we have to create a SigningCertificate elment
		      if ((this._state == MODE_SIGN) && (this._signingCertificate == null)) {

		         // create the SigningCertificate
		         this._signingCertificate = new SigningCertificate(this._doc);

		         // get the Element 
		         Element signingCertificateElement = this._signingCertificate.getElement();
		         Element firstObject=null;
		         Node sibling= this._constructionElement.getFirstChild();
		         firstObject = XMLUtils.selectDsNode(sibling,Constants._TAG_SIGNATUREPOLICYIDENTIFIER,0);
			   	     
		            if (firstObject != null) {

		            	// add it before the SignaturePolicyIdentifier Element 
		               this._constructionElement.insertBefore(signingCertificateElement,
		                                                      firstObject);
		               this._constructionElement
		                  .insertBefore(this._doc.createTextNode("\n"), firstObject);
		            } else {

		            // add it as the last element to the SignedSignatureProperties
		               
		               this._constructionElement.appendChild(signingCertificateElement);
		               XMLUtils.addReturnToElement(this._constructionElement);
		            }         
		      }

		      return this._signingCertificate;
		   }
	
	   

	   /**
	    * Method getSignaturePolicyIdentifier
	    * @return the SignaturePolicyIdentifier
	    */
	   
	   public SignaturePolicyIdentifier getSignaturePolicyIdentifier() {

		      // check to see if we are signing and if we have to create a SignaturePolicyIdentifier elment
		      if ((this._state == MODE_SIGN) && (this._signaturePolicyIdentifier == null)) {

		         // create the SignaturePolicyIdentifier
		         this._signaturePolicyIdentifier = new SignaturePolicyIdentifier(this._doc);

		         // get the Element 
		         Element signaturePolicyIdentifierElement = this._signaturePolicyIdentifier.getElement();
		         Element firstObject=null;
		         Node sibling= this._constructionElement.getFirstChild();
		         firstObject = XMLUtils.selectDsNode(sibling,Constants._TAG_SIGNATUREPRODUCTIONPLACE,0);
			   	     
		            if (firstObject != null) {

		            	// add it before the SignatureProductionPlace Element 
		               this._constructionElement.insertBefore(signaturePolicyIdentifierElement,
		                                                      firstObject);
		               this._constructionElement
		                  .insertBefore(this._doc.createTextNode("\n"), firstObject);
		            } else {

		               // add it as the last element to the SignedSignatureProperties
		               this._constructionElement.appendChild(signaturePolicyIdentifierElement);
		               XMLUtils.addReturnToElement(this._constructionElement);
		            }         
		      }

		      return this._signaturePolicyIdentifier;
		   }
	
	   
	   /**
	    * Method getSignatureProductionPlace
	    * @return the SignatureProductionPlace
	    */
	   
	   public SignatureProductionPlace getSignatureProductionPlace() {

		      // check to see if we are signing and if we have to create a signerrole elment
		      if ((this._state == MODE_SIGN) && (this._signatureProductionPlace == null)) {

		         // create the SignerRole
		         this._signatureProductionPlace = new SignatureProductionPlace(this._doc);

		         // get the Element 
		         Element signatureProductionPlaceElement = this._signatureProductionPlace.getElement();
		         Element firstObject=null;
		         Node sibling= this._constructionElement.getFirstChild();
		         firstObject = XMLUtils.selectDsNode(sibling,Constants._TAG_SIGNERROLE,0);
			   	     
		            if (firstObject != null) {

		               // add it before the SignerRole Element  
		               this._constructionElement.insertBefore(signatureProductionPlaceElement,
		                                                      firstObject);
		               this._constructionElement
		                  .insertBefore(this._doc.createTextNode("\n"), firstObject);
		            } else {

		               // add it as the last element to the SignedSignatureProperties
		               this._constructionElement.appendChild(signatureProductionPlaceElement);
		               XMLUtils.addReturnToElement(this._constructionElement);
		            }         
		      }

		      return this._signatureProductionPlace;
		   }
	
	   /**
	    * Method getSignerRole
	    * @return the signerrole
	    */
	   
	   public SignerRole getSignerRole() {

			    return this._signerRole;
			 
		   }
	   
		
	
	   /** @inheritDoc */
	   public String getBaseLocalName() {
	      return Constants._TAG_SIGNEDSIGNATUREPROPERTIES;
	   }
	
	
	   /** @inheritDoc */
	   public String getBaseNamespace() {
	      return "http://uri.etsi.org/01903/v1.1.1#";
	   }
	
	
}

  