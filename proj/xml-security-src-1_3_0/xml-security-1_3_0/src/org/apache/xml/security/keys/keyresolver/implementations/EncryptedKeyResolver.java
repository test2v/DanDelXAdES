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

import java.security.Key;
import java.security.PublicKey;
import java.security.cert.X509Certificate;

import javax.crypto.SecretKey;

import org.apache.xml.security.encryption.EncryptedKey;
import org.apache.xml.security.encryption.XMLCipher;
import org.apache.xml.security.keys.keyresolver.KeyResolverSpi;
import org.apache.xml.security.keys.storage.StorageResolver;
import org.apache.xml.security.utils.EncryptionConstants;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Element;


/**
 * The <code>EncryptedKeyResolver</code> is not a generic resolver.  It can 
 * only be for specific instantiations, as the key being unwrapped will 
 * always be of a particular type and will always have been wrapped by 
 * another key which needs to be recursively resolved.
 *
 * The <code>EncryptedKeyResolver</code> can therefore only be instantiated
 * with an algorithm.  It can also be instantiated with a key (the KEK) or 
 * will search the static KeyResolvers to find the appropriate key.
 *
 * @author Berin Lautenbach
 */

public class EncryptedKeyResolver extends KeyResolverSpi {

	/** {@link org.apache.commons.logging} logging facility */
    static org.apache.commons.logging.Log log = 
        org.apache.commons.logging.LogFactory.getLog(
                        RSAKeyValueResolver.class.getName());

	
	Key _key;
	Key _kek;
	String _algorithm;

	/**
	 * Constructor for use when a KEK needs to be derived from a KeyInfo
	 * list
	 * @param algorithm
	 */
	public EncryptedKeyResolver(String algorithm) {
		_key = null;
		_kek = null;
        _algorithm=algorithm;
	}

	/**
	 * Constructor used for when a KEK has been set
	 * @param algorithm
	 * @param kek
	 */

	public EncryptedKeyResolver(String algorithm, Key kek) {
		_key = null;
		_algorithm = algorithm;
		_kek = kek;

	}

	/**
	 * Method engineCanResolve
	 *
	 * @param element
	 * @param BaseURI
	 * @param storage
	 * @return true if can resolve the key in the element
	 *
	 */

	public boolean engineCanResolve(Element element, String BaseURI,
                                   StorageResolver storage) {
	  if (log.isDebugEnabled())
	  	log.debug("EncryptedKeyResolver - Can I resolve " + element.getTagName());

      if (element == null) {
         return false;
      }

      boolean isEncryptedKey = XMLUtils.elementIsInEncryptionSpace(element,
                              EncryptionConstants._TAG_ENCRYPTEDKEY);

      if (isEncryptedKey) {
		  log.debug("Passed an Encrypted Key");
		  try {
			  XMLCipher cipher = XMLCipher.getInstance();
			  cipher.init(XMLCipher.UNWRAP_MODE, _kek);
			  EncryptedKey ek = cipher.loadEncryptedKey(element);
			  _key = cipher.decryptKey(ek, _algorithm);
		  }
		  catch (Exception e) {}
      }
	  
      return (_key != null);
   }

    /** @inheritDoc */
   public PublicKey engineResolvePublicKey(
           Element element, String BaseURI, StorageResolver storage) {

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
      return (SecretKey) _key;
   }
}
