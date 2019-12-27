package com.callfire.api.client.integration.numbers;

import static com.callfire.api.client.api.numbers.model.NumberConfig.ConfigType.TRACKING;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;

import com.callfire.api.client.CallfireClient;
import com.callfire.api.client.api.common.model.DayOfWeek;
import com.callfire.api.client.api.common.model.LocalTime;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.WeeklySchedule;
import com.callfire.api.client.api.numbers.NumberLeasesApi;
import com.callfire.api.client.api.numbers.model.CallTrackingConfig;
import com.callfire.api.client.api.numbers.model.GoogleAnalytics;
import com.callfire.api.client.api.numbers.model.NumberConfig;
import com.callfire.api.client.api.numbers.model.NumberLease;
import com.callfire.api.client.api.numbers.model.NumberLease.FeatureStatus;
import com.callfire.api.client.api.numbers.model.request.FindNumberLeaseConfigsRequest;
import com.callfire.api.client.api.numbers.model.request.FindNumberLeasesRequest;
import com.callfire.api.client.integration.AbstractIntegrationTest;

import lombok.extern.slf4j.Slf4j;

/**
 * integration tests for /numbers/leases api endpoint
 */
@Slf4j
public class NumberLeasesApiTest extends AbstractIntegrationTest {

    @Test
    public void testFindNumberLeases() {
        CallfireClient callfireClient = getCallfireClient();

        FindNumberLeasesRequest request = FindNumberLeasesRequest.create()
            .limit(2L)
            .build();
        Page<NumberLease> leases = callfireClient.numberLeasesApi().find(request);
        assertEquals(2, leases.getItems().size());
        assertFalse(leases.getItems().get(0).getLabels().isEmpty());

        log.info("{}", leases);
    }

    @Test
    public void testGetNumberLease() {
        CallfireClient callfireClient = getCallfireClient();

        String number = getDid3();
        NumberLease lease = callfireClient.numberLeasesApi().get(number);
        assertNotNull(lease.getRegion());
        assertEquals(number, lease.getNumber());
        assertFalse(lease.getLabels().isEmpty());
        assertThat(lease.getRegion().getCity(), containsString("LOS ANGELES"));

        log.info("{}", lease);
    }

    @Test
    public void testUpdateNumberLease() {
        CallfireClient callfireClient = getCallfireClient();

        String number = getDid3();
        NumberLeasesApi api = callfireClient.numberLeasesApi();
        NumberLease lease = api.get(number);
        assertNotNull(lease.getRegion());
        lease.setNumber(number);
        lease.setTextFeatureStatus(FeatureStatus.DISABLED);
        lease.setCallFeatureStatus(FeatureStatus.DISABLED);

        api.update(lease);
        lease = api.get(number, "number,callFeatureStatus,textFeatureStatus");
        assertNotNull(lease.getNumber());
        assertEquals(FeatureStatus.DISABLED, lease.getCallFeatureStatus());
        assertEquals(FeatureStatus.DISABLED, lease.getTextFeatureStatus());

        lease.setTextFeatureStatus(FeatureStatus.ENABLED);
        lease.setCallFeatureStatus(FeatureStatus.ENABLED);
        api.update(lease);

        log.info("{}", lease);
    }

    @Test
    public void testFindNumberLeaseConfigs() {
        CallfireClient callfireClient = getCallfireClient();

        FindNumberLeaseConfigsRequest request = FindNumberLeaseConfigsRequest.create()
            .limit(2L)
            .build();
        Page<NumberConfig> configs = callfireClient.numberLeasesApi().findConfigs(request);
        assertEquals(2, configs.getItems().size());

        log.info("{}", configs);
    }

    @Test
    public void testGetNumberLeaseConfig() {
        CallfireClient callfireClient = getCallfireClient();

        NumberConfig config = callfireClient.numberLeasesApi().getConfig(getDid3());
        assertEquals(TRACKING, config.getConfigType());
        assertNotNull(config.getCallTrackingConfig());

        log.info("{}", config);
    }

    @Test
    public void testUpdateNumberLeaseConfig() {
        CallfireClient callfireClient = getCallfireClient();

        String number = getDid3();
        NumberLeasesApi api = callfireClient.numberLeasesApi();
        NumberConfig config = api.getConfig(number, "number,configType,callTrackingConfig");
        assertNull(config.getIvrInboundConfig());
        assertEquals(TRACKING, config.getConfigType());

        config.setConfigType(NumberConfig.ConfigType.TRACKING);
        CallTrackingConfig callTrackingConfig = new CallTrackingConfig();
        callTrackingConfig.setScreen(true);
        callTrackingConfig.setRecorded(true);
        callTrackingConfig.setTransferNumbers(asList("12132212384"));
        callTrackingConfig.setVoicemail(true);
        callTrackingConfig.setIntroSoundId(9643523003L);
        callTrackingConfig.setVoicemailSoundId(9643523003L);
        callTrackingConfig.setFailedTransferSoundId(9643523003L);
        callTrackingConfig.setWhisperSoundId(9643523003L);

        WeeklySchedule weeklySchedule = new WeeklySchedule();
        weeklySchedule.setStartTimeOfDay(new LocalTime(1, 1, 1));
        weeklySchedule.setStopTimeOfDay(new LocalTime(2, 2, 2));
        weeklySchedule.setDaysOfWeek(new HashSet<>(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.FRIDAY)));
        weeklySchedule.setTimeZone("America/New_York");
        callTrackingConfig.setWeeklySchedule(weeklySchedule);

        GoogleAnalytics googleAnalytics = new GoogleAnalytics();
        googleAnalytics.setCategory("Sales");
        googleAnalytics.setGoogleAccountId("UA-12345-26");
        googleAnalytics.setDomain("testDomain");
        callTrackingConfig.setGoogleAnalytics(googleAnalytics);

        config.setCallTrackingConfig(callTrackingConfig);

        api.updateConfig(config);
        config = api.getConfig(number, "callTrackingConfig,configType");
        assertNotNull(config.getCallTrackingConfig());
        assertNull(config.getNumber());
        assertEquals(TRACKING, config.getConfigType());
        log.info("{}", config);
    }
}
