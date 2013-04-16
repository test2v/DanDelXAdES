package org.apache.xml.security.xades.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Date;
import org.apache.xml.security.Init;

import org.apache.xml.security.signature.ObjectContainer;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.utils.Constants;
import org.apache.xml.security.utils.XMLUtils;
import org.apache.xml.security.xades.IssuerSerial;
import org.apache.xml.security.xades.QualifyingProperties;
import org.apache.xml.security.xades.sp.AllDataObjectsTimeStamp;
import org.apache.xml.security.xades.sp.AllSignedDataObjects;
import org.apache.xml.security.xades.sp.CertID;
import org.apache.xml.security.xades.sp.CertIDList;
import org.apache.xml.security.xades.sp.CertifiedRolesList;
import org.apache.xml.security.xades.sp.ClaimedRolesList;
import org.apache.xml.security.xades.sp.CommitmentTypeIndication;
import org.apache.xml.security.xades.sp.CommitmentTypeQualifiersList;
import org.apache.xml.security.xades.sp.DataObjectFormat;
import org.apache.xml.security.xades.sp.DigestAlgAndValue;
import org.apache.xml.security.xades.sp.DigestMethod;
import org.apache.xml.security.xades.sp.EncapsulatedPKIDataType;
import org.apache.xml.security.xades.sp.HashDataInfoType;
import org.apache.xml.security.xades.sp.IndividualDataObjectsTimeStamp;
import org.apache.xml.security.xades.sp.ObjectIdentifier;
import org.apache.xml.security.xades.sp.SigPolicyQualifiersListType;
import org.apache.xml.security.xades.sp.SignaturePolicyIdType;
import org.apache.xml.security.xades.sp.SignaturePolicyIdentifier;
import org.apache.xml.security.xades.sp.SignatureProductionPlace;
import org.apache.xml.security.xades.sp.SignedDataObjectProperties;
import org.apache.xml.security.xades.sp.SignedProperties;
import org.apache.xml.security.xades.sp.SignedSignatureProperties;
import org.apache.xml.security.xades.sp.SignerRole;
import org.apache.xml.security.xades.sp.SigningCertificate;
import org.apache.xml.security.xades.sp.SigningTime;
import org.apache.xml.security.xades.sp.TimeStampType;
import org.apache.xml.security.xades.sp.Transforms1;
import org.apache.xml.security.xades.up.CounterSignature;
import org.apache.xml.security.xades.up.UnsignedDataObjectProperties;
import org.apache.xml.security.xades.up.UnsignedProperties;
import org.apache.xml.security.xades.up.UnsignedSignatureProperties;
import org.w3c.dom.Element;

import junit.framework.TestCase;
/**
 * 
 * @author Psycho_core( nqkoi_ot_bg@yahoo.com)
 * @author mwnnj( Krassen.Deltchev@rub.de)
 * 
 * JUnit tests on the XAdES library for the Apache XML Security Project
 *
 */
public class JUtests extends TestCase {
	private X509Certificate signCert;
	private PrivateKey signKey;
	private boolean initialized = false;
	
	public void setUp() throws Exception{
		if(initialized)			
			return;
		
		Init.init();
	      //J-
	      //All the parameters for the keystore
	      String keystoreType = "JKS";
	      String keystoreFile = "src-xades/input/keystore.jks";
	      String keystorePass = "xmlsecurity";
	      String privateKeyAlias = "test";
	      String privateKeyPass = "xmlsecurity";
	     String certificateAlias = "test";
	      //J+
	      KeyStore ks = KeyStore.getInstance(keystoreType);
	      FileInputStream fis = new FileInputStream(keystoreFile);

	      //load the keystore
	      ks.load(fis, keystorePass.toCharArray());

	      //get the private key for signing.
	   signKey = (PrivateKey) ks.getKey(privateKeyAlias,
	                                             privateKeyPass.toCharArray());
	   
       signCert = (X509Certificate) ks.getCertificate(certificateAlias);
       initialized = true;
	}
	

