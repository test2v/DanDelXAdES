
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
package org.apache.xml.security.keys.keyresolver.implementations;



import java.security.PublicKey;
import java.security.cert.X509Certificate;


import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.keys.content.x509.XMLX509Certificate;
import org.apache.xml.security.keys.keyresolver.KeyResolverException;
import org.apache.xml.security.keys.keyresolver.KeyResolverSpi;
import org.apache.xml.security.keys.storage.StorageResolver;
import org.apache.xml.security.utils.Constants;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Element;


/**
 * Resolves Certificates which are directly contained inside a
 * <CODE>ds:X509Certificate</CODE> Element.
 *
 * @author $Author: raul $
 */
public class X509CertificateResolver extends KeyResolverSpi {

   /** {@link org.apache.commons.logging} logging facility */
    static org.apache.commons.logging.Log log = 
        org.apache.commons.logging.LogFactory.getLog(X509CertificateResolver.class.getName());

   /** Field _dsaKeyElement */
   Element[] _x509CertKeyElements = null;

   /**
    * Method engineCanResolve
    * @inheritDoc
    * @param element
    * @param BaseURI
    * @param storage
    *
    */
   public boolean engineCanResolve(Element element, String BaseURI,
                                   StorageResolver storage) {
   	  if (log.isDebugEnabled())
   	  	log.debug("Can I resolve " + element.getTagName() + "?");

      if (!XMLUtils.elementIsInSignatureSpace(element,
                 Constants._TAG_X509DATA)) {
         log.debug("I can't");

         return false;
      }
         

         this._x509CertKeyElements = XMLUtils.selectDsNodes(element.getFirstChild(),
                 Constants._TAG_X509CERTIFICATE);

         if ((this._x509CertKeyElements != null)
                 && (this._x509CertKeyElements.length > 0)) {
            log.debug("Yes Sir, I can");

            return true;
         }

      log.debug("I can't");

      return false;
   }

   /** Field _x509certObject[] */
   XMLX509Certificate _x509certObject[] = null;

   /**
    * Method engineResolvePublicKey
    * @inheritDoc
    * @param element
    * @param BaseURI
    * @param storage
    *
    * @throws KeyResolverException
    */
   public PublicKey engineResolvePublicKey(
           Element element, String BaseURI, StorageResolver storage)
              throws KeyResolverException {

      X509Certificate cert = this.engineResolveX509Certificate(element,
                                BaseURI, storage);

      if (cert != null) {
         return cert.getPublicKey();
      }

      return null;
   }

   /**
    * Method engineResolveX509Certificate
    * @inheritDoc
    * @param element
    * @param BaseURI
    * @param storage
    *
    * @throws KeyResolverException
    */
   public X509Certificate engineResolveX509Certificate(
           Element element, String BaseURI, StorageResolver storage)
              throws KeyResolverException {

      try {
         if ((this._x509CertKeyElements == null)
                 || (this._x509CertKeyElements.length == 0)) {
            boolean weCanResolve = this.engineCanResolve(element, BaseURI,
                                      storage);

            if (!weCanResolve || (this._x509CertKeyElements == null)
                    || (this._x509CertKeyElements.length == 0)) {
               return null;
            }
         }

         this._x509certObject =
            new XMLX509Certificate[this._x509CertKeyElements.length];

         // populate Object array
         for (int i = 0; i < this._x509CertKeyElements.length; i++) {
            this._x509certObject[i] =
               new XMLX509Certificate(this._x509CertKeyElements[i]
                  , BaseURI);
         }

         for (int i = 0; i < this._x509certObject.length; i++) {
            X509Certificate cert = this._x509certObject[i].getX509Certificate();

            if (cert != null) {
               return cert;
            }
         }

         return null;
      } catch (XMLSecurityException ex) {
         log.debug("XMLSecurityException", ex);

         throw new KeyResolverException("generic.EmptyMessage", ex);
      }
   }

   /**
    * Method engineResolveSecretKey
    * @inheritDoc
    * @param element
    * @param BaseURI
    * @param storage
    *
    */
   public javax.crypto.SecretKey engineResolveSecretKey(
           Element element, String BaseURI, StorageResolver storage)
   {
      return null;
   }
}
