/*
 * no xml code referred
 * the constants-list that shall be used by the diffrent
 * files in the package xml.security.xades
 */



package org.apache.xml.security.xades;

import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.utils.ElementProxy;


/** 
 * @author Psycho_core (nqkoi_ot_bg@yahoo.com)
 * @author mwnnj (krassen.deltchev@rub.de)
 **/



public class Constants {

   /** {@link org.apache.commons.logging} logging facility */
   static org.apache.commons.logging.Log log = 
        org.apache.commons.logging.LogFactory.getLog(Constants.class.getName());
   
   /**
    * The URL of the <A HREF="http://www.w3.org/TR/2001/CR-xmldsig-core-20010419/">XML Signature specification</A>
    */
   public static final String SIGNATURESPECIFICATION_URL = "http://www.w3.org/TR/2001/CR-xmldsig-core-20010419/";
   
   /**
    * The namespace of the <A HREF="http://www.w3.org/TR/2001/CR-xmldsig-core-20010419/">XML Signature specification</A>
    */
   public static final String SignatureSpecNS   = "http://www.w3.org/2000/09/xmldsig#";
   
   /**
    * The local namespace  
    */
   public static final String LocalSpecNS   = "http://uri.etsi.org/01903/v1.1.1#";
   
   /** The URL for more algorithm **/
   public static final String MoreAlgorithmsSpecNS   = "http://www.w3.org/2001/04/xmldsig-more#";
   
   /** Digest - Required SHA1 */
   public static final String ALGO_ID_DIGEST_SHA1        = SignatureSpecNS + "sha1";

   
   /** Tag of Attr Algorithm**/
   public static final String _ATT_ALGORITHM              			= "Algorithm";
   /** Tag of Element Encoding URI**/
   public static final String _ENCODING_URI                    	    = "Encoding URI";
   /** Tag of Attr Encoding **/
   public static final String _ATT_ENCODING  						= "Encoding";
   /** Tag of Attr Id**/
   public static final String _ATT_ID                     			= "Id";
   /** Tag of Attr MimeType **/
   public static final String _ATT_MIMETYPE             			= "MimeType";
   /** Tag of Element ObjectReference**/
   public static final String _ATT_OBJECTREFERENCE                  = "ObjectReference";
   /** Tag of Attr QualifyingProperties**/
   public static final String _ATT_QUALIFYINGPROPERTIES   			= "QualifyingProperties"; 
   /** Tag of Attr Target**/
   public static final String _ATT_TARGET                 			= "Target"; 
   /** Tag of Attr Type**/
   public static final String _ATT_TYPE                   			= "Type";
   /** Tag of Attr URI**/
   public static final String _ATT_URI                    			= "URI";
   



