package com.tech.batch.demo.schedule.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.*;
import org.springframework.stereotype.Component;

@Component
//@StepScope
public class DemoItemReader implements ItemReader<String> {
    private static Logger logger = LoggerFactory.getLogger(DemoItemReader.class);
    public static int loop = 0;

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        while (DemoItemReader.loop<10){
            DemoItemReader.loop = DemoItemReader.loop+1;
            logger.debug("read items # {}",DemoItemReader.loop);
            return "demo string item " + loop;
        }
        logger.debug("read items done #########");
        DemoItemReader.loop = 0;
       return null;

    }
}