	public void testPositive() throws Exception{
	      Constants.setSignatureSpecNSprefix("");
	      
	      File signatureFile = new File("signature.xml");
	      
	      javax.xml.parsers.DocumentBuilderFactory dbf =
	         javax.xml.parsers.DocumentBuilderFactory.newInstance();
	      //XML Signature needs to be namespace aware
	      dbf.setNamespaceAware(true);

	      javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
	      org.w3c.dom.Document doc = db.newDocument();



	      Element root = doc.createElementNS("http://www.apache.org/ns/#app1",
	                                         "nds:RootElement");




	      root.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:nds", "http://www.nds.rub.de/xades");
	      doc.appendChild(root);
	      
	      root.appendChild(doc.createTextNode("\n"));
	      Element tmpElem = doc.createElementNS("http://www.nds.rub.de/xades", "nds:AI-NDS-HGI");      
	      tmpElem.appendChild(doc.createTextNode("Some simple text"));
	      String tmpElemId = "AI-NDS-HGI-" + tmpElem.hashCode();
	      tmpElem.setAttributeNS(null, "Id", tmpElemId);
	      root.appendChild(tmpElem);
	      root.appendChild(doc.createTextNode("\n"));
	      
	      //The BaseURI is the URI that's used to prepend to relative URIs
	      String BaseURI = signatureFile.toURL().toString();
	      //Create an XML Signature object from the document, BaseURI and
	      //signature algorithm (in this case DSA)
	      XMLSignature sig = new XMLSignature(doc, BaseURI,
	                                          XMLSignature.ALGO_ID_SIGNATURE_DSA);     
	      
	     
	      
	      sig.setId("SignatureId");
	      sig.getId();
	      
	      
	      root.appendChild(sig.getElement());
	      sig.getSignedInfo()
	         .addResourceResolver(new org.apache.xml.security.samples.utils.resolver
	            .OfflineResolver());
	      root.appendChild(sig.getElement());
	      /*
	       * our Tests
	       */

			
//		    Transforms
	 		Transforms1 transforms5 = new Transforms1(doc);
	      
//	   	Transforms
	 		Transforms1 transforms3 = new Transforms1(doc);  
	         		
//	      Transforms
	 		Transforms1 transformsl = new Transforms1(doc);
	 		
//		    DigestMethod
	      
	      	DigestMethod digestmethod = new DigestMethod(doc);
	      
	      	DigestMethod digestlmethod = new DigestMethod(doc);
	      	

	        sig.addKeyInfo(signCert);
	        sig.addKeyInfo(signCert.getPublicKey());     	
//	 		XMLX509IssuerSerial

	      	IssuerSerial  issuerserial =  new IssuerSerial(doc,signCert);

	      	
	      	
//	 		EncapsulatedPKIDataType
	      	EncapsulatedPKIDataType encapsulatedtimestamp = new EncapsulatedPKIDataType(doc);

	      	EncapsulatedPKIDataType encapsulatedtimelstamp = new EncapsulatedPKIDataType(doc);
	      	
//	      Transforms
	      	Transforms transforms = new Transforms(doc); 
	      	
//	 		HashDatalInfo
	     	HashDataInfoType hashdatalinfo = new HashDataInfoType(doc);      	

	     	HashDataInfoType hashdatainfo = new HashDataInfoType(doc);
	 
//	 		TimeStampType
	      	TimeStampType timestampltype = new TimeStampType(doc);
	      	
//	 		TimeStampType
	      	TimeStampType timestamptype = new TimeStampType(doc);
//			AllDataObjectsTimeStamp
	      	AllDataObjectsTimeStamp alldataobjectstimestamp = new AllDataObjectsTimeStamp(doc,timestamptype);

//			IndividualDataObjectsTimeStamp
	      	IndividualDataObjectsTimeStamp individualdataobjectstimestamp = new IndividualDataObjectsTimeStamp(doc,timestampltype);

	      	
//			CommitmentTypeQualifiersList 
	      	CommitmentTypeQualifiersList commitmentTypeQualifiers = new CommitmentTypeQualifiersList(doc);
	      	
//			AllSignedDataObjects
	      	AllSignedDataObjects allsigneddataobjects = new AllSignedDataObjects(doc);
	      	
//			CommitmentTypeId 
	     	ObjectIdentifier sidpolicyididentifier= new ObjectIdentifier(doc);

//			CommitmentTypeId 
	      	ObjectIdentifier commitmenttypeid = new ObjectIdentifier(doc);
	      	
//	 		CommitmentTypeIndication
	      	CommitmentTypeIndication commitmenttypeindication = new CommitmentTypeIndication(doc ,commitmenttypeid,allsigneddataobjects,commitmentTypeQualifiers);
	      
//			ObjectIdentifier
	      	ObjectIdentifier objectidentifier = new ObjectIdentifier(doc);
	      	
//	   	ClaimedRolesList ClaimedRoles
	      	
	      	ClaimedRolesList ClaimedRoles = new ClaimedRolesList(doc);
	      	
//			CertifiedRolesList   	
	      	
	      	CertifiedRolesList  CertifiedRoles = new CertifiedRolesList(doc);
	      	
//			SignerRole
	      	SignerRole signerrole = new SignerRole(doc,ClaimedRoles, CertifiedRoles);
	      
//			SignatureProductionPlace
	      	SignatureProductionPlace signatureproductionplace = new SignatureProductionPlace(doc);
	 
	      	
//			SigPolicyQualifiersListType
	      	
	      	SigPolicyQualifiersListType  sigpolicyqualifiers  = new SigPolicyQualifiersListType(doc);
	      	
	      	
//			SignaturePolicyIdType 
	      	
	      	SignaturePolicyIdType  signaturepolicyid = new SignaturePolicyIdType(doc);

//			SignaturePolicyIdentifier
	      	SignaturePolicyIdentifier signaturepolicyidentifier = new SignaturePolicyIdentifier(doc);
	      	
	      	
//			DigestAlgAndValue
	      	DigestAlgAndValue digestalgandvalue = new DigestAlgAndValue(doc);
	      	
	      	DigestAlgAndValue digestalgandlvalue = new DigestAlgAndValue(doc);
	      	
//	 		CertID 
	      	CertID certid = new CertID(doc);
	      	
//	 		CertIDList 
	      	CertIDList certidlist = new CertIDList(doc);
	      	
//			SigningCertificate
	      	SigningCertificate signingcertificate = new SigningCertificate(doc);
	      
//	   	SigningTime
	      	Date dateTime = new Date();
	      	SigningTime signingtime = new SigningTime(doc,dateTime);
	      	
	      	DataObjectFormat dataobjectformat = new DataObjectFormat(doc);
	      	SignedDataObjectProperties sdop = new SignedDataObjectProperties(doc,dataobjectformat,commitmenttypeindication,alldataobjectstimestamp,individualdataobjectstimestamp);
	      	SignedSignatureProperties ssp = new SignedSignatureProperties(doc, signingtime, signingcertificate, signaturepolicyidentifier, signatureproductionplace, signerrole );
	      	SignedProperties  sp = new SignedProperties(doc);

	      	
	      	UnsignedProperties  usp = new UnsignedProperties(doc);
	      	CounterSignature signature = new CounterSignature(doc);
	      	UnsignedSignatureProperties ussp = new UnsignedSignatureProperties(doc,signature);
	      	UnsignedDataObjectProperties usdop = new UnsignedDataObjectProperties(doc);
	      	
	      	
	      	QualifyingProperties qp = new QualifyingProperties(doc);
	      	ObjectContainer objContainer = new ObjectContainer(doc); 
	      	
	      	
	      	SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
	        byte s[] = new byte[20];
	        byte r[] = new byte[20];
	        random.nextBytes(s);
	        random.nextBytes(r);
	        
	      	String qp_Id ="QualifyingProperties-" + qp.hashCode();
	      	qp.setSignedProperties(sp);
	      	qp.setId(qp_Id);
	      	qp.setTarget("#SignatureId");
	      	
	      	String sp_Id = "SignedProperties-" + sp.hashCode();
	      	sp.setId(sp_Id);
	      	sp.setSignedSignatureProperties(ssp);
	      	sp.setSignedDataObjectProperties(sdop);
	      	sp.getSignedSignaturePropeties();
	      	signatureproductionplace.setCity("Bochum");
	      	signatureproductionplace.setStateOrProvince("NRW");
	      	signatureproductionplace.setPostalCode("44789");
	      	signatureproductionplace.setCountryName("Germany");
	      	signerrole.setCertifiedRoles(CertifiedRoles);
	      	signerrole.setClaimedRoles(ClaimedRoles);
	      	CertifiedRoles.addBase64Extension("Example ".getBytes(), "CertifiedRole");
	      	ClaimedRoles.setClaimedRole("http://uri.etsi.org/01903/v1.1.1#");
	      	signingcertificate.setSigningCertificate(certidlist);
	      	certidlist.setCert(certid);
	      	
	      	certid.setCertDigest(digestalgandlvalue);
	      	digestalgandlvalue.setDigestMethod(digestlmethod);
	      	digestlmethod.setAlgorithm("http://www.w3.org/2000/09/xmldsig#sha1");
	      	digestalgandlvalue.addBase64Extension(s, "DigestValue");
	      	
	      	
	      	certid.setIssuerSerial(issuerserial);      	
	      	issuerserial.getIssuerName();
	      	issuerserial.getSerialNumber();

	            	
	      
	        

	      	
	      	
	      	signaturepolicyidentifier.setSignaturePolicyId(signaturepolicyid);
	      	signaturepolicyid.setSigPolicyId(sidpolicyididentifier);
	      	sidpolicyididentifier.setIdentifier("URN:OID:0.9.2342.19200300.100.4");
	      	sidpolicyididentifier.setDescription("Description of ObjectIdentifier");
	      	sidpolicyididentifier.setDocumentationReferences("http://www.ietf.org/rfc/rfc3061.txt,http://www.ietf.org/rfc/rfc2396.txt");
	      	signaturepolicyid.setTransforms1(transforms3);
	      	transforms3.addTransform(Transforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS);
	      	
	      	signaturepolicyid.setSigPolicyHash(digestalgandvalue);
	      	digestalgandvalue.setDigestMethod(digestmethod);
	      	digestmethod.setAlgorithm("http://www.w3.org/2000/09/xmldsig#sha1");
	      	digestalgandvalue.addBase64Extension(r, "DigestValue"); 
	      	signaturepolicyid.setSigPolicyQualifiers(sigpolicyqualifiers);
	      	sigpolicyqualifiers.setSigPolicyQualifier("SigPolicyQualifier");
	      	
	      	dataobjectformat.setDescription("Description");
	      	dataobjectformat.setObjectIdentifier(objectidentifier);      	
	      	objectidentifier.setIdentifier("URN:OID:0.9.2342.19200300.100.4");
	      	objectidentifier.setDescription("Description of ObjectIdentifier");
	      	objectidentifier.setDocumentationReferences("http://www.ietf.org/rfc/rfc3061.txt,http://www.ietf.org/rfc/rfc2396.txt");
	      	dataobjectformat.setEncoding("UTF-8");
	      	dataobjectformat.setMimeType("plain/text,charset=ISO-8859-1");
	      	dataobjectformat.setObjectReference(sp_Id);
	      	sdop.getDataObjectFormat();
	      	commitmenttypeid.setDescription("Description of CommitmentTypeId");
	      	commitmenttypeid.setIdentifier("URN:OID:0.9.2342.19200300.100.4");
	      	commitmenttypeid.setDocumentationReferences("http://www.ietf.org/rfc/rfc3061.txt,http://www.ietf.org/rfc/rfc2396.txt");
	      	commitmentTypeQualifiers.setCommitmentTypeQualifier("CommitmentTypeQualifier");      	
	     	
	      	alldataobjectstimestamp.setAllDataObjectsTimeStamp(timestamptype);
	     	
	      	timestamptype.setHashDataInfo(hashdatainfo); 
	     	hashdatainfo.setTransforms1(transforms5);
	     	transforms5.addTransform(Transforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS);
	     	hashdatainfo.setUriAttr(tmpElemId);      	
	      	timestamptype.setEncapsulatedTimeStamp(encapsulatedtimestamp);
	      	encapsulatedtimestamp.addBase64Extension(signCert.getEncoded(), "EncapsulatedPKIData");
	      	encapsulatedtimestamp.setId("EncapsulatedTimeStamp");
	      	timestamptype.setXMLTimeStamp("XMLTimeStamp");
	      	
	      	individualdataobjectstimestamp.setIndividualDataObjectsTimeStamp(timestampltype);
	      	
	      	timestampltype.setHashDataInfo(hashdatalinfo);
	      	hashdatalinfo.setTransforms1(transformsl);
	      	transformsl.addTransform(Transforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS);      	
	      	hashdatalinfo.setUriAttr(tmpElemId);
	      	timestampltype.setEncapsulatedTimeStamp(encapsulatedtimelstamp);
	      	encapsulatedtimelstamp.addBase64Extension(signCert.getEncoded(), "EncapsulatedPKIData");
	      	encapsulatedtimelstamp.setId("EncapsulatedTimeStamp");
	      	timestampltype.setXMLTimeStamp("XMLTimeStamp");
	      	
	      	sp.getSignedDataObjectProperties();
	      	qp.setUnsignedProperties(usp);
	      	String usp_Id = "UnsignedProperties-" + usp.hashCode();
	      	usp.setId(usp_Id);
	      	usp.setUnsignedSignatureProperties(ussp); 
	      	ussp.setCounterSignature(signature);
	      	signature.getXMLSignature();
	      	usp.setUnsignedDataObjectProperties(usdop);
	      	qp.getUnsignedProperties();
	      	objContainer.appendChild(qp.getElement());         	
	      	sig.appendObject(objContainer); 
	            	 
	         // our code
	         //First we have to strip away the signature element (it's not part of the
	         //signature calculations). The enveloped transform can be used for this.
	         transforms.addTransform(Transforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS);
	         //Part of the signature element needs to be canonicalized. It is a kind
	         //of normalizing algorithm for XML. For more information please take a
	         //look at the W3C XML Digital Signature webpage.
	         sig.addDocument("#" + tmpElemId, transforms, Constants.ALGO_ID_DIGEST_SHA1);
	         
	         // Signed properties ID
	         Transforms transforms7 = new Transforms(doc);
	         
	         transforms7.addTransform(Transforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS);
	         
	         sig.addDocument("#" + sp_Id, transforms7, Constants.ALGO_ID_DIGEST_SHA1);
	         // unsigned properties ID
	         Transforms transforms9 = new Transforms(doc);
	         
	         transforms9.addTransform(Transforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS);
	         
	         sig.addDocument("#" + usp_Id, transforms9, Constants.ALGO_ID_DIGEST_SHA1);
	         //
	         
	         
	         sig.sign(signKey);

		         
		         //Add the above Document/Reference
		         FileOutputStream f = new FileOutputStream(signatureFile);

		         XMLUtils.outputDOMc14nWithComments(doc, f);
		         
		         boolean b = sig.checkSignatureValue(signCert);
		         assertTrue("Some messgage", b);	      	
	}
	