   /** Tag of Element AllDataObjectsTimeStamp **/
   public static final String _TAG_ALLDATAOBJECTSTIMESTAMP   		= "AllDataObjectsTimeStamp";
   /** Tag of Element AllSignedDataObjects **/
   public static final String _TAG_ALLSIGNEDDATAOBJECTS   			= "AllSignedDataObjects";
   /** Tag of Element Cert **/
   public static final String _TAG_CERT                   			= "Cert";
   /** Tag of Element CertID **/
   public static final String _TAG_CERTID                 			= "CertID";
   /** Tag of Element CertIDList **/
   public static final String _TAG_CERTIDLIST                 		= "CertIDList";
   /** Tag of Element CertDigest **/
   public static final String _TAG_CERTDIGEST                 		= "CertDigest";
   /** Tag of Element CertifiedRoles **/
   public static final String _TAG_CERTIFIEDROLES                 	= "CertifiedRoles";
   /** Tag of Element ClaimedRoles **/
   public static final String _TAG_CLAIMEDROLES                 	= "ClaimedRoles";   
   /** Tag of Element ClaimedRolesList **/
   public static final String _TAG_CLAIMEDROLESLIST                	= "ClaimedRolesList";   
   /** Tag of Element ClaimedRole **/
   public static final String _TAG_CLAIMEDROLE                 	    = "ClaimedRole"; 
   /** Tag of Element City **/
   public static final String _TAG_CITY                 	    	= "City"; 
   /** Tag of Element CertifiedRolesList **/
   public static final String _TAG_CERTIFIEDROLESLIST               = "CertifiedRolesList";   
   /** Tag of Element CertifiedRole **/
   public static final String _TAG_CERTIFIEDROLE                 	= "CertifiedRole";  
   /** Tag of Element CommitmentTypeId **/
   public static final String _TAG_COMMITMENTTYPEID  				= "CommitmentTypeId";
   /** Tag of Element CommitmentTypeIndication **/
   public static final String _TAG_COMMITMENTTYPEINDICATION  		= "CommitmentTypeIndication";
   /** Tag of Element CommitmentTypeQualifiers **/
   public static final String _TAG_COMMITMENTTYPEQUALIFIERS  		= "CommitmentTypeQualifiers";
   /** Tag of Element CommitmentTypeQualifiers **/
   public static final String _TAG_COMMITMENTTYPEQUALIFIERSLIST  	= "CommitmentTypeQualifiersList";
   /** Tag of Element CommitmentTypeQualifiers **/
   public static final String _TAG_COMMITMENTTYPEQUALIFIER  		= "CommitmentTypeQualifier";
   /** Tag of Element CounterSignature **/
   public static final String _TAG_COUNTERSIGNATURE  				= "CounterSignature"; 
   /** Tag of Element CountryName **/
   public static final String _TAG_COUNTRYNAME  					= "CountryName";    
   /** Tag of Element Description **/
   public static final String _TAG_DESCRIPTION          			= "Description";
   /** Tag of Element DigestAlgAndValue **/
   public static final String _TAG_DIGESTALGANDVALUE          		= "DigestAlgAndValue";
   /** Tag of Element DigestMethod **/
   public static final String _TAG_DIGESTMETHOD          			= "DigestMethod";
   /** Tag of Element DigestValue **/
   public static final String _TAG_DIGESTVALUE           			= "DigestValue";
   /** Tag of Element DataObjectFormat **/
   public static final String _TAG_DATAOBJECTFORMAT       			= "DataObjectFormat";   
   /** Tag of Element DocumentationReferences **/
   public static final String _TAG_DOCUMENTATIONREFERENCES       	= "DocumentationReferences";
   /** Tag of Element DocumentationReference **/
   public static final String _TAG_DOCUMENTATIONREFERENCE       	= "DocumentationReference";
   /** Tag of Element EncapsulatedTimeStamp **/
   public static final String _TAG_ENCAPSULATEDTIMESTAMP         	= "EncapsulatedTimeStamp";
   /** Tag of Element EncapsulatedPKIData **/
   public static final String _TAG_ENCAPSULATEDPKIDATA         	    = "EncapsulatedPKIData";   
   /** Tag of Element Encoding **/
   public static final String _TAG_ENCODING                    	    = "Encoding"; 
   /** Tag of Element HashDataInfo **/
   public static final String _TAG_HASHDATAINFO                		= "HashDataInfo";
   /** Tag of Element Identifier **/
   public static final String _TAG_IDENTIFIER   					= "Identifier";
   /** Tag of Element IndividualDataObjectsTimeStamp **/
   public static final String _TAG_INDIVIDUALDATAOBJECTSTIMESTAMP   = "IndividualDataObjectsTimeStamp"; 
   /** Tag of Element IssuerSerial **/
   public static final String _TAG_ISSUERSERIAL  					= "IssuerSerial";
   /** Tag of Element KeyInfo **/
   public static final String _TAG_KEYINFO               			= "KeyInfo";
   /** Tag of Element Methods **/
   public static final String _TAG_METHODS                			= "Methods";
   /** Tag of Element MimeType **/
   public static final String _TAG_MIMETYPE             			= "MimeType";
   /** Tag of Element Object **/
   public static final String _TAG_OBJECT                 			= "Object";
   /** Tag of Element ObjectIdentifier **/
   public static final String _TAG_OBJECTIDENTIFIER                 = "ObjectIdentifier";
   /** Tag of Element ObjectReference **/
   public static final String _TAG_OBJECTREFERENCE                 	= "ObjectReference";
   /** Tag of Element PostalCode **/
   public static final String _TAG_POSTALCODE              			= "PostalCode";
   /** Tag of Element QualifyingProperties **/
   public static final String _TAG_QUALIFYINGPROPERTIES  		    = "QualifyingProperties";
   /** Tag of Element Reference **/
   public static final String _TAG_REFERENCE              			= "Reference";
   /** Tag of Element SigningCertificate **/
   public static final String _TAG_SIGNINGCERTIFICATE    			= "SigningCertificate";  
   /** Tag of Element SignedProperties **/
   public static final String _TAG_SIGNEDPROPERTIES      			= "SignedProperties";
   /** Tag of Element SignerRole **/
   public static final String _TAG_SIGNERROLE            			= "SignerRole";
   /** Tag of Element SignatureProductionPlace **/
   public static final String _TAG_SIGNATUREPRODUCTIONPLACE  		= "SignatureProductionPlace";
   /** Tag of Element SignaturePolicyIdentifier **/
   public static final String _TAG_SIGNATUREPOLICYIDENTIFIER  		= "SignaturePolicyIdentifier";
   /** Tag of Element SignedSignaturePropeties **/
   public static final String _TAG_SIGNEDSIGNATUREPROPERTIES  		= "SignedSignaturePropeties";
   /** Tag of Element SignedDataObjectProperties **/
   public static final String _TAG_SIGNEDDATAOBJECTPROPERTIES 		= "SignedDataObjectProperties";
   /** Tag of Element SigningTime **/
   public static final String _TAG_SIGNINGTIME			  			= "SigningTime";
   /** Tag of Element Signature **/
   public static final String _TAG_SIGNATURE             			= "Signature";
   /** Tag of Element SignatureMethod **/
   public static final String _TAG_SIGNATUREMETHOD       			= "SignatureMethod";
   /** Tag of Element SignaturePolicyID **/
   public static final String _TAG_SIGNATUREPOLICYID               	= "SignaturePolicyID";
   /** Tag of Element SignatureProperties **/
   public static final String _TAG_SIGNATUREPROPERTIES   			= "SignatureProperties";
   /** Tag of Element SignatureProperty **/
   public static final String _TAG_SIGNATUREPROPERTY      			= "SignatureProperty";
   /** Tag of Element SignatureValue **/
   public static final String _TAG_SIGNATUREVALUE         			= "SignatureValue";
   /** Tag of Element SignedInfo **/
   public static final String _TAG_SIGNEDINFO             			= "SignedInfo";
   /** Tag of Element SigPolicyHash **/
   public static final String _TAG_SIGPOLICYHASH            		= "SigPolicyHash"; 
   /** Tag of Element SigPolicyId **/
   public static final String _TAG_SIGPOLICYID            			= "SigPolicyId";
   /** Tag of Element SigPolicyQualifier **/
   public static final String _TAG_SIGPOLICYQUALIFIER             	= "SigPolicyQualifier";
   /** Tag of Element SigPolicyQualifiers **/
   public static final String _TAG_SIGPOLICYQUALIFIERS             	= "SigPolicyQualifiers";
   /** Tag of Element StateOrProvince **/
   public static final String _TAG_STATEORPROVINCE               	= "StateOrProvince";
   /** Tag of Element Target **/
   public static final String _TAG_TARGET                 			= "Target";
   /** Tag of Element Transform **/
   public static final String _TAG_TRANSFORM              			= "Transform";
   /** Tag of Element Transforms **/
   public static final String _TAG_TRANSFORMS            			= "Transforms";
   /** Tag of Element TimeStampType **/
   public static final String _TAG_TIMESTAMPTYPE                 	= "TimeStampType";
   /** Tag of Element UnsignedProperties **/
   public static final String _TAG_UNSIGNEDPROPERTIES    			= "UnsignedProperties";
   /** Tag of Element UnsignedSignatureProperties **/
   public static final String _TAG_UNSIGNEDSIGNATUREPROPERTIES    	= "UnsignedSignatureProperties";
   /** Tag of Element UnsignedDataObjectProperties **/
   public static final String _TAG_UNSIGNEDDATAOBJECTPROPERTIES    	= "UnsignedDataObjectProperties";
   /** Tag of Element XMLTimeStamp **/
   public static final String _TAG_XMLTIMESTAMP   					= "XMLTimeStamp";
   

  
   
