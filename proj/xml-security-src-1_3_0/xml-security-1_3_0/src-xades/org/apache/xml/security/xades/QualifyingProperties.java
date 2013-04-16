

package org.apache.xml.security.xades;




import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.XMLSignatureException;
import org.apache.xml.security.signature.ObjectContainer;
import org.apache.xml.security.utils.IdResolver;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.apache.xml.security.xades.up.UnsignedProperties;
import org.apache.xml.security.xades.sp.SignedProperties;
import org.apache.xml.security.xades.Constants;



/** 
 * <pre>&lt;xsd:element name="QualifyingProperties" type="QualifyingPropertiesType"/>
 	&lt;xsd:complexType name="QualifyingPropertiesType">
 		&lt;xsd:sequence>
 			&lt;xsd:element name="SignedProperties" type="SignedPropertiesType" minOccurs="0"/>
 			&lt;xsd:element name="UnsignedProperties" type="UnsignedPropertiesType" minOccurs="0"/>
 		&lt;/xsd:sequence>
 	&lt;xsd:attribute name="Target" type="xsd:anyURI" use="required"/>
	&lt;xsd:attribute name="Id" type="xsd:ID" use="optional"/>
 	&lt;/xsd:complexType></pre>
 
 * 
 * @author Psycho_core (nqkoi_ot_bg@yahoo.com)
 * @author mwnnj (krassen.deltchev@rub.de)
 **/


public class QualifyingProperties extends ObjectContainer{

	
		/** ds:QualifyingProperties.ds:SignedProperties element */
		   private SignedProperties _signedProperties = null;
		   
	   	/** ds:QualifyingProperties.ds:UnsignedProperties element */
	   private UnsignedProperties _unsignedProperties = null;	   

	   
	   	/**
	    * Constructor QualifyingProperties
	    * @param doc
	    */
	
	public QualifyingProperties(Document doc) {
		super(doc);
		XMLUtils.addReturnToElement(this._constructionElement);
	}
	
	
	
	/**
	    * Constructor QualifiyngProperties
	    *
	    * @param Element
	    * @param doc
	    * @throws XMLSecurityException
	    */

	public QualifyingProperties(Document doc, Element SignedProperties, Element UnsignedProperties)
			throws XMLSecurityException {
		
		super(doc);
		
		this._signedProperties = new SignedProperties(SignedProperties, null);

	      this._constructionElement
	         .appendChild(this._signedProperties.getElement());
	      XMLUtils.addReturnToElement(this._constructionElement);
		
		this._unsignedProperties = new UnsignedProperties(UnsignedProperties, null);

	      this._constructionElement
	         .appendChild(this._unsignedProperties.getElement());
	      XMLUtils.addReturnToElement(this._constructionElement);

	     }
	
	/**
	    * Constructor QualifiyngProperties
	    *
	    * @param sp
	    * @param usp
	    * @param doc
	    * @throws XMLSecurityException
	    */

	public QualifyingProperties(Document doc, SignedProperties sp,UnsignedProperties usp)
					throws XMLSecurityException {
		
		super(doc);
		
		XMLUtils.addReturnToElement(this._constructionElement);

	     
	      this._signedProperties = new SignedProperties(this._doc);
	      this._constructionElement.appendChild(this._signedProperties.getElement());
	      this._unsignedProperties = new UnsignedProperties(this._doc);
	      this._constructionElement.appendChild(this._unsignedProperties.getElement());
	      XMLUtils.addReturnToElement(this._constructionElement);
	   
	      }
	
	
	/**
	    * Constructor QualifiyngProperties
	    *
	    * @param Element
	    * @param BaseURI
	    * @throws XMLSecurityException
	    * @throws XMLSignatureException
	    */

