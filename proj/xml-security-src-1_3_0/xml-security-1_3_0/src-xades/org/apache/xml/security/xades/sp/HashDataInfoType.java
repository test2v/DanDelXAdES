package org.apache.xml.security.xades.sp;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.xml.security.c14n.CanonicalizationException;
import org.apache.xml.security.c14n.Canonicalizer;
import org.apache.xml.security.c14n.InvalidCanonicalizerException;
import org.apache.xml.security.exceptions.XMLSecurityException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.apache.xml.security.signature.ObjectContainer;
import org.apache.xml.security.signature.XMLSignatureException;
import org.apache.xml.security.signature.XMLSignatureInput;
import org.apache.xml.security.transforms.InvalidTransformException;
import org.apache.xml.security.transforms.Transform;
import org.apache.xml.security.transforms.TransformationException;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.utils.Constants;
import org.apache.xml.security.utils.XMLUtils;

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


public class HashDataInfoType extends ObjectContainer {
	
	

	   
	   /** {@link org.apache.commons.logging} logging facility */
	    static org.apache.commons.logging.Log log = 
	        org.apache.commons.logging.LogFactory.getLog(Transforms.class.getName());
	   //J-
	   /** Canonicalization - Required Canonical XML (omits comments) */
	   public static final String TRANSFORM_C14N_OMIT_COMMENTS = Canonicalizer.ALGO_ID_C14N_OMIT_COMMENTS;
	   /** Canonicalization - Recommended Canonical XML with Comments */
	   public static final String TRANSFORM_C14N_WITH_COMMENTS = Canonicalizer.ALGO_ID_C14N_WITH_COMMENTS;
	   /** Canonicalization - Required Exclusive Canonicalization (omits comments) */
	   public static final String TRANSFORM_C14N_EXCL_OMIT_COMMENTS = Canonicalizer.ALGO_ID_C14N_EXCL_OMIT_COMMENTS;
	   /** Canonicalization - Recommended Exclusive Canonicalization with Comments */
	   public static final String TRANSFORM_C14N_EXCL_WITH_COMMENTS = Canonicalizer.ALGO_ID_C14N_EXCL_WITH_COMMENTS;
	   /** Transform - Optional XSLT */
	   public static final String TRANSFORM_XSLT = "http://www.w3.org/TR/1999/REC-xslt-19991116";
	   /** Transform - Required base64 decoding */
	   public static final String TRANSFORM_BASE64_DECODE = Constants.SignatureSpecNS + "base64";
	   /** Transform - Recommended XPath */
	   public static final String TRANSFORM_XPATH = "http://www.w3.org/TR/1999/REC-xpath-19991116";
	   /** Transform - Required Enveloped Signature */
	   public static final String TRANSFORM_ENVELOPED_SIGNATURE = Constants.SignatureSpecNS + "enveloped-signature";
	   /** Transform - XPointer */
	   public static final String TRANSFORM_XPOINTER = "http://www.w3.org/TR/2001/WD-xptr-20010108";
	   /** Transform - XPath Filter v2.0 */
	   public static final String TRANSFORM_XPATH2FILTER04 = "http://www.w3.org/2002/04/xmldsig-filter2";
	   /** Transform - XPath Filter */
	   public static final String TRANSFORM_XPATH2FILTER = "http://www.w3.org/2002/06/xmldsig-filter2";
	   /** Transform - XPath Filter  CHGP private*/
	   public static final String TRANSFORM_XPATHFILTERCHGP = "http://www.nue.et-inf.uni-siegen.de/~geuer-pollmann/#xpathFilter";

	   /** MAC - Required HMAC-SHA1 */
	   public static final String ALGO_ID_MAC_HMAC_SHA1 = Constants.SignatureSpecNS + "hmac-sha1";

	   /** Signature - Required DSAwithSHA1 (DSS) */
	   public static final String ALGO_ID_SIGNATURE_DSA = Constants.SignatureSpecNS + "dsa-sha1";

