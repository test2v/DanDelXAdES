
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
import org.apache.xml.security.keys.content.keyvalues.RSAKeyValue;
import org.apache.xml.security.keys.keyresolver.KeyResolverSpi;
import org.apache.xml.security.keys.storage.StorageResolver;
import org.apache.xml.security.utils.Constants;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Element;


/**
 *
 * @author $Author: raul $
 */
public class RSAKeyValueResolver extends KeyResolverSpi {

   /** {@link org.apache.commons.logging} logging facility */
    static org.apache.commons.logging.Log log = 
        org.apache.commons.logging.LogFactory.getLog(
                        RSAKeyValueResolver.class.getName());

   /** Field _rsaKeyElement */
   private Element _rsaKeyElement = null;

   /** @inheritDoc */
   public boolean engineCanResolve(Element element, String BaseURI,
                                   StorageResolver storage) {
   	  if (log.isDebugEnabled())
   	  	log.debug("Can I resolve " + element.getTagName());

      if (element == null) {
         return false;
      }

      boolean isKeyValue = XMLUtils.elementIsInSignatureSpace(element,
                              Constants._TAG_KEYVALUE);
      boolean isRSAKeyValue = XMLUtils.elementIsInSignatureSpace(element,
                                 Constants._TAG_RSAKEYVALUE);

      if (isKeyValue) {                  
            this._rsaKeyElement = XMLUtils.selectDsNode(element.getFirstChild(),
                    Constants._TAG_RSAKEYVALUE, 0);

            if (this._rsaKeyElement != null) {
               return true;
            }         
      } else if (isRSAKeyValue) {

         // this trick is needed to allow the RetrievalMethodResolver to eat a
         // ds:RSAKeyValue directly (without KeyValue)
         this._rsaKeyElement = element;

         return true;
      }

      return false;
   }

   /** @inheritDoc */
   public PublicKey engineResolvePublicKey(
           Element element, String BaseURI, StorageResolver storage) {

      if (this._rsaKeyElement == null) {
         boolean weCanResolve = this.engineCanResolve(element, BaseURI,
                                   storage);

         if (!weCanResolve || (this._rsaKeyElement == null)) {
            return null;
         }
      }

      try {
         RSAKeyValue rsaKeyValue = new RSAKeyValue(this._rsaKeyElement,
                                                   BaseURI);

         return rsaKeyValue.getPublicKey();
      } catch (XMLSecurityException ex) {
         log.debug("XMLSecurityException", ex);
      }

      return null;
   }

   /** @inheritDoc */
   public X509Certificate engineResolveX509Certificate(
           Element element, String BaseURI, StorageResolver storage) {
      return null;
   }

   /** @inheritDoc */
   public javax.crypto.SecretKey engineResolveSecretKey(
           Element element, String BaseURI, StorageResolver storage) {
      return null;
   }
}
