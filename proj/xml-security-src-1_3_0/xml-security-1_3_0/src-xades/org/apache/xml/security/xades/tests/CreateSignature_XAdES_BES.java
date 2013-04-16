/*
 * Copyright  1999-2004 The Apache Software Foundation.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package org.apache.xml.security.xades.tests;




import org.apache.xml.security.signature.ObjectContainer;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.utils.Constants;
import org.apache.xml.security.utils.XMLUtils;
import java.security.cert.X509Certificate;
import java.io.FileOutputStream;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.io.FileInputStream;
import java.security.KeyStore;
import org.w3c.dom.Element;
import java.util.Date;
import java.io.File;

import org.apache.xml.security.xades.sp.Transforms1;
import org.apache.xml.security.xades.IssuerSerial;
import org.apache.xml.security.xades.QualifyingProperties;
import org.apache.xml.security.xades.sp.SignaturePolicyIdentifier;
import org.apache.xml.security.xades.sp.SignaturePolicyIdType;
import org.apache.xml.security.xades.sp.SigPolicyQualifiersListType;
import org.apache.xml.security.xades.sp.SignedProperties;
import org.apache.xml.security.xades.sp.SignedSignatureProperties;
import org.apache.xml.security.xades.sp.SigningCertificate;
import org.apache.xml.security.xades.sp.CertIDList;
import org.apache.xml.security.xades.sp.CertID;
import org.apache.xml.security.xades.sp.DigestAlgAndValue;
import org.apache.xml.security.xades.sp.DigestMethod;
import org.apache.xml.security.xades.sp.SigningTime;
import org.apache.xml.security.xades.sp.ObjectIdentifier;


/**
 *
 *
 * Editors: Psycho_core,mwnnj$
 */


public class CreateSignature_XAdES_BES {

	/** {@link org.apache.commons.logging} logging facility */
    static org.apache.commons.logging.Log log = 
        org.apache.commons.logging.LogFactory.getLog(CreateSignatureSignedSignatureProperties.class.getName());

   /**
    * Method main
    *
    * @param unused
    * @throws Exception
    */
   public static void main(String unused[]) throws Exception {
      
	  Constants.setSignatureSpecNSprefix("");

      //J-
      //All the parameters for the keystore
      String keystoreType = "JKS";
      String keystoreFile = "src-xades/input/keystore.jks";
      String keystorePass = "xmlsecurity";
      String privateKeyAlias = "test";
      String privateKeyPass = "xmlsecurity";
      String certificateAlias = "test";
      File signatureFile = new File("signature1.xml");
      //J+
      KeyStore ks = KeyStore.getInstance(keystoreType);
      FileInputStream fis = new FileInputStream(keystoreFile);

      //load the keystore
      ks.load(fis, keystorePass.toCharArray());

      //get the private key for signing.
   PrivateKey privateKey = (PrivateKey) ks.getKey(privateKeyAlias,
                                             privateKeyPass.toCharArray());
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

      
//   	Transforms
 		Transforms1 transforms3 = new Transforms1(doc);  
         		

 		
//	    DigestMethod
      
      	DigestMethod digestmethod = new DigestMethod(doc);
      
      	DigestMethod digestlmethod = new DigestMethod(doc);
      	

        //Add in the KeyInfo for the certificate that we used the private key of
        X509Certificate cert =
           (X509Certificate) ks.getCertificate(certificateAlias);

        sig.addKeyInfo(cert);
        sig.addKeyInfo(cert.getPublicKey());     	
// 		XMLX509IssuerSerial

      	IssuerSerial  issuerserial =  new IssuerSerial(doc,cert);


      	
//      Transforms
      	Transforms transforms = new Transforms(doc); 
      	   	


//		CommitmentTypeId 
     	ObjectIdentifier sidpolicyididentifier= new ObjectIdentifier(doc);

  
      	
//		SigPolicyQualifiersListType
      	
      	SigPolicyQualifiersListType  sigpolicyqualifiers  = new SigPolicyQualifiersListType(doc);
      	
      	
//		SignaturePolicyIdType 
      	
      	SignaturePolicyIdType  signaturepolicyid = new SignaturePolicyIdType(doc);

//		SignaturePolicyIdentifier
      	SignaturePolicyIdentifier signaturepolicyidentifier = new SignaturePolicyIdentifier(doc);
      	
      	
//		DigestAlgAndValue
      	DigestAlgAndValue digestalgandvalue = new DigestAlgAndValue(doc);
      	
      	DigestAlgAndValue digestalgandlvalue = new DigestAlgAndValue(doc);
      	
// 		CertID 
      	CertID certid = new CertID(doc);
      	
// 		CertIDList 
      	CertIDList certidlist = new CertIDList(doc);
      	
//		SigningCertificate
      	SigningCertificate signingcertificate = new SigningCertificate(doc);
      
//   	SigningTime
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
      	sidpolicyididentifier.setIdentifier("URN:OID:0.9.2342.19200300.100.4/");
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
         

             sig.sign(privateKey);
             
          
         //Add the above Document/Reference
         FileOutputStream f = new FileOutputStream(signatureFile);

         XMLUtils.outputDOMc14nWithComments(doc, f);

         f.close();
         System.out.println("Wrote signature to " + BaseURI);
   }

   static {
      org.apache.xml.security.Init.init();
   }
}

