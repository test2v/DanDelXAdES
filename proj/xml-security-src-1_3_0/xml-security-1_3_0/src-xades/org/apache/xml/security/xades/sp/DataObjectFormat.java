package org.apache.xml.security.xades.sp;




import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.XMLSignatureException;
import org.apache.xml.security.signature.ObjectContainer;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


import org.apache.xml.security.xades.sp.ObjectIdentifier;
import org.apache.xml.security.xades.Constants;

/**
 * <pre>&lt;xsd:element name="DataObjectFormat" type="DataObjectFormatType"/>
	&lt;xsd:complexType name="DataObjectFormatType">
  		&lt;xsd:sequence>
    		&lt;xsd:element name="Description" type="xsd:string" minOccurs="0"/>
    		&lt;xsd:element name="ObjectIdentifier" type="ObjectIdentifierType" minOccurs="0"/>
    		&lt;xsd:element name="MimeType" type="xsd:string" minOccurs="0"/>
    		&lt;xsd:element name="Encoding" type="xsd:anyURI" minOccurs="0"/>
  		&lt;/xsd:sequence>
  	&lt;xsd:attribute name="ObjectReference" type="xsd:anyURI" use="required"/>
	&lt;/xsd:complexType></pre>
 * 
 * @author Psycho_core (nqkoi_ot_bg@yahoo.com)
 * @author mwnnj (krassen.deltchev@rub.de)
 **/



public class DataObjectFormat extends ObjectContainer implements  SignedDataObjectPropertiesContent{
	
 	/** ds:DataObjectFormat.ObjectIdentifier element */
	   private ObjectIdentifier _objectIdentifier = null;	 
	
	/**
	    * Constructor DataObjectFormat
	    * @param doc
	    */
	public DataObjectFormat(Document doc) {
		super(doc);	
		XMLUtils.addReturnToElement(this._constructionElement);
	}
		   
	   /**
	    * Constructor DataObjectFormat
	    *
	    * @param element
	    * @param BaseURI
	    */
	
	   public DataObjectFormat(Element element, String BaseURI) throws  XMLSignatureException, XMLSecurityException {

			super(element, BaseURI);

//			<element name="Description" minOccurs="0"/>
		      Element Description = XMLUtils.selectDsNode(this._constructionElement.getFirstChild(),
		                                  Constants._TAG_DESCRIPTION,0);

		      // check to see if it is there
		      if (Description == null) {
		         Object exArgs[] = { Constants._TAG_DESCRIPTION,
		                             Constants._TAG_DATAOBJECTFORMAT };

		         throw new XMLSignatureException("xml.WrongContent", exArgs);
		      }
//				<element name="ObjectIdentifier" minOccurs="0"/>
		      Element objectIdentifier = XMLUtils.selectDsNode(this._constructionElement.getFirstChild(),
		                                  Constants._TAG_OBJECTIDENTIFIER,0);

		      // check to see if it is there
		      if (objectIdentifier == null) {
		         Object exArgs[] = { Constants._TAG_OBJECTIDENTIFIER,
		                             Constants._TAG_DATAOBJECTFORMAT };

		         throw new XMLSignatureException("xml.WrongContent", exArgs);
		      }
		      
		      // create a ObjectIdentifier object from that element
		      this._objectIdentifier = new ObjectIdentifier(objectIdentifier, BaseURI);
		      
		      
//				<element name="MimeType" minOccurs="0"/>
		      Element MimeType = XMLUtils.selectDsNode(this._constructionElement.getFirstChild(),
		                                  Constants._TAG_MIMETYPE,0);

		      // check to see if it is there
		      if (MimeType == null) {
		         Object exArgs[] = { Constants._TAG_MIMETYPE,
		                             Constants._TAG_DATAOBJECTFORMAT };

		         throw new XMLSignatureException("xml.WrongContent", exArgs);
		      }
//				<element name="Encoding" minOccurs="0"/>
		      Element Encoding = XMLUtils.selectDsNode(this._constructionElement.getFirstChild(),
		                                  Constants._TAG_ENCODING,0);

		      // check to see if it is there
		      if (Encoding == null) {
		         Object exArgs[] = { Constants._TAG_ENCODING,
		                             Constants._TAG_DATAOBJECTFORMAT };

		         throw new XMLSignatureException("xml.WrongContent", exArgs);
		      }

	      
	   }

	   
	   /**
	    * Method setDescription
	    *
	    * @param Description
	    */
	   public void setDescription (String Description) {

	      if ((this._state == MODE_SIGN)&& (Description != null)) {
	    	  
	    	  this.addStringElement(Description, Constants._TAG_DESCRIPTION);
	    	  XMLUtils.addReturnToElement(this._constructionElement);
	      }
	   }
	   
