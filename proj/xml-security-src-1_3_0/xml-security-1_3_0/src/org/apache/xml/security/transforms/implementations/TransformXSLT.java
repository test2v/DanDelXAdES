
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



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.XMLSignatureInput;
import org.apache.xml.security.transforms.TransformSpi;
import org.apache.xml.security.transforms.TransformationException;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.utils.XMLUtils;
import org.w3c.dom.Element;


/**
 * Class TransformXSLT
 *
 * Implements the <CODE>http://www.w3.org/TR/1999/REC-xslt-19991116</CODE>
 * transform.
 *
 * @author Christian Geuer-Pollmann
 */
public class TransformXSLT extends TransformSpi {

   /** Field implementedTransformURI */
   public static final String implementedTransformURI =
      Transforms.TRANSFORM_XSLT;
   //J-
   static final String XSLTSpecNS              = "http://www.w3.org/1999/XSL/Transform";
   static final String defaultXSLTSpecNSprefix = "xslt";
   static final String XSLTSTYLESHEET          = "stylesheet";


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
    * 
    * @param input the input for this transform
    * @return the result of this Transform
    * @throws IOException
    * @throws TransformationException
    */
   protected XMLSignatureInput enginePerformTransform(XMLSignatureInput input)
           throws IOException,
                  TransformationException {
   	return enginePerformTransform(input,null);
   }
    protected XMLSignatureInput enginePerformTransform(XMLSignatureInput input,OutputStream baos)
    throws IOException,
           TransformationException {
      try {
         Element transformElement = this._transformObject.getElement();        

         Element _xsltElement =
            XMLUtils.selectNode(transformElement.getFirstChild(),
                                                XSLTSpecNS,"stylesheet", 0);

         if (_xsltElement == null) {
            Object exArgs[] = { "xslt:stylesheet", "Transform" };

            throw new TransformationException("xml.WrongContent", exArgs);
         }

         TransformerFactory tFactory = TransformerFactory.newInstance();

         /*
          * This transform requires an octet stream as input. If the actual
          * input is an XPath node-set, then the signature application should
          * attempt to convert it to octets (apply Canonical XML]) as described
          * in the Reference Processing Model (section 4.3.3.2).
          */
         Source xmlSource =
            new StreamSource(new ByteArrayInputStream(input.getBytes()));
         Source stylesheet;

         /*
          * This complicated transformation of the stylesheet itself is necessary
          * because of the need to get the pure style sheet. If we simply say
          * Source stylesheet = new DOMSource(this._xsltElement);
          * whereby this._xsltElement is not the rootElement of the Document,
          * this causes problems;
          * so we convert the stylesheet to byte[] and use this as input stream
          */
         {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            Transformer transformer = tFactory.newTransformer();
            DOMSource source = new DOMSource(_xsltElement);
            StreamResult result = new StreamResult(os);

            transformer.transform(source, result);

            stylesheet =
               new StreamSource(new ByteArrayInputStream(os.toByteArray()));
         }

         Transformer transformer = tFactory.newTransformer(stylesheet);
         if (baos==null) {
         	    ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
               StreamResult outputTarget = new StreamResult(baos1);
               transformer.transform(xmlSource, outputTarget);
               return new XMLSignatureInput(baos1.toByteArray());

         }
         StreamResult outputTarget = new StreamResult(baos);

         transformer.transform(xmlSource, outputTarget);         
         XMLSignatureInput output=new XMLSignatureInput((byte[])null);
         output.setOutputStream(baos);
         return output;
      } catch (XMLSecurityException ex) {
         Object exArgs[] = { ex.getMessage() };

         throw new TransformationException("generic.EmptyMessage", exArgs, ex);
      } catch (TransformerConfigurationException ex) {
         Object exArgs[] = { ex.getMessage() };

         throw new TransformationException("generic.EmptyMessage", exArgs, ex);
      } catch (TransformerException ex) {
         Object exArgs[] = { ex.getMessage() };

         throw new TransformationException("generic.EmptyMessage", exArgs, ex);
      }
   }
}
