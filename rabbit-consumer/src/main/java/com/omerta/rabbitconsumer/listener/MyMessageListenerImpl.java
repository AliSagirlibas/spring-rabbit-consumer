package com.omerta.rabbitconsumer.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MyMessageListenerImpl implements MyMessageListener {

	private static final Logger logger=LoggerFactory.getLogger(MyMessageListenerImpl.class);
	
	@Override
	public void onMessage(String message) {
		logger.info("\n\n\nMessage Received:"+message+"\n\n\n");
		try {
			Thread.currentThread().sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
