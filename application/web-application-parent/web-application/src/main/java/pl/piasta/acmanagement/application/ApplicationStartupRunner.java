package pl.piasta.acmanagement.application;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.piasta.acmanagement.infrastructure.quartz.SynDataScheduler;

@Component
public class ApplicationStartupRunner implements CommandLineRunner {
    protected final Log logger = LogFactory.getLog(getClass());
    private final SynDataScheduler synDataScheduler;

    public ApplicationStartupRunner(SynDataScheduler synDataScheduler) {
        this.synDataScheduler = synDataScheduler;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("项目启动，启动定时任务");
        synDataScheduler.schedule();
    }
}