/*
 * Copyright  2003-2004 The Apache Software Foundation.
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

package org.apache.xml.security.encryption;

import java.io.IOException;

import org.apache.xml.security.c14n.CanonicalizationException;
import org.apache.xml.security.utils.resolver.ResourceResolver;
import org.apache.xml.security.utils.resolver.ResourceResolverException;
import org.apache.xml.security.exceptions.Base64DecodingException;
import org.apache.xml.security.signature.XMLSignatureInput;
import org.apache.xml.security.transforms.TransformationException;
import org.w3c.dom.Attr;
import org.apache.xml.security.utils.Base64;


/**
 * <code>XMLCipherInput</code> is used to wrap input passed into the
 * XMLCipher encryption operations.
 *
 * In decryption mode, it takes a <code>CipherData</code> object and allows
 * callers to dereference the CipherData into the encrypted bytes that it
 * actually represents.  This takes care of all base64 encoding etc.
 *
 * While primarily an internal class, this can be used by applications to
 * quickly and easily retrieve the encrypted bytes from an EncryptedType
 * object
 *
 * @author Berin Lautenbach
 */
public class XMLCipherInput {

    private static org.apache.commons.logging.Log logger = 
        org.apache.commons.logging.LogFactory.getLog(XMLCipher.class.getName());

	/** The data we are working with */
	private CipherData _cipherData;

	/** MODES */
	private int _mode;

	/**
	 * Constructor for processing encrypted octets
	 *
	 * @param data The <code>CipherData</code> object to read the bytes from
	 * @throws XMLEncryptionException {@link XMLEncryptionException}
	 */

	public XMLCipherInput(CipherData data) throws XMLEncryptionException {

		_cipherData = data;
		_mode = XMLCipher.DECRYPT_MODE;
		if (_cipherData == null) {
			throw new XMLEncryptionException("CipherData is null");
		}

	}

	/**
	 * Constructor for processing encrypted octets
	 *
	 * @param input The <code>EncryptedType</code> object to read 
	 * the bytes from.
	 * @throws XMLEncryptionException {@link XMLEncryptionException}
	 */

	public XMLCipherInput(EncryptedType input) throws XMLEncryptionException {

		_cipherData = ((input == null) ? null : input.getCipherData());
		_mode = XMLCipher.DECRYPT_MODE;
		if (_cipherData == null) {
			throw new XMLEncryptionException("CipherData is null");
		}

	}

	/**
	 * Dereferences the input and returns it as a single byte array.
	 *
	 * @throws XMLEncryptionException
     * @return The decripted bytes.
	 */

	public byte[] getBytes() throws XMLEncryptionException {

		if (_mode == XMLCipher.DECRYPT_MODE) {
			return getDecryptBytes();
		}
		return null;
	}

	/**
	 * Internal method to get bytes in decryption mode
     * @return the decripted bytes
     * @throws XMLEncryptionException
	 */

	private byte[] getDecryptBytes() throws XMLEncryptionException {

		String base64EncodedEncryptedOctets = null;

        if (_cipherData.getDataType() == CipherData.REFERENCE_TYPE) {
			// Fun time!
			logger.debug("Found a reference type CipherData");
			CipherReference cr = _cipherData.getCipherReference();

			// Need to wrap the uri in an Attribute node so that we can
			// Pass to the resource resolvers

			Attr uriAttr = cr.getURIAsAttr();
			XMLSignatureInput input = null;

			try {
				ResourceResolver resolver = 
					ResourceResolver.getInstance(uriAttr, null);
				input = resolver.resolve(uriAttr, null);
			} catch (ResourceResolverException ex) {
				throw new XMLEncryptionException("empty", ex);
			} 

			if (input != null) {
				logger.debug("Managed to resolve URI \"" + cr.getURI() + "\"");
			}
			else {
				logger.debug("Failed to resolve URI \"" + cr.getURI() + "\"");
			}
		
			// Lets see if there are any transforms
			Transforms transforms = cr.getTransforms();
			if (transforms != null) {
				logger.debug ("Have transforms in cipher reference");
				try {
 				    org.apache.xml.security.transforms.Transforms dsTransforms =
						transforms.getDSTransforms();
				    input =	dsTransforms.performTransforms(input);
				} catch (TransformationException ex) {
					throw new XMLEncryptionException("empty", ex);
				}
			}

			try {
				return input.getBytes();
			}
			catch (IOException ex) {
				throw new XMLEncryptionException("empty", ex);
			} catch (CanonicalizationException ex) {
				throw new XMLEncryptionException("empty", ex);
			}
			
            // retrieve the cipher text
        } else if (_cipherData.getDataType() == CipherData.VALUE_TYPE) {
            CipherValue cv = _cipherData.getCipherValue();
            base64EncodedEncryptedOctets = new String(cv.getValue());
        } else {
			throw new XMLEncryptionException("CipherData.getDataType() returned unexpected value");
		}

        logger.debug("Encrypted octets:\n" + base64EncodedEncryptedOctets);

        byte[] encryptedBytes = null;

        try {
			encryptedBytes = Base64.decode(base64EncodedEncryptedOctets);
        } catch (Base64DecodingException bde) {
            throw new XMLEncryptionException("empty", bde);
        }

		return (encryptedBytes);

	}

}


