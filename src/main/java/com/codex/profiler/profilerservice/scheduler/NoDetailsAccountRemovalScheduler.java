package com.codex.profiler.profilerservice.scheduler;

import com.codex.profiler.profilerservice.entity.Candidate;
import com.codex.profiler.profilerservice.repository.CandidateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NoDetailsAccountRemovalScheduler {

    Logger logger = LoggerFactory.getLogger(NoDetailsAccountRemovalScheduler.class);

    @Autowired
    CandidateRepository candidateRepository;

    @Scheduled(cron = "0 0 */3 * * *") //For every 3hrs check
    public void removeNoDetailsAccounts() {
        List<Candidate> noDetailsCandidateList = candidateRepository.findNoDetailsCandidates();
        candidateRepository.deleteAll(noDetailsCandidateList);
        logger.trace("NoDetails account cleanup scheduler ran. Found " + noDetailsCandidateList.size() + " record");
    }
}
