


package org.apache.xml.security.xades.sp;



import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.XMLSignatureException;
import org.apache.xml.security.signature.ObjectContainer;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.apache.xml.security.xades.Constants;

import org.apache.xml.security.xades.sp.IndividualDataObjectsTimeStamp;

/**
 * <pre>&lt;xsd:element name="SignedDataObjectProperties"   type="SignedDataObjectPropertiesType"/>
&lt;xsd:complexType name="SignedDataObjectPropertiesType">
  &lt;xsd:sequence>
    &lt;xsd:element name="DataObjectFormat" type="DataObjectFormatType" minOccurs="0" maxOccurs="unbounded"/>
    &lt;xsd:element name="CommitmentTypeIndication" type="CommitmentTypeIndicationType" minOccurs="0" maxOccurs="unbounded"/>
    &lt;xsd:element name="AllDataObjectsTimeStamp" type="TimeStampType" minOccurs="0" maxOccurs="unbounded"/>
    &lt;xsd:element name="IndividualDataObjectsTimeStamp" type="TimeStampType" minOccurs="0" maxOccurs="unbounded"/>
  &lt;/xsd:sequence>
&lt;/xsd:complexType></pre>
 * 
 * @author Psycho_core (nqkoi_ot_bg@yahoo.com)
 * @author mwnnj (krassen.deltchev@rub.de)
 **/


public class SignedDataObjectProperties extends ObjectContainer implements SignedPropertiesContent{
	
	/**
	    * Constructor SignedDataObjectProperties
	    * @param doc
	    */
	public SignedDataObjectProperties(Document doc){
		super(doc);
	}
	
	/**
	    * Constructor SignedDataObjectProperties
	    *
	    * @param element
	    * @param BaseURI
	    * @throws XMLSecurityException
	    */
	
	public SignedDataObjectProperties(Element element, String BaseURI)
    throws XMLSecurityException {
	      super(element, BaseURI);
	   }

	   /**
	    * Constructor SignedDataObjectProperties
	    *
	    * @param doc
	    * @param dataobjectformat DataObjectFormat  
	    * @param commitmenttypeindication CommitmentTypeIndication
	    * @param alldataobjectstimestamp AllDataObjectsTimeStamp
	    * @param individualdataobjectstimestamp IndividualDataObjectsTimeStamp
	    */
	   public SignedDataObjectProperties(Document doc, DataObjectFormat dataobjectformat,CommitmentTypeIndication commitmenttypeindication,
			   AllDataObjectsTimeStamp alldataobjectstimestamp,IndividualDataObjectsTimeStamp individualdataobjectstimestamp) throws XMLSignatureException, XMLSecurityException {

		      super(doc);

	     
	      if (dataobjectformat != null) {
	         this._constructionElement.appendChild(dataobjectformat.getElement());
	         XMLUtils.addReturnToElement(this._constructionElement);
	      }
	      
	     
	      
	      if (commitmenttypeindication != null) {
		         this._constructionElement.appendChild(commitmenttypeindication.getElement());
		         XMLUtils.addReturnToElement(this._constructionElement);
		      }
	      
	      
	      if (alldataobjectstimestamp != null) {
		         this._constructionElement.appendChild(alldataobjectstimestamp.getElement());
		         XMLUtils.addReturnToElement(this._constructionElement);		         
	      }
		         
		         
		  if (individualdataobjectstimestamp != null) {
			     this._constructionElement.appendChild(individualdataobjectstimestamp.getElement());
			     XMLUtils.addReturnToElement(this._constructionElement);
		  }
	   }
	   
	   /**
	    * Method setDataObjectFormat
	    *
	    * @param dataobjectformat
	    */
	   public void setDataObjectFormat (DataObjectFormat dataobjectformat) {

	      if ((this._state == MODE_SIGN)&& (dataobjectformat != null)) {
	         this._constructionElement.appendChild(dataobjectformat.getElement());
	         XMLUtils.addReturnToElement(this._constructionElement);
	      }
	   }
	   /**
	    * Method setCommitmentTypeIndication
	    *
	    * @param commitmenttypeindication
	    */
	   public void setCommitmentTypeIndication (CommitmentTypeIndication commitmenttypeindication) {

	      if ((this._state == MODE_SIGN)&& (commitmenttypeindication != null)) {
	         this._constructionElement.appendChild(commitmenttypeindication.getElement());
	         XMLUtils.addReturnToElement(this._constructionElement);
	      }
	   }
	   	   