	   /**
	    * Method setObjectIdentifier
	    *
	    * @param objectIdentifier
	    */
	   public void setObjectIdentifier (ObjectIdentifier objectIdentifier) {

	      if ((this._state == MODE_SIGN)&& (objectIdentifier != null)) {
	         this._constructionElement.appendChild(objectIdentifier.getElement());
	         XMLUtils.addReturnToElement(this._constructionElement);
	      }
	   }
	   
	   /**
	    * Method setMimeType
	    *
	    * @param MimeType
	    */
	   public void setMimeType (String MimeType) {

	      if ((this._state == MODE_SIGN)&& (MimeType != null)) {
	    	  this.addStringElement(MimeType,Constants._TAG_MIMETYPE);
	         XMLUtils.addReturnToElement(this._constructionElement);
	      }
	   }
	   
	   /**
	    * Method setEncoding
	    *
	    * @param Encoding
	    */
	   public void setEncoding (String Encoding) {

	      if ((this._state == MODE_SIGN)&& (Encoding != null)) {
	    	  this.addStringElement(Encoding,Constants._TAG_ENCODING);
	         XMLUtils.addReturnToElement(this._constructionElement);
	      }
	   }
		
	/**
	    * Sets the <code>ObjectReference</code> attribute
	    *
	    * @param ObjectReference (anyURI)
	    * @throws IllegalArgumentException **** because of "use=required" ****
	    */
	   public void setObjectReference(String ObjectReference) {
		   if (ObjectReference == null) {
						throw new IllegalArgumentException("ObjectReference Attibute is required!");
					}
		   else if ((this._state == MODE_SIGN) && (ObjectReference != null)) {
		         this._constructionElement.setAttributeNS(null, Constants._ATT_OBJECTREFERENCE, ObjectReference );
		   		}
	      }
	  
	   

	  
	   /**
	    * Method getDescription
	    *
	    *
	    * @return the Description Value
	    */
	   public String getDescription() {

	      String Description =
	         this.getTextFromChildElement(Constants._TAG_DESCRIPTION,Constants.LocalSpecNS);
	      if (Description != null) {
	    	  return new String(Description);
	      }
	      return null;	      
	   }
	   
	   
	   /**
	    * Method getObjectIdentifier
	    * @return the ObjectIdentifierElement
	    */
	 
	   public ObjectIdentifier getObjectIdentifier() {
	      return this._objectIdentifier;
	   }
	   
	   /**
	    * Method getMimeType
	    *
	    *
	    * @return the MimeType Value
	    */
	   public String getMimeType() {

	      String MimeType =
	         this.getTextFromChildElement(Constants._TAG_MIMETYPE,Constants.SignatureSpecNS);
	      if (MimeType != null) {
	    	  return new String(MimeType);
	      }
	      return null;	      
	   }
	   
	   /**
	    * Method getEncoding
	    *
	    *
	    * @return the Encoding Value
	    */
	 
	   public String getEncoding() {

	      String Encoding =
	         this.getTextFromChildElement(Constants._TAG_ENCODING,Constants.LocalSpecNS);
	      if (Encoding != null) {
	    	  return new String(Encoding);
	      }
	      return null;	      
	   }
	   
	   /**
	    * Returns the <code>ObjectReference</code> attribute
	    *
	    * @return the <code>ObjectReference</code> attribute
	    */
	   public String getObjectReference() {
	      return this._constructionElement.getAttributeNS(null, Constants._ATT_OBJECTREFERENCE);
	   }	   
	   
	   

	   /** @inheritDoc */
	   public String getBaseLocalName() {
	      return Constants._TAG_DATAOBJECTFORMAT;
	   }
	   /** @inheritDoc */
	   public String getBaseNamespace() {
	      return "http://uri.etsi.org/01903/v1.1.1#";
	   }
	}
