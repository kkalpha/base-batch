package com.tech.batch.demo.schedule.writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DemoItemWriter implements ItemWriter<String> {
    private static Logger logger = LoggerFactory.getLogger(DemoItemWriter.class);
    @Override
    public void write(List<? extends String> items) throws Exception {
        logger.debug("items count# {}",items.size());
    }
}