	   /** Signature - Recommended RSAwithSHA1 */
	   public static final String ALGO_ID_SIGNATURE_RSA = Constants.SignatureSpecNS + "rsa-sha1";
	   /** Signature - Recommended RSAwithSHA1 */
	   public static final String ALGO_ID_SIGNATURE_RSA_SHA1 = Constants.SignatureSpecNS + "rsa-sha1";
	   /** Signature - NOT Recommended RSAwithMD5 */
	   public static final String ALGO_ID_SIGNATURE_NOT_RECOMMENDED_RSA_MD5 = Constants.MoreAlgorithmsSpecNS + "rsa-md5";
	   /** Signature - Optional RSAwithRIPEMD160 */
	   public static final String ALGO_ID_SIGNATURE_RSA_RIPEMD160 = Constants.MoreAlgorithmsSpecNS + "rsa-ripemd160";
	   /** Signature - Optional RSAwithSHA256 */
	   public static final String ALGO_ID_SIGNATURE_RSA_SHA256 = Constants.MoreAlgorithmsSpecNS + "rsa-sha256";
	   /** Signature - Optional RSAwithSHA384 */
	   public static final String ALGO_ID_SIGNATURE_RSA_SHA384 = Constants.MoreAlgorithmsSpecNS + "rsa-sha384";
	   /** Signature - Optional RSAwithSHA512 */
	   public static final String ALGO_ID_SIGNATURE_RSA_SHA512 = Constants.MoreAlgorithmsSpecNS + "rsa-sha512";

	   /** HMAC - NOT Recommended HMAC-MD5 */
	   public static final String ALGO_ID_MAC_HMAC_NOT_RECOMMENDED_MD5 = Constants.MoreAlgorithmsSpecNS + "hmac-md5";
	   /** HMAC - Optional HMAC-RIPEMD160 */
	   public static final String ALGO_ID_MAC_HMAC_RIPEMD160 = Constants.MoreAlgorithmsSpecNS + "hmac-ripemd160";
	   /** HMAC - Optional HMAC-SHA256 */
	   public static final String ALGO_ID_MAC_HMAC_SHA256 = Constants.MoreAlgorithmsSpecNS + "hmac-sha256";
	   /** HMAC - Optional HMAC-SHA284 */
	   public static final String ALGO_ID_MAC_HMAC_SHA384 = Constants.MoreAlgorithmsSpecNS + "hmac-sha384";
	   /** HMAC - Optional HMAC-SHA512 */
	   public static final String ALGO_ID_MAC_HMAC_SHA512 = Constants.MoreAlgorithmsSpecNS + "hmac-sha512";
	   
	   //J+
	   Element []transforms;
	   
	   //hashdatainfo.hashdatainfotype
	   private HashDataInfo _hashdatainfo = null;
	 
	   

	   
		/**
	    * Constructor HashDataInfoType
	    * @param doc
	    */   
	   
