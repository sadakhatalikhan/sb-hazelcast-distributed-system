package com.sb.hazelcast.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hazelcast.cluster.MembershipEvent;
import com.hazelcast.cluster.MembershipListener;

@Component
public class ApplicationMembershipListener implements MembershipListener {


    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void memberAdded(MembershipEvent membershipEvent) {
        logger.info("New member added to cluster {} ",membershipEvent);

    }

    @Override
    public void memberRemoved(MembershipEvent membershipEvent) {
        logger.info("A member removed from cluster {} ",membershipEvent);

    }
}