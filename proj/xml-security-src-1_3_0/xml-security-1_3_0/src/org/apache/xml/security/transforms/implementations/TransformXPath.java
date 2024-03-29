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



import javax.xml.transform.TransformerException;

import org.apache.xml.security.exceptions.XMLSecurityRuntimeException;
import org.apache.xml.security.signature.NodeFilter;
import org.apache.xml.security.signature.XMLSignatureInput;
import org.apache.xml.security.transforms.TransformSpi;
import org.apache.xml.security.transforms.TransformationException;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.utils.CachedXPathAPIHolder;
import org.apache.xml.security.utils.CachedXPathFuncHereAPI;
import org.apache.xml.security.utils.Constants;
import org.apache.xml.security.utils.XMLUtils;
import org.apache.xml.utils.PrefixResolverDefault;
import org.apache.xpath.objects.XObject;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


/**
 * Class TransformXPath
 *
 * Implements the <CODE>http://www.w3.org/TR/1999/REC-xpath-19991116</CODE>
 * transform.
 *
 * @author Christian Geuer-Pollmann
 * @see <a href="http://www.w3.org/TR/1999/REC-xpath-19991116">XPath</a>
 *
 */
public class TransformXPath extends TransformSpi {

   /** {@link org.apache.commons.logging} logging facility */
    static org.apache.commons.logging.Log log = 
        org.apache.commons.logging.LogFactory.getLog(TransformXPath.class.getName());

   /** Field implementedTransformURI */
   public static final String implementedTransformURI =
      Transforms.TRANSFORM_XPATH;


   /**
    * Method engineGetURI
    *
    * @inheritDoc
    */
   protected String engineGetURI() {
      return implementedTransformURI;
   }

   /**
    * Method enginePerformTransform
    * @inheritDoc
    * @param input
    *
    * @throws TransformationException
    */
   protected XMLSignatureInput enginePerformTransform(XMLSignatureInput input)
           throws TransformationException {

      try {

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
		  CachedXPathAPIHolder.setDoc(this._transformObject.getElement().getOwnerDocument());
         
         

         Element xpathElement =XMLUtils.selectDsNode(
            this._transformObject.getElement().getFirstChild(),
               Constants._TAG_XPATH,0);

         if (xpathElement == null) {
            Object exArgs[] = { "ds:XPath", "Transform" };

            throw new TransformationException("xml.WrongContent", exArgs);
         }
         Node xpathnode = xpathElement.getChildNodes().item(0);
         String str=CachedXPathFuncHereAPI.getStrFromNode(xpathnode);
         input.setNeedsToBeExpanded(needsCircunvent(str));
         if (xpathnode == null) {
     	    throw new DOMException(DOMException.HIERARCHY_REQUEST_ERR,
     	                           "Text must be in ds:Xpath");
     	 }


         input.addNodeFilter(new XPathNodeFilter( xpathElement, xpathnode, str));
         input.setNodeSet(true);
         return input;
      } catch (DOMException ex) {
         throw new TransformationException("empty", ex);
      } 
   }

   /**
    *  @param str
    * @return true if needs to be circunvent for bug.
    */
    private boolean needsCircunvent(String str) {
    	return true;
    	//return str.contains("namespace");
    	
    }
    class XPathNodeFilter implements NodeFilter {
    	 PrefixResolverDefault prefixResolver;
    	 CachedXPathFuncHereAPI xPathFuncHereAPI =
             new CachedXPathFuncHereAPI(CachedXPathAPIHolder.getCachedXPathAPI());
          ;
    	Node xpathnode; 
    	String str;
    	XPathNodeFilter(Element xpathElement,
    			Node xpathnode, String str) {
    		this.xpathnode=xpathnode;
    		this.str=str;
    		prefixResolver =new PrefixResolverDefault(xpathElement);
    	}
    	    

		/**
		 * @see org.apache.xml.security.signature.NodeFilter#isNodeInclude(org.w3c.dom.Node)
		 */
		public boolean isNodeInclude(Node currentNode) {			
			XObject includeInResult;
			try {
				includeInResult = xPathFuncHereAPI.eval(currentNode,
				        xpathnode, str,prefixResolver);
				return includeInResult.bool();
			} catch (TransformerException e) {
                Object[] eArgs = {currentNode};
				throw new XMLSecurityRuntimeException("signature.Transform.node", eArgs, e);
			}	
			catch (Exception e) {
                Object[] eArgs = {currentNode, new Short(currentNode.getNodeType())};
				throw new XMLSecurityRuntimeException("signature.Transform.nodeAndType",eArgs, e);
			}
		}
    }
}
