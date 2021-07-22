package dgca.verifier.app.engine.data.source.local

import dgca.verifier.app.engine.data.source.local.rules.Converters
import junit.framework.Assert.assertEquals
import org.junit.jupiter.api.Test
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime

/*-
 * ---license-start
 * eu-digital-green-certificates / dgc-certlogic-android
 * ---
 * Copyright (C) 2021 T-Systems International GmbH and all other contributors
 * ---
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ---license-end
 *
 * Created by osarapulov on 16.06.21 8:52
 */
internal class ConvertersTest {
    private val utcZoneId: ZoneId = ZoneId.ofOffset("", ZoneOffset.UTC).normalized()

    @Test
    fun testConverter() {
        val converters = Converters()
        val zonedDateTimeZone = ZonedDateTime.now()
        val timestamp = converters.zonedDateTimeToTimestamp(zonedDateTimeZone)
        val actualZonedDateTime = converters.fromTimestamp(timestamp)

        assertEquals(
            zonedDateTimeZone.withZoneSameInstant(utcZoneId),
            actualZonedDateTime
        )
    }

    @Test
    fun testConverterEdgeCase() {
        val converters = Converters()
        val zonedDateTimeZone = ZonedDateTime.parse("2021-06-30T14:54:51.748565Z")
        val timestamp = converters.zonedDateTimeToTimestamp(zonedDateTimeZone)
        val actualZonedDateTime = converters.fromTimestamp(timestamp)

        assertEquals(
            zonedDateTimeZone.withZoneSameInstant(utcZoneId),
            actualZonedDateTime
        )
    }

    @Test
    fun testListOfStringConverter() {
        val expected = listOf("one", "two", "three")

        val converters = Converters()

        val serialized = converters.fromList(expected)

        val actual = converters.fromString(serialized)


        assertEquals(expected, actual)
    }
}