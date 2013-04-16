


package org.apache.xml.security.xades.sp;



import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.XMLSignatureException;
import org.apache.xml.security.signature.ObjectContainer;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.apache.xml.security.xades.*;

/**
 * <pre>&lt;xsd:element name="SignatureProductionPlace" type="SignatureProductionPlaceType"/>
	&lt;xsd:complexType name="SignatureProductionPlaceType">
		&lt;xsd:sequence>
			&lt;xsd:element name="City" type="xsd:string" minOccurs="0"/>
			&lt;xsd:element name="StateOrProvince" type="xsd:string" minOccurs="0"/>
			&lt;xsd:element name="PostalCode" type="xsd:string" minOccurs="0"/>
			&lt;xsd:element name="CountryName" type="xsd:string" minOccurs="0"/>
		&lt;/xsd:sequence>
&lt;/xsd:complexType></pre>
 * 
 * @author Psycho_core (nqkoi_ot_bg@yahoo.com)
 * @author mwnnj (krassen.deltchev@rub.de)
 **/

public class SignatureProductionPlace extends ObjectContainer implements  SignedSignaturePropertiesContent{
	
	
	/**
	    * Constructor SignatureProductionPlace
	    *
	    * @param doc
	    */
	   public SignatureProductionPlace(Document doc) {

	      super(doc);

	      XMLUtils.addReturnToElement(this._constructionElement);
	     	      
	   }
	
	/**
	    * Constructor SignatureProductionPlace
	    *
	    * @param element
	    * @param BaseURI
	    * @throws XMLSecurityException
	    * @throws XMLSignatureException
	    */
	   public SignatureProductionPlace(Element element, String BaseURI)
	           throws XMLSignatureException, XMLSecurityException {
	      super(element, BaseURI);
	  
//			<element name="City" minOccurs="0"/>
	      Element City = XMLUtils.selectDsNode(this._constructionElement.getFirstChild(),
	                                  Constants._TAG_CITY,0);

	      // check to see if it is there
	      if (City == null) {
	         Object exArgs[] = { Constants._TAG_CITY,
	                             Constants._TAG_SIGNATUREPRODUCTIONPLACE};

	         throw new XMLSignatureException("xml.WrongContent", exArgs);
	      }
	      
//			<element name="StateOrProvince" minOccurs="0"/>
	      Element StateOrProvince = XMLUtils.selectDsNode(this._constructionElement.getFirstChild(),
	                                  Constants._TAG_STATEORPROVINCE,0);

	      // check to see if it is there
	      if (StateOrProvince == null) {
	         Object exArgs[] = { Constants._TAG_STATEORPROVINCE,
	                             Constants._TAG_SIGNATUREPRODUCTIONPLACE };

	         throw new XMLSignatureException("xml.WrongContent", exArgs);
	      }
	      
//			<element name="PostalCode" minOccurs="0"/>
	      Element PostalCode = XMLUtils.selectDsNode(this._constructionElement.getFirstChild(),
	                                  Constants._TAG_POSTALCODE,0);

	      // check to see if it is there
	      if (PostalCode == null) {
	         Object exArgs[] = { Constants._TAG_POSTALCODE,
	                             Constants._TAG_SIGNATUREPRODUCTIONPLACE };

	         throw new XMLSignatureException("xml.WrongContent", exArgs);
	      }
//			<element name="CountryName" minOccurs="0"/>
	      Element CountryName = XMLUtils.selectDsNode(this._constructionElement.getFirstChild(),
	                                  Constants._TAG_COUNTRYNAME,0);

	      // check to see if it is there
	      if (CountryName == null) {
	         Object exArgs[] = { Constants._TAG_COUNTRYNAME,
	                             Constants._TAG_SIGNATUREPRODUCTIONPLACE };

	         throw new XMLSignatureException("xml.WrongContent", exArgs);
	      }
	   
	   }
	  
	   
	   /**
	    * Method setCity
	    *
	    * @param City
	    */
	   public void setCity (String City) {

	      if ((this._state == MODE_SIGN)&& (City != null)) {
	    	  //this.addText(City);
	    	  this.addStringElement(City, Constants._TAG_CITY);
	         XMLUtils.addReturnToElement(this._constructionElement);
	      }
	   }
	   
	   /**
	    * Method setStateOrProvince
	    *
	    * @param StateOrProvince
	    */
	   public void setStateOrProvince (String StateOrProvince) {

	      if ((this._state == MODE_SIGN)&& (StateOrProvince != null)) {
	    	  this.addStringElement(StateOrProvince,Constants._TAG_STATEORPROVINCE);
	         XMLUtils.addReturnToElement(this._constructionElement);
	      }
	   }
	   
	   /**
	    * Method setPostalCode
	    *
	    * @param PostalCode
	    */
	   public void setPostalCode (String PostalCode) {

	      if ((this._state == MODE_SIGN)&& (PostalCode != null)) {
	    	  this.addStringElement(PostalCode, Constants._TAG_POSTALCODE);
	         XMLUtils.addReturnToElement(this._constructionElement);
	      }
	   }
	   
	   /**
	    * Method setCountryName
	    *
	    * @param CountryName
	    */
	   public void setCountryName (String CountryName) {

	      if ((this._state == MODE_SIGN)&& (CountryName != null)) {
	    	  this.addStringElement(CountryName,Constants._TAG_COUNTRYNAME);
	         XMLUtils.addReturnToElement(this._constructionElement);
	      }
	   }
	   
	   
	   /**
	    * Method getCity
	    *
	    *
	    * @return the City Value
	    */
	   public String getCity() {

	      String City =
	         this.getTextFromChildElement(Constants._TAG_CITY,Constants.SignatureSpecNS);
	      if (City != null) {
	    	  return new String(City);
	      }
	      return null;	      
	   }
	   
	   /**
	    * Method getStateOrProvince
	    *
	    *
	    * @return the StateOrProvince Value
	    */
	   public String getStateOrProvince() {

	      String StateOrProvince =
	         this.getTextFromChildElement(Constants._TAG_STATEORPROVINCE,Constants.SignatureSpecNS);
	      if (StateOrProvince != null) {
	    	  return new String(StateOrProvince);
	      }
	      return null;	      
	   }
	   
	   /**
	    * Method getPostalCode
	    *
	    *
	    * @return the PostalCode Value
	    */
	   public String getPostalCode() {

	      String PostalCode =
	         this.getTextFromChildElement(Constants._TAG_POSTALCODE,Constants.SignatureSpecNS);
	      if (PostalCode != null) {
	    	  return new String(PostalCode);
	      }
	      return null;	      
	   }
	   
	   /**
	    * Method getCountryName
	    *
	    *
	    * @return the CountryName Value
	    */
	   public String getCountryName() {

	      String CountryName =
	         this.getTextFromChildElement(Constants._TAG_COUNTRYNAME,Constants.SignatureSpecNS);
	      if (CountryName != null) {
	    	  return new String(CountryName);
	      }
	      return null;	      
	   }

	   /** @inheritDoc */
	   public String getBaseLocalName() {
	      return Constants._TAG_SIGNATUREPRODUCTIONPLACE;
	   }
	   /** @inheritDoc */
	   public String getBaseNamespace() {
	      return "http://uri.etsi.org/01903/v1.1.1#";
	   }
	
	}
