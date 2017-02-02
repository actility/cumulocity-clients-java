package com.cumulocity.java.sms.client;

import com.cumulocity.model.sms.Address;
import com.cumulocity.model.sms.IncomingMessage;
import com.cumulocity.model.sms.IncomingMessages;
import com.cumulocity.model.sms.OutgoingMessageRequest;

public interface SmsMessagingApi {

    /**
     * Sends the sms message
     * @param senderAddress
     * @param outgoingMessageRequest
     */
    public void sendMessage(Address senderAddress, OutgoingMessageRequest request);
    /**
     * Gets the list of sms messages for the given address
     * @param receiveAddress
     * @return the list of incoming messages
     */
    public IncomingMessages getAllMessages(Address receiveAddress);
    /**
     * Get the last message for the given address
     * @param receiveAddress
     * @return the last message
     */
    public IncomingMessage getLastMessage(Address receiveAddress);
    /**
     * Gets the sms message with given the id, null if message with the id does not exist.
     * @param receiveAddress
     * @param messageId
     * @return the message with the given the id
     */
    public IncomingMessage getMessage(Address receiveAddress, String messageId);
    
}
