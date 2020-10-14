/*
 * Copyright 2016 Goldman Sachs.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.gs.dmn.signavio.feel.lib.type.time.xml;

import com.gs.dmn.signavio.feel.lib.type.time.SignavioBaseDateTimeLib;
import com.gs.dmn.signavio.feel.lib.type.time.SignavioTimeLib;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.Duration;

public class DefaultSignavioTimeLib extends SignavioBaseDateTimeLib implements SignavioTimeLib<XMLGregorianCalendar> {
    @Override
    public Long hourDiff(XMLGregorianCalendar dateTime1, XMLGregorianCalendar dateTime2) {
        Duration duration = durationBetween(dateTime1, dateTime2);
        Long diff = duration == null ? null : duration.getSeconds() / (60 * 60);
        return diff;
    }

    @Override
    public Long minutesDiff(XMLGregorianCalendar dateTime1, XMLGregorianCalendar dateTime2) {
        Duration duration = durationBetween(dateTime1, dateTime2);
        long diff = duration == null ? null : duration.getSeconds() / 60;
        return diff;
    }
}