	public void testPositive_SignedSignatureProperties_Elements_required() throws Exception{
	      Constants.setSignatureSpecNSprefix("");

	      File signatureFile = new File("signature1.xml");
	      javax.xml.parsers.DocumentBuilderFactory dbf =
	         javax.xml.parsers.DocumentBuilderFactory.newInstance();
	      //XML Signature needs to be namespace aware
	      dbf.setNamespaceAware(true);

	      javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
	      org.w3c.dom.Document doc = db.newDocument();


	      Element root = doc.createElementNS("http://www.apache.org/ns/#app1",
	                                         "nds:RootElement");

	      root.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:nds", "http://www.nds.rub.de/xades");
	      doc.appendChild(root);
	      
	      root.appendChild(doc.createTextNode("\n"));
	      Element tmpElem = doc.createElementNS("http://www.nds.rub.de/xades", "nds:AI-NDS-HGI");      
	      tmpElem.appendChild(doc.createTextNode("Some simple text"));
	      String tmpElemId = "AI-NDS-HGI-" + tmpElem.hashCode();
	      tmpElem.setAttributeNS(null, "Id", tmpElemId);
	      root.appendChild(tmpElem);
	      root.appendChild(doc.createTextNode("\n"));
	      
	      //The BaseURI is the URI that's used to prepend to relative URIs
	      String BaseURI = signatureFile.toURL().toString();
	      //Create an XML Signature object from the document, BaseURI and
	      //signature algorithm (in this case DSA)
	      XMLSignature sig = new XMLSignature(doc, BaseURI,
	                                          XMLSignature.ALGO_ID_SIGNATURE_DSA);


	      sig.setId("SignatureId");
	      sig.getId();
	      
	      root.appendChild(sig.getElement());
	      sig.getSignedInfo()
	         .addResourceResolver(new org.apache.xml.security.samples.utils.resolver
	            .OfflineResolver());
	      root.appendChild(sig.getElement());
	      /*
	       * our Tests
	       */
	      
//	   		Transforms
	 		Transforms1 transforms3 = new Transforms1(doc);  
	         		
	      
//		    DigestMethod
	        
	      	DigestMethod digestmethod = new DigestMethod(doc);
	      
	      	DigestMethod digestlmethod = new DigestMethod(doc);	            

	      	sig.addKeyInfo(signCert);
		    sig.addKeyInfo(signCert.getPublicKey());
		    
//	 		IssuerSerial
	      	IssuerSerial  issuerserial = new IssuerSerial(doc,signCert);
	      
//	      	Transforms
	      	Transforms transforms = new Transforms(doc); 

//			CommitmentTypeId 
	     	ObjectIdentifier sidpolicyididentifier= new ObjectIdentifier(doc);

    	
//			SigPolicyQualifiersListType
	      	
	      	SigPolicyQualifiersListType  sigpolicyqualifiers  = new SigPolicyQualifiersListType(doc);
	      	
	      	
//			SignaturePolicyIdType 
	      	
	      	SignaturePolicyIdType  signaturepolicyid = new SignaturePolicyIdType(doc);

//			SignaturePolicyIdentifier
	      	SignaturePolicyIdentifier signaturepolicyidentifier = new SignaturePolicyIdentifier(doc);
	      	
	      	
//			DigestAlgAndValue
	      	DigestAlgAndValue digestalgandvalue = new DigestAlgAndValue(doc);
	      	
	      	DigestAlgAndValue digestalgandlvalue = new DigestAlgAndValue(doc);
	      	
//	 		CertID 
	      	CertID certid = new CertID(doc);
//	 		CertIDList 
	      	CertIDList certidlist = new CertIDList(doc);
//			SigningCertificate
	      	SigningCertificate signingcertificate = new SigningCertificate(doc);
	      
//	   	SigningTime
	      	Date dateTime = new Date();
	      	SigningTime signingtime = new SigningTime(doc,dateTime);
	      	
	      	
	      	SignedSignatureProperties ssp = new SignedSignatureProperties(doc, signingtime, signingcertificate, signaturepolicyidentifier, null, null );
	      	SignedProperties  sp = new SignedProperties(doc);
	      	
	      	QualifyingProperties qp = new QualifyingProperties(doc);
	      	ObjectContainer objContainer = new ObjectContainer(doc);  
	      	
	    	SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
	        byte s[] = new byte[20];
	        byte r[] = new byte[20];
	        random.nextBytes(s);
	        random.nextBytes(r);
	      	
	      	String qp_Id ="QualifyingProperties-" + qp.hashCode();
	      	qp.setSignedProperties(sp);
	      	qp.setId(qp_Id);
	      	qp.setTarget("#SignatureId");
	      	
	      	String sp_Id = "SignedProperties-" + sp.hashCode();
	      	sp.setId(sp_Id);
	      	sp.setSignedSignatureProperties(ssp);
	      		      	
	      	signingcertificate.setSigningCertificate(certidlist);
	      	certidlist.setCert(certid);
	      	
	      	certid.setCertDigest(digestalgandlvalue);
	      	digestalgandlvalue.setDigestMethod(digestlmethod);
	      	digestlmethod.setAlgorithm("http://www.w3.org/2000/09/xmldsig#sha1");
	      	digestalgandlvalue.addBase64Extension(s, "DigestValue");
	      	
	      	certid.setIssuerSerial(issuerserial);      	
	      	issuerserial.getIssuerName();
	      	issuerserial.getSerialNumber();

	      	
	      	signaturepolicyidentifier.setSignaturePolicyId(signaturepolicyid);
	      	signaturepolicyid.setSigPolicyId(sidpolicyididentifier);
	      	sidpolicyididentifier.setIdentifier("http://example.org/");
	      	sidpolicyididentifier.setDescription("Description of ObjectIdentifier");
	      	sidpolicyididentifier.setDocumentationReferences("http://example.org/, http://example.org/, http://example.org/");
	      	signaturepolicyid.setTransforms1(transforms3);
	      	transforms3.addTransform(Transforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS);
	      	
	      	signaturepolicyid.setSigPolicyHash(digestalgandvalue);
	      	digestalgandvalue.setDigestMethod(digestmethod);
	      	digestmethod.setAlgorithm("http://www.w3.org/2000/09/xmldsig#sha1");
	      	digestalgandvalue.addBase64Extension(r, "DigestValue"); 
	      	signaturepolicyid.setSigPolicyQualifiers(sigpolicyqualifiers);
	      	sigpolicyqualifiers.setSigPolicyQualifier("SigPolicyQualifier");
	      	
	      	

	      	objContainer.appendChild(qp.getElement());         	
	      	sig.appendObject(objContainer); 
	            	 
	         // our code
	         //First we have to strip away the signature element (it's not part of the
	         //signature calculations). The enveloped transform can be used for this.
	         transforms.addTransform(Transforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS);
	         //Part of the signature element needs to be canonicalized. It is a kind
	         //of normalizing algorithm for XML. For more information please take a
	         //look at the W3C XML Digital Signature webpage.
	         sig.addDocument("#" + tmpElemId, transforms, Constants.ALGO_ID_DIGEST_SHA1);
	         
	         Transforms transforms7 = new Transforms(doc);
	         
	         transforms7.addTransform(Transforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS);
	         
	         sig.addDocument("#" + sp_Id, transforms7, Constants.ALGO_ID_DIGEST_SHA1);
	      	 	         
           sig.sign(signKey);

	         
	         //Add the above Document/Reference
	         FileOutputStream f = new FileOutputStream(signatureFile);

	         XMLUtils.outputDOMc14nWithComments(doc, f);
	         
	         boolean b = sig.checkSignatureValue(signCert);
	         assertTrue("Some messgage", b);	      	
	}

