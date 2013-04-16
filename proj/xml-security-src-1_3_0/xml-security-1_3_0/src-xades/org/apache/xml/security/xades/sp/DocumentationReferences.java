package org.apache.xml.security.xades.sp;



import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.ObjectContainer;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.apache.xml.security.xades.sp.ObjectIdentifierContent;
import org.apache.xml.security.xades.Constants;

/**<pre>&lt;xsd:element name="DocumentationReferences" type="DocumentationReferencesType" minOccurs="0"/></pre>

 * 
 * @author Psycho_core (nqkoi_ot_bg@yahoo.com)
 * @author mwnnj (krassen.deltchev@rub.de)
 **/

public class DocumentationReferences extends ObjectContainer implements  ObjectIdentifierContent{
	
	/**
	    * Constructor DocumentationReferences
	    *
	    * @param element
	    * @param BaseURI
	    * @throws XMLSecurityException
	    */
	   public DocumentationReferences(Element element, String BaseURI)
	           throws XMLSecurityException {
	      super(element, BaseURI);
	   }
	   
	   /**
	    * Constructor DocumentationReferences
	    *	
	    * @param doc
	    * @param DocumentationReference anyURI
	    */
	   public DocumentationReferences(Document doc, String DocumentationReference) {

	      super(doc);
	      
	      if (DocumentationReference != null) {
	    	  this.addText(DocumentationReference);
	          XMLUtils.addReturnToElement(this._constructionElement);
	       }
	      
	   }
	   
	   /**
	    * Method setDocumentationReference
	    *
	    * @param DocumentationReference
	    */
	   public void setDocumentationReference (String DocumentationReference) {

	      if ((this._state == MODE_SIGN)&& (DocumentationReference != null)) {
	    	  this.addStringElement(DocumentationReference,Constants._TAG_DOCUMENTATIONREFERENCE);
	         XMLUtils.addReturnToElement(this._constructionElement);
	      }
	   }
	   
	   
	   /**
	    * Method getDocumentationReferences
	    *
	    *
	    * @return the DocumentationReferences Value
	    */
	   public String getDocumentationReference() {

	      String DocumentationReference =
	         this.getTextFromChildElement(Constants._TAG_DOCUMENTATIONREFERENCE,Constants.SignatureSpecNS);
	      if (DocumentationReference != null) {
	    	  return new String(DocumentationReference);
	      }
	      return null;	      
	   }
	   
	   
	   /** @inheritDoc */
	   public String getBaseLocalName() {
	      return Constants._TAG_DOCUMENTATIONREFERENCES;
	   }
	}
