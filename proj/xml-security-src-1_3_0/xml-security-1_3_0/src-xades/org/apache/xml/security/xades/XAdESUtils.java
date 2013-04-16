

package org.apache.xml.security.xades;




import org.w3c.dom.Document;
import org.w3c.dom.Element;

/** 
* @author Psycho_core (nqkoi_ot_bg@yahoo.com)
* @author mwnnj (krassen.deltchev@rub.de)
**/

import org.apache.xml.security.xades.Constants;



public class XAdESUtils {

   /**
    * Constructor XMLUtils
    *
    */
   private XAdESUtils() {

      
   }

   
   

   /**
    * Creates an Element in the Local specification namespace.
    *
    * @param doc the factory Document
    * @param elementName the local name of the Element
    * @return the Element
    */
   public static Element createElementInLocalNameSpace(Document doc,
           String elementName) {

      if (doc == null) {
         throw new RuntimeException("Document is null");
      }

      String ds = Constants.getLocalSpecNSprefix();

      if ((ds == null) || (ds.length() == 0)) {
         Element element = doc.createElementNS(Constants.LocalSpecNS,
                                               elementName);

         element.setAttributeNS(Constants.NamespaceSpecNS, "xmlns",
                 Constants.LocalSpecNS);

         return element;
      } 
         Element element = doc.createElementNS(Constants.LocalSpecNS,
                                               ds + ":" + elementName);

         element.setAttributeNS(Constants.NamespaceSpecNS, "xmlns:" + ds,
                                Constants.LocalSpecNS);

         return element;
      
   }
   
   /**
    * Method addReturnToElement
    *
    * @param e
    */
   public static void addReturnToElement(Element e) {

      Document doc = e.getOwnerDocument();

      e.appendChild(doc.createTextNode("\n"));
   }


   
}
