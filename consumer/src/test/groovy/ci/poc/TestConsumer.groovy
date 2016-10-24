package ci.poc

import org.testng.annotations.Test

import static org.assertj.core.api.Assertions.assertThat

/**
 * Date of creation: 29.09.2016..
 *
 * Copyright \(c\) CompuGROUP Software GmbH,
 * This software is the confidential and proprietary information of
 * CompuGROUP Software GmbH. You shall not disclose such confidential
 * information and shall use it only in accordance with the terms of
 * the license agreement you entered into with CompuGROUP Software GmbH.
 */
class TestConsumer {
    final private DefaultConsumer consumer = new DefaultConsumer();

    @Test
    void "test count"() {
        assertThat(consumer.usedCount()).isBetween(1,301)
    }
}
