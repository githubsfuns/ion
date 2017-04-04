/*
 * PAPResponseHandler.java
 *
 * Copyright � 1998-2009 Research In Motion Ltd.
 * 
 * Note: For the sake of simplicity, this sample application may not leverage
 * resource bundles and resource strings.  However, it is STRONGLY recommended
 * that application developers make use of the localization features available
 * within the BlackBerry development platform to ensure a seamless application
 * experience across a variety of languages and geographies.  For more information
 * on localizing your application, please refer to the BlackBerry Java Development
 * Environment Development Guide associated with this release.
 */

package com.semm.core.conexiones.bb;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX handler implementation that parses a PAP message for the push demo. Two
 * sample XML documents are listed below: <code>
 * <?xml version="1.0"?>
 * <!DOCTYPE pap PUBLIC "-//WAPFORUM//DTD PAP 1.0//EN" "http://www.openmobilealliance.org/tech/DTD/pap_1.0.dtd">
 * <pap>
 * 	<push-response 
 * 	    push-id="999999999" 
 * 		sender-address="https://www.pushdatadomain.com/PD_pushRequest"
 * 		sender-name="RIM Push-Data" 
 * 		reply-time="2008-09-31T13:00:00Z">
 * 			<response-result 
 * 				code="1001"
 * 				desc="Accepted for Processing" 
 * 			</response-result>
 * 	</push-response>
 * </pap>
 * </code>
 * 
 * For failed requests:<code>
 * <?xml version="1.0"?>
 * <!DOCTYPE pap PUBLIC "-//WAPFORUM//DTD PAP 2.0//EN" "http://www.openmobilealliance.org/tech/DTD/pap_2.0.dtd">
 * <pap>
 *   <badmessage-response 
 *       code="2000" 
 *       desc="Invalid or missing attribute address-value" 
 *       bad-message-fragment="Invalid or missing attribute address-value"/>
 * </pap> 
 * </code>
 */
public class PAPResponseHandler extends DefaultHandler {
    private String _pushId;
    private String _senderAddress;
    private String _senderName;
    private String _replyTime;
    private String _responseCode;
    private String _responseDesc;
    
	/**
	 * Default constructor.
	 */
	public PAPResponseHandler() {
	}

    /**
     * @see org.xml.sax.ContentHandler#startElement(String, String, String, Attributes)
     */
    public void startElement(String uri, String lName, String qName, 
            Attributes attr) throws SAXException {

        if ("push-response".equals(qName)) {
            _pushId = attr.getValue("push-id");
            _senderAddress = attr.getValue("sender-address");
            _senderName = attr.getValue("sender-name");
            _replyTime = attr.getValue("reply-time");
        } else if ("response-result".equals(qName) || 
        		"badmessage-response".equals(qName)) {
        	
            _responseCode = attr.getValue("code");
            _responseDesc = attr.getValue("desc");
        }
    }


    /**
     * Method to retrieve the push ID.
     *
     * @return The value of the push-id attribute 
     * from the &lt;push-response&gt; tag.
     */
    public String getPushId() {
        return _pushId;
    }


    /**
     * Method to retrieve the sender's address.
     *
     * @return The value of the sender-address attribute 
     * from the &lt;push-response&gt; tag.
     */
    public String getSenderAddress() {
        return _senderAddress;
    }

    
    /**
     * Method to retrieve the sender's name.
     *
     * @return The value of the sender-name attribute 
     * from the &lt;push-response&gt; tag.
     */
    public String getSenderName() {
        return _senderName;
    }

    
    /**
     * Method to retrieve the response's reply time.
     *
     * @return The value of the reply-time attribute 
     * from the &lt;push-response&gt; tag.
     */
    public String getReplyTime() {
        return _replyTime;
    }


    /**
     * Method to retrieve the numeric response code.
     *
     * @return The value of the code attribute 
     * from the &lt;response-result&gt; tag.
     */
    public String getResponseCode() {
        return _responseCode;
    }


    /**
     * Method to retrieve the response description.
     *
     * @return The value of the desc attribute 
     * from the &lt;response-result&gt; tag.
     */
    public String getResponseDescription() {
        return _responseDesc;
    }
}
