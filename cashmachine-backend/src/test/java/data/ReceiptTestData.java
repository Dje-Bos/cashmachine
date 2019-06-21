package data;

import org.sut.cashmachine.dataload.test.data.UserTestData;
import org.sut.cashmachine.model.order.ReceiptModel;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import static data.TimeUtil.convert;

public class ReceiptTestData {
    public static final ReceiptModel RECEIPT_0 = new ReceiptModel(1L, convert("2019-05-17T18:57:52"), UserTestData.SAVED_USER, Set.of(), BigDecimal.valueOf(10.0), "OK");
    public static final ReceiptModel RECEIPT_1 = new ReceiptModel(2L, convert("2019-05-17T18:58:52"), UserTestData.SAVED_USER, Set.of(), BigDecimal.valueOf(11.0), "OK");
    public static final ReceiptModel RECEIPT_2 = new ReceiptModel(3L, convert("2019-05-17T18:59:52"), UserTestData.SAVED_USER, Set.of(), BigDecimal.valueOf(12.0), "OK");
    public static final ReceiptModel RECEIPT_3 = new ReceiptModel(4L, convert("2019-05-17T19:57:52"), UserTestData.SAVED_USER, Set.of(), BigDecimal.valueOf(13.0), "OK");


}
