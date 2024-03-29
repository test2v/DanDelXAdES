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
package org.apache.xml.security.xades;



import java.math.BigInteger;
import java.security.cert.X509Certificate;

import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.keys.content.x509.XMLX509DataContent;
import org.apache.xml.security.utils.Constants;
import org.apache.xml.security.utils.RFC2253Parser;
import org.apache.xml.security.utils.SignatureElementProxy;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 * <pre>&lt;xsd:element name="IssuerSerial" type="ds:X509IssuerSerialType"/>
  	&lt;xsd:complexType name="X509IssuerSerialType">
  			&lt;xsd:sequence>
    				&lt;xsd:element name="X509IssuerName" type="string"/>
    				&lt;xsd:element name="X509SerialNumber" type="integer"/>
  			&lt;/xsd:sequence>
	&lt;/xsd:complexType></pre>

  
  
 *
 * @author $Author: raul $
 * @author Edited by Psycho_core(nqkoi_ot_bg@yahoo.com),mwnnj(krassen.deltchev@rub.de)
 */
public class IssuerSerial extends SignatureElementProxy
        implements XMLX509DataContent {

   /** {@link org.apache.commons.logging} logging facility */
    static org.apache.commons.logging.Log log = 
        org.apache.commons.logging.LogFactory.getLog(
                    IssuerSerial.class.getName());

   /**
    * Constructor XMLX509IssuerSerial
    *
    * @param element
    * @param BaseURI
    * @throws XMLSecurityException
    */
   public IssuerSerial(Element element, String BaseURI)
           throws XMLSecurityException {
      super(element, BaseURI);
   }

   /**
    * Constructor XMLX509IssuerSerial
    *
    * @param doc
    * @param X509IssuerName
    * @param X509SerialNumber
    */
   public IssuerSerial(Document doc, String X509IssuerName,
                              BigInteger X509SerialNumber) {

      super(doc);

      XMLUtils.addReturnToElement(this._constructionElement);
      this.addTextElement(X509IssuerName, Constants._TAG_X509ISSUERNAME);
      XMLUtils.addReturnToElement(this._constructionElement);
      this.addTextElement(X509SerialNumber.toString(), Constants._TAG_X509SERIALNUMBER);
   }

   /**
    * Constructor XMLX509IssuerSerial
    *
    * @param doc
    * @param X509IssuerName
    * @param X509SerialNumber
    */
   public IssuerSerial(Document doc, String X509IssuerName,
                              String X509SerialNumber) {
      this(doc, X509IssuerName, new BigInteger(X509SerialNumber));
   }

   /**
    * Constructor XMLX509IssuerSerial
    *
    * @param doc
    * @param X509IssuerName
    * @param X509SerialNumber
    */
   public IssuerSerial(Document doc, String X509IssuerName,
                              int X509SerialNumber) {
      this(doc, X509IssuerName,
           new BigInteger(Integer.toString(X509SerialNumber)));
   }

   /**
    * Constructor XMLX509IssuerSerial
    *
    * @param doc
    * @param x509certificate
    */
   public IssuerSerial(Document doc, X509Certificate x509certificate) {

      this(doc,
           RFC2253Parser.normalize(x509certificate.getIssuerDN().getName()),
           x509certificate.getSerialNumber());
   }

   /**
    * Method getSerialNumber
    *
    *
    * @return the serial number
    */
   public BigInteger getSerialNumber() {

      String text =
         this.getTextFromChildElement(Constants._TAG_X509SERIALNUMBER,
                                      Constants.SignatureSpecNS);
      if (log.isDebugEnabled())
      	log.debug("In dem X509SerialNumber wurde gefunden: " + text);

      return new BigInteger(text);
   }

   /**
    * Method getSerialNumberInteger
    *
    *
    * @return the serial number as plain int
    */
   public int getSerialNumberInteger() {
      return this.getSerialNumber().intValue();
   }

   /**
    * Method getIssuerName
    *
    *
    * @return the issuer name
    */
   public String getIssuerName()  {

      return RFC2253Parser
         .normalize(this
            .getTextFromChildElement(Constants._TAG_X509ISSUERNAME,
                                     Constants.SignatureSpecNS));
   }

   /** @inheritDoc */
   public boolean equals(Object obj) {

      if (!obj.getClass().getName().equals(this.getClass().getName())) {
         return false;
      }

      IssuerSerial other = (IssuerSerial) obj;


      if (other.getSerialNumber().equals(this.getSerialNumber())
                 && other.getIssuerName().equals(this.getIssuerName())) {
           return true;
      }

       return false;      
   }

   /** @inheritDoc */
   public String getBaseLocalName() {
      return Constants._TAG_ISSUERSERIAL;
   }
}