	public void testPositive_More_Than_One_Element() throws Exception{
	      Constants.setSignatureSpecNSprefix("");

	   
	      File signatureFile = new File("signature2.xml");

	      javax.xml.parsers.DocumentBuilderFactory dbf =
	         javax.xml.parsers.DocumentBuilderFactory.newInstance();
	      //XML Signature needs to be namespace aware
	      dbf.setNamespaceAware(true);

	      javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
	      org.w3c.dom.Document doc = db.newDocument();


	      Element root = doc.createElementNS("http://www.apache.org/ns/#app1",
	                                         "nds:RootElement");


	      root.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:nds", "http://www.nds.rub.de/xades");
	      doc.appendChild(root);
	      
	      root.appendChild(doc.createTextNode("\n"));
	      Element tmpElem = doc.createElementNS("http://www.nds.rub.de/xades", "nds:AI-NDS-HGI");      
	      tmpElem.appendChild(doc.createTextNode("Some simple text"));
	      String tmpElemId = "AI-NDS-HGI-" + tmpElem.hashCode();
	      tmpElem.setAttributeNS(null, "Id", tmpElemId);
	      root.appendChild(tmpElem);
	      root.appendChild(doc.createTextNode("\n"));
	      
	      //The BaseURI is the URI that's used to prepend to relative URIs
	      String BaseURI = signatureFile.toURL().toString();
	      //Create an XML Signature object from the document, BaseURI and
	      //signature algorithm (in this case DSA)
	      XMLSignature sig = new XMLSignature(doc, BaseURI,
	                                          XMLSignature.ALGO_ID_SIGNATURE_DSA);
	     
	      sig.setId("SignatureId");
	      sig.getId();
	      
	      root.appendChild(sig.getElement());
	      sig.getSignedInfo()
	         .addResourceResolver(new org.apache.xml.security.samples.utils.resolver
	            .OfflineResolver());
	      root.appendChild(sig.getElement());
	      /*
	       * our Tests
	       */

			
//		    Transforms
	 		Transforms1 transforms5 = new Transforms1(doc);
	 		Transforms1 transforms51 = new Transforms1(doc);
	 		Transforms1 transforms52 = new Transforms1(doc);
	      
//	   	Transforms
	 		Transforms1 transforms3 = new Transforms1(doc);  
	         		
//	      Transforms @ IDOST
	 		Transforms1 transformsl = new Transforms1(doc);
	 		Transforms1 transformsl1 = new Transforms1(doc);
	 		Transforms1 transformsl2 = new Transforms1(doc);
	 		
//		    DigestMethod
	      
	      	DigestMethod digestmethod = new DigestMethod(doc);
	      
	      	DigestMethod digestlmethod = new DigestMethod(doc);
	      	
	        sig.addKeyInfo(signCert);
	        sig.addKeyInfo(signCert.getPublicKey());     	
//	 		XMLX509IssuerSerial

	      	IssuerSerial  issuerserial =  new IssuerSerial(doc,signCert);

	      	
	      	
//	 		EncapsulatedPKIDataType @ ADOST
	      	EncapsulatedPKIDataType encapsulatedtimestamp = new EncapsulatedPKIDataType(doc);
	      	EncapsulatedPKIDataType encapsulatedtimestamp1 = new EncapsulatedPKIDataType(doc);
	      	EncapsulatedPKIDataType encapsulatedtimestamp2 = new EncapsulatedPKIDataType(doc);

//	 		EncapsulatedPKIDataType @ IDOST
	      	EncapsulatedPKIDataType encapsulatedtimelstamp = new EncapsulatedPKIDataType(doc);
	      	EncapsulatedPKIDataType encapsulatedtimelstamp1 = new EncapsulatedPKIDataType(doc);
	      	EncapsulatedPKIDataType encapsulatedtimelstamp2 = new EncapsulatedPKIDataType(doc);
	      	
//	      Transforms
	      	Transforms transforms = new Transforms(doc); 
	      	
//	 		HashDatalInfo @ IDOTS
	     	HashDataInfoType hashdatalinfo = new HashDataInfoType(doc);
	     	HashDataInfoType hashdatalinfo1 = new HashDataInfoType(doc);
	     	HashDataInfoType hashdatalinfo2 = new HashDataInfoType(doc);

	     	HashDataInfoType hashdatainfo = new HashDataInfoType(doc);
	     	HashDataInfoType hashdatainfo1 = new HashDataInfoType(doc);
	     	HashDataInfoType hashdatainfo2 = new HashDataInfoType(doc);
	 
	      	
//	 		TimeStampType @ ADOTS
	      	TimeStampType timestamptype = new TimeStampType(doc);
	      	TimeStampType timestamptype1 = new TimeStampType(doc);
	      	TimeStampType timestamptype2 = new TimeStampType(doc);
	      	
//			AllDataObjectsTimeStamp
	      	AllDataObjectsTimeStamp alldataobjectstimestamp = new AllDataObjectsTimeStamp(doc,timestamptype);
	      	AllDataObjectsTimeStamp alldataobjectstimestamp1 = new AllDataObjectsTimeStamp(doc,timestamptype1);
	      	AllDataObjectsTimeStamp alldataobjectstimestamp2 = new AllDataObjectsTimeStamp(doc,timestamptype2);

//	 		TimeStampType @IDOTS
	      	TimeStampType timestampltype = new TimeStampType(doc);
	      	TimeStampType timestampltype1 = new TimeStampType(doc);
	      	TimeStampType timestampltype2 = new TimeStampType(doc);
	      	
//			IndividualDataObjectsTimeStamp
	      	IndividualDataObjectsTimeStamp individualdataobjectstimestamp = new IndividualDataObjectsTimeStamp(doc,timestampltype);
	      	IndividualDataObjectsTimeStamp individualdataobjectstimestamp1 = new IndividualDataObjectsTimeStamp(doc,timestampltype1);
	      	IndividualDataObjectsTimeStamp individualdataobjectstimestamp2 = new IndividualDataObjectsTimeStamp(doc,timestampltype2);

	      	
//			CommitmentTypeQualifiersList 
	      	CommitmentTypeQualifiersList commitmentTypeQualifiers = new CommitmentTypeQualifiersList(doc);
	      	CommitmentTypeQualifiersList commitmentTypeQualifiers1 = new CommitmentTypeQualifiersList(doc);
	      	CommitmentTypeQualifiersList commitmentTypeQualifiers2 = new CommitmentTypeQualifiersList(doc);
	      	
//			AllSignedDataObjects
	      	AllSignedDataObjects allsigneddataobjects = new AllSignedDataObjects(doc);
	      	
//			CommitmentTypeId 
	     	ObjectIdentifier sidpolicyididentifier= new ObjectIdentifier(doc);

//			CommitmentTypeId 
	      	ObjectIdentifier commitmenttypeid = new ObjectIdentifier(doc);
	      	ObjectIdentifier commitmenttypeid1 = new ObjectIdentifier(doc);
	      	ObjectIdentifier commitmenttypeid2 = new ObjectIdentifier(doc);
	      	
//	 		CommitmentTypeIndication
	      	CommitmentTypeIndication commitmenttypeindication = new CommitmentTypeIndication(doc ,commitmenttypeid,allsigneddataobjects,commitmentTypeQualifiers);
	      	CommitmentTypeIndication commitmenttypeindication1 = new CommitmentTypeIndication(doc ,commitmenttypeid1,allsigneddataobjects,commitmentTypeQualifiers1);
	      	CommitmentTypeIndication commitmenttypeindication2 = new CommitmentTypeIndication(doc ,commitmenttypeid2,allsigneddataobjects,commitmentTypeQualifiers2);
	      
//			ObjectIdentifier
	      	ObjectIdentifier objectidentifier = new ObjectIdentifier(doc);
	      	ObjectIdentifier objectidentifier1 = new ObjectIdentifier(doc);     
	      	ObjectIdentifier objectidentifier2 = new ObjectIdentifier(doc);    
	      	
//	   	ClaimedRolesList ClaimedRoles
	      	
	      	ClaimedRolesList ClaimedRoles = new ClaimedRolesList(doc);
	      	
//			CertifiedRolesList   	
	      	
	      	CertifiedRolesList  CertifiedRoles = new CertifiedRolesList(doc);
	      	
//			SignerRole
	      	SignerRole signerrole = new SignerRole(doc,ClaimedRoles, CertifiedRoles);
	      
//			SignatureProductionPlace
	      	SignatureProductionPlace signatureproductionplace = new SignatureProductionPlace(doc);
	 
	      	
//			SigPolicyQualifiersListType
	      	
	      	SigPolicyQualifiersListType  sigpolicyqualifiers  = new SigPolicyQualifiersListType(doc);
	      	
	      	
//			SignaturePolicyIdType 
	      	
	      	SignaturePolicyIdType  signaturepolicyid = new SignaturePolicyIdType(doc);

//			SignaturePolicyIdentifier
	      	SignaturePolicyIdentifier signaturepolicyidentifier = new SignaturePolicyIdentifier(doc);
	      	
	      	
//			DigestAlgAndValue
	      	DigestAlgAndValue digestalgandvalue = new DigestAlgAndValue(doc);
	      	
	      	DigestAlgAndValue digestalgandlvalue = new DigestAlgAndValue(doc);
	      	
//	 		CertID 
	      	CertID certid = new CertID(doc);
	      	
//	 		CertIDList 
	      	CertIDList certidlist = new CertIDList(doc);
	      	
//			SigningCertificate
	      	SigningCertificate signingcertificate = new SigningCertificate(doc);
	      
//	   	SigningTime
	      	Date dateTime = new Date();
	      	SigningTime signingtime = new SigningTime(doc,dateTime);

//	   	DataObjectFormat      	
	      	DataObjectFormat dataobjectformat = new DataObjectFormat(doc);
	      	DataObjectFormat dataobjectformat1 = new DataObjectFormat(doc);
	      	DataObjectFormat dataobjectformat2 = new DataObjectFormat(doc);
	      	
	      	SignedDataObjectProperties sdop = new SignedDataObjectProperties(doc);
	      	SignedSignatureProperties ssp = new SignedSignatureProperties(doc, signingtime, signingcertificate, signaturepolicyidentifier, signatureproductionplace, signerrole );
	      	SignedProperties  sp = new SignedProperties(doc);

	      	
	      	UnsignedProperties  usp = new UnsignedProperties(doc);
	      	CounterSignature signature = new CounterSignature(doc);
	      	UnsignedSignatureProperties ussp = new UnsignedSignatureProperties(doc,signature);
	      	UnsignedDataObjectProperties usdop = new UnsignedDataObjectProperties(doc);
	      	
	      	
	      	QualifyingProperties qp = new QualifyingProperties(doc);
	      	ObjectContainer objContainer = new ObjectContainer(doc); 
	      	
	      	
	      	
	      	SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
	        byte s[] = new byte[20];
	        byte r[] = new byte[20];
	        random.nextBytes(s);
	        random.nextBytes(r);
			
			String qp_Id = "QualifyingProperties-" + qp.hashCode();
			qp.setSignedProperties(sp);
			qp.setId(qp_Id);
			qp.setTarget("#SignatureId");

			String sp_Id = "SignedProperties-" + sp.hashCode();
			sp.setId(sp_Id);
			sp.setSignedSignatureProperties(ssp);
			sp.setSignedDataObjectProperties(sdop);
			sp.getSignedSignaturePropeties();
			signatureproductionplace.setCity("Bochum");
			signatureproductionplace.setStateOrProvince("NRW");
			signatureproductionplace.setPostalCode("44789");
			signatureproductionplace.setCountryName("Germany");
			signerrole.setCertifiedRoles(CertifiedRoles);
			signerrole.setClaimedRoles(ClaimedRoles);
			CertifiedRoles.addBase64Extension("Example ".getBytes(),
					"CertifiedRole");
			ClaimedRoles.setClaimedRole("http://uri.etsi.org/01903/v1.1.1#");
			signingcertificate.setSigningCertificate(certidlist);
			certidlist.setCert(certid);

			certid.setCertDigest(digestalgandlvalue);
			digestalgandlvalue.setDigestMethod(digestlmethod);
			digestlmethod.setAlgorithm("http://www.w3.org/2000/09/xmldsig#sha1");
			digestalgandlvalue.addBase64Extension(s, "DigestValue");

			certid.setIssuerSerial(issuerserial);
			issuerserial.getIssuerName();
			issuerserial.getSerialNumber();

			signaturepolicyidentifier.setSignaturePolicyId(signaturepolicyid);
			signaturepolicyid.setSigPolicyId(sidpolicyididentifier);
			sidpolicyididentifier.setIdentifier("URN:OID:0.9.2342.19200300.100.4");
			sidpolicyididentifier.setDescription("Description of ObjectIdentifier");
			sidpolicyididentifier
					.setDocumentationReferences("http://www.ietf.org/rfc/rfc3061.txt,http://www.ietf.org/rfc/rfc2396.txt");
			signaturepolicyid.setTransforms1(transforms3);
			transforms3.addTransform(Transforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS);

			signaturepolicyid.setSigPolicyHash(digestalgandvalue);
			digestalgandvalue.setDigestMethod(digestmethod);
			digestmethod.setAlgorithm("http://www.w3.org/2000/09/xmldsig#sha1");
			digestalgandvalue.addBase64Extension(r, "DigestValue");
			signaturepolicyid.setSigPolicyQualifiers(sigpolicyqualifiers);
			sigpolicyqualifiers.setSigPolicyQualifier("SigPolicyQualifier");

			sdop.setDataObjectFormat(dataobjectformat);
			dataobjectformat.setDescription("Description");
			dataobjectformat.setObjectIdentifier(objectidentifier);
			objectidentifier.setIdentifier("URN:OID:0.9.2342.19200300.100.4");
			objectidentifier.setDescription("Description of ObjectIdentifier");
			objectidentifier
					.setDocumentationReferences("http://www.ietf.org/rfc/rfc3061.txt,http://www.ietf.org/rfc/rfc2396.txt");
			dataobjectformat.setEncoding("UTF-8");
			dataobjectformat.setMimeType("plain/text,charset=ISO-8859-1");
			dataobjectformat.setObjectReference(sp_Id);

			sdop.setDataObjectFormat(dataobjectformat1);
			dataobjectformat1.setDescription("Description");
			dataobjectformat1.setObjectIdentifier(objectidentifier1);
			objectidentifier1.setIdentifier("URN:OID:0.9.2342.19200300.100.4");
			objectidentifier1.setDescription("Description of ObjectIdentifier");
			objectidentifier1
					.setDocumentationReferences("http://www.ietf.org/rfc/rfc3061.txt,http://www.ietf.org/rfc/rfc2396.txt");
			dataobjectformat1.setEncoding("UTF-8");
			dataobjectformat1.setMimeType("plain/text,charset=ISO-8859-1");
			dataobjectformat1.setObjectReference(sp_Id);

			sdop.setDataObjectFormat(dataobjectformat2);
			dataobjectformat2.setDescription("Description");
			dataobjectformat2.setObjectIdentifier(objectidentifier2);
			objectidentifier2.setIdentifier("URN:OID:0.9.2342.19200300.100.4");
			objectidentifier2.setDescription("Description of ObjectIdentifier");
			objectidentifier2
					.setDocumentationReferences("http://www.ietf.org/rfc/rfc3061.txt,http://www.ietf.org/rfc/rfc2396.txt");
			dataobjectformat2.setEncoding("UTF-8");
			dataobjectformat2.setMimeType("plain/text,charset=ISO-8859-1");
			dataobjectformat2.setObjectReference(sp_Id);

			sdop.setCommitmentTypeIndication(commitmenttypeindication);
			commitmenttypeid.setDescription("Description of CommitmentTypeId");
			commitmenttypeid.setIdentifier("URN:OID:0.9.2342.19200300.100.4");
			commitmenttypeid.setDocumentationReferences("http://www.ietf.org/rfc/rfc3061.txt,http://www.ietf.org/rfc/rfc2396.txt");
			commitmentTypeQualifiers
					.setCommitmentTypeQualifier("CommitmentTypeQualifier");

			sdop.setCommitmentTypeIndication(commitmenttypeindication1);
			commitmenttypeid1.setDescription("Description of CommitmentTypeId");
			commitmenttypeid1.setIdentifier("URN:OID:0.9.2342.19200300.100.4");
			commitmenttypeid1.setDocumentationReferences("http://www.ietf.org/rfc/rfc3061.txt,http://www.ietf.org/rfc/rfc2396.txt");
			commitmentTypeQualifiers1
					.setCommitmentTypeQualifier("CommitmentTypeQualifier");

			sdop.setCommitmentTypeIndication(commitmenttypeindication2);
			commitmenttypeid2.setDescription("Description of CommitmentTypeId");
			commitmenttypeid2.setIdentifier("URN:OID:0.9.2342.19200300.100.4");
			commitmenttypeid2.setDocumentationReferences("http://www.ietf.org/rfc/rfc3061.txt,http://www.ietf.org/rfc/rfc2396.txt");
			commitmentTypeQualifiers2
					.setCommitmentTypeQualifier("CommitmentTypeQualifier");

			sdop.setAllDataObjectsTimeStamp(alldataobjectstimestamp);
			alldataobjectstimestamp.setAllDataObjectsTimeStamp(timestamptype);
			timestamptype.setHashDataInfo(hashdatainfo);
			hashdatainfo.setTransforms1(transforms5);
			transforms5.addTransform(Transforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS);
			hashdatainfo.setUriAttr(tmpElemId);
			timestamptype.setEncapsulatedTimeStamp(encapsulatedtimestamp);
			encapsulatedtimestamp.addBase64Extension(signCert.getEncoded(),
					"EncapsulatedPKIData");
			encapsulatedtimestamp.setId("EncapsulatedTimeStamp");
			timestamptype.setXMLTimeStamp("XMLTimeStamp");

			sdop.setAllDataObjectsTimeStamp(alldataobjectstimestamp1);
			alldataobjectstimestamp1.setAllDataObjectsTimeStamp(timestamptype1);
			timestamptype1.setHashDataInfo(hashdatainfo1);
			hashdatainfo1.setTransforms1(transforms51);
			transforms51.addTransform(Transforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS);
			hashdatainfo1.setUriAttr(tmpElemId);
			timestamptype1.setEncapsulatedTimeStamp(encapsulatedtimestamp1);
			encapsulatedtimestamp1.addBase64Extension(signCert.getEncoded(),
					"EncapsulatedPKIData");
			encapsulatedtimestamp1.setId("EncapsulatedTimeStamp1");
			timestamptype1.setXMLTimeStamp("XMLTimeStamp");

			sdop.setAllDataObjectsTimeStamp(alldataobjectstimestamp2);
			alldataobjectstimestamp2.setAllDataObjectsTimeStamp(timestamptype2);
			timestamptype2.setHashDataInfo(hashdatainfo2);
			hashdatainfo2.setTransforms1(transforms52);
			transforms52.addTransform(Transforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS);
			hashdatainfo2.setUriAttr(tmpElemId);
			timestamptype2.setEncapsulatedTimeStamp(encapsulatedtimestamp2);
			encapsulatedtimestamp2.addBase64Extension(signCert.getEncoded(),
					"EncapsulatedPKIData");
			encapsulatedtimestamp2.setId("EncapsulatedTimeStamp2");
			timestamptype2.setXMLTimeStamp("XMLTimeStamp");

			sdop.setIndividualDataObjectsTimeStamp(individualdataobjectstimestamp);
			individualdataobjectstimestamp
					.setIndividualDataObjectsTimeStamp(timestampltype);
			timestampltype.setHashDataInfo(hashdatalinfo);
			hashdatalinfo.setTransforms1(transformsl);
			transformsl.addTransform(Transforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS);
			hashdatalinfo.setUriAttr(tmpElemId);
			timestampltype.setEncapsulatedTimeStamp(encapsulatedtimelstamp);
			encapsulatedtimelstamp.addBase64Extension(signCert.getEncoded(),
					"EncapsulatedPKIData");
			encapsulatedtimelstamp.setId("EncapsulatedTimeStamp");
			timestampltype.setXMLTimeStamp("XMLTimeStamp");

			sdop.setIndividualDataObjectsTimeStamp(individualdataobjectstimestamp1);
			individualdataobjectstimestamp1
					.setIndividualDataObjectsTimeStamp(timestampltype1);
			timestampltype1.setHashDataInfo(hashdatalinfo1);
			hashdatalinfo1.setTransforms1(transformsl1);
			transformsl1.addTransform(Transforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS);
			hashdatalinfo1.setUriAttr(tmpElemId);
			timestampltype1.setEncapsulatedTimeStamp(encapsulatedtimelstamp1);
			encapsulatedtimelstamp1.addBase64Extension(signCert.getEncoded(),
					"EncapsulatedPKIData");
			encapsulatedtimelstamp1.setId("EncapsulatedTimeStamp1");
			timestampltype1.setXMLTimeStamp("XMLTimeStamp");

			sdop.setIndividualDataObjectsTimeStamp(individualdataobjectstimestamp2);
			individualdataobjectstimestamp2
					.setIndividualDataObjectsTimeStamp(timestampltype2);
			timestampltype2.setHashDataInfo(hashdatalinfo2);
			hashdatalinfo2.setTransforms1(transformsl2);
			transformsl2.addTransform(Transforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS);
			hashdatalinfo2.setUriAttr(tmpElemId);
			timestampltype2.setEncapsulatedTimeStamp(encapsulatedtimelstamp2);
			encapsulatedtimelstamp2.addBase64Extension(signCert.getEncoded(),
					"EncapsulatedPKIData");
			encapsulatedtimelstamp2.setId("EncapsulatedTimeStamp2");
			timestampltype2.setXMLTimeStamp("XMLTimeStamp");

			sp.getSignedDataObjectProperties();
			qp.setUnsignedProperties(usp);
			String usp_Id = "UnsignedProperties-" + usp.hashCode();
			usp.setId(usp_Id);
			usp.setUnsignedSignatureProperties(ussp);
			ussp.setCounterSignature(signature);
			signature.getXMLSignature();
			usp.setUnsignedDataObjectProperties(usdop);
			qp.getUnsignedProperties();
			objContainer.appendChild(qp.getElement());
			sig.appendObject(objContainer);
	            	 
	         // our code
	         //First we have to strip away the signature element (it's not part of the
	         //signature calculations). The enveloped transform can be used for this.
	         transforms.addTransform(Transforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS);
	         //Part of the signature element needs to be canonicalized. It is a kind
	         //of normalizing algorithm for XML. For more information please take a
	         //look at the W3C XML Digital Signature webpage.
	         sig.addDocument("#" + tmpElemId, transforms, Constants.ALGO_ID_DIGEST_SHA1);
	                  
	         Transforms transforms7 = new Transforms(doc);
	         
	         transforms7.addTransform(Transforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS);
	         
	         sig.addDocument("#" + sp_Id, transforms7, Constants.ALGO_ID_DIGEST_SHA1);
	         
	         Transforms transforms9 = new Transforms(doc);

	         transforms9.addTransform(Transforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS);
	         
	         sig.addDocument("#" + usp_Id, transforms9, Constants.ALGO_ID_DIGEST_SHA1);
	         
	         sig.sign(signKey);

		         
		         //Add the above Document/Reference
		         FileOutputStream f = new FileOutputStream(signatureFile);

		         XMLUtils.outputDOMc14nWithComments(doc, f);
		         
		         boolean b = sig.checkSignatureValue(signCert);
		         assertTrue("Some messgage", b);	      	
	}
	
	
	public void testNeg_SignedSignatureProperties() throws Exception{
	      Constants.setSignatureSpecNSprefix("");

	      File signatureFile = new File("signature_neg.xml");
	      javax.xml.parsers.DocumentBuilderFactory dbf =
	         javax.xml.parsers.DocumentBuilderFactory.newInstance();
	      //XML Signature needs to be namespace aware
	      dbf.setNamespaceAware(true);

	      javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
	      org.w3c.dom.Document doc = db.newDocument();



	      Element root = doc.createElementNS("http://www.apache.org/ns/#app1",
	                                         "nds:RootElement");




	      root.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:nds", "http://www.nds.rub.de/xades");
	      doc.appendChild(root);
	      
	      root.appendChild(doc.createTextNode("\n"));
	      Element tmpElem = doc.createElementNS("http://www.nds.rub.de/xades", "nds:AI-NDS-HGI");      
	      tmpElem.appendChild(doc.createTextNode("Some simple text"));
	      String tmpElemId = "AI-NDS-HGI-" + tmpElem.hashCode();
	      tmpElem.setAttributeNS(null, "Id", tmpElemId);
	      root.appendChild(tmpElem);
	      root.appendChild(doc.createTextNode("\n"));
	      
	      //The BaseURI is the URI that's used to prepend to relative URIs
	      String BaseURI = signatureFile.toURL().toString();
	      //Create an XML Signature object from the document, BaseURI and
	      //signature algorithm (in this case DSA)
	      XMLSignature sig = new XMLSignature(doc, BaseURI,
	                                          XMLSignature.ALGO_ID_SIGNATURE_DSA);


	     
	      
	      root.appendChild(sig.getElement());
	      sig.getSignedInfo()
	         .addResourceResolver(new org.apache.xml.security.samples.utils.resolver
	            .OfflineResolver());
	      root.appendChild(sig.getElement());
	     
	      /*
	       * our Tests
	       */	      

	 
//	 		TimeStampType
	      	TimeStampType timestampltype = new TimeStampType(doc);
	      	
//	 		TimeStampType
	      	TimeStampType timestamptype = new TimeStampType(doc);
//			AllDataObjectsTimeStamp
	      	AllDataObjectsTimeStamp alldataobjectstimestamp = new AllDataObjectsTimeStamp(doc,timestamptype);

//			IndividualDataObjectsTimeStamp
	      	IndividualDataObjectsTimeStamp individualdataobjectstimestamp = new IndividualDataObjectsTimeStamp(doc,timestampltype);

	      	
//			CommitmentTypeQualifiersList 
	      	CommitmentTypeQualifiersList commitmentTypeQualifiers = new CommitmentTypeQualifiersList(doc);

//			AllSignedDataObjects
	      	AllSignedDataObjects allsigneddataobjects = new AllSignedDataObjects(doc);

//			CommitmentTypeId 
	      	ObjectIdentifier commitmenttypeid = new ObjectIdentifier(doc);
	      	
//	 		CommitmentTypeIndication
	      	CommitmentTypeIndication commitmenttypeindication = new CommitmentTypeIndication(doc ,commitmenttypeid,allsigneddataobjects,commitmentTypeQualifiers);
	          

	      	
	      	DataObjectFormat dataobjectformat = new DataObjectFormat(doc);
	      	SignedDataObjectProperties sdop = new SignedDataObjectProperties(doc,dataobjectformat,commitmenttypeindication,alldataobjectstimestamp,individualdataobjectstimestamp);
	      	
	      	
	      	
	      	try {
	      		SignedProperties  sp = new SignedProperties(doc, null, sdop);
	      	}catch(IllegalArgumentException e) {
	      		return;
	      	}
	      	
	      	fail("SignedSignatureProperties Element is required!");
	}
	
	
	public void testNeg_SigningTime() throws Exception{
	      Constants.setSignatureSpecNSprefix("");

	      File signatureFile = new File("signature_neg.xml");
	      javax.xml.parsers.DocumentBuilderFactory dbf =
	         javax.xml.parsers.DocumentBuilderFactory.newInstance();
	      //XML Signature needs to be namespace aware
	      dbf.setNamespaceAware(true);

	      javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
	      org.w3c.dom.Document doc = db.newDocument();


	      Element root = doc.createElementNS("http://www.apache.org/ns/#app1",
	                                         "nds:RootElement");


	      root.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:nds", "http://www.nds.rub.de/xades");
	      doc.appendChild(root);
	      
	      root.appendChild(doc.createTextNode("\n"));
	      Element tmpElem = doc.createElementNS("http://www.nds.rub.de/xades", "nds:AI-NDS-HGI");      
	      tmpElem.appendChild(doc.createTextNode("Some simple text"));
	      String tmpElemId = "AI-NDS-HGI-" + tmpElem.hashCode();
	      tmpElem.setAttributeNS(null, "Id", tmpElemId);
	      root.appendChild(tmpElem);
	      root.appendChild(doc.createTextNode("\n"));
	      
	      //The BaseURI is the URI that's used to prepend to relative URIs
	      String BaseURI = signatureFile.toURL().toString();
	      //Create an XML Signature object from the document, BaseURI and
	      //signature algorithm (in this case DSA)
	      XMLSignature sig = new XMLSignature(doc, BaseURI,
	                                          XMLSignature.ALGO_ID_SIGNATURE_DSA);
	     
	      
	      root.appendChild(sig.getElement());
	      sig.getSignedInfo()
	         .addResourceResolver(new org.apache.xml.security.samples.utils.resolver
	            .OfflineResolver());
	      root.appendChild(sig.getElement());

	      /*
	       * our Tests
	       */
      

	      	
//	   		ClaimedRolesList ClaimedRoles
	      	
	      	ClaimedRolesList ClaimedRoles = new ClaimedRolesList(doc);
	      	
//			CertifiedRolesList   	
	      	
	      	CertifiedRolesList  CertifiedRoles = new CertifiedRolesList(doc);
	      	
//			SignerRole
	      	SignerRole signerrole = new SignerRole(doc,ClaimedRoles, CertifiedRoles);
	      
//			SignatureProductionPlace
	      	SignatureProductionPlace signatureproductionplace = new SignatureProductionPlace(doc);		      	


//			SignaturePolicyIdentifier
	      	SignaturePolicyIdentifier signaturepolicyidentifier = new SignaturePolicyIdentifier(doc);	      	


//			SigningCertificate
	      	SigningCertificate signingcertificate = new SigningCertificate(doc);	      	

	      	
	      	
	      	try {
	      		SignedSignatureProperties ssp = new SignedSignatureProperties(doc, null, signingcertificate, signaturepolicyidentifier, signatureproductionplace, signerrole );
	      	}catch(IllegalArgumentException e) {
	      		return;
	      	}
	      	
	      	fail("SigningTime Element is required!");
	}
	