	   /**
	    * Method setAllDataObjectsTimeStamp
	    *
	    * @param alldataobjectstimestamp
	    */
	   public void setAllDataObjectsTimeStamp (AllDataObjectsTimeStamp alldataobjectstimestamp) {

	      if ((this._state == MODE_SIGN)&& (alldataobjectstimestamp != null)) {
	         this._constructionElement.appendChild(alldataobjectstimestamp.getElement());
	         XMLUtils.addReturnToElement(this._constructionElement);
	      }
	   }
	   
	   /**
	    * Method setIndividualDataObjectsTimeStamp
	    *
	    * @param individualdataobjectstimestamp
	    */
	   public void setIndividualDataObjectsTimeStamp (IndividualDataObjectsTimeStamp individualdataobjectstimestamp) {

	      if ((this._state == MODE_SIGN)&& (individualdataobjectstimestamp != null)) {
	         this._constructionElement.appendChild(individualdataobjectstimestamp.getElement());
	         XMLUtils.addReturnToElement(this._constructionElement);
	      }
	   }
	   
	   

	   /**
	    * Method getDataObjectFormat
	    *
	    *
	    * @throws XMLSecurityException
	    * @return the dataobjectformat
	    */
	   public DataObjectFormat getDataObjectFormat() throws XMLSecurityException {

	      try {
	       Element dataobjectformat =
	             XMLUtils.selectDsNode(this._constructionElement,                                                
	                                                Constants
	                                                   ._TAG_DATAOBJECTFORMAT, 0);

	         if (dataobjectformat != null) {
	            return new DataObjectFormat(dataobjectformat, this._baseURI);
	         }

	         return null;
	      } catch (XMLSignatureException ex) {
	         throw new XMLSecurityException("empty", ex);
	      }
	   }
	   
	   
	   /**
	    * Method getCommitmentTypeIndication
	    *
	    *
	    * @throws XMLSecurityException
	    * @return the commitmenttypeindication
	    */
	   public CommitmentTypeIndication getCommitmentTypeIndication() throws XMLSecurityException {

	      try {
	       Element commitmenttypeindication =
	             XMLUtils.selectDsNode(this._constructionElement,                                                
	                                                Constants
	                                                   ._TAG_COMMITMENTTYPEINDICATION, 0);

	         if (commitmenttypeindication != null) {
	            return new CommitmentTypeIndication(commitmenttypeindication, this._baseURI);
	         }

	         return null;
	      } catch (XMLSignatureException ex) {
	         throw new XMLSecurityException("empty", ex);
	      }
	   }
	   
	   
	   /**
	    * Method getAllDataObjectsTimeStamp
	    *
	    *
	    * @throws XMLSecurityException
	    * @return the alldataobjectstimestamp
	    */
	   public AllDataObjectsTimeStamp getAllDataObjectsTimeStamp() throws XMLSecurityException {

	      try {
	       Element alldataobjectstimestamp =
	             XMLUtils.selectDsNode(this._constructionElement,                                                
	                                                Constants
	                                                   ._TAG_ALLDATAOBJECTSTIMESTAMP, 0);

	         if (alldataobjectstimestamp != null) {
	            return new AllDataObjectsTimeStamp(alldataobjectstimestamp, this._baseURI);
	         }

	         return null;
	      } catch (XMLSignatureException ex) {
	         throw new XMLSecurityException("empty", ex);
	      }
	   }
	   
	   /**
	    * Method getIndividualDataObjectsTimeStamp
	    *
	    *
	    * @throws XMLSecurityException
	    * @return the individualdataobjectstimestamp
	    */
	   public IndividualDataObjectsTimeStamp getIndividualDataObjectsTimeStamp() throws XMLSecurityException {

	      try {
	       Element individualdataobjectstimestamp =
	             XMLUtils.selectDsNode(this._constructionElement,                                                
	                                                Constants
	                                                   ._TAG_INDIVIDUALDATAOBJECTSTIMESTAMP, 0);

	         if (individualdataobjectstimestamp != null) {
	            return new IndividualDataObjectsTimeStamp(individualdataobjectstimestamp, this._baseURI);
	         }

	         return null;
	      } catch (XMLSignatureException ex) {
	         throw new XMLSecurityException("empty", ex);
	      }
	   }
	
	   
		
	
	   /** @inheritDoc */
	   public String getBaseLocalName() {
	      return Constants._TAG_SIGNEDDATAOBJECTPROPERTIES;
	   }
	
	   /** @inheritDoc */
	   public String getBaseNamespace() {
	      return "http://uri.etsi.org/01903/v1.1.1#";
	   }

	
	
	
}

  