   private Constants() {
     // we don't allow instantiation
   }

   /**
    * Sets the namespace prefix which will be used to identify elements in the
    * XML Signature Namespace.
    *
    * <pre>
    * Constants.setSignatureSpecNSprefix("dsig");
    * </pre>
    *
    * @param newPrefix is the new namespace prefix.
    * @throws XMLSecurityException
    * @see org.apache.xml.security.utils.Constants#getSignatureSpecNSprefix
    * $todo$ Add consistency checking for valid prefix
    */
   public static void setSignatureSpecNSprefix(String newPrefix) throws XMLSecurityException {
      ElementProxy.setDefaultPrefix(Constants.SignatureSpecNS, newPrefix);
   }

   /**
    * Returns the XML namespace prefix which is used for elements in the XML
    * Signature namespace.
    *
    * It is defaulted to <code>dsig</code>, but can be changed using the
    * {@link #setSignatureSpecNSprefix} function.
    *
    * @return the current used namespace prefix
    * @see #setSignatureSpecNSprefix
    */
   public static String getSignatureSpecNSprefix() {
      return ElementProxy.getDefaultPrefix(Constants.SignatureSpecNS);
   }
   
   public static String getLocalSpecNSprefix() {
      return ElementProxy.getDefaultPrefix(Constants.LocalSpecNS);
   }
   /** The URI for XMLNS spec*/
   public static final String NamespaceSpecNS = "http://www.w3.org/2000/xmlns/";
}