	public void testNeg_SigningCertificate() throws Exception{
	      Constants.setSignatureSpecNSprefix("");

	      File signatureFile = new File("signature_neg.xml");
	      javax.xml.parsers.DocumentBuilderFactory dbf =
	         javax.xml.parsers.DocumentBuilderFactory.newInstance();
	      //XML Signature needs to be namespace aware
	      dbf.setNamespaceAware(true);

	      javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
	      org.w3c.dom.Document doc = db.newDocument();


	      Element root = doc.createElementNS("http://www.apache.org/ns/#app1",
	                                         "nds:RootElement");


	      root.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:nds", "http://www.nds.rub.de/xades");
	      doc.appendChild(root);
	      
	      root.appendChild(doc.createTextNode("\n"));
	      Element tmpElem = doc.createElementNS("http://www.nds.rub.de/xades", "nds:AI-NDS-HGI");      
	      tmpElem.appendChild(doc.createTextNode("Some simple text"));
	      String tmpElemId = "AI-NDS-HGI-" + tmpElem.hashCode();
	      tmpElem.setAttributeNS(null, "Id", tmpElemId);
	      root.appendChild(tmpElem);
	      root.appendChild(doc.createTextNode("\n"));
	      
	      //The BaseURI is the URI that's used to prepend to relative URIs
	      String BaseURI = signatureFile.toURL().toString();
	      //Create an XML Signature object from the document, BaseURI and
	      //signature algorithm (in this case DSA)
	      XMLSignature sig = new XMLSignature(doc, BaseURI,
	                                          XMLSignature.ALGO_ID_SIGNATURE_DSA);
	     
	      
	      root.appendChild(sig.getElement());
	      sig.getSignedInfo()
	         .addResourceResolver(new org.apache.xml.security.samples.utils.resolver
	            .OfflineResolver());
	      root.appendChild(sig.getElement());

	      /*
	       * our Tests
	       */


	      	
//	   		ClaimedRolesList ClaimedRoles
	      	
	      	ClaimedRolesList ClaimedRoles = new ClaimedRolesList(doc);
	      	
//			CertifiedRolesList   	
	      	
	      	CertifiedRolesList  CertifiedRoles = new CertifiedRolesList(doc);
	      	
//			SignerRole
	      	SignerRole signerrole = new SignerRole(doc,ClaimedRoles, CertifiedRoles);
	      
//			SignatureProductionPlace
	      	SignatureProductionPlace signatureproductionplace = new SignatureProductionPlace(doc);		      	


//			SignaturePolicyIdentifier
	      	SignaturePolicyIdentifier signaturepolicyidentifier = new SignaturePolicyIdentifier(doc);	      	
	      	

//		   	SigningTime
	      	Date dateTime = new Date();
	      	SigningTime signingtime = new SigningTime(doc,dateTime);
	      	
	      	
	      	try {
	      		SignedSignatureProperties ssp = new SignedSignatureProperties(doc, signingtime, null, signaturepolicyidentifier, signatureproductionplace, signerrole );
	      	}catch(IllegalArgumentException e) {
	      		return;
	      	}
	      	
	      	fail("SigningCertificate Element is required!");
	}
	
	
	public void testNeg_SignaturePolicyIdentifier() throws Exception{
	      Constants.setSignatureSpecNSprefix("");

	      File signatureFile = new File("signature_neg.xml");
	      javax.xml.parsers.DocumentBuilderFactory dbf =
	         javax.xml.parsers.DocumentBuilderFactory.newInstance();
	      //XML Signature needs to be namespace aware
	      dbf.setNamespaceAware(true);

	      javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
	      org.w3c.dom.Document doc = db.newDocument();


	      Element root = doc.createElementNS("http://www.apache.org/ns/#app1",
	                                         "nds:RootElement");


	      root.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:nds", "http://www.nds.rub.de/xades");
	      doc.appendChild(root);
	      
	      root.appendChild(doc.createTextNode("\n"));
	      Element tmpElem = doc.createElementNS("http://www.nds.rub.de/xades", "nds:AI-NDS-HGI");      
	      tmpElem.appendChild(doc.createTextNode("Some simple text"));
	      String tmpElemId = "AI-NDS-HGI-" + tmpElem.hashCode();
	      tmpElem.setAttributeNS(null, "Id", tmpElemId);
	      root.appendChild(tmpElem);
	      root.appendChild(doc.createTextNode("\n"));
	      
	      //The BaseURI is the URI that's used to prepend to relative URIs
	      String BaseURI = signatureFile.toURL().toString();
	      //Create an XML Signature object from the document, BaseURI and
	      //signature algorithm (in this case DSA)
	      XMLSignature sig = new XMLSignature(doc, BaseURI,
	                                          XMLSignature.ALGO_ID_SIGNATURE_DSA);
	     
	      
	      root.appendChild(sig.getElement());
	      sig.getSignedInfo()
	         .addResourceResolver(new org.apache.xml.security.samples.utils.resolver
	            .OfflineResolver());
	      root.appendChild(sig.getElement());

	      /*
	       * our Tests
	       */
    

	      	
//	   		ClaimedRolesList ClaimedRoles
	      	
	      	ClaimedRolesList ClaimedRoles = new ClaimedRolesList(doc);
	      	
//			CertifiedRolesList   	
	      	
	      	CertifiedRolesList  CertifiedRoles = new CertifiedRolesList(doc);
	      	
//			SignerRole
	      	SignerRole signerrole = new SignerRole(doc,ClaimedRoles, CertifiedRoles);
	      
//			SignatureProductionPlace
	      	SignatureProductionPlace signatureproductionplace = new SignatureProductionPlace(doc);		      	
     	

//			SigningCertificate
	      	SigningCertificate signingcertificate = new SigningCertificate(doc);	

//		   	SigningTime
	      	Date dateTime = new Date();
	      	SigningTime signingtime = new SigningTime(doc,dateTime);
	      	
	      	
	      	try {
	      		SignedSignatureProperties ssp = new SignedSignatureProperties(doc, signingtime, signingcertificate, null, signatureproductionplace, signerrole );
	      	}catch(IllegalArgumentException e) {
	      		return;
	      	}
	      	
	      	fail("SignaturePolicyIdentifier Element is required!");
	}
	
