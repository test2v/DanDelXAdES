

package org.apache.xml.security.xades;





import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.apache.xml.security.xades.XAdESUtils;
import org.w3c.dom.Text;

/** 
* @author Psycho_core (nqkoi_ot_bg@yahoo.com)
* @author mwnnj (krassen.deltchev@rub.de)
**/

public class XAdESElementProxy {
	
		/** Field _doc */
	   protected Document _doc = null;
	   
	   /** Field _constructionElement */
	   protected Element _constructionElement = null;
	 /**
	    * Method addTextElement
	    *
	    * @param text
	    * @param localname
	    */
	   public void addStringElement(String text, String localname) {

	      Element e = XAdESUtils.createElementInLocalNameSpace(this._doc, localname);
	      Text t = this._doc.createTextNode(text);

	      e.appendChild(t);
	      this._constructionElement.appendChild(e);
	      XAdESUtils.addReturnToElement(this._constructionElement);
	   }
}