	public HashDataInfoType(Document doc) {

	      super(doc);
	}
	   /**
	    * Constructor XMLSignature
	    *
	    * @param doc
	    * @param BaseURI
	    * @throws XMLSecurityException
	    */
	   public HashDataInfoType(
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
	    *  Creates a HashDataInfoType in a Document
	    * @param doc
	    * @param Uri
	    * @param transforms
	    * @throws XMLSecurityException
	    */
	   public HashDataInfoType(Document doc, Element transforms, String Uri)
	              throws XMLSecurityException {

	      super(doc);

	      XMLUtils.addReturnToElement(this._constructionElement);

	      
	      this._hashdatainfo = new HashDataInfo(this._doc, transforms, Uri);

	      this._constructionElement.appendChild(this._hashdatainfo.getElement());
	      XMLUtils.addReturnToElement(this._constructionElement);

	     
	   }
	   
	   /**
	    * @param element
	    * @param BaseURI URI to be prepended to all relative URIs
	    * @throws XMLSecurityException
	    * @throws XMLSignatureException 
	    */
	   public HashDataInfoType(Element element, String BaseURI)
	           throws XMLSignatureException, XMLSecurityException {

	      super(element, BaseURI);

	      // check out HashDataInfo child
	      Element hashdatainfo = XMLUtils.selectDsNode(this._constructionElement.getFirstChild(),
	                                  Constants._TAG_HASHDATAINFO,0);

	      // check to see if it is there
	      if (hashdatainfo == null) {
	         Object exArgs[] = { Constants._TAG_HASHDATAINFO};

	         throw new XMLSignatureException("xml.WrongContent", exArgs);
	      }

	      // create a HashDataInfo object from that element
	      this._hashdatainfo = new HashDataInfo(hashdatainfo, BaseURI);

	      int numberOfTransformElems = this.getLength();

	      if (numberOfTransformElems == 0) {

	         // At least one Transform element must be present. Bad.
	         Object exArgs[] = { Constants._TAG_TRANSFORM,
	                             Constants._TAG_TRANSFORMS };

	         throw new TransformationException("xml.WrongContent", exArgs);
	      }
	      
	   }
	   
	   /**
	    * Method seturiAttr
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
	    * Method setTransforms1
	    * 
	    * @param transforms1
	    */
	   public void setTransforms1 (Transforms1 transforms1) {

		   if (this._state == MODE_SIGN) {
		         this._constructionElement.appendChild(transforms1.getElement());
		         XMLUtils.addReturnToElement(this._constructionElement);
		      }
	      }
	   




	

	   /**
	    * Adds the <code>Transform</code> with the specified <code>Transform algorithm URI</code>
	    *
	    * @param transformURI the URI form of transform that indicates which transformation is applied to data
	    * @throws TransformationException
	    */
	   public void addTransform(String transformURI)
	           throws TransformationException {

	      try {
	         if (log.isDebugEnabled())
	         	log.debug("Transforms.addTransform(" + transformURI + ")");

	         Transform transform = Transform.getInstance(this._doc, transformURI);

	         this.addTransform(transform);
	      } catch (InvalidTransformException ex) {
	         throw new TransformationException("empty", ex);
	      }
	   }

	   /**
	    * Adds the <code>Transform</code> with the specified <code>Transform algorithm URI</code>
	    *
	    * @param transformURI the URI form of transform that indicates which transformation is applied to data
	    * @param contextElement
	    * @throws TransformationException
	    * @see Transform#getInstance(Document doc, String algorithmURI, Element childElement)
	    */
	   public void addTransform(String transformURI, Element contextElement)
	           throws TransformationException {

	      try {
	         if (log.isDebugEnabled())
	        	log.debug("Transforms.addTransform(" + transformURI + ")");

	         Transform transform = Transform.getInstance(this._doc, transformURI,
	                                                     contextElement);

	         this.addTransform(transform);
	      } catch (InvalidTransformException ex) {
	         throw new TransformationException("empty", ex);
	      }
	   }

	   /**
	    * Adds the <code>Transform</code> with the specified <code>Transform algorithm URI</code>
	    *
	    * @param transformURI the URI form of transform that indicates which transformation is applied to data
	    * @param contextNodes
	    * @throws TransformationException
	    * @see Transform#getInstance(Document doc, String algorithmURI, NodeList contextNodes)
	    */
	   public void addTransform(String transformURI, NodeList contextNodes)
	           throws TransformationException {

	      try {
	         Transform transform = Transform.getInstance(this._doc, transformURI,
	                                                     contextNodes);

	         this.addTransform(transform);
	      } catch (InvalidTransformException ex) {
	         throw new TransformationException("empty", ex);
	      }
	   }

	   /**
	    * Adds a user-provided Transform step.
	    *
	    * @param transform {@link Transform} object
	    */
	   private void addTransform(Transform transform) {
	      if (log.isDebugEnabled())
	      	log.debug("Transforms.addTransform(" + transform.getURI() + ")");

	      Element transformElement = transform.getElement();

	      this._constructionElement.appendChild(transformElement);
	      XMLUtils.addReturnToElement(this._constructionElement);
	   }

	   /**
	    * Applies all included <code>Transform</code>s to xmlSignatureInput and returns the result of these transformations.
	    *
	    * @param xmlSignatureInput the input for the <code>Transform</code>s
	    * @return the result of the <code>Transforms</code>
	    * @throws TransformationException
	    */
	   public XMLSignatureInput performTransforms(
	           XMLSignatureInput xmlSignatureInput) throws TransformationException {
	   	     return performTransforms(xmlSignatureInput,null);
	   }
	   
	   /**
	    * Applies all included <code>Transform</code>s to xmlSignatureInput and returns the result of these transformations.
	    *
	    * @param xmlSignatureInput the input for the <code>Transform</code>s
	    * @param os where to output the last transformation.
	    * @return the result of the <code>Transforms</code>
	    * @throws TransformationException
	    */
	    public XMLSignatureInput performTransforms(
	            XMLSignatureInput xmlSignatureInput,OutputStream os) throws TransformationException {

	      try {
	        int last=this.getLength()-1;
	         for (int i = 0; i < last; i++) {
	            Transform t = this.item(i);
	            if (log.isDebugEnabled()) {
	            	log.debug("Preform the (" + i + ")th " + t.getURI() + " transform");
	            }
				xmlSignatureInput = t.performTransform(xmlSignatureInput);
	         }
	         if (last>=0) {
				Transform t = this.item(last);
	            xmlSignatureInput = t.performTransform(xmlSignatureInput, os);
	         }


	         return xmlSignatureInput;
	      } catch (IOException ex) {
	         throw new TransformationException("empty", ex);
	      // } catch (ParserConfigurationException ex) { throw new TransformationException("empty", ex);
	      // } catch (SAXException ex) { throw new TransformationException("empty", ex);
	      } catch (CanonicalizationException ex) {
	         throw new TransformationException("empty", ex);
	      } catch (InvalidCanonicalizerException ex) {
	         throw new TransformationException("empty", ex);
	      }
	   }

	   /**
	    * Return the nonnegative number of transformations.
	    *
	    * @return the number of transformations
	    */
	   public int getLength()
	   {

	       if (transforms==null) {
	        transforms=XMLUtils.selectDsNodes(this._constructionElement.getFirstChild(),
	           "Transform");
	       }
	       return transforms.length;       
	  }

	   /**
	    * Return the <it>i</it><sup>th</sup> <code>{@link Transform}</code>.
	    * Valid <code>i</code> values are 0 to <code>{@link #getLength}-1</code>.
	    *
	    * @param i index of {@link Transform} to return
	    * @return the <it>i</it><sup>th</sup> transforms
	    * @throws TransformationException
	    */
	   public Transform item(int i) throws TransformationException {
	   	
		   	try {
		   		if (transforms==null) {
		   			transforms=XMLUtils.selectDsNodes(this._constructionElement.getFirstChild(),
		   			"Transform");
		   		}
		   		return new Transform(transforms[i], this._baseURI);
		   	} catch (XMLSecurityException ex) {
		   		throw new TransformationException("empty", ex);
		   	}
	   } 

	   
	   /**
	    * Method getTransforms1
	    *
	    *
	    * @throws XMLSecurityException
	    * @return the transforamitons
	    */
	   public Transforms1 getTransforms1() throws XMLSecurityException {

	      try {
	       Element transforms1 =
	             XMLUtils.selectDsNode(this._constructionElement,                                                
	                                                Constants
	                                                   ._TAG_TRANSFORMS, 0);

	         if (transforms1 != null) {
	            return new Transforms1(transforms1, this._baseURI);
	         }

	         return null;
	      } catch (XMLSignatureException ex) {
	         throw new XMLSecurityException("empty", ex);
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