	public void testNeg_QP_Target_required() throws Exception{
	      Constants.setSignatureSpecNSprefix("");

	      File signatureFile = new File("signature_neg.xml");
	      javax.xml.parsers.DocumentBuilderFactory dbf =
	         javax.xml.parsers.DocumentBuilderFactory.newInstance();
	      //XML Signature needs to be namespace aware
	      dbf.setNamespaceAware(true);

	      javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
	      org.w3c.dom.Document doc = db.newDocument();


	      Element root = doc.createElementNS("http://www.apache.org/ns/#app1",
	                                         "nds:RootElement");

	      root.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:nds", "http://www.nds.rub.de/xades");
	      doc.appendChild(root);
	      
	      root.appendChild(doc.createTextNode("\n"));
	      Element tmpElem = doc.createElementNS("http://www.nds.rub.de/xades", "nds:AI-NDS-HGI");      
	      tmpElem.appendChild(doc.createTextNode("Some simple text"));
	      String tmpElemId = "AI-NDS-HGI-" + tmpElem.hashCode();
	      tmpElem.setAttributeNS(null, "Id", tmpElemId);
	      root.appendChild(tmpElem);
	      root.appendChild(doc.createTextNode("\n"));
	      
	      //The BaseURI is the URI that's used to prepend to relative URIs
	      String BaseURI = signatureFile.toURL().toString();
	      //Create an XML Signature object from the document, BaseURI and
	      //signature algorithm (in this case DSA)
	      XMLSignature sig = new XMLSignature(doc, BaseURI,
	                                          XMLSignature.ALGO_ID_SIGNATURE_DSA);


	     
	      
	      root.appendChild(sig.getElement());
	      sig.getSignedInfo()
	         .addResourceResolver(new org.apache.xml.security.samples.utils.resolver
	            .OfflineResolver());
	      root.appendChild(sig.getElement());
	      /*
	       * our Tests
	       */	      	      	
	      	
	      	QualifyingProperties qp = new QualifyingProperties(doc);
  	
	      	
	      	try { qp.setTarget(null);
	      		
	      	}catch(IllegalArgumentException e) {
	      		return;
	      	}
	      	
	      	fail("Target Attribute is required!");	         
	 	
	}
	
	public void testNeg_DataObjectFormat_Attr_ObjectReference_required() throws Exception{
	      Constants.setSignatureSpecNSprefix("");

	      File signatureFile = new File("signature_neg.xml");
	      javax.xml.parsers.DocumentBuilderFactory dbf =
	         javax.xml.parsers.DocumentBuilderFactory.newInstance();
	      //XML Signature needs to be namespace aware
	      dbf.setNamespaceAware(true);

	      javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
	      org.w3c.dom.Document doc = db.newDocument();


	      Element root = doc.createElementNS("http://www.apache.org/ns/#app1",
	                                         "nds:RootElement");

	      root.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:nds", "http://www.nds.rub.de/xades");
	      doc.appendChild(root);
	      
	      root.appendChild(doc.createTextNode("\n"));
	      Element tmpElem = doc.createElementNS("http://www.nds.rub.de/xades", "nds:AI-NDS-HGI");      
	      tmpElem.appendChild(doc.createTextNode("Some simple text"));
	      String tmpElemId = "AI-NDS-HGI-" + tmpElem.hashCode();
	      tmpElem.setAttributeNS(null, "Id", tmpElemId);
	      root.appendChild(tmpElem);
	      root.appendChild(doc.createTextNode("\n"));
	      
	      //The BaseURI is the URI that's used to prepend to relative URIs
	      String BaseURI = signatureFile.toURL().toString();
	      //Create an XML Signature object from the document, BaseURI and
	      //signature algorithm (in this case DSA)
	      XMLSignature sig = new XMLSignature(doc, BaseURI,
	                                          XMLSignature.ALGO_ID_SIGNATURE_DSA);


	     
	      
	      root.appendChild(sig.getElement());
	      sig.getSignedInfo()
	         .addResourceResolver(new org.apache.xml.security.samples.utils.resolver
	            .OfflineResolver());
	      root.appendChild(sig.getElement());
	      /*
	       * our Tests
	       */
	      	
	      	DataObjectFormat dataobjectformat = new DataObjectFormat(doc);

    	
	      	
	      	try { dataobjectformat.setObjectReference(null);
	      		
	      	}catch(IllegalArgumentException e) {
	      		return;
	      	}
	      	
	      	fail("ObjectReference Attribute is required!");		      	
	}
	
