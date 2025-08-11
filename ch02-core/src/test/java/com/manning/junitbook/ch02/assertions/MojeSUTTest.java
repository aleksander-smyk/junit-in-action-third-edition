package com.manning.junitbook.ch02.assertions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class MojeSUTTest {

    @Test
    void should_create_system_with_correct_name() {
        //given
        SUT sut = new SUT("Our system under test");
        //when
        //Brak akcji - to test konstruktora

        //then
        assertEquals("Our system under test", sut.getSystemName());
    }

    @Test
    void should_be_not_verified_initially() {
       //given
       SUT sut = new SUT("Our system under test");

       //when
       // Brak akcji - to test stanu początkowego

       //then
       assertFalse(sut.isVerified(),"System nie powinien być zwryfikowany na początku");
    }

    @Test
    void should_be_verified_after_verify_call() {
        //given
        SUT sut = new SUT("Our system under test");

        //when
        sut.verify();

        //then
        assertTrue(sut.isVerified(),"System powinien być zweryfikowany po wywołaniu metody verify()");
    }

    @Test
    void should_add_job_to_execution_list() {
        //given
        SUT sut = new SUT("Our system under test");
        Job job = new Job("Job 1");
        //when
        sut.addJob(job);

        //tehn
        Object[] jobs = sut.getJobsAsArray();
        assertEquals(1, jobs.length, "Lista powinna zawierać tylko jedno zadanie");
        assertEquals(job, jobs[0], "Dodane zadanie powinno być na liście");
    }

    @Test
    void should_throw_exception_when_no_jobs_to_run() {
        //given
        SUT sut = new SUT("Our system under test");

        //when
        NoJobException exception = assertThrows(NoJobException.class,
                sut::run,
                "Powinien rzucić wyjątek gdy brak zadań");

        //then
        assertEquals("No jobs on the execution list!", exception.getMessage());
    }

    @Test
    void should_properly_initialize_system_using_assertAl() {
        //given
        String systemName = "Test system";

        //when
        SUT sut = new SUT(systemName);

        //then
        assertAll("System initialize",
                () -> assertEquals(systemName, sut.getSystemName(), "Nazwa systemu"),
                () -> assertFalse(sut.isVerified(), "Status weryfikacji"),
                () -> assertEquals(0, sut.getJobsAsArray().length, "Lista zadań")
                );
    }

    @Test
    void should_set_current_job_after_successful_run () {
        //given
        SUT sut = new SUT("Test system");
        Job expectedJob = new Job("Important Task");
        sut.addJob(expectedJob);

        //when
        sut.run();

        //then
        assertEquals(expectedJob, sut.getCurrentJob(), "Obecnie wykonane zadanie");
        assertEquals(0, sut.getJobsAsArray().length,"Zadanie powinno byc usuniete z listy");
    }

    @Test
    void should_process_jobs_in_correct_order() {
        //given
        SUT sut = new SUT("Test system");
        Job job1 = new Job("Important Task 1");
        Job job2 = new Job("Important Task 2");
        sut.addJob(job1);
        sut.addJob(job2);

        //when
        sut.run();

        //then
        assertAll("After first run",
                () -> assertEquals(job1, sut.getCurrentJob(), "Obecnie wykonane zadanie"),
                () -> assertEquals(1, sut.getJobsAsArray().length,"Jedno zadanie powinno zostać na liście"));
    }

    @Test
    void should_handle_null_system_name() {
        //given
        SUT sut = new SUT(null);

        //then
        assertNull(sut.getSystemName(), "System powinien akceptować null jako nazwę");
    }

    @Test
    void should_handle_null_job() {
        //given
        SUT sut = new SUT("Test system");

        //when
        sut.addJob(null);

        //then
        Object[] jobs = sut.getJobsAsArray();

        assertEquals(1, jobs.length, "Null job powinien być dodany do listy");
        assertNull(jobs[0],"Pierwszy element powinnien być null");
    }

}
