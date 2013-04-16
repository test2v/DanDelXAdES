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
 * <pre>&lt;xsd:element name="CommitmentTypeIndication" type="CommitmentTypeIndicationType"/>
		&lt;xsd:complexType name="CommitmentTypeIndicationType">
  			&lt;xsd:sequence>
    				&lt;xsd:element name="CommitmentTypeId" type="ObjectIdentifierType"/>
    				&lt;xsd:choice>
      					&lt;xsd:element name="ObjectReference" type="xsd:anyURI"  minOccurs="0" maxOccurs="unbounded"/>
      					&lt;xsd:element name="AllSignedDataObjects"/>
    				&lt;/xsd:choice>
    				&lt;xsd:element name="CommitmentTypeQualifiers" type="CommitmentTypeQualifiersListType" minOccurs="0"/>
  			&lt;/xsd:sequence>
		&lt;/xsd:complexType></pre>
 
 * 
 * @author Psycho_core (nqkoi_ot_bg@yahoo.com)
 * @author mwnnj (krassen.deltchev@rub.de)
 **/


public class CommitmentTypeIndication extends ObjectContainer implements  SignedDataObjectPropertiesContent{
	
	
	/**
	    * Constructor CommitmentTypeIndication
	    * @param doc
	    */
	public CommitmentTypeIndication(Document doc) {
		super(doc);	
		XMLUtils.addReturnToElement(this._constructionElement);
	}
	
	/**
	    * Constructor CommitmentTypeIndication
	    *
	    * @param element
	    * @param BaseURI
	    * @throws XMLSecurityException
	    */
	   public CommitmentTypeIndication(Element element, String BaseURI)
	           throws XMLSecurityException {
	      super(element, BaseURI);
	   }
	   
	   /**
	    * Constructor CommitmentTypeIndication
	    *
	    * @param doc
	    * @param CommitmentTypeId ObjectIdentifier
	    * @param commitmentTypeQualifiers CommitmentTypeQualifiersList
	    * @param AllSignedDataObjects
	    */
	   public CommitmentTypeIndication(Document doc, ObjectIdentifier CommitmentTypeId,AllSignedDataObjects AllSignedDataObjects, CommitmentTypeQualifiersList commitmentTypeQualifiers) {

	      super(doc);
	      
		   if (CommitmentTypeId == null) {
				throw new IllegalArgumentException("ObjectReference Attibute is required!");
			} 
		   else if (CommitmentTypeId != null) {
	          this._constructionElement.appendChild(CommitmentTypeId.getElement());
	          XMLUtils.addReturnToElement(this._constructionElement);
	       }
		   if (AllSignedDataObjects != null) {
			   this._constructionElement.appendChild(AllSignedDataObjects.getElement());
		         XMLUtils.addReturnToElement(this._constructionElement);
		       }


	      if (commitmentTypeQualifiers != null) {
	          this._constructionElement.appendChild(commitmentTypeQualifiers.getElement());
	          XMLUtils.addReturnToElement(this._constructionElement);
	       }
	   }
	   
	   
	   /**
	    * Method getCommitmentTypeId
	    *
	    *
	    * @throws XMLSecurityException
	    * @return the CommitmentTypeIdElement
	    */
	   public ObjectIdentifier getCommitmentTypeId() throws XMLSecurityException {

	      try {
	       Element CommitmentTypeId =
	             XMLUtils.selectDsNode(this._constructionElement,                                                
	                                                Constants._TAG_COMMITMENTTYPEID, 0);

	         if (CommitmentTypeId != null) {
	            return new ObjectIdentifier(CommitmentTypeId, this._baseURI);
	         }

	         return null;
	      } catch (XMLSignatureException ex) {
	         throw new XMLSecurityException("empty", ex);
	      }
	   }
	   /**
	    * Method getcommitmentTypeQualifiers
	    *
	    *
	    * @throws XMLSecurityException
	    * @return the CommitmentTypeQualifiersElement
	    */
	   public CommitmentTypeQualifiersList getcommitmentTypeQualifiers() throws XMLSecurityException {

	      try {
	       Element commitmentTypeQualifiersElem =
	             XMLUtils.selectDsNode(this._constructionElement,                                                
	                                                Constants._TAG_COMMITMENTTYPEQUALIFIERS, 0);

	         if (commitmentTypeQualifiersElem != null) {
	            return new CommitmentTypeQualifiersList(commitmentTypeQualifiersElem, this._baseURI);
	         }

	         return null;
	      } catch (XMLSignatureException ex) {
	         throw new XMLSecurityException("empty", ex);
	      }
	   }
	   
	   
	   

	   /** @inheritDoc */
	   public String getBaseLocalName() {
	      return Constants._TAG_COMMITMENTTYPEINDICATION;
	   }
	   /** @inheritDoc */
	   public String getBaseNamespace() {
	      return "http://uri.etsi.org/01903/v1.1.1#";
	   }
	}
