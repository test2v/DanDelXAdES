package org.apache.xml.security.xades.sp;





import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.XMLSignatureException;
import org.apache.xml.security.signature.ObjectContainer;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.apache.xml.security.xades.Constants;

/*** 
 * @author Psycho_core (nqkoi_ot_bg@yahoo.com)
 * @author mwnnj (krassen.deltchev@rub.de)
 **/

public class DigestMethod extends ObjectContainer{
	
	/**
	    * Constructor DigestMethod
	    * @param doc
	    */
	
	public DigestMethod(Document doc){
		super(doc);
		XMLUtils.addReturnToElement(this._constructionElement);
	}
		

	
	   
	   /**
	    * Constructor DigestMethod
	    *
	    * @param element
	    * @param BaseURI
	    * @throws XMLSignatureException
	    * @throws XMLSecurityException
	    */
	   public DigestMethod(Element element, String BaseURI) throws  XMLSignatureException, XMLSecurityException {

	      super(element, BaseURI);

	  
//			<element name="Encoding" minOccurs="0"/>
	      Element Algorithm = XMLUtils.selectDsNode(this._constructionElement.getFirstChild(),
	                                  Constants._ATT_ALGORITHM,0);

	      // check to see if it is there
	      if (Algorithm == null) {
	         Object exArgs[] = { Constants._ATT_ALGORITHM,
	                             Constants._TAG_DIGESTMETHOD };

	         throw new XMLSignatureException("xml.WrongContent", exArgs);
	      }
	       }
	   

	   
		/**
	    * Sets the <code>Algorithm</code> attribute
	    *
	    * @param Algorithm (anyURI)
	    * @throws IllegalArgumentException **** because of "use=required" ****
	    */
	   public void setAlgorithm(String Algorithm) {
		   if (Algorithm == null) {
						throw new IllegalArgumentException("Algorithm Attibute is required!");
					}
		   else if ((this._state == MODE_SIGN) && (Algorithm != null)) {
		         this._constructionElement.setAttributeNS(null, Constants._ATT_ALGORITHM, Algorithm );
		   		}
	      }
	   
	   /**
	    * Returns the <code>Algorithm</code> attribute
	    *
	    * @return the <code>Algorithm</code> attribute
	    */
	   public String getAlgorithm() {
	      return this._constructionElement.getAttributeNS(null, Constants._ATT_ALGORITHM);
	   }

	   
	   

	   /** @inheritDoc */
	   public String getBaseLocalName() {
	      return Constants._TAG_DIGESTMETHOD;
	   }
	   
	   /** @inheritDoc */
	   public String getBaseNamespace() {
	      return "http://uri.etsi.org/01903/v1.1.1#";
	   }
	}
