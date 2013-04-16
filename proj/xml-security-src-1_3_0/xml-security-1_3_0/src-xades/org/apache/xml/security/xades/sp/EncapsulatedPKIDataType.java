package org.apache.xml.security.xades.sp;



import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.ObjectContainer;
import org.apache.xml.security.utils.IdResolver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.apache.xml.security.xades.Constants;

/**<pre>&lt;xsd:complexType name="EncapsulatedPKIDataType">  
  	&lt;xsd:complexContent>
    	&lt;xsd:extension base="xsd:base64Binary">
      		&lt;xsd:attribute name="Id" type="xsd:ID" use="optional"/>
    	&lt;/xsd:extension>
  	&lt;/xsd:complexContent>
&lt;/xsd:complexType></pre> 
 * @author Psycho_core (nqkoi_ot_bg@yahoo.com)
 * @author mwnnj (krassen.deltchev@rub.de)
 **/

public class EncapsulatedPKIDataType extends ObjectContainer implements  CertifiedRolesListContent{
	
	/**
	    * Constructor EncapsulatedPKIDataType
	    * @param doc
	    */
	public EncapsulatedPKIDataType(Document doc) {

	      super(doc);
	}
	
	/**
	    * Constructor EncapsulatedPKIDataType
	    *
	    * @param element
	    * @param BaseURI
	    * @throws XMLSecurityException
	    */
	   public EncapsulatedPKIDataType(Element element, String BaseURI)
	           throws XMLSecurityException {
	      super(element, BaseURI);
	   }
	   
	   /**
	    * Constructor EncapsulatedPKIDataType
	    *
	    * @param doc	    
	    * 
	    * @param Id
	    * 
	    */
	   public EncapsulatedPKIDataType(Document doc,String Id ) {

	      super(doc);

	      if (Id != null) {
		         this._constructionElement.setAttributeNS(null, Constants._ATT_ID, Id); // the Id attribute is optional
		       
		      }
	      
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

	   
	   
	   

	   /** @inheritDoc */
	   public String getBaseLocalName() {
	      return Constants._TAG_ENCAPSULATEDTIMESTAMP;
	   }
	   /** @inheritDoc */
	   public String getBaseNamespace() {
	      return "http://uri.etsi.org/01903/v1.1.1#";
	   }
	}