	public void testNeg_ObjectIdentifier_Identifier_Attribute_required() throws Exception{
	      Constants.setSignatureSpecNSprefix("");

	      File signatureFile = new File("signature_neg.xml");
	      javax.xml.parsers.DocumentBuilderFactory dbf =
	         javax.xml.parsers.DocumentBuilderFactory.newInstance();
	      //XML Signature needs to be namespace aware
	      dbf.setNamespaceAware(true);

	      javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
	      org.w3c.dom.Document doc = db.newDocument();


	      Element root = doc.createElementNS("http://www.apache.org/ns/#app1",
	                                         "nds:RootElement");

	      root.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:nds", "http://www.nds.rub.de/xades");
	      doc.appendChild(root);
	      
	      root.appendChild(doc.createTextNode("\n"));
	      Element tmpElem = doc.createElementNS("http://www.nds.rub.de/xades", "nds:AI-NDS-HGI");      
	      tmpElem.appendChild(doc.createTextNode("Some simple text"));
	      String tmpElemId = "AI-NDS-HGI-" + tmpElem.hashCode();
	      tmpElem.setAttributeNS(null, "Id", tmpElemId);
	      root.appendChild(tmpElem);
	      root.appendChild(doc.createTextNode("\n"));
	      
	      //The BaseURI is the URI that's used to prepend to relative URIs
	      String BaseURI = signatureFile.toURL().toString();
	      //Create an XML Signature object from the document, BaseURI and
	      //signature algorithm (in this case DSA)
	      XMLSignature sig = new XMLSignature(doc, BaseURI,
	                                          XMLSignature.ALGO_ID_SIGNATURE_DSA);


	     
	      
	      root.appendChild(sig.getElement());
	      sig.getSignedInfo()
	         .addResourceResolver(new org.apache.xml.security.samples.utils.resolver
	            .OfflineResolver());
	      root.appendChild(sig.getElement());
	      /*
	       * our Tests
	       */

      
//			ObjectIdentifier
	      	ObjectIdentifier objectidentifier = new ObjectIdentifier(doc);
	      	 
	      	try { objectidentifier.setIdentifier(null);
      		
	      	}catch(IllegalArgumentException e) {
	      		return;
	      	}      	
	}
	
	public void testNeg_TimeStampType_HashDataInfo_Element_required() throws Exception{
	      Constants.setSignatureSpecNSprefix("");

	      File signatureFile = new File("signature_neg.xml");
	      javax.xml.parsers.DocumentBuilderFactory dbf =
	         javax.xml.parsers.DocumentBuilderFactory.newInstance();
	      //XML Signature needs to be namespace aware
	      dbf.setNamespaceAware(true);

	      javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
	      org.w3c.dom.Document doc = db.newDocument();


	      Element root = doc.createElementNS("http://www.apache.org/ns/#app1",
	                                         "nds:RootElement");

	      root.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:nds", "http://www.nds.rub.de/xades");
	      doc.appendChild(root);
	      
	      root.appendChild(doc.createTextNode("\n"));
	      Element tmpElem = doc.createElementNS("http://www.nds.rub.de/xades", "nds:AI-NDS-HGI");      
	      tmpElem.appendChild(doc.createTextNode("Some simple text"));
	      String tmpElemId = "AI-NDS-HGI-" + tmpElem.hashCode();
	      tmpElem.setAttributeNS(null, "Id", tmpElemId);
	      root.appendChild(tmpElem);
	      root.appendChild(doc.createTextNode("\n"));
	      
	      //The BaseURI is the URI that's used to prepend to relative URIs
	      String BaseURI = signatureFile.toURL().toString();
	      //Create an XML Signature object from the document, BaseURI and
	      //signature algorithm (in this case DSA)
	      XMLSignature sig = new XMLSignature(doc, BaseURI,
	                                          XMLSignature.ALGO_ID_SIGNATURE_DSA);


	     
	      
	      root.appendChild(sig.getElement());
	      sig.getSignedInfo()
	         .addResourceResolver(new org.apache.xml.security.samples.utils.resolver
	            .OfflineResolver());
	      root.appendChild(sig.getElement());

	      /*
	       * our Tests
	       */
	      
//	 		TimeStampType
	      	TimeStampType timestamptype = new TimeStampType(doc);
	      	

	      	 
	      	try { timestamptype.setHashDataInfo(null);
    		
	      	}catch(IllegalArgumentException e) {
	      		return;
	      	}      	
	}
	
	public void testNeg_CertID_CerDigest_Element_required() throws Exception{
	      Constants.setSignatureSpecNSprefix("");

	      File signatureFile = new File("signature_neg.xml");
	      javax.xml.parsers.DocumentBuilderFactory dbf =
	         javax.xml.parsers.DocumentBuilderFactory.newInstance();
	      //XML Signature needs to be namespace aware
	      dbf.setNamespaceAware(true);

	      javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
	      org.w3c.dom.Document doc = db.newDocument();


	      Element root = doc.createElementNS("http://www.apache.org/ns/#app1",
	                                         "nds:RootElement");

	      root.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:nds", "http://www.nds.rub.de/xades");
	      doc.appendChild(root);
	      
	      root.appendChild(doc.createTextNode("\n"));
	      Element tmpElem = doc.createElementNS("http://www.nds.rub.de/xades", "nds:AI-NDS-HGI");      
	      tmpElem.appendChild(doc.createTextNode("Some simple text"));
	      String tmpElemId = "AI-NDS-HGI-" + tmpElem.hashCode();
	      tmpElem.setAttributeNS(null, "Id", tmpElemId);
	      root.appendChild(tmpElem);
	      root.appendChild(doc.createTextNode("\n"));
	      
	      //The BaseURI is the URI that's used to prepend to relative URIs
	      String BaseURI = signatureFile.toURL().toString();
	      //Create an XML Signature object from the document, BaseURI and
	      //signature algorithm (in this case DSA)
	      XMLSignature sig = new XMLSignature(doc, BaseURI,
	                                          XMLSignature.ALGO_ID_SIGNATURE_DSA);


	     
	      
	      root.appendChild(sig.getElement());
	      sig.getSignedInfo()
	         .addResourceResolver(new org.apache.xml.security.samples.utils.resolver
	            .OfflineResolver());
	      root.appendChild(sig.getElement());
	      /*
	       * our Tests
	       */
 

//	 		XMLX509IssuerSerial
	      	IssuerSerial  IssuerSerial = new IssuerSerial(doc,signCert);

	      	try { 	CertID certid = new CertID(doc,null ,IssuerSerial);		      			
	      			} catch(IllegalArgumentException e) {
		      		return;
		      	}		      	
		      	fail("CertID Element is required!");	      		      		
	}
	
	public void testNeg_CertID_IssuerSerial_Element_required() throws Exception{
	      Constants.setSignatureSpecNSprefix("");

	      File signatureFile = new File("signature_neg.xml");
	      javax.xml.parsers.DocumentBuilderFactory dbf =
	         javax.xml.parsers.DocumentBuilderFactory.newInstance();
	      //XML Signature needs to be namespace aware
	      dbf.setNamespaceAware(true);

	      javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
	      org.w3c.dom.Document doc = db.newDocument();


	      Element root = doc.createElementNS("http://www.apache.org/ns/#app1",
	                                         "nds:RootElement");

	      root.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:nds", "http://www.nds.rub.de/xades");
	      doc.appendChild(root);
	      
	      root.appendChild(doc.createTextNode("\n"));
	      Element tmpElem = doc.createElementNS("http://www.nds.rub.de/xades", "nds:AI-NDS-HGI");      
	      tmpElem.appendChild(doc.createTextNode("Some simple text"));
	      String tmpElemId = "AI-NDS-HGI-" + tmpElem.hashCode();
	      tmpElem.setAttributeNS(null, "Id", tmpElemId);
	      root.appendChild(tmpElem);
	      root.appendChild(doc.createTextNode("\n"));
	      
	      //The BaseURI is the URI that's used to prepend to relative URIs
	      String BaseURI = signatureFile.toURL().toString();
	      //Create an XML Signature object from the document, BaseURI and
	      //signature algorithm (in this case DSA)
	      XMLSignature sig = new XMLSignature(doc, BaseURI,
	                                          XMLSignature.ALGO_ID_SIGNATURE_DSA);


	     
	      
	      root.appendChild(sig.getElement());
	      sig.getSignedInfo()
	         .addResourceResolver(new org.apache.xml.security.samples.utils.resolver
	            .OfflineResolver());
	      root.appendChild(sig.getElement());
	      /*
	       * our Tests
	       */
	      	
//			DigestAlgAndValue
	      	DigestAlgAndValue digestalgandvalue = new DigestAlgAndValue(doc);
	      	
	      	DigestAlgAndValue digestalgandlvalue = new DigestAlgAndValue(doc);
	      	 
	      	try { 	CertID certid = new CertID(doc,digestalgandvalue ,null);
	      			CertID certid1 = new CertID(doc,digestalgandlvalue ,null);
	      			} catch(IllegalArgumentException e) {
		      		return;
		      	}		      	
		      	fail("IssuerSerial Element is required!");	      		      		
	}
	
	public void testNeg_DigestAlgAndValueType_DigestMethod_Element_required() throws Exception{
	      Constants.setSignatureSpecNSprefix("");

	      File signatureFile = new File("signature_neg.xml");
	      javax.xml.parsers.DocumentBuilderFactory dbf =
	         javax.xml.parsers.DocumentBuilderFactory.newInstance();
	      //XML Signature needs to be namespace aware
	      dbf.setNamespaceAware(true);

	      javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
	      org.w3c.dom.Document doc = db.newDocument();


	      Element root = doc.createElementNS("http://www.apache.org/ns/#app1",
	                                         "nds:RootElement");

	      root.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:nds", "http://www.nds.rub.de/xades");
	      doc.appendChild(root);
	      
	      root.appendChild(doc.createTextNode("\n"));
	      Element tmpElem = doc.createElementNS("http://www.nds.rub.de/xades", "nds:AI-NDS-HGI");      
	      tmpElem.appendChild(doc.createTextNode("Some simple text"));
	      String tmpElemId = "AI-NDS-HGI-" + tmpElem.hashCode();
	      tmpElem.setAttributeNS(null, "Id", tmpElemId);
	      root.appendChild(tmpElem);
	      root.appendChild(doc.createTextNode("\n"));
	      
	      //The BaseURI is the URI that's used to prepend to relative URIs
	      String BaseURI = signatureFile.toURL().toString();
	      //Create an XML Signature object from the document, BaseURI and
	      //signature algorithm (in this case DSA)
	      XMLSignature sig = new XMLSignature(doc, BaseURI,
	                                          XMLSignature.ALGO_ID_SIGNATURE_DSA);


	     
	      
	      root.appendChild(sig.getElement());
	      sig.getSignedInfo()
	         .addResourceResolver(new org.apache.xml.security.samples.utils.resolver
	            .OfflineResolver());
	      root.appendChild(sig.getElement());
	      /*
	       * our Tests
	       */
 	
	      	
//			DigestAlgAndValue
	      	DigestAlgAndValue digestalgandvalue = new DigestAlgAndValue(doc);
	      	
	      	

	      	 
	      	try { 	digestalgandvalue.setDigestMethod(null);
	      			} catch(IllegalArgumentException e) {
		      		return;
		      	}		      	
		      	fail("DigestMethod Element is required!");	      		      		
	}
	
