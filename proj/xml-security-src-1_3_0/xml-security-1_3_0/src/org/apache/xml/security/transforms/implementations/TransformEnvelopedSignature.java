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
package org.apache.xml.security.transforms.implementations;



import org.apache.xml.security.signature.NodeFilter;
import org.apache.xml.security.signature.XMLSignatureInput;
import org.apache.xml.security.transforms.TransformSpi;
import org.apache.xml.security.transforms.TransformationException;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.utils.Constants;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


/**
 * Implements the <CODE>http://www.w3.org/2000/09/xmldsig#enveloped-signature</CODE>
 * transform.
 *
 * @author Christian Geuer-Pollmann
 */
public class TransformEnvelopedSignature extends TransformSpi {

   /** Field implementedTransformURI */
   public static final String implementedTransformURI =
      Transforms.TRANSFORM_ENVELOPED_SIGNATURE;

   /**
    * Method engineGetURI
    *
    * @inheritDoc
    */
   protected String engineGetURI() {
      return implementedTransformURI;
   }

   /**
    * @inheritDoc
    */
   protected XMLSignatureInput enginePerformTransform(XMLSignatureInput input)
           throws TransformationException {



         /**
          * If the actual input is an octet stream, then the application MUST
          * convert the octet stream to an XPath node-set suitable for use by
          * Canonical XML with Comments. (A subsequent application of the
          * REQUIRED Canonical XML algorithm would strip away these comments.)
          *
          * ...
          *
          * The evaluation of this expression includes all of the document's nodes
          * (including comments) in the node-set representing the octet stream.
          */

         /*
         if (input.isOctetStream()) {
            input.setNodesetXPath(Canonicalizer.XPATH_C14N_WITH_COMMENTS);
         }
         */
         
         Element transformElement = this._transformObject.getElement();
         Node signatureElement = transformElement;
         

         signatureElement = searchSignatureElement(signatureElement);        
         	input.setExcludeNode(signatureElement);   
         	input.addNodeFilter(new EnvelopedNodeFilter(signatureElement));
         	return input;
         
         //
         
      
   }

   /**
    * @param signatureElement    
    * @return the node that is the signature
    * @throws TransformationException
    */
    private static Node searchSignatureElement(Node signatureElement) throws TransformationException {
	    boolean found=false;
        
	    while (true) {
	    	if ((signatureElement == null)
	            || (signatureElement.getNodeType() == Node.DOCUMENT_NODE)) {
	    		break;
	    	}
	    	Element el=(Element)signatureElement;
	    	if (el.getNamespaceURI().equals(Constants.SignatureSpecNS)
                    && 
	               el.getLocalName().equals(Constants._TAG_SIGNATURE)) {
	    		found = true;
	    		break;
	    	}

	    	signatureElement = signatureElement.getParentNode();
	    }

	    if (!found) {
	      throw new TransformationException(
	       "envelopedSignatureTransformNotInSignatureElement");
	    }
	    return signatureElement;
    }
    class EnvelopedNodeFilter implements NodeFilter {
    	Node exclude;    	
    	EnvelopedNodeFilter(Node n) {
    		exclude=n;
    	}
		/**
		 * @see org.apache.xml.security.signature.NodeFilter#isNodeInclude(org.w3c.dom.Node)
		 */
		public boolean isNodeInclude(Node n) {
			// TODO Optimize me.
			return !XMLUtils.isDescendantOrSelf(exclude,n);
		}
    	
    }
}
