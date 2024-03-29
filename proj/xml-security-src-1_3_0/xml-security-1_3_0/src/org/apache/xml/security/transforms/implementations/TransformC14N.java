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



import java.io.OutputStream;

import org.apache.xml.security.c14n.CanonicalizationException;
import org.apache.xml.security.c14n.implementations.Canonicalizer20010315OmitComments;
import org.apache.xml.security.signature.XMLSignatureInput;
import org.apache.xml.security.transforms.TransformSpi;
import org.apache.xml.security.transforms.Transforms;


/**
 * Implements the <CODE>http://www.w3.org/TR/2001/REC-xml-c14n-20010315</CODE>
 * transform.
 *
 * @author Christian Geuer-Pollmann
 */
public class TransformC14N extends TransformSpi {

   /** Field implementedTransformURI */
   public static final String implementedTransformURI =
      Transforms.TRANSFORM_C14N_OMIT_COMMENTS;


   /**
    * @inheritDoc 
    */
   protected String engineGetURI() {
      return TransformC14N.implementedTransformURI;
   }

   /**
    *  @inheritDoc 
    */
   protected XMLSignatureInput enginePerformTransform(XMLSignatureInput input)
           throws CanonicalizationException {
   	    return enginePerformTransform(input,null);
   }
    protected XMLSignatureInput enginePerformTransform(XMLSignatureInput input,OutputStream os)
    throws CanonicalizationException {   
         Canonicalizer20010315OmitComments c14n = new Canonicalizer20010315OmitComments();
         if (os!=null) {
         	c14n.setWriter(os);
         }
         byte[] result = null;                
         input.setNeedsToBeExpanded(true);
         result=c14n.engineCanonicalize(input);         		         	         
         XMLSignatureInput output=new XMLSignatureInput(result);
         if (os!=null) {
            output.setOutputStream(os);
         }
         return output;     
   }
}