	public QualifyingProperties(Element element, String BaseURI)
    		throws XMLSignatureException, XMLSecurityException {

		super(element, BaseURI);
		
//<element name="SignedProperties" minOccurs="0"/>
		
	      Element sp = XMLUtils.selectDsNode(this._constructionElement.getFirstChild(),
	                                  Constants._TAG_SIGNEDPROPERTIES,0);

//check to see if it is there
	      if (sp == null) {
	         Object exArgs[] = { Constants._TAG_SIGNEDPROPERTIES,
	                             Constants._TAG_QUALIFYINGPROPERTIES };

	         throw new XMLSignatureException("xml.WrongContent", exArgs);
	      }

//create a SignedProperties object from that element
	      this._signedProperties = new SignedProperties(sp, BaseURI);
	      
//<element name="UnsignedProperties" minOccurs="0"/>
	      Element usp = XMLUtils.selectDsNode(this._constructionElement.getFirstChild(),
                         Constants._TAG_UNSIGNEDPROPERTIES,0);
		
		
//check to see if it is there
	      if (usp != null) {
	    	  Object exArgs[] = { Constants._TAG_UNSIGNEDPROPERTIES,
	    			  			  Constants._TAG_QUALIFYINGPROPERTIES };

	         throw new XMLSignatureException("xml.WrongContent", exArgs);
	      }
	      
//create a SignedProperties object from that element
	         this._unsignedProperties = new UnsignedProperties(usp, BaseURI);

		}
	
	
	
	
		/**
	    * Sets the <code>Id</code> Element
	    *
	    * @param Id 
	    */
	   public void setId(String Id) {

	      if ((this._state == MODE_SIGN) && (Id != null)) {
	         this._constructionElement.setAttributeNS(null, Constants._ATT_ID, Id);
	         IdResolver.registerElementById(this._constructionElement, Id);
	      }
	   }
	
	
	   /**
	    * Returns the <code>Id</code> attribute
	    *
	    * @return the <code>Id</code> attribute
	    */
	   public String getId() {
	      return this._constructionElement.getAttributeNS(null, Constants._ATT_ID);
	   }
	  	
		/**
	    * Sets the <code>Target</code> attribute
	    *
	    * @param Target (anyURI)
	    * @throws IllegalArgumentException **** because of "use=required" ****
	    */
	   public void setTarget(String Target) {
		   if (Target == null) {
						throw new IllegalArgumentException("Target Attibute is required!");
					}
		   else if ((this._state == MODE_SIGN) && (Target != null)) {
		         this._constructionElement.setAttributeNS(null, Constants._ATT_TARGET, Target);
		   		}
	      }
	  

	   /**
	    * Returns the <code>Target</code> attribute
	    *
	    * @return the <code>Target</code> attribute
	    */
	   public String getTarget() {
	      return this._constructionElement.getAttributeNS(null, Constants._ATT_TARGET);
	   }
	
	  
	   
	   /**
	    * Method setSignedProperties
	    *
	    * @param sp
	    */
	   public void setSignedProperties (SignedProperties sp) {

	      if ((this._state == MODE_SIGN)&& (sp != null)) {
	         this._constructionElement.appendChild(sp.getElement());
	         XMLUtils.addReturnToElement(this._constructionElement);
	      }
	   }
	   
	   /**
	    * 
	    * Method setUnsignedProperties
	    *
	    * @param usp
	    */
	   public void setUnsignedProperties(UnsignedProperties usp) {

	      if ((this._state == MODE_SIGN)&& (usp != null)) {
	         this._constructionElement.appendChild(usp.getElement());
	         XMLUtils.addReturnToElement(this._constructionElement);
	      }
	   }
	   
	 

		   /**
		    * Returns <code>SignedProperties</code> object.
		    *
		    * @return <code>SignedProperties</code> object.
		    */
		   public SignedProperties getSignedProperties() {
		      return this._signedProperties;
		   }
		   
		   /**
		    * Returns <code>UnsignedProperties</code> object.
		    *
		    * @return <code>UnsignedProperties</code> object.
		    */
		   public UnsignedProperties getUnsignedProperties() {
		      return this._unsignedProperties;
		   }



	   
	   /** @inheritDoc */
	   public String getBaseLocalName() {
	      return Constants._TAG_QUALIFYINGPROPERTIES;
	   }
	   
	   /** @inheritDoc */
	   public String getBaseNamespace() {
	      return "http://uri.etsi.org/01903/v1.1.1#";
	   }
}
