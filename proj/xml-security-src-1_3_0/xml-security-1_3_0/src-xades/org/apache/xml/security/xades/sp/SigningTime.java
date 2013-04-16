


package org.apache.xml.security.xades.sp;





import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.ObjectContainer;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.xml.security.utils.XMLUtils;
import java.util.GregorianCalendar;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.util.Date;


import org.apache.xml.security.xades.Constants;


/**<pre>&lt;xsd:element name="SigningTime" type="xsd:dateTime"/></pre>
 * 
 * @author Psycho_core (nqkoi_ot_bg@yahoo.com)
 * @author mwnnj (krassen.deltchev@rub.de)
 **/


public class SigningTime extends ObjectContainer implements  SignedSignaturePropertiesContent{
	
	/**
	    * Constructor SigningTime
	    *
	    * @param doc
	    */
	   public SigningTime(Document doc) {

	      super(doc);

	      XMLUtils.addReturnToElement(this._constructionElement);
	     	      
	   }
	   
	   
	/**
	    * Constructor SigningTime
	    *
	    * @param element
	    * @param BaseURI
	    * @throws XMLSecurityException
	    */
	   public SigningTime(Element element, String BaseURI)
	           throws XMLSecurityException {
	      super(element, BaseURI);
	   }
	   
	   /**
	    * Constructor SigningTime
	    *
	    * @param doc
	    * @param dateTime
	    */
	   public SigningTime(Document doc, Date dateTime) {

	      super(doc);	      
	      
	      this.addText(toXMLDate(dateTime).toString());
	   }
	   
	   /**
	    * Method getSigningTime
	    *
	    * @return the data time
	    */
	   public String getSigningTime() {
	      return this.getTextFromTextChild();
	   }

	   /** @inheritDoc */
	   public String getBaseLocalName() {
	      return Constants._TAG_SIGNINGTIME;
	   }
	   
	   protected XMLGregorianCalendar toXMLDate(Date date){
		   GregorianCalendar cal = new GregorianCalendar();
		   cal.setTime(date);
		   return new XMLGregorianCalendarImpl(cal);
	   }
	   
	   protected Date toDate(String xmlDateTime){
		   XMLGregorianCalendar cal = XMLGregorianCalendarImpl.parse(xmlDateTime);
		   return cal.toGregorianCalendar().getTime();
	   }
	   /** @inheritDoc */
	   public String getBaseNamespace() {
	      return "http://uri.etsi.org/01903/v1.1.1#";
	   }
	}
