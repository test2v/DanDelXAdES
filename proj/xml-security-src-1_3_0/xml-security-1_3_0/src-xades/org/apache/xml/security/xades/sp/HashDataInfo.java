package org.apache.xml.security.xades.sp;


import org.apache.xml.security.signature.XMLSignatureException;
import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.ObjectContainer;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.utils.Constants;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Attr;

/**
 * <pre>&lt;xsd:complexType name="HashDataInfoType">
  	&lt;xsd:sequence>
    	&lt;xsd:element name="Transforms" type="ds:TransformsType" minOccurs="0"/>
  	&lt;/xsd:sequence>
&lt;xsd:attribute name="uri" type="xsd:anyURI" use="required"/>
&lt;/xsd:complexType></pre>
 
 * @author Psycho_core (nqkoi_ot_bg@yahoo.com)
 * @author mwnnj (krassen.deltchev@rub.de)
 **/

public class HashDataInfo extends ObjectContainer {
	
	
	/**	hashdatainfo.hashdatainfotype **/
	   private HashDataInfo _hashdatainfo = null;
	 
	
		/**
	    * Constructor QualifyingProperties
	    * @param doc
	    */
	
	public HashDataInfo(Document doc) {

	      super(doc);
	}
	   
	  
	   
	   

	   /**
	    * Constructor HashDataInfo
	    *
	    * @param doc
	    * @param BaseURI
	    * @throws XMLSecurityException
	    */
	   public HashDataInfo(
	           Document doc, String BaseURI)
	              throws XMLSecurityException {

	      super(doc);

	      XMLUtils.addReturnToElement(this._constructionElement);

	      this._baseURI = BaseURI;
	      this._hashdatainfo   = new HashDataInfo(this._doc);

	      this._constructionElement.appendChild(this._hashdatainfo.getElement());
	      XMLUtils.addReturnToElement(this._constructionElement);
	   }
	   
	   
	   /**
	    *  Creates a HashDataInfo in a Document
	    * @param doc
	    * @param Uri
	    * @param transforms
	    * @throws XMLSecurityException
	    */
	   public HashDataInfo(Document doc, Element transforms, String Uri)
	              throws XMLSecurityException {

	      super(doc);

	      XMLUtils.addReturnToElement(this._constructionElement);

	      
	      this._hashdatainfo = new HashDataInfo(this._doc, transforms, Uri);

	      this._constructionElement.appendChild(this._hashdatainfo.getElement());
	      XMLUtils.addReturnToElement(this._constructionElement);

	     
	   }
	   
	   /**
	    *
	    * @param element 
	    * @param BaseURI 
	    * @throws XMLSecurityException
	    * @throws XMLSignatureException 
	    */
	   public HashDataInfo(Element element, String BaseURI)
	           throws XMLSignatureException, XMLSecurityException {

	      super(element, BaseURI);

	      // check out SignedInfo child
	      Element hashdatainfo = XMLUtils.selectDsNode(this._constructionElement.getFirstChild(),
	                                  Constants._TAG_HASHDATAINFO,0);

	      // check to see if it is there
	      if (hashdatainfo == null) {
	         Object exArgs[] = { Constants._TAG_HASHDATAINFO};

	         throw new XMLSignatureException("xml.WrongContent", exArgs);
	      }

	      // create a SignedInfo object from that element
	      this._hashdatainfo = new HashDataInfo(hashdatainfo, BaseURI);
	      
	   }
	   
	   /**
	    * Method setUriAttr
	    *
	    * @param UriAttr
	    */
	   public void  setUriAttr(String UriAttr) {
		   if (UriAttr == null) {
				throw new IllegalArgumentException("URI Attibute is required!");
			}
		   else if ((this._state == MODE_SIGN) && (UriAttr != null)) {
		   
			   this._constructionElement.setAttributeNS(null, Constants._ATT_URI, UriAttr);
	   			}
	   }  
	   
	   
	   
	   /**
	    * Method setTransforms
	    * 
	    * @param transforms
	    */
	 public void setTransforms(Transforms transforms) {

	      if (this._state == MODE_SIGN) {
	         
	    	  this._constructionElement.appendChild(transforms.getElement());
	    	 
	         XMLUtils.addReturnToElement(this._constructionElement);
	      }
	   }

	   /**
	    * Method getUriAttr
	    *
	    * @return the uri attribute
	    */
	   public Attr getUriAttr() {
		   
	      return this._constructionElement.getAttributeNodeNS(null, Constants._ATT_URI);
	   }
	
	  
	   /**
	    * Method getTransforms
	    *
	    *
	    * @throws XMLSecurityException
	    * @return the transforamitons
	    */
	   public Transforms getTransforms() throws XMLSecurityException {

	      try {
	       Element transforms =
	             XMLUtils.selectDsNode(this._constructionElement,                                                
	                                                Constants
	                                                   ._TAG_TRANSFORMS, 0);

	         if (transforms != null) {
	            return new Transforms(transforms, this._baseURI);
	         }

	         return null;
	      } catch (XMLSignatureException ex) {
	         throw new XMLSecurityException("empty", ex);
	      }
	   }
	   

	   /**
	    * Returns <code>HashDataInfo</code> object.
	    *
	    * @return <code>HashDataInfo</code> object.
	    */
	   public HashDataInfo getHashDataInfo() {
	      return this._hashdatainfo;
	   }

	  
	   

	   /** @inheritDoc */
	   public String getBaseLocalName() {
	      return Constants._TAG_HASHDATAINFO;
	   }
	   /** @inheritDoc */
	   public String getBaseNamespace() {
	      return "http://uri.etsi.org/01903/v1.1.1#";
	   }
	   
	}