	public void testNeg_DigestAlgAndValueType_DigestValue_Element_required() throws Exception{
	      Constants.setSignatureSpecNSprefix("");

	      File signatureFile = new File("signature_neg.xml");
	      javax.xml.parsers.DocumentBuilderFactory dbf =
	         javax.xml.parsers.DocumentBuilderFactory.newInstance();
	      //XML Signature needs to be namespace aware
	      dbf.setNamespaceAware(true);

	      javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
	      org.w3c.dom.Document doc = db.newDocument();


	      Element root = doc.createElementNS("http://www.apache.org/ns/#app1",
	                                         "nds:RootElement");

	      root.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:nds", "http://www.nds.rub.de/xades");
	      doc.appendChild(root);
	      
	      root.appendChild(doc.createTextNode("\n"));
	      Element tmpElem = doc.createElementNS("http://www.nds.rub.de/xades", "nds:AI-NDS-HGI");      
	      tmpElem.appendChild(doc.createTextNode("Some simple text"));
	      String tmpElemId = "AI-NDS-HGI-" + tmpElem.hashCode();
	      tmpElem.setAttributeNS(null, "Id", tmpElemId);
	      root.appendChild(tmpElem);
	      root.appendChild(doc.createTextNode("\n"));
	      
	      //The BaseURI is the URI that's used to prepend to relative URIs
	      String BaseURI = signatureFile.toURL().toString();
	      //Create an XML Signature object from the document, BaseURI and
	      //signature algorithm (in this case DSA)
	      XMLSignature sig = new XMLSignature(doc, BaseURI,
	                                          XMLSignature.ALGO_ID_SIGNATURE_DSA);


	     
	      
	      root.appendChild(sig.getElement());
	      sig.getSignedInfo()
	         .addResourceResolver(new org.apache.xml.security.samples.utils.resolver
	            .OfflineResolver());
	      root.appendChild(sig.getElement());
	      /*
	       * our Tests
	       */
	
	      	
//			DigestAlgAndValue
	      	DigestAlgAndValue digestalgandvalue = new DigestAlgAndValue(doc);
	      	
	      	

	      	 
	      	try { 	digestalgandvalue.setDigestValue(null);
	      			} catch(IllegalArgumentException e) {
		      		return;
		      	}		      	
		      	fail("DigestValue Element is required!");	      		      		
	}
	
	public void testNeg_CommitmentTypeIndication_CommitmentTypeId_Element_required() throws Exception{
	      Constants.setSignatureSpecNSprefix("");

	      File signatureFile = new File("signature_neg.xml");
	      javax.xml.parsers.DocumentBuilderFactory dbf =
	         javax.xml.parsers.DocumentBuilderFactory.newInstance();
	      //XML Signature needs to be namespace aware
	      dbf.setNamespaceAware(true);

	      javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
	      org.w3c.dom.Document doc = db.newDocument();


	      Element root = doc.createElementNS("http://www.apache.org/ns/#app1",
	                                         "nds:RootElement");

	      root.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:nds", "http://www.nds.rub.de/xades");
	      doc.appendChild(root);
	      
	      root.appendChild(doc.createTextNode("\n"));
	      Element tmpElem = doc.createElementNS("http://www.nds.rub.de/xades", "nds:AI-NDS-HGI");      
	      tmpElem.appendChild(doc.createTextNode("Some simple text"));
	      String tmpElemId = "AI-NDS-HGI-" + tmpElem.hashCode();
	      tmpElem.setAttributeNS(null, "Id", tmpElemId);
	      root.appendChild(tmpElem);
	      root.appendChild(doc.createTextNode("\n"));
	      
	      //The BaseURI is the URI that's used to prepend to relative URIs
	      String BaseURI = signatureFile.toURL().toString();
	      //Create an XML Signature object from the document, BaseURI and
	      //signature algorithm (in this case DSA)
	      XMLSignature sig = new XMLSignature(doc, BaseURI,
	                                          XMLSignature.ALGO_ID_SIGNATURE_DSA);


	     
	      
	      root.appendChild(sig.getElement());
	      sig.getSignedInfo()
	         .addResourceResolver(new org.apache.xml.security.samples.utils.resolver
	            .OfflineResolver());
	      root.appendChild(sig.getElement());
	      /*
	       * our Tests
	       */

//			CommitmentTypeQualifiersList 
	      	CommitmentTypeQualifiersList commitmentTypeQualifiers = new CommitmentTypeQualifiersList(doc);
//			AllSignedDataObjects
	      	AllSignedDataObjects allsigneddataobjects = new AllSignedDataObjects(doc);	      	 
	      	try { 	
		      	CommitmentTypeIndication commitmenttypeindication = new CommitmentTypeIndication(doc ,null, allsigneddataobjects ,commitmentTypeQualifiers);
  			} catch(IllegalArgumentException e) {
      		return;
      	}		      	
      	fail("IssuerSerial Element is required!");	      	
	}
	public void testNeg_SignaturePolicyId_Element_required() throws Exception{
	      Constants.setSignatureSpecNSprefix("");

	      File signatureFile = new File("signature.xml");
	      javax.xml.parsers.DocumentBuilderFactory dbf =
	         javax.xml.parsers.DocumentBuilderFactory.newInstance();
	      //XML Signature needs to be namespace aware
	      dbf.setNamespaceAware(true);

	      javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
	      org.w3c.dom.Document doc = db.newDocument();


	      Element root = doc.createElementNS("http://www.apache.org/ns/#app1",
	                                         "nds:RootElement");

	      root.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:nds", "http://www.nds.rub.de/xades");
	      doc.appendChild(root);
	      
	      root.appendChild(doc.createTextNode("\n"));
	      Element tmpElem = doc.createElementNS("http://www.nds.rub.de/xades", "nds:AAA");      
	      tmpElem.appendChild(doc.createTextNode("Some simple text"));
	      String tmpElemId = "AAA-" + tmpElem.hashCode();
	      tmpElem.setAttributeNS(null, "Id", tmpElemId);
	      root.appendChild(tmpElem);
	      root.appendChild(doc.createTextNode("\n"));
	      
	      //The BaseURI is the URI that's used to prepend to relative URIs
	      String BaseURI = signatureFile.toURL().toString();
	      //Create an XML Signature object from the document, BaseURI and
	      //signature algorithm (in this case DSA)
	      XMLSignature sig = new XMLSignature(doc, BaseURI,
	                                          XMLSignature.ALGO_ID_SIGNATURE_DSA);


	     
	      
	      root.appendChild(sig.getElement());
	      sig.getSignedInfo()
	         .addResourceResolver(new org.apache.xml.security.samples.utils.resolver
	            .OfflineResolver());
	      root.appendChild(sig.getElement());
	      /*
	       * our Tests
	       */


//			SignaturePolicyIdentifier
	      	SignaturePolicyIdentifier signaturepolicyidentifier = new SignaturePolicyIdentifier(doc);
	 
	      	try { 	
	      		signaturepolicyidentifier.setSignaturePolicyId(null);
			} catch(IllegalArgumentException e) {
		return;
	}		      	
	fail("SignaturePolicyId Element is required!");	      	
	}
	
	public void testNeg_SigPolicyId_Element_required() throws Exception{
	      Constants.setSignatureSpecNSprefix("");

	      File signatureFile = new File("signature.xml");
	      javax.xml.parsers.DocumentBuilderFactory dbf =
	         javax.xml.parsers.DocumentBuilderFactory.newInstance();
	      //XML Signature needs to be namespace aware
	      dbf.setNamespaceAware(true);

	      javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
	      org.w3c.dom.Document doc = db.newDocument();


	      Element root = doc.createElementNS("http://www.apache.org/ns/#app1",
	                                         "nds:RootElement");

	      root.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:nds", "http://www.nds.rub.de/xades");
	      doc.appendChild(root);
	      
	      root.appendChild(doc.createTextNode("\n"));
	      Element tmpElem = doc.createElementNS("http://www.nds.rub.de/xades", "nds:AAA");      
	      tmpElem.appendChild(doc.createTextNode("Some simple text"));
	      String tmpElemId = "AAA-" + tmpElem.hashCode();
	      tmpElem.setAttributeNS(null, "Id", tmpElemId);
	      root.appendChild(tmpElem);
	      root.appendChild(doc.createTextNode("\n"));
	      
	      //The BaseURI is the URI that's used to prepend to relative URIs
	      String BaseURI = signatureFile.toURL().toString();
	      //Create an XML Signature object from the document, BaseURI and
	      //signature algorithm (in this case DSA)
	      XMLSignature sig = new XMLSignature(doc, BaseURI,
	                                          XMLSignature.ALGO_ID_SIGNATURE_DSA);


	     
	      
	      root.appendChild(sig.getElement());
	      sig.getSignedInfo()
	         .addResourceResolver(new org.apache.xml.security.samples.utils.resolver
	            .OfflineResolver());
	      root.appendChild(sig.getElement());
	      /*
	       * our Tests
	       */

//			SignaturePolicyIdType 
	      	
	      	SignaturePolicyIdType  signaturepolicyid = new SignaturePolicyIdType(doc);
    	 
	      	try { 	
	      		signaturepolicyid.setSigPolicyId(null);
			} catch(IllegalArgumentException e) {
  		return;
  	}		      	
  	fail("SigPolicyId Element is required!");	      	
	}
	
	public void testNeg_SigPolicyHash_Element_required() throws Exception{
	      Constants.setSignatureSpecNSprefix("");

	      File signatureFile = new File("signature.xml");
	      javax.xml.parsers.DocumentBuilderFactory dbf =
	         javax.xml.parsers.DocumentBuilderFactory.newInstance();
	      //XML Signature needs to be namespace aware
	      dbf.setNamespaceAware(true);

	      javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
	      org.w3c.dom.Document doc = db.newDocument();


	      Element root = doc.createElementNS("http://www.apache.org/ns/#app1",
	                                         "nds:RootElement");

	      root.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:nds", "http://www.nds.rub.de/xades");
	      doc.appendChild(root);
	      
	      root.appendChild(doc.createTextNode("\n"));
	      Element tmpElem = doc.createElementNS("http://www.nds.rub.de/xades", "nds:AAA");      
	      tmpElem.appendChild(doc.createTextNode("Some simple text"));
	      String tmpElemId = "AAA-" + tmpElem.hashCode();
	      tmpElem.setAttributeNS(null, "Id", tmpElemId);
	      root.appendChild(tmpElem);
	      root.appendChild(doc.createTextNode("\n"));
	      
	      //The BaseURI is the URI that's used to prepend to relative URIs
	      String BaseURI = signatureFile.toURL().toString();
	      //Create an XML Signature object from the document, BaseURI and
	      //signature algorithm (in this case DSA)
	      XMLSignature sig = new XMLSignature(doc, BaseURI,
	                                          XMLSignature.ALGO_ID_SIGNATURE_DSA);


	     
	      
	      root.appendChild(sig.getElement());
	      sig.getSignedInfo()
	         .addResourceResolver(new org.apache.xml.security.samples.utils.resolver
	            .OfflineResolver());
	      root.appendChild(sig.getElement());
	      /*
	       * our Tests
	       */


//			SignaturePolicyIdType 
	      	
	      	SignaturePolicyIdType  signaturepolicyid = new SignaturePolicyIdType(doc);
  	 
	      	try { 	
	      		signaturepolicyid.setSigPolicyHash(null);
			} catch(IllegalArgumentException e) {
		return;
	}		      	
	fail("SigPolicyHash Element is required!");	      	
	}
	
}

