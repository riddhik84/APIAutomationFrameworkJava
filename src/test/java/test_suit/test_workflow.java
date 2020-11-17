package test_suit;

import org.testng.annotations.Test;

import java.util.logging.Logger;

public class test_workflow {

    private final static Logger LOG = Logger.getLogger(test_workflow.class.getName());

    @Test
    public void test_students_workflow() {
        LOG.info("Step 1 - Get all students list");

        LOG.info("Step 2 - Create a new student info");

        LOG.info("Step 3 - Update the new student's info");

        LOG.info("Step 4 - Get the new student's record");

        LOG.info("Step 5 - Get all students records, count should be +1");

        LOG.info("Step 6 - Delete 1 student record");

        LOG.info("Step 7 - Get all students recrods, count should be -1");

    }
}
