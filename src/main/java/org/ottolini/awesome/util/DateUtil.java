package org.ottolini.awesome.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by virtual on 25-12-2017.
 */

@Component //(@Repository @Service) -- @Service não acrescenta nada a @Component
public class DateUtil {
    public String formatLocalDateTimeToDatabaseStyle(LocalDateTime localDateTime){
        return DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss").format(localDateTime);
    }
}